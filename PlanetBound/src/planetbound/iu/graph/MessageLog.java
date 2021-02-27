/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package planetbound.iu.graph;

import javafx.geometry.Insets;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;

/**
 *
 * @author Rui
 */
public class MessageLog extends TextArea{

    public MessageLog() {
        super();
        
        setMaxSize(400, 120);
        setBorder(new Border(new BorderStroke(Color.BLACK,
                    BorderStrokeStyle.SOLID, null, BorderStroke.THICK)));
        setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));
        setWrapText(true);
        setEditable(false);
    }
    
    public MessageLog(int h, int w) {
        super();
        
        setMaxSize(h, w);
        setBorder(new Border(new BorderStroke(Color.BLACK,
                    BorderStrokeStyle.SOLID, null, BorderStroke.THICK)));
        setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));
        setWrapText(true);
        setEditable(false);
    }
    
    
}
