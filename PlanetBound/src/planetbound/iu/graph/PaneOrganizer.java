/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package planetbound.iu.graph;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.util.Optional;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaPlayer.Status;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import planetbound.logica.estados.MaqJogoObservavel;

/**
 *
 * @author Rui
 */
public class PaneOrganizer extends BorderPane implements IConstantes_GUI, PropertyChangeListener{
    private MaqJogoObservavel maq;
    
    private Alert alertaNovo;
    private Alert alertaSair;
    private File nomeFile;
    
    private VBox root;
    private StackPane conteudo;
    
    private MediaPlayer introMusica;
    private MediaPlayer gameMusica;
    boolean introPlaying;
    boolean gamePlaying;
    
    private PainelInicio menuInicial;
    private PainelAguardaEscolhaNave menuEscolha;
    private PainelJogo menuMovimento;
    private PainelJogo menuViagem;
    private PainelLoja menuLoja;
    private PainelConversao menuConversao;
    private PainelPreExploracao menuPreExploracao;
    private PainelExploracao menuExploracao;
    private PainelFinal menuFinal;
    private PainelDesistiu desistencia;
    private PainelDerrota derrota;
    private PainelVitoria vitoria;
    
    private MenuItem fmNew;
    private MenuItem fmSave;
    private MenuItem fmSaveAs;
    private MenuItem fmExit;
    private MenuBar menubar;
        
    public PaneOrganizer(MaqJogoObservavel maq){
        this.maq = maq;
        this.maq.addPropertyChangeListener("MUDAPAINEL", this);
        nomeFile = null;
        
        //Criar objetos
        createObjects();
        //Colocar no cenário
        setOnView();
        //Definir as ações / listeners
        setListener();
        
        verificaEstados();
    }
    
    public Pane getRoot(){
        return root;
    }

    private void createObjects() {
        root = new VBox();
        conteudo = new StackPane();
        
        introMusica = Sons.getSom(SOM_MENUINICIAL);
        introPlaying = introMusica.getStatus().equals(Status.PLAYING);
                
        gameMusica = Sons.getSom(SOM_JOGO);
        gameMusica.setVolume(0.1);
        gamePlaying = gameMusica.getStatus().equals(Status.PLAYING);
        
        alertaNovo = new Alert(AlertType.CONFIRMATION);
        Stage stageAN = (Stage) alertaNovo.getDialogPane().getScene().getWindow();
        stageAN.getIcons().add(Imagens.getImagem(ICON));
        
        alertaSair = new Alert(AlertType.CONFIRMATION);
        Stage stageAS = (Stage) alertaSair.getDialogPane().getScene().getWindow();
        stageAS.getIcons().add(Imagens.getImagem(ICON));
        
        alertaNovo.setTitle("Novo jogo");
        alertaNovo.setHeaderText("Deseja mesmo começar um novo jogo?");
        alertaNovo.setContentText("Todo o seu progresso atual será perdido.");
        alertaSair.setTitle("Sair");
        alertaSair.setHeaderText("Deseja mesmo sair?");
        alertaSair.setContentText("Todo o seu progresso atual será perdido\nse não tiver sido guardado.");
        
        
        menuInicial = new PainelInicio(maq);
        menuEscolha = new PainelAguardaEscolhaNave(maq);
        menuMovimento = new PainelJogo(maq);
        menuViagem = new PainelJogo(maq);
        menuLoja = new PainelLoja(maq);
        menuConversao = new PainelConversao(maq);
        menuPreExploracao = new PainelPreExploracao(maq);
        menuExploracao = new PainelExploracao(maq);
        menuFinal = new PainelFinal(maq);
        desistencia = new PainelDesistiu(maq);
        derrota = new PainelDerrota(maq);
        vitoria = new PainelVitoria(maq);
        
        fmNew = new MenuItem("Novo");
        fmSave = new MenuItem("Guardar");
        fmSaveAs = new MenuItem("Guardar como...");
        fmExit = new MenuItem("Sair");
        Menu mJogo = new Menu("Jogo");
        mJogo.getItems().addAll(fmNew, fmSave, fmSaveAs, new SeparatorMenuItem(), fmExit);
        menubar = new MenuBar(mJogo);
    }

