/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package planetbound.iu.graph;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import static planetbound.iu.graph.IConstantes_GUI.BCKGND_MENUJOGO;
import planetbound.logica.estados.MaqJogoObservavel;

/**
 *
 * @author Rui
 */
public class PainelExploracao extends BorderPane implements IConstantes_GUI, PropertyChangeListener{
    private MaqJogoObservavel maq;
    
    private AudioClip tiroAudio;
    private AudioClip movAudio;
    
    private ImageView imgDrone;
    private ImageView imgAlien;
    private ImageView imgCruz;
    private ImageView imgTesouro;
    private List<Image> imgVida;
    
    private ImagensTeclado itKEY_UP;
    private ImagensTeclado itKEY_DOWN;
    private ImagensTeclado itKEY_LEFT;
    private ImagensTeclado itKEY_RIGHT;
    private ImagensTeclado itKEY_SPACE;
    
    private Text tTitulo;
    private Text stMovimento;
    private Text stAtacar;
    private Text stInfo;
    private Text txtInfoTab;
    private Text txtInfoJogo;
    private TextArea taLog;
    
    private Label lVidaDrone;
    
    private Label lRecurso;
    private ImageView imgTesouroInfo;
            
    private Label lRecolha;
    private ImageView imgCruzInfo;
    
    private Label lAlien;
    private ImageView imgAlienInfo;
    
    private VBox boxTitulo;
    private VBox boxJogo;
    private VBox box;
    
    private HBox vida;
    private HBox boxInfoVida;
    private VBox boxVida;
    
    private VBox boxInfo;
    private HBox boxInfoRecurso;
    private HBox boxInfoRecolha;
    private HBox boxInfoAlien;
    
