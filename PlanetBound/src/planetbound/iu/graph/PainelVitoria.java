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
import static planetbound.iu.graph.IConstantes_GUI.SPACING_BT_V;
import planetbound.logica.estados.MaqJogoObservavel;

/**
 *
 * @author Rui
 */
public class PainelVitoria extends BorderPane implements IConstantes_GUI{
    MaqJogoObservavel maq;
    
    private ImageView imgTrofeu;
    
    private Text tTitulo;
    private Text txtInfo;
    
    private Button btVoltaJogar;
    private Button btSair;
    
    private VBox boxTitulo;
    private HBox boxBt;
    private VBox boxEfeitos;
    private VBox box;
    
    public PainelVitoria(MaqJogoObservavel maq) {
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
        
        box = new VBox(imgTrofeu, txtInfo, boxBt);
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
        tTitulo = new Titulo("GAME OVER\n", Color.GREEN);
        tTitulo.setTextAlignment(TextAlignment.CENTER);
        
        boxTitulo = new VBox(tTitulo);
        boxTitulo.setAlignment(Pos.CENTER);
        boxTitulo.setPadding(new Insets(30));
    }
    
    private void criaTxt(){
        txtInfo = new Subtitulo("Conseguiste! Com a tua liderança, a tripulação\n conseguiu alcançar um novo planeta!\n A humanidade não será extinta graças ao teu trabalho!\nMas será que consegues vencer o jogo mais uma vez?\n Tenta outra vez!\n", Color.AZURE);
        txtInfo.setTextAlignment(TextAlignment.CENTER);
        txtInfo.setTextAlignment(TextAlignment.CENTER);
    }
    
    private void criaImg(){
        imgTrofeu = new ImageView(Imagens.getImagem(TROFEU));
    }
    
    private void criaBt(){
        btVoltaJogar = new BotoesJogo("Voltar a\njogar");
        btSair = new BotoesJogo("Sair");
        
        boxBt = new HBox(btVoltaJogar, btSair);
        boxBt.setAlignment(Pos.CENTER);
        boxBt.setSpacing(100);
    }
}   
