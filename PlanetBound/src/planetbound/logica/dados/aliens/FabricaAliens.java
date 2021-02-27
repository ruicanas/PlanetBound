/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package planetbound.logica.dados.aliens;

/**
 *
 * @author Rui
 */
public class FabricaAliens {
    public static Alien criaAlien(String cor){
        if(cor.equalsIgnoreCase("AZUL")){
            return new AlienAzul("AZUL");
        } else if (cor.equalsIgnoreCase("NEGRO")){
            return new AlienNegro("NEGRO");
        } else if (cor.equalsIgnoreCase("VERDE")){
            return new AlienVerde("VERDE");
        } else if (cor.equalsIgnoreCase("VERMELHO")){
            return new AlienVermelho("VERMELHO");
        }
        return null;
    }
}
