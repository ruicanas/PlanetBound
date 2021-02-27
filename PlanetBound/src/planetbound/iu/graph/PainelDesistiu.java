/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package planetbound.iu.graph;


import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import static planetbound.iu.graph.IConstantes_GUI.BCKGND_MENUJOGO;
import planetbound.logica.estados.MaqJogoObservavel;

/**
 *
 * @author Rui
 */
public class PainelDesistiu extends BorderPane implements IConstantes_GUI{
    MaqJogoObservavel maq;
    
    private ImageView imgBandeira;
    
    private Text tTitulo;
    private Text txtInfo;
    
    private Button btVoltaJogar;
    private Button btSair;
    
    private VBox boxTitulo;
    private HBox boxBt;
    private VBox box;
    
    public PainelDesistiu(MaqJogoObservavel maq) {
        this.maq = maq;
        
        //Criar objetos
        createObjects();
        //Colocar no cenário
        setOnView();
        //Definir as ações / listeners
        setListener();
    }

    private void createObjects() {
        criaTitulo();
        criaImg();
        criaTxt();
        criaBt();
        
        box = new VBox(imgBandeira, txtInfo, boxBt);
        box.setAlignment(Pos.CENTER);
        box.setSpacing(SPACING_BT_V);
        
        setBackground(new Background(new BackgroundImage(Imagens.getImagem(BCKGND_MENUJOGO),
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, true))));
    }

    private void setOnView() {
        setTop(boxTitulo);
        setCenter(box);
    }

    private void setListener() {
        btVoltaJogar.setOnAction((ActionEvent t) -> {
            maq.voltaJogar();
        });
        
        btSair.setOnAction((ActionEvent t) -> {
            Platform.exit();
        });
    }
    
    private void criaTitulo() {
        tTitulo = new Titulo("GAME OVER\n", Color.WHITE);
        tTitulo.setTextAlignment(TextAlignment.CENTER);
        
        boxTitulo = new VBox(tTitulo);
        boxTitulo.setAlignment(Pos.CENTER);
        boxTitulo.setPadding(new Insets(30));
    }
    
    private void criaTxt(){
        txtInfo = new Subtitulo("Por alguma razão decidiste terminar o jogo.\nUma nova tripulação estará à tua espera caso queiras voltar!\n");
        txtInfo.setTextAlignment(TextAlignment.CENTER);
    }
    
    private void criaImg(){
        imgBandeira = new ImageView(Imagens.getImagem(BANDEIRA_BRANCA));
    }
    
    private void criaBt(){
        btVoltaJogar = new BotoesJogo("Voltar a\njogar");
        btSair = new BotoesJogo("Sair");
        
        boxBt = new HBox(btVoltaJogar, btSair);
        boxBt.setAlignment(Pos.CENTER);
        boxBt.setSpacing(100);
    }
    
}
