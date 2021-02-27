/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package planetbound.iu.graph;

import java.util.HashMap;
import java.util.Map;
import javafx.scene.text.Font;


/**
 *
 * @author Rui
 */
public class Fonts implements IConstantes_GUI {
    private static final Map<String, Font> fonts = new HashMap<>();
    
    static{
        fonts.put(SPACE_FONT_BT, (Font.loadFont(Resources.getResourceFile(PATH_FONT_ESPACIAL), SPACE_BT)));
        fonts.put(SPACE_FONT_TITULO, (Font.loadFont(Resources.getResourceFile(PATH_FONT_ESPACIAL), SPACE_TITULO)));
        fonts.put(SPACE_FONT_SUBTITULO, (Font.loadFont(Resources.getResourceFile(PATH_FONT_ESPACIAL), SPACE_SUBTITULO)));
        fonts.put(SPACE_FONT_TEXTO, (Font.loadFont(Resources.getResourceFile(PATH_FONT_ESPACIAL), SPACE_TEXTO)));
    }
    
    public static Font getFont(String nome){
        return fonts.get(nome);
    }
}
