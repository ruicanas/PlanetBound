/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package planetbound.iu.graph;


import java.util.HashMap;
import java.util.Map;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import static planetbound.iu.graph.IConstantes_GUI.PATH_SOM_JOGO;
import static planetbound.iu.graph.IConstantes_GUI.PATH_SOM_MENUINICIAL;
import static planetbound.iu.graph.IConstantes_GUI.SOM_JOGO;
import static planetbound.iu.graph.IConstantes_GUI.SOM_MENUINICIAL;

/**
 *
 * @author Rui
 */
public class Sons {
    private static final Map<String, MediaPlayer> sons = new HashMap<>();
    
    static{
        sons.put(SOM_MENUINICIAL, new MediaPlayer((new Media(Resources.getResourceSom(PATH_SOM_MENUINICIAL).toURI().toString()))));
        sons.put(SOM_JOGO, new MediaPlayer((new Media(Resources.getResourceSom(PATH_SOM_JOGO).toURI().toString()))));
    }
    
    public static MediaPlayer getSom(String nome){
        return sons.get(nome);
    }
}
