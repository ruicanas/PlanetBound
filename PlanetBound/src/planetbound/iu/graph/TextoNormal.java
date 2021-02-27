/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package planetbound.iu.graph;

import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import static planetbound.iu.graph.IConstantes_GUI.SPACE_FONT_TEXTO;

/**
 *
 * @author Rui
 */
public class TextoNormal extends Text{
    public TextoNormal(String titulo) {
        super(titulo);
        
        setFill(Color.AQUAMARINE);
        setFont(Fonts.getFont(SPACE_FONT_TEXTO));
    }
    
    public TextoNormal(String titulo, Color color) {
        super(titulo);
        
        setFill(color);
        setFont(Fonts.getFont(SPACE_FONT_TEXTO));
    }
}
