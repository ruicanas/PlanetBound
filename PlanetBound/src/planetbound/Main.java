/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author Rui
 */


package planetbound;
import planetbound.iu.texto.UITexto;
import planetbound.logica.estados.MaqJogo;


public class Main {

    public static void main(String[] args) {
        MaqJogo PlanetBound = new MaqJogo();
        UITexto ui = new UITexto(PlanetBound);
        ui.corre();
    }
}
