/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package planetbound.logica.dados.Setores.Planetas;

/**
 *
 * @author Rui
 */
public class FabricaPlanetas {
    public static Planeta criaPlaneta(String cor){
        if(cor.equalsIgnoreCase("AZUL")){
            return new PlanetaAzul();
        } else if (cor.equalsIgnoreCase("NEGRO")){
            return new PlanetaNegro();
        } else if (cor.equalsIgnoreCase("VERDE")){
            return new PlanetaVerde();
        } else if (cor.equalsIgnoreCase("VERMELHO")){
            return new PlanetaVermelho();
        }
        return null;
    }
}
