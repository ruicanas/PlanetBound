/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package planetbound.iu.graph;

import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.paint.Color;
import javafx.scene.text.TextAlignment;

/**
 *
 * @author Rui
 */
public class BotoesJogo extends Button implements IConstantes_GUI{

    private static AudioClip somBt;
    
    public BotoesJogo(String nomeBotao){
        super(nomeBotao);
        
        //Tamanho por predefinição destes botões
        setPrefSize(195, 54);
        
        somBt = new AudioClip(Resources.getResourceSom(PATH_SOM_BOTAO).toURI().toString());
        
        //Background dos botões
        setBackground(new Background(new BackgroundImage(Imagens.getImagem(BCKGND_BTINICIAIS),
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, true))));
        
        //Bordas dos botões
        setBorder(new Border(new BorderStroke(Color.LIGHTSTEELBLUE,
                    BorderStrokeStyle.SOLID, null, BorderStroke.THIN)));
        
        //Fonte adicionada
        setFont(Fonts.getFont(Fonts.SPACE_FONT_BT));
        
        //Cor do texto dentro dos botões
        setTextFill(Color.WHITE);
        
        this.setTextAlignment(TextAlignment.CENTER);
        
        //Botão aumenta quando passa com o rato por cima
        setOnMouseEntered((MouseEvent event) -> {
                setScaleX(1.25);
                setScaleY(1.25);
                setTextFill(Color.RED);
                getScene().setCursor(Cursor.HAND);
        });
        
        //Botão diminui quando passa com o rato por cima
        setOnMouseExited((MouseEvent event) -> {
            setScaleX(1);
            setScaleY(1);
            setTextFill(Color.WHITE);
            getScene().setCursor(Cursor.DEFAULT);
        });
        
        setOnMousePressed((MouseEvent event) -> {
            somBt.play();
        });
        
                
    }
}
