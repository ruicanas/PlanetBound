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
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import planetbound.logica.estados.MaqJogoObservavel;


public class PainelConversao extends BorderPane implements IConstantes_GUI, PropertyChangeListener{
    private MaqJogoObservavel maq;
    private String corUm;
    private String corDois;
    
    private Text tTitulo;
    
    private Button btAzul;
    private Button btNegro;
    private Button btVermelho;
    private Button btVerde;
    private Button btCancela;
    
    private VBox boxTitulo;
    private VBox boxBt;
    private VBox boxBtCancela;
    private VBox box;
    
    
    public PainelConversao(MaqJogoObservavel maq) {
        this.maq = maq;
        this.maq.addPropertyChangeListener("ATUALIZA_INFORECURSOS", this);
        corUm = null;
        corDois = null;
        
        //Criar objetos
        createObjects();
        //Colocar no cenário
        setOnView();
        //Definir as ações / listeners
        setListener();
    }
    
    private void createObjects() {
        criaTitulo();
        criaBotoes();
        
        box = new VBox();
        
        setBackground(new Background(new BackgroundImage(Imagens.getImagem(BCKGND_MENUJOGO),
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, true))));
    }

    private void setOnView() {
        setTop(boxTitulo);
        setCenter(boxBt);
        setBottom(boxBtCancela);
    }

    private void setListener() {
        btAzul.setOnAction((ActionEvent t) -> {
           if(corUm == null){
               corUm = AZUL;
           }else{
               corDois = AZUL;
               maq.escolheRecursos(corUm, corDois);
           }
        });
        
        btNegro.setOnAction((ActionEvent t) -> {
           if(corUm == null){
               corUm = NEGRO;
           }else{
               corDois = NEGRO;
               maq.escolheRecursos(corUm, corDois);
           }
        });
        
        btVermelho.setOnAction((ActionEvent t) -> {
           if(corUm == null){
               corUm = VERMELHO;
           }else{
               corDois = VERMELHO;
               maq.escolheRecursos(corUm, corDois);
           }
        });
        
        btVerde.setOnAction((ActionEvent t) -> {
           if(corUm == null){
               corUm = VERDE;
           }else{
               corDois = VERDE;
               maq.escolheRecursos(corUm, corDois);
           }
        });
        
        btCancela.setOnAction((ActionEvent t) -> {
            corUm = null;
            corDois = null;
            maq.voltaAoMenuAnterior();
        });
    }
    
    private void criaTitulo() {
        tTitulo = new Titulo("CONVERSOR DE RECURSOS", Color.RED);
        boxTitulo = new VBox(tTitulo);
        boxTitulo.setAlignment(Pos.CENTER);
        boxTitulo.setPadding(new Insets(15));
    }
    
    private void criaBotoes(){
        btCancela = new BotoesJogo("Cancelar");
        boxBtCancela = new VBox(btCancela);
        boxBtCancela.setPadding(new Insets(30));
        boxBtCancela.setAlignment(Pos.CENTER);
        
        btAzul = new BotoesCor(AZUL);
        btNegro = new BotoesCor(NEGRO);
        btVermelho = new BotoesCor(VERMELHO);
        btVerde = new BotoesCor(VERDE);
        
        boxBt = new VBox(btAzul, btNegro, btVermelho, btVerde);
        boxBt.setSpacing(30);
        boxBt.setAlignment(Pos.CENTER);
    }
    
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        corUm = null;
        corDois = null;
    }
}
