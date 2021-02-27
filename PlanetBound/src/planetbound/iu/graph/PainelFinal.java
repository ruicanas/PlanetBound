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
import javafx.scene.control.Label;
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
import static planetbound.iu.graph.IConstantes_GUI.SPACING_BT_V;
import planetbound.logica.estados.MaqJogoObservavel;

/**
 *
 * @author Rui
 */
public class PainelFinal extends BorderPane implements IConstantes_GUI, PropertyChangeListener{
    private MaqJogoObservavel maq;
    
    private Text tTitulo;
    private Text tLog;
    private Text stInfo;
    private TextArea taLog;
    
    private Label lCombustivel;
    private Label lArma;
    private Label lEscudo;
    
    private Button btCombustivel;
    private Button btArma;
    private Button btEscudo;
    private Button btNovaJornada;
    
    private VBox boxTitulo;
    private VBox boxCombustivel;
    private VBox boxArma;
    private VBox boxEscudo;
    private HBox boxAll;
    
    private VBox boxLog;
    
    private VBox boxBt;
    
    private VBox box;
    
    public PainelFinal(MaqJogoObservavel maq) {
        this.maq = maq;
        this.maq.addPropertyChangeListener("ATUALIZA_FIM", this);
        
        //Criar objetos
        createObjects();
        //Colocar no cenário
        setOnView();
        //Definir as ações / listeners
        setListener();
    }
    
    private void createObjects() {
        criaTitulo();
        criaLog();
        criaSubTituloInfo();
        criaCombustivel();
        criaArmas();
        criaEscudo();
        criaButton();
        
        boxAll = new HBox(boxCombustivel, boxArma, boxEscudo);
        boxAll.setAlignment(Pos.CENTER);
        boxAll.setSpacing(100);
        
        box = new VBox(stInfo, boxAll, boxLog);
        box.setAlignment(Pos.TOP_CENTER);
        box.setSpacing(30);
        
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

    private void setListener(){
        btCombustivel.setOnAction((ActionEvent t) -> {
            maq.converteRecursosExplorados(1);
        });
        
        btArma.setOnAction((ActionEvent t) -> {
            maq.converteRecursosExplorados(2);
        });
        
        btEscudo.setOnAction((ActionEvent t) -> {
            maq.converteRecursosExplorados(3);
        });
        
        btNovaJornada.setOnAction((ActionEvent t) -> {
            maq.terminaTurno();
        });
        
        
    }

    private void criaTitulo() {
        tTitulo = new Titulo("FINAL DE TURNO\n", Color.RED);
        tTitulo.setTextAlignment(TextAlignment.CENTER);
        
        boxTitulo = new VBox(tTitulo);
        boxTitulo.setAlignment(Pos.CENTER);
        boxTitulo.setPadding(new Insets(30));
    }
    
    private void criaSubTituloInfo(){
        stInfo = new Subtitulo("Melhore a sua nave!");
    }
    
    private void criaCombustivel(){
        lCombustivel = new LabelExploracao("COMBUSTIVEL");
        lCombustivel.setTextAlignment(TextAlignment.CENTER);
        
        btCombustivel = new BotoesImagens(COMBUSTIVEL);
        
        boxCombustivel = new VBox(btCombustivel, lCombustivel);
        boxCombustivel.setAlignment(Pos.CENTER);
        boxCombustivel.setSpacing(SPACING_BT_V);
    }
    
    private void criaArmas(){
        lArma = new LabelExploracao("SISTEMA DE ARMAS");
        lArma.setTextAlignment(TextAlignment.CENTER);
        
        btArma = new BotoesImagens(ARMA);
        
        boxArma = new VBox(btArma, lArma);
        boxArma.setAlignment(Pos.CENTER);
        boxArma.setSpacing(SPACING_BT_V);
    }
    
    private void criaEscudo(){
        lEscudo = new LabelExploracao("SISTEMA DE ESCUDO");
        lEscudo.setTextAlignment(TextAlignment.CENTER);
        
        btEscudo = new BotoesImagens(ESCUDO);
        
        boxEscudo = new VBox(btEscudo, lEscudo);
        boxEscudo.setAlignment(Pos.CENTER);
        boxEscudo.setSpacing(SPACING_BT_V);
    }
    
    private void criaLog(){
        tLog = new Subtitulo("Acontecimentos do jogo", Color.RED);
        taLog = new MessageLog();
        
        boxLog = new VBox(tLog, taLog);
        boxLog.setAlignment(Pos.CENTER);
    }

    private void criaButton() {
        btNovaJornada = new BotoesJogo("Nova\nJornada");
        
        boxBt = new VBox(btNovaJornada);
        boxBt.setAlignment(Pos.CENTER);
        boxBt.setPadding(new Insets(30));
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        taLog.setText(maq.mostraLog());
    }
    
}
