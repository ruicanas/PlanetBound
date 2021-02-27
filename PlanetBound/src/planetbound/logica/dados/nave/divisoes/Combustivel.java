/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package planetbound.logica.dados.nave.divisoes;

import java.io.Serializable;

/**
 *
 * @author Rui
 */
public class Combustivel implements Serializable{
    private int combustivel;

    public Combustivel(int combustivel) {
        this.combustivel = combustivel;
    }

    public int getCombustivel() {
        return combustivel;
    }

    public boolean diminuiCombustivel(int pontos) {
        if(pontos <= 0) return false;
        combustivel = combustivel - pontos;
        return true;
    }
    
    public boolean aumentaCombustivel(int pontos) {
        if(pontos <= 0) return false;
        combustivel = combustivel + pontos;
        return true;
    }
    
    @Override
    public String toString(){
        return "Combustivel - " + getCombustivel();
    }
}