    private void setOnView() {
        root.getChildren().addAll(menubar, conteudo);
        
        conteudo.getChildren().add(menuInicial);
        conteudo.getChildren().add(menuEscolha);
        conteudo.getChildren().add(menuMovimento);
        conteudo.getChildren().add(menuViagem);
        conteudo.getChildren().add(menuLoja);
        conteudo.getChildren().add(menuConversao);
        conteudo.getChildren().add(menuPreExploracao);
        conteudo.getChildren().add(menuExploracao);
        conteudo.getChildren().add(menuFinal);
        conteudo.getChildren().add(desistencia);
        conteudo.getChildren().add(derrota);
        conteudo.getChildren().add(vitoria);
    }

    private void setListener() {
        fmNew.setOnAction((ActionEvent t) -> {
            Optional<ButtonType> result = alertaNovo.showAndWait();
            if (result.get() == ButtonType.OK){
                maq.utilizadorTermina();
                maq.voltaJogar();
            }
        });
        
        fmSave.setOnAction((ActionEvent t) -> {
            if (nomeFile == null){
                FileChooser fileChooser = new FileChooser();
                FileChooser.ExtensionFilter extF = new FileChooser.ExtensionFilter("OBJ (*.obj)", "*.obj");
                fileChooser.getExtensionFilters().add(extF);
                nomeFile = fileChooser.showSaveDialog(new Stage());
            }
            if (nomeFile != null){
                maq.save(nomeFile);
            }
        });
        
        fmSaveAs.setOnAction((ActionEvent t) -> {
            FileChooser fileChooser = new FileChooser();
            FileChooser.ExtensionFilter extF = new FileChooser.ExtensionFilter("OBJ (*.obj)", "*.obj");
            fileChooser.getExtensionFilters().add(extF);
            File file = fileChooser.showSaveDialog(new Stage());
            if (nomeFile != null){
                maq.save(file);
            }
        });
        
        fmExit.setOnAction((ActionEvent t) -> {
            Optional<ButtonType> result = alertaSair.showAndWait();
            if (result.get() == ButtonType.OK){
                Platform.exit();
            }
        });
    }
    
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        verificaEstados();
    }
    
    private void setVisible(){
        menubar.setVisible(true);
        menuInicial.setVisible(false);
        menuEscolha.setVisible(false);
        menuMovimento.setVisible(false);
        menuViagem.setVisible(false);
        menuLoja.setVisible(false);
        menuConversao.setVisible(false);
        menuPreExploracao.setVisible(false);
        menuExploracao.setVisible(false);
        menuFinal.setVisible(false);
        desistencia.setVisible(false);
        derrota.setVisible(false);
        vitoria.setVisible(false);
    }
    
    private void verificaEstados() {
        setVisible();
        
        
        switch (maq.informaEstados()) {
                case inicio: 
                    gameMusica.stop();
                    introMusica.play();
                    
                    menuInicial.setVisible(true);
                    break;
                case escolhaNave: 
                    menuEscolha.setVisible(true);
                    break;
                case esperaMov:
                    introMusica.stop();
                    gameMusica.play();
                    
                    menuMovimento.setVisible(true);
                    break;
                case ocorreEvento:
                    maq.determinaEvento();
                    break;
                case decideOrbita:
                    if(gamePlaying == false){
                        introMusica.stop();
                        gameMusica.play();
                    }
                    
                    menuViagem.setVisible(true);
                    break;
                case aguardaLoja:
                    if(gamePlaying == false){
                        introMusica.stop();
                        gameMusica.play();
                    }
                    
                    menuLoja.setVisible(true);
                    break;
                case escolheRecursos:
                    if(gamePlaying == false){
                        introMusica.stop();
                        gameMusica.play();
                    }
                    
                    menuConversao.setVisible(true);
                    break;
                case aguardaExploracao:
                    if(gamePlaying == false){
                        introMusica.stop();
                        gameMusica.play();
                    }
                    
                    menuPreExploracao.setVisible(true);
                    break;
                case aguardaRecolha:
                    if(gamePlaying == false){
                        introMusica.stop();
                        gameMusica.play();
                    }
                    
                    menuExploracao.setVisible(true);
                    menuExploracao.requestFocus();
                    break;
                case aguardaConvers:
                    if(gamePlaying == false){
                        introMusica.stop();
                        gameMusica.play();
                    }
                    
                    menuFinal.setVisible(true);
                    break;
                case gameOver:
                    menubar.setVisible(false);
                    if(maq.isGameOver()){
                        if(maq.isJogoVencido()){
                            vitoria.setVisible(true);
                        }else{
                            derrota.setVisible(true);
                        }
                    }
                    else{
                        desistencia.setVisible(true);
                    }
                    break;
            }
    }
}
