/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package planetbound.logica.dados.nave;

import planetbound.logica.dados.nave.tipos.Exploratoria;
import planetbound.logica.dados.nave.tipos.Militar;

/**
 *
 * @author Rui
 */
public class FabricaNaves {
    public static Nave criaNave(int opt){
        switch (opt) {
            case 1:
                return new Militar();
            case 2:
                return new Exploratoria();
            default:
                return null;
        }
    }
}
