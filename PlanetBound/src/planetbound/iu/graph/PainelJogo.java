/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package planetbound.iu.graph;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
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
public class PainelJogo extends BorderPane implements IConstantes_GUI, PropertyChangeListener{
    MaqJogoObservavel maq;
    static int TIPO = 0;
    
    private Text nomeFase;
    private Text turno;
    private Text stituloNave;    //Sub Titulo "Nave ..."
    private Text stituloCarga;   //Sub titulo "A sua carga"
    private Text infoNave;
    private Text infoCarga;
    private Text infoSetor;
    private Text tituloLog;
    private TextArea infoLog;
    
    private Button btViajar;
    private Button btFazExploracao;
    private Button btLoja;
    private Button btTerminaTurno;
    private Button btSaiJogo;
    
    private VBox boxTitulo;
    private HBox boxSubTitulos;
    private HBox boxInfo;
    private VBox boxLog;
    private HBox boxBt;
    private VBox box;
    
    public PainelJogo(MaqJogoObservavel maq) {
        this.maq = maq;
        TIPO = TIPO+1;
        this.maq.addPropertyChangeListener("ATUALIZA_INFONAVE" + TIPO, this);
        
        //Criar objetos
        createObjects();
        //Colocar no cenário
        setOnView();
        //Definir as ações / listeners
        setListener();
    }

    private void createObjects() {
        criaTitulo();
        criaSubTitulos();
        criaLog();
        criaBotoes();
        criaInfo();
        
        box = new VBox(boxSubTitulos, boxInfo, boxLog);
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
        setBottom(boxBt);
    }
    
    private void listenerMovimento(){
        btViajar.setOnAction((ActionEvent t) -> {
            maq.fazMovimento(1);
        });
        
        btSaiJogo.setOnAction((ActionEvent t) -> {
            maq.utilizadorTermina();
        });
    }
    
    private void listenerOrbita(){
        btLoja.setOnAction((ActionEvent t) -> {
            maq.acessaLoja();
        });
        
        btFazExploracao.setOnAction((ActionEvent t) -> {
            maq.fazMovimento(2);
        });
        
        btTerminaTurno.setOnAction((ActionEvent t) -> {
            maq.terminaTurno();
        });
        
        btSaiJogo.setOnAction((ActionEvent t) -> {
            maq.utilizadorTermina();
        });
    }

    private void setListener() {
        switch(TIPO){
            case 1: listenerMovimento();
                    break;
            case 2: listenerOrbita();
                    break;
        }
    }
    
    private void criaTitulo(){
        nomeFase = new Titulo("JORNADA ESPACIAL");
        infoSetor = new Subtitulo("");
        infoSetor.setTextAlignment(TextAlignment.CENTER);
        turno = new Subtitulo("Turno: ");
        
        if(TIPO == 1){
            infoSetor.setVisible(false);
        }
        
        boxTitulo = new VBox(nomeFase, infoSetor, turno);
        boxTitulo.setAlignment(Pos.CENTER);
        boxTitulo.setPadding(new Insets(15));
    }
    
    private void criaSubTitulos() {
        stituloNave = new Subtitulo("A sua nave");
        stituloCarga = new Subtitulo("A sua carga");
        stituloCarga.setTranslateX(-43);
        
        boxSubTitulos = new HBox(stituloNave, stituloCarga);
        boxSubTitulos.setSpacing(SPACING_SUBTITULOS);
        boxSubTitulos.setAlignment(Pos.CENTER);
    }
    
    private void criaLog(){
        tituloLog = new Subtitulo("Acontecimentos do jogo", Color.RED);
        infoLog = new MessageLog();
        
        boxLog = new VBox(tituloLog, infoLog);
        boxLog.setAlignment(Pos.CENTER);
    }
    
    private void botoesMovimento(){
        btViajar = new BotoesJogo("Fazer uma viagem");
        btSaiJogo = new BotoesJogo("Abandonar jogo");
        
        boxBt = new HBox(btViajar, btSaiJogo);
        boxBt.setAlignment(Pos.CENTER);
        boxBt.setSpacing(SPACING_BT_H);
        boxBt.setPadding(new Insets(30));
    }
    
    private void botoesOrbita(){
        btFazExploracao = new BotoesJogo("Explorar\nPlaneta");
        btLoja = new BotoesJogo("LOJA ESPACIAL\n(SPACE STATION)");
        btTerminaTurno = new BotoesJogo("Termina turno");
        btSaiJogo = new BotoesJogo("Abandonar jogo");
        if(!maq.setorIsVermelho()){
            btLoja.setDisable(true);
        }
        
        boxBt = new HBox(btFazExploracao, btLoja, btTerminaTurno, btSaiJogo);
        boxBt.setAlignment(Pos.CENTER);
        boxBt.setSpacing(SPACING_BT_H);
        boxBt.setPadding(new Insets(30));
    }
    
    private void criaBotoes(){
        switch(TIPO){
            case 1: botoesMovimento();
                    break;
            case 2: botoesOrbita();
                    break;
        }
    }
    
    private void criaInfo() {
        infoNave = new TextoNormal("");
        infoCarga = new TextoNormal("");
        infoCarga.setTranslateX(30);
        boxInfo = new HBox(infoNave, infoCarga);
        boxInfo.setSpacing(SPACING_TEXTO);
        boxInfo.setAlignment(Pos.BASELINE_CENTER);
        
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt){
        turno.setText("Turno " + maq.mostraTurno());
        if(maq.setorCriado() == true){
            infoSetor.setText(maq.mostraInfoSetor());
        }
        infoLog.setText(maq.mostraLog());
        infoNave.setText(maq.mostraNave());
        infoCarga.setText(maq.mostraCarga());
        
        if(btLoja != null){
            if(maq.setorIsVermelho()){
                btLoja.setDisable(false);
            }else{
                btLoja.setDisable(true);
            }
        }
    }
}
