/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package planetbound.iu.graph;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
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
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import static planetbound.iu.graph.IConstantes_GUI.BCKGND_MENUJOGO;
import planetbound.logica.estados.MaqJogoObservavel;

/**
 *
 * @author Rui
 */
public class PainelLoja extends BorderPane implements IConstantes_GUI, PropertyChangeListener{
    private MaqJogoObservavel maq;
    
    private Text tTitulo;
    private Text tLog;
    private Text stCarga;
    private Text txtCarga;
    private TextArea infoLog;
    
    private Button btMelhoraCarga;
    private Button btConvRecursos;
    private Button btCompraMembro;
    private Button btEvoluiSA;
    private Button btRegeneraDrone;
    private Button btCompraDrone;
    private Button btSaiLoja;
    
    private VBox boxTitulo;
    private VBox boxInfo;
    private VBox boxLog;
    private VBox boxBtEsquerda;
    private VBox boxBtDireita;
    private VBox boxBtSai;
    private VBox box;
    
    public PainelLoja(MaqJogoObservavel maq) {
        this.maq = maq;
        this.maq.addPropertyChangeListener("ATUALIZA_INFOLOJA", this);

        //Criar objetos
        createObjects();
        //Colocar no cenário
        setOnView();
        //Definir as ações / listeners
        setListener();
    }

    private void createObjects() {
        criaTitulo();
        criaInfoCarga();
        criaLog();
        criaBotoes();
        
        
        box = new VBox(boxInfo, boxLog);
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
        setLeft(boxBtEsquerda);
        setCenter(box);
        setRight(boxBtDireita);
        setBottom(boxBtSai);
    }

    private void setListener() {
        btMelhoraCarga.setOnAction((ActionEvent t) -> {
            maq.fazCompras(MELHORA_CARGA);
        });
        
        btConvRecursos.setOnAction((ActionEvent t) -> {
            maq.fazCompras(CONVERTE_RECURSOS);
        });
        
        btCompraMembro.setOnAction((ActionEvent t) -> {
            maq.fazCompras(COMPRA_MEMBRO);
        });
        
        btEvoluiSA.setOnAction((ActionEvent t) -> {
            maq.fazCompras(EVOLUI_SA);
        });
        
        btRegeneraDrone.setOnAction((ActionEvent t) -> {
            maq.fazCompras(REGENERA_DRONE);
        });
        
        btCompraDrone.setOnAction((ActionEvent t) -> {
            maq.fazCompras(COMPRA_DRONE);
        });
        
        btSaiLoja.setOnAction((ActionEvent t) -> {
            maq.voltaAoMenuAnterior();
        });
    }
    
    private void criaTitulo(){
        tTitulo = new Titulo("SPACE STATION", Color.RED);
        boxTitulo = new VBox(tTitulo);
        boxTitulo.setAlignment(Pos.CENTER);
        boxTitulo.setPadding(new Insets(15));
    }
    
    private void criaInfoCarga(){
        stCarga = new Subtitulo("A sua carga");
        txtCarga = new TextoNormal("");
        
        boxInfo = new VBox(stCarga, txtCarga);
        boxInfo.setTranslateY(-50);
        boxInfo.setAlignment(Pos.CENTER);
        boxInfo.setSpacing(SPACING_BT_V);
    }
    
    private void criaLog(){
        tLog = new Subtitulo("Acontecimentos do jogo", Color.RED);
        infoLog = new MessageLog();
        
        boxLog = new VBox(tLog, infoLog);
        boxLog.setAlignment(Pos.CENTER);
    }
    
    private void criaBotoes(){
        btSaiLoja = new BotoesJogo("Sair da loja");
        boxBtSai = new VBox(btSaiLoja);
        boxBtSai.setPadding(new Insets(30));
        boxBtSai.setAlignment(Pos.CENTER);
        
        btMelhoraCarga = new BotoesJogo("Melhora nivel\nde carga");
        btConvRecursos = new BotoesJogo("Converte\n recursos");
        btCompraMembro = new BotoesJogo("Comprar novo\ntripulante");
        boxBtEsquerda = new VBox(btMelhoraCarga, btConvRecursos, btCompraMembro);
        boxBtEsquerda.setPadding(new Insets(30));
        boxBtEsquerda.setSpacing(SPACING_BT_LOJA);
        boxBtEsquerda.setAlignment(Pos.CENTER_RIGHT);
        
        btEvoluiSA = new BotoesJogo("Evoluir sistema\nde armas");
        btRegeneraDrone = new BotoesJogo("Regenera drone");
        btCompraDrone = new BotoesJogo("Compra drone");
        boxBtDireita = new VBox(btEvoluiSA, btRegeneraDrone, btCompraDrone);
        boxBtDireita.setPadding(new Insets(30));
        boxBtDireita.setSpacing(SPACING_BT_LOJA);
        boxBtDireita.setAlignment(Pos.CENTER_LEFT);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        txtCarga.setText(maq.mostraCarga());
        infoLog.setText(maq.mostraLog());
        
        if(!maq.isNaveMilitar()){
            btEvoluiSA.setVisible(false);
        }
    }
}
