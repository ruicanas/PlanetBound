/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package planetbound.iu.graph;

import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;
import static planetbound.iu.graph.IConstantes_GUI.BT_EXPLORATORIA;
import static planetbound.iu.graph.IConstantes_GUI.BT_MILITAR;
import static planetbound.iu.graph.IConstantes_GUI.PATH_SOM_BOTAO;

/**
 *
 * @author Rui
 */
public class BotoesImagens extends Button{
    
    private static AudioClip somBt;
    
    public BotoesImagens(String imagem) {
        super();
        
        somBt = new AudioClip(Resources.getResourceSom(PATH_SOM_BOTAO).toURI().toString());
        
        DropShadow ds = new DropShadow();
        ds.setOffsetY(3.0);
        ds.setColor(Color.ANTIQUEWHITE);
        
        setPrefSize(240, 160);
        
        preparaBackground(imagem);
        
        setOnMouseEntered((MouseEvent event) -> {
            setScaleX(1.25);
            setScaleY(1.25);
            
            setEffect(ds);
            getScene().setCursor(Cursor.HAND);
        });
        
        setOnMouseExited((MouseEvent event) -> {
            setScaleX(1);
            setScaleY(1);
            setEffect(null);
            getScene().setCursor(Cursor.DEFAULT);
        });
        
        setOnMousePressed((MouseEvent event) -> {
            somBt.play();
        });
    }

    private void preparaBackground(String imagem) {
        setBackground(new Background(new BackgroundImage(Imagens.getImagem(imagem),
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                new BackgroundSize(220, 140, false, false, false, false))));
    }

}
