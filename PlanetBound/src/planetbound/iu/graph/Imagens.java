/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package planetbound.iu.graph;

import java.util.HashMap;
import java.util.Map;
import javafx.scene.image.Image;
import static planetbound.iu.graph.IConstantes_GUI.BCKGND_BTINICIAIS;
import static planetbound.iu.graph.IConstantes_GUI.PATH_BACKGROUND_BTINICIAIS;


/**
 *
 * @author Rui
 */
public class Imagens implements IConstantes_GUI{
    private static final Map<String, Image> imagens = new HashMap<>();
    
    static{
        imagens.put(ICON, new Image(Resources.getResourceFile(PATH_ICON)));
        imagens.put(BCKGND_BTINICIAIS, new Image(Resources.getResourceFile(PATH_BACKGROUND_BTINICIAIS)));
        imagens.put(BCKGND_MENUINICIAL, new Image(Resources.getResourceFile(PATH_BACKGROUND_MENUINICIAL)));
        imagens.put(BCKGND_MENUJOGO, new Image(Resources.getResourceFile(PATH_BACKGROUND_MENUJOGO)));
        imagens.put(BT_MILITAR, new Image(Resources.getResourceFile(PATH_BT_MILITAR)));
        imagens.put(BT_EXPLORATORIA, new Image(Resources.getResourceFile(PATH_BT_EXPLORATORIA)));
        imagens.put(BT_MILITAR_ICON, new Image(Resources.getResourceFile(PATH_BT_MILITAR), 80, 80, false, false));
        imagens.put(BT_EXPLORATORIA_ICON, new Image(Resources.getResourceFile(PATH_BT_EXPLORATORIA), 80, 80, false, false));
        imagens.put(VERMELHO, new Image(Resources.getResourceFile(PATH_VERMELHO)));
        imagens.put(AZUL, new Image(Resources.getResourceFile(PATH_AZUL)));
        imagens.put(VERDE, new Image(Resources.getResourceFile(PATH_VERDE)));
        imagens.put(NEGRO, new Image(Resources.getResourceFile(PATH_NEGRO)));
        imagens.put(CARTA, new Image(Resources.getResourceFile(PATH_CARTA)));
        imagens.put(BANDEIRA_BRANCA, new Image(Resources.getResourceFile(PATH_BANDEIRA_BRANCA)));
        imagens.put(DRONE, new Image(Resources.getResourceFile(PATH_DRONE), 80, 80, false, false));
        imagens.put(CRUZ, new Image(Resources.getResourceFile(PATH_CRUZ), 30, 30, false, false));
        imagens.put(BAU, new Image(Resources.getResourceFile(PATH_BAU), 50, 50, false, false));
        imagens.put(BAU_PEQUENO, new Image(Resources.getResourceFile(PATH_BAU), 30, 30, false, false));
        imagens.put(ALIEN_AZUL, new Image(Resources.getResourceFile(PATH_ALIEN_AZUL), 35, 35, false, false));
        imagens.put(ALIEN_NEGRO, new Image(Resources.getResourceFile(PATH_ALIEN_NEGRO), 35, 35, false, false));
        imagens.put(ALIEN_VERDE, new Image(Resources.getResourceFile(PATH_ALIEN_VERDE), 35, 35, false, false));
        imagens.put(ALIEN_VERMELHO, new Image(Resources.getResourceFile(PATH_ALIEN_VERMELHO), 35, 35, false, false));
        imagens.put(VIDA, new Image(Resources.getResourceFile(PATH_VIDA), 35, 35, false, false));
        imagens.put(KEY_UP, new Image(Resources.getResourceFile(PATH_KEY_UP), 80, 80, false, false));
        imagens.put(KEY_DOWN, new Image(Resources.getResourceFile(PATH_KEY_DOWN), 80, 80, false, false));
        imagens.put(KEY_RIGHT, new Image(Resources.getResourceFile(PATH_KEY_RIGHT), 80, 80, false, false));
        imagens.put(KEY_LEFT, new Image(Resources.getResourceFile(PATH_KEY_LEFT), 80, 80, false, false));
        imagens.put(KEY_SPACE, new Image(Resources.getResourceFile(PATH_KEY_SPACE), 238, 65, false, false));
        imagens.put(ARMA, new Image(Resources.getResourceFile(PATH_ARMA), 200, 200, false, false));
        imagens.put(ESCUDO, new Image(Resources.getResourceFile(PATH_ESCUDO), 200, 200, false, false));
        imagens.put(COMBUSTIVEL, new Image(Resources.getResourceFile(PATH_COMBUSTIVEL), 200, 200, false, false));
        imagens.put(EXPLOSAO, new Image(Resources.getResourceFile(PATH_EXPLOSAO)));
        imagens.put(TROFEU, new Image(Resources.getResourceFile(PATH_TROFEU), 124, 124, false, false));
        
    }
    
    public static Image getImagem(String nome){
        return imagens.get(nome);
    }
}
