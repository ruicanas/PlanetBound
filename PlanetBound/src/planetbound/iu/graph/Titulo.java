/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package planetbound.iu.graph;

import javafx.scene.effect.DropShadow;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import static planetbound.iu.graph.IConstantes_GUI.SPACE_FONT_TITULO;

/**
 *
 * @author Rui
 */
public class Titulo extends Text{

    public Titulo(String titulo) {
        super(titulo);
        
        DropShadow ds = new DropShadow();
        ds.setOffsetY(3.0);
        ds.setColor(Color.ANTIQUEWHITE);
        
        setEffect(ds);
        setFill(Color.WHITE);
        setFont(Fonts.getFont(SPACE_FONT_TITULO));
    }
    
    public Titulo(String titulo, Color color) {
        super(titulo);
        
        DropShadow ds = new DropShadow();
        ds.setOffsetY(3.0);
        ds.setColor(Color.ANTIQUEWHITE);
        
        setEffect(ds);
        setFill(color);
        setFont(Fonts.getFont(SPACE_FONT_TITULO));
    }
    
}
