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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import static planetbound.iu.graph.IConstantes_GUI.BCKGND_MENUINICIAL;
import planetbound.logica.estados.MaqJogoObservavel;

/**
 *
 * @author Rui
 */
public class PainelAguardaEscolhaNave extends BorderPane implements IConstantes_GUI{
    MaqJogoObservavel maq;
    
    private Text nomeFase;

    private Button btNaveExploratoria;
    private Button btNaveMilitar;
    private Button btSai;
    
    private Text textoNaveExploratoria;
    private Text textoNaveMilitar;
    
    private HBox boxBtNaves;
    private HBox boxSubTitulos;
    private VBox boxTitulo;
    private VBox boxAvanca;
    private VBox box;
    

    public PainelAguardaEscolhaNave(MaqJogoObservavel maq) {
        this.maq = maq;
        
        //Criar objetos
        createObjects();
        //Colocar no cenário
        setOnView();
        //Definir as ações / listeners
        setListener();
        
        setPrefSize(SIZEPANEL_X, SIZEPANEL_Y);
    }

    private void createObjects() {
        //Titulo principal
        nomeFase = new Titulo("ESCOLHA UMA NAVE");
        boxTitulo = new VBox(nomeFase);
        boxTitulo.setAlignment(Pos.CENTER);
        boxTitulo.setPadding(new Insets(30));
        
        //SubTitulos que aparecem
        textoNaveExploratoria = new Subtitulo(infoExploratoria());
        textoNaveExploratoria.setTranslateX(140);       //Para movimentar o texto da nave exploratória
        textoNaveMilitar = new Subtitulo(infoMilitar());
        textoNaveMilitar.setTranslateX(-74);            //Para movimentar o texto da nave militar
        
        
        //Box que contém os respetivos subtitulos que identificam as naves
        boxSubTitulos = new HBox(textoNaveMilitar, textoNaveExploratoria);
        boxSubTitulos.setAlignment(Pos.CENTER);
        
        
        btNaveMilitar = new BotoesNaves(BT_MILITAR);
        btNaveExploratoria = new BotoesNaves(BT_EXPLORATORIA);
        
        boxBtNaves = new HBox(btNaveMilitar, btNaveExploratoria);
        boxBtNaves.setAlignment(Pos.CENTER);
        boxBtNaves.setSpacing(250);
        
        btSai = new BotoesJogo("Sair");
        boxAvanca = new VBox(btSai);
        boxAvanca.setAlignment(Pos.CENTER);
        boxAvanca.setPadding(new Insets(30));
        
        box = new VBox(boxBtNaves, boxSubTitulos);
        box.setAlignment(Pos.CENTER);
        box.setSpacing(10);
        
        
        setBackground(new Background(new BackgroundImage(Imagens.getImagem(BCKGND_MENUINICIAL),
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, true))));
    }

    private void setOnView() {
        setTop(boxTitulo);
        setCenter(box);
        setBottom(boxAvanca);
    }

    private void setListener() {
        btNaveMilitar.setOnMouseClicked((MouseEvent event) -> {
            maq.selecionaNave(ESCOLHA_MILITAR);
        });
        
        btNaveExploratoria.setOnMouseClicked((MouseEvent event) -> {
            maq.selecionaNave(ESCOLHA_EXPLORATORIA);
        });
        
        btSai.setOnAction((ActionEvent t) -> {
            maq.utilizadorTermina();
        });
    }
    
    private String infoExploratoria(){
        StringBuilder sb = new StringBuilder();
        sb.append("Nave Exploratória");
        return sb.toString();
    }
    
    private String infoMilitar(){
        StringBuilder sb = new StringBuilder();
        sb.append("Nave Militar");
        return sb.toString();
    }
}
