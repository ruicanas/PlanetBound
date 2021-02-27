/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package planetbound.iu.graph;

/**
 *
 * @author Rui
 */
public interface IConstantes_GUI {
    //    
    //TAMANHO
    //
    int SIZEPANEL_X = 3100;
    int SIZEPANEL_Y = 2600;
    
    //
    //FONTES
    //
    int SPACE_BT = 13;
    int SPACE_TITULO = 50;
    int SPACE_SUBTITULO = 25;
    int SPACE_TEXTO = 20;
    String SPACE_FONT_BT = "Fonte Espacial(BT)";
    String SPACE_FONT_TITULO = "Fonte Espacial(TITULO)";
    String SPACE_FONT_SUBTITULO = "Fonte Espacial(SUBTITULO)";
    String SPACE_FONT_TEXTO = "Fonte Espacial(TEXTO)";
    String PATH_FONT_ESPACIAL = "fonts/space age.ttf";
    
    //
    //SONS
    //
    String SOM_MENUINICIAL = "SOM_MENUINICIAL";
    String SOM_BOTAO = "SOM_BOTAO";
    String PATH_SOM_MENUINICIAL  = "sons/intro.mp3";
    String PATH_SOM_BOTAO  = "sons/botao_clique.mp3";
    String SOM_BOLHA  = "SOM_BOLHA";
    String PATH_SOM_BOLHA  = "sons/bolha.mp3";
    String SOM_JOGO  = "SOM_JOGO";
    String PATH_SOM_JOGO  = "sons/game.mp3";
    String PATH_SOM_TIRO = "sons/shot.mp3";
    
    //
    //IMAGENS
    //
    
    //Imagem de fundo dos Botões do menu inicial
    String BCKGND_BTINICIAIS = "IMG_BCKGND_BTINICIAIS";
    String PATH_BACKGROUND_BTINICIAIS = "imagens/menuInicial/nebula.jpg";
   
    //Imagens Background do menu inicial
    String BCKGND_MENUINICIAL = "BCK_MENU_INICIAL";
    String PATH_BACKGROUND_MENUINICIAL = "imagens/menuInicial/background_menuInicio.jpg";
    
    //Imagens Background do menu jogo
    String BCKGND_MENUJOGO = "BCK_MENU_JOGO";
    String PATH_BACKGROUND_MENUJOGO = "imagens/menuJogo/bckMenuJogo.jpg";
    
    //Imagem do icon da da aplicação
    String ICON = "iconPb";
    String PATH_ICON = "icones/icon.jpg";
    
    //Imagens painel de jogo
    String IMG_COMB = "imgComb";
    String PATH_IMG_COMB = "imagens/menuJogo/comb.png";
    
    //Imagens de cores
    String VERMELHO = "VERMELHO";
    String AZUL = "AZUL";
    String VERDE = "VERDE";
    String NEGRO = "NEGRO";
    String PATH_VERMELHO = "imagens/menuEscolhaRec/red.jpg";
    String PATH_AZUL = "imagens/menuEscolhaRec/blue.jpg";
    String PATH_VERDE = "imagens/menuEscolhaRec/green.jpg";
    String PATH_NEGRO = "imagens/menuEscolhaRec/black.jpg";
    
