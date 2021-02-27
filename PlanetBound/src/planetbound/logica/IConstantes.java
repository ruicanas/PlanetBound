/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package planetbound.logica;

/**
 *
 * @author Rui
 */
public interface IConstantes {
    //
    //Defaults do jogo
    public static final String [] CORES = {"AZUL", "NEGRO", "VERMELHO", "VERDE"};
    public static final String AZUL = CORES[0];
    public static final String NEGRO = CORES[1];
    public static final String VERMELHO = CORES[2];
    public static final String VERDE = CORES[3];
    public static final String ARTEFACTO = "ROXO";
    public static final int SETOR = 10;
    public static final int PROB_SETORVERM = 2;
    public static final int PROB_BURACO = 8;
    public static final int ESPACO = 6;
    public static final int CELULAS_SISTEMA_ARMAS = 9;
    public static final int PROTECAO_EXPLORACAO = 18;
    public static final int PROTECAO_MILITAR = 9;
    public static final int COMBUSTIVEL_EXPLORACAO = 53;
    public static final int COMBUSTIVEL_MILITAR = 35;
    public static final int NIVEL_CARGA_MILITAR = 1;
    public static final int NIVEL_CARGA_EXPLORACAO = 3;
    public static final int MAX_CARGA_MILITAR = 1;
    public static final int MAX_CARGA_EXPLORATORIA = 3;
    
    
    //
    //Tripulantes nave espacial
    //
    public static final String [] TRIPULACAO = {"Capitão",
        "Navegação",
        "Grupo de Desembarque",
        "Escudeiro",
        "Armeiro",
        "Responsável por Cargas"};
    
    public static final String CAPITAO = "Capitão";
    public static final String NAVEGACAO = "Navegação";
    public static final String GRUPO_DESEMBARQUE = "Grupo de Desembarque";
    public static final String ESCUDEIRO = "Escudeiro";
    public static final String ARMEIRO = "Armeiro";
    public static final String RESP_CARGA = "Responsável por Cargas";
    public static final int MAX_TRIPULANTES = 6;
    
    //
    //Viagens no buraco negro
    //
    public static final int SARMAS_DIMINUI = 1;
    public static final int COMBUSTIVEL_DIMINUI = 1;
    public static final int COMBUSTIVEL_DIMINUI_BNEGRO = 3;
    public static final int COMBUSTIVEL_DIMINUI_BNEGRO_SEM_OFICIAL = 4;
    public static final int ESCUDO_DIMINUI = 1;
    public static final int ESCUDO_DIMINUI_BNEGRO = 2;
    public static final int ESCUDO_DIMINUI_BNEGRO_SEM_OFICIAL = 4;
    public static final int MIN_COMBUSTIVEL_SEM_OFICIAL_BN = 4;
    public static final int MIN_ESCUDO_BN = 2;
    public static final int MIN_COMBUSTIVEL_BN = 3;
    
    //
    //Recursos necessários para fazer determinadas compras na Space Station
    //
    public static final int UPGRADE_CARGA = 1;
    public static final int CONTRATAR_MEMBRO_PERDIDO = 1;
    public static final int UPGRADE_SISTEMA_ARMAS = 2;
    public static final int PREENCHE_PROTECAO = 1;
    public static final int COMPRA_DRONE = 3;
    public static final String SAI = "sai";
    
    //
    //Tamanho Terrain Cards
    //
    public static final int TAM_TERRENO = 6;
    
    //
    //Drone
    //
    public static final int VIDA_DRONE = 6;
    public static final int PERDE_VIDA = 1;
    
    //
    //Defaults para os aliens
    //
    public static final int [] ATAQUE_ALIEN_AZUL = {3,4,5};
    public static final int [] ATAQUE_ALIEN_NEGRO = {1};
    public static final int [] ATAQUE_ALIEN_VERDE = {1,2};
    public static final int [] ATAQUE_ALIEN_VERMELHO = {5,6};
    public static final int [] MORTE_ALIEN_AZUL = {3,4,5};
    public static final int [] MORTE_ALIEN_NEGRO = {5,6};
    public static final int [] MORTE_ALIEN_VERDE = {4,5,6};
    public static final int [] MORTE_ALIEN_VERMELHO = {1,2};
    
    //
    //Número máximo de artefactos
    //
    public static final int MAX_ARTEFACTOS = 5;
    
    //
    //Movimento do Drone
    //
    public static final int CIMA = 1;
    public static final int DIREITA = 2;
    public static final int ESQUERDA = 3;
    public static final int BAIXO = 4;
    
    //
    //Abreviatura para o tabuleiro
    //
    public static final String RECURSO_TAB = "R";
    public static final String ALIEN_TAB = "A";
    public static final String DRONE_TAB = "D";
    
    //
    //Número de pontos aumentado nos recursos
    //
    public static final int AUMENTA_ESCUDO = 1;
    public static final int AUMENTA_SA = 1;
    public static final int AUMENTA_COMBUSTIVEL = 1;
    
    //
    //Escolha dos recursos que se pretendem converter
    //
    public static final int CONVERTER_COMBUSTIVEL = 1;
    public static final int CONVERTER_SARMAS = 2;
    public static final int CONVERTER_ESCUDO = 3;
}