    private VBox boxKeysSpace;
    private VBox boxAllArrows;
    private VBox boxKeys;
    private HBox boxHKeys;
    private VBox boxSpace;
    
    
    public PainelExploracao(MaqJogoObservavel maq) {
        this.maq = maq;
        this.maq.addPropertyChangeListener("ATUALIZA_EXP", this);
        
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
        criaInfoJogo();
        criaKeys();
        criaLabelVida();
        criaLabelRecurso();
        criaLabelCruz();
        criaLabelAlien();
        criaTabuleiro();
        criaTextoAux();
        
        tiroAudio = new AudioClip(Resources.getResourceSom(PATH_SOM_TIRO).toURI().toString());
        movAudio = new AudioClip(Resources.getResourceSom(PATH_SOM_BOTAO).toURI().toString());
        
        boxVida = new VBox(boxInfoVida);
        boxVida.setPadding(new Insets(30));
        boxVida.setAlignment(Pos.CENTER);
        
        boxInfo = new VBox(stInfo, boxInfoRecurso, boxInfoRecolha, boxInfoAlien, taLog);
        boxInfo.setPadding(new Insets(30));
        boxInfo.setAlignment(Pos.CENTER);
        
        setBackground(new Background(new BackgroundImage(Imagens.getImagem(BCKGND_MENUJOGO),
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, true))));
    }

    private void setOnView() {
        setTop(boxTitulo);
        setCenter(boxJogo);
        setRight(boxKeysSpace);
        setLeft(boxInfo);
        setBottom(boxVida);
    }

    private void setListener() {
       setOnKeyPressed(e -> {
            if(!maq.isDroneALutar()){
                switch (e.getCode()) {
                   case UP:
                       movAudio.play();
                       maq.moveDrone(1);
                       itKEY_UP.setScaleX(0.75);
                       itKEY_UP.setScaleY(0.75);
                       break;
                   case RIGHT:
                       movAudio.play();
                       maq.moveDrone(2);
                       itKEY_RIGHT.setScaleX(0.75);
                       itKEY_RIGHT.setScaleY(0.75);
                       break;
                   case LEFT:
                       movAudio.play();
                       maq.moveDrone(3);
                       itKEY_LEFT.setScaleX(0.75);
                       itKEY_LEFT.setScaleY(0.75);
                       break;
                   case DOWN:
                       movAudio.play();
                       maq.moveDrone(4);
                       itKEY_DOWN.setScaleX(0.75);
                       itKEY_DOWN.setScaleY(0.75);
                       break;
                   default:
                       break;
               }
            }
           else{
              if (e.getCode() == KeyCode.SPACE) {
                    tiroAudio.play();
                    maq.moveDrone(1);
                    itKEY_SPACE.setScaleX(0.75);
                    itKEY_SPACE.setScaleY(0.75);
                }
           }
       });
       
       setOnKeyReleased(e -> {
            itKEY_UP.setScaleX(1);
            itKEY_UP.setScaleY(1);
            itKEY_RIGHT.setScaleX(1);
            itKEY_RIGHT.setScaleY(1);
            itKEY_LEFT.setScaleX(1);
            itKEY_LEFT.setScaleY(1);
            itKEY_DOWN.setScaleX(1);
            itKEY_DOWN.setScaleY(1);
            itKEY_SPACE.setScaleX(1);
            itKEY_SPACE.setScaleY(1);
       });
    }
    
    private void criaTitulo() {
        tTitulo = new Titulo("EXPLORAÇÃO\nESPACIAL", Color.RED);
        tTitulo.setTextAlignment(TextAlignment.CENTER);
        
        boxTitulo = new VBox(tTitulo);
        boxTitulo.setAlignment(Pos.CENTER);
        boxTitulo.setPadding(new Insets(30));
    }
    
    private void criaTabuleiro(){
        imgTesouro = new ImageView(Imagens.getImagem(BAU));
        imgCruz = new ImageView(Imagens.getImagem(CRUZ));
        imgDrone = new ImageView(Imagens.getImagem(DRONE));
        imgAlien = new ImageView();
        boxJogo = new VBox(imgTesouro, imgCruz, imgDrone, imgAlien);
        boxJogo.setAlignment(Pos.CENTER);
        boxJogo.setSpacing(-50);
        //imgAlien.setTranslateY(10);
        
                
                
        boxJogo.setBackground(new Background(new BackgroundImage(Imagens.getImagem(CARTA),
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, false, false))));
    }
    
    private void criaTextoAux(){
        txtInfoTab = new TextoNormal("");
        txtInfoTab.setTextAlignment(TextAlignment.CENTER);
    }
    
    private void criaInfoJogo() {
        stInfo = new Subtitulo("Informação");
        stInfo.setTextAlignment(TextAlignment.CENTER);
    }
    
    private void criaLabelVida(){
        lVidaDrone = new LabelExploracao("Vida do drone: ");
        imgVida = new ArrayList<>();
        
        vida = new HBox();
        boxInfoVida = new HBox(lVidaDrone, vida);
        boxInfoVida.setAlignment(Pos.CENTER);
        boxInfoVida.setSpacing(10);
    }
    
    private void criaLabelRecurso(){
        lRecurso = new LabelExploracao("", Color.GRAY);
        imgTesouroInfo = new ImageView(Imagens.getImagem(BAU_PEQUENO));
        
        boxInfoRecurso = new HBox(imgTesouroInfo, lRecurso);
        boxInfoRecurso.setAlignment(Pos.CENTER);
    }
    
    private void criaLabelCruz(){
        lRecolha = new LabelExploracao("Ponto de Recolha", Color.GRAY);
        imgCruzInfo = new ImageView(Imagens.getImagem(CRUZ));
        
        boxInfoRecolha = new HBox(imgCruzInfo, lRecolha);
        boxInfoRecolha.setAlignment(Pos.CENTER);
    }
    
    private void criaLabelAlien(){
        lAlien = new LabelExploracao("", Color.GRAY);
        imgAlienInfo = new ImageView();
        
        boxInfoAlien = new HBox(imgAlienInfo, lAlien);
        boxInfoAlien.setAlignment(Pos.CENTER);
    }
    
    private void criaLog(){
        taLog = new MessageLog(200, 250);
        taLog.setTranslateX(15);
    }
    
    private void criaKeys(){
        stMovimento = new Subtitulo("MOVER O DRONE");
        stAtacar = new Subtitulo("ATACAR O ALIEN");
        
        itKEY_UP = new ImagensTeclado(KEY_UP);
        itKEY_DOWN = new ImagensTeclado(KEY_DOWN);
        itKEY_RIGHT = new ImagensTeclado(KEY_RIGHT);
        itKEY_LEFT = new ImagensTeclado(KEY_LEFT);
        itKEY_SPACE = new ImagensTeclado(KEY_SPACE);
        
        //Contém as teclas na horizontal
        boxHKeys = new HBox(itKEY_LEFT, itKEY_DOWN, itKEY_RIGHT);
        boxHKeys.setAlignment(Pos.CENTER);
        
        //Junta a KEY_UP com as teclas horizontais
        boxKeys = new VBox(itKEY_UP, boxHKeys);
        boxKeys.setAlignment(Pos.CENTER);
        //boxKeys.setPadding(new Insets(50));
        
        //Junta um título às teclas
        boxAllArrows = new VBox(stMovimento, boxKeys);
        boxAllArrows.setSpacing(SPACING_BT_V);
        boxAllArrows.setAlignment(Pos.CENTER);
        
        //Junta um título ao espaço
        boxSpace = new VBox(stAtacar, itKEY_SPACE);
        boxSpace.setSpacing(SPACING_BT_V);
        boxSpace.setAlignment(Pos.CENTER);
        
        //Junta tudo
        boxKeysSpace = new VBox(boxAllArrows, boxSpace);
        boxKeysSpace.setSpacing(SPACING_BT_V);
        boxKeysSpace.setAlignment(Pos.CENTER);
        boxKeysSpace.setPadding(new Insets(50));
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt){
        if(maq.getDroneX() != -1 && maq.getDroneY() != -1){
            imagensTabuleiro();
            imagensInfo();
        }
        infoTabuleiro();
        
        taLog.setText(maq.mostraLog());
    }
    
    /**
     * Método que trata de todas as imagens do tabuleiro
     */
    private void imagensTabuleiro(){
        imgAlien.setImage(Imagens.getImagem("ALIEN_" + maq.getCorAlien()));
        imgDrone = conversorCoordenadas(maq.getDroneX(), maq.getDroneY(), imgDrone);
        imgAlien = conversorCoordenadas(maq.getAlienX(), maq.getAlienY(), imgAlien);
        imgCruz = conversorCoordenadas(maq.getXResgate(), maq.getYResgate(), imgCruz);
        imgTesouro = conversorCoordenadas(maq.getXRecurso(), maq.getYRecurso(), imgTesouro);
        
        if(maq.recursoApanhado()){
            imgTesouro.setVisible(true);
        }else{
            imgTesouro.setVisible(false);
        }
        
    }
    
    /**
     * Método que trata de todas as imagens que servem para informar
     * de alguma forma, o significado de alguma coisa ao utilizador
     */
    private void imagensInfo(){
        vida.getChildren().clear();                 //Limpa os nós anteriores
        imgVida.clear();                            //Limpa as imagens anteriores
        for (int i = 0; i < maq.getVidaDrone(); i++) {
            imgVida.add(Imagens.getImagem(VIDA));
        }
        for (Image img : imgVida) {
            vida.getChildren().add(new ImageView(img));
        }
        
        imgAlienInfo.setImage(Imagens.getImagem("ALIEN_" + maq.getCorAlien()));
    }
    
    private void infoTabuleiro(){
        if(!maq.getCorRecurso().equals("ROXO")){
            lRecurso.setText("Recurso " + maq.getCorRecurso());
        }
        else{
            lRecurso.setText("ARTEFACTO");
        }
        lAlien.setText("Alien " + maq.getCorAlien());
    }
    
    private ImageView conversorCoordenadas(int x, int y, ImageView iv){
        switch(x){
            case 0: iv.setTranslateX(X_INICIAL);
                    break;
            case 1: iv.setTranslateX(X_1);
                    break;
            case 2: iv.setTranslateX(X_2);
                    break;
            case 3: iv.setTranslateX(X_3);
                    break;
            case 4: iv.setTranslateX(X_4);
                    break;
            case 5: iv.setTranslateX(X_5);
                    break;
        }
        
        switch(y){
            case 0: iv.setTranslateY(Y_INICIAL);
                    break;
            case 1: iv.setTranslateY(Y_1);
                    break;
            case 2: iv.setTranslateY(Y_2);
                    break;
            case 3: iv.setTranslateY(Y_3);
                    break;
            case 4: iv.setTranslateY(Y_4);
                    break;
            case 5: iv.setTranslateY(Y_5);
                    break;
        }
        return iv;
    }
}