    //Imagens para a exploração
    String CARTA = "CARTA_TERRENO";
    String PATH_CARTA = "imagens/menuExp/carta2.png";
    String DRONE = "DRONE";
    String PATH_DRONE = "imagens/menuExp/drone.png";
    String CRUZ = "CRUZ";
    String PATH_CRUZ = "imagens/menuExp/cruz.png";
    String ALIEN_AZUL = "ALIEN_AZUL";
    String ALIEN_NEGRO = "ALIEN_NEGRO";
    String ALIEN_VERDE = "ALIEN_VERDE";
    String ALIEN_VERMELHO = "ALIEN_VERMELHO";
    String PATH_ALIEN_AZUL = "imagens/menuExp/alienAzul.png";
    String PATH_ALIEN_NEGRO = "imagens/menuExp/alienPreto.png";
    String PATH_ALIEN_VERDE = "imagens/menuExp/alienVerde.png";
    String PATH_ALIEN_VERMELHO = "imagens/menuExp/alienVermelho.png";
    String BAU = "BAU_RECURSOS";
    String BAU_PEQUENO = "BAU_RECURSOS_PEQUENO";
    String PATH_BAU = "imagens/menuExp/bau.png";
    String VIDA = "VIDA_DRONE";
    String PATH_VIDA = "imagens/menuExp/vida.png";
    String KEY_UP = "KEY_UP";
    String KEY_DOWN = "KEY_DOWN";
    String KEY_LEFT = "KEY_LEFT";
    String KEY_RIGHT = "KEY_RIGHT";
    String KEY_SPACE = "KEY_SPACE";
    String PATH_KEY_UP = "imagens/menuExp/key_up.png";
    String PATH_KEY_DOWN = "imagens/menuExp/key_down.png";
    String PATH_KEY_LEFT = "imagens/menuExp/key_left.png";
    String PATH_KEY_RIGHT = "imagens/menuExp/key_right.png";
    String PATH_KEY_SPACE = "imagens/menuExp/space.png";
    String COMBUSTIVEL = "COMBUSTIVEL";
    String ARMA = "ARMA";
    String ESCUDO = "ESCUDO";
    String PATH_COMBUSTIVEL = "imagens/menuFinal/comb.png";
    String PATH_ARMA = "imagens/menuFinal/gun.png";
    String PATH_ESCUDO = "imagens/menuFinal/shield.png";
    String BANDEIRA_BRANCA = "BANDEIRABRANCA";
    String PATH_BANDEIRA_BRANCA = "imagens/gameover/desistencia.png";
    String EXPLOSAO = "Explosao";
    String PATH_EXPLOSAO = "imagens/gameover/explosao.gif";
    String TROFEU = "TROFEU";
    String PATH_TROFEU = "imagens/gameover/trofeu.png";
    
    
    
    //
    //PAINEL INICIAL
    //
    int ESPACO_TITULO_BT = 70;
    
    //
    //BOTÕES DE NAVES
    //
    String BT_MILITAR = "naveMilitar";
    String BT_MILITAR_ICON = "naveMilitarIcon";
    int ESCOLHA_MILITAR = 1;
    String BT_EXPLORATORIA = "naveExploratoria";
    String BT_EXPLORATORIA_ICON = "naveExploratoriaIcon";
    int ESCOLHA_EXPLORATORIA = 2;
    String PATH_BT_MILITAR = "imagens/naves/naveMil.png";
    String PATH_BT_EXPLORATORIA = "imagens/naves/naveExp.png";
    double SPACING_BT_V = 13.5;
    double SPACING_BT_H = 90;
    double SPACING_BT_LOJA = 25;
    
    //PAINEL DE JOGO
    double SPACING_SUBTITULOS = 650;
    double SPACING_TEXTO = 320;
    
    //OPÇÕES DA LOJA
    int MELHORA_CARGA = 1;
    int CONVERTE_RECURSOS = 2;
    int COMPRA_MEMBRO = 3;
    int EVOLUI_SA = 4;
    int REGENERA_DRONE = 5;
    int COMPRA_DRONE = 6;
    
    //
    //PAINEL DE JOGO
    //
    int X_INICIAL = -165;
    int X_1 = -99;
    int X_2 = -33;
    int X_3 = 33;
    int X_4 = 99;
    int X_5 = 165;
    int Y_INICIAL = -165;
    int Y_1 = -99;
    int Y_2 = -33;
    int Y_3 = 33;
    int Y_4 = 99;
    int Y_5 = 165;
    int MOV_CELULAS = 66;
    
}
