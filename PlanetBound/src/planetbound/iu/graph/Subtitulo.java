/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package planetbound.iu.graph;

import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import static planetbound.iu.graph.IConstantes_GUI.SPACE_FONT_SUBTITULO;

/**
 *
 * @author Rui
 */
public class Subtitulo extends Text{
    
    public Subtitulo(String titulo) {
        super(titulo);
        setFill(Color.WHITE);
        setFont(Fonts.getFont(SPACE_FONT_SUBTITULO));
    }
    
    public Subtitulo(String titulo, Color color){
        super(titulo);
        setTextAlignment(TextAlignment.CENTER);
        setFill(color);
        setFont(Fonts.getFont(SPACE_FONT_SUBTITULO));
    }
}
