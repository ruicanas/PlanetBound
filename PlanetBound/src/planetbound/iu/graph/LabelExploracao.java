/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package planetbound.iu.graph;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.scene.text.TextAlignment;
import static planetbound.iu.graph.IConstantes_GUI.SPACE_FONT_TEXTO;

/**
 *
 * @author Rui
 */
public class LabelExploracao extends Label{

    public LabelExploracao(String string) {
        super(string);
        
        
        
        setTextFill(Color.RED);
        setFont(Fonts.getFont(SPACE_FONT_TEXTO));
        setTextAlignment(TextAlignment.CENTER);
        setBackground(new Background(new BackgroundFill(Color.WHITE, new CornerRadii(5), Insets.EMPTY)));
    }
    
    public LabelExploracao(String string, Color cor) {
        super(string);
        
        
        setTextFill(cor);
        setFont(Fonts.getFont(SPACE_FONT_TEXTO));
        setTextAlignment(TextAlignment.CENTER);
        setBackground(new Background(new BackgroundFill(Color.WHITE, new CornerRadii(5), Insets.EMPTY)));
    }
    
}
