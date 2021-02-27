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
public class Escudo implements Serializable{
    private int sistemaEscudo;

    public Escudo(int sistemaEscudo) {
        this.sistemaEscudo = sistemaEscudo;
    }

    public int getSistemaEscudo() {
        return sistemaEscudo;
    }

    public boolean aumentaSistemaEscudo(int pontos) {
        if(pontos <= 0) return false;
        sistemaEscudo = sistemaEscudo + pontos;
        return true;
    }
    
    public boolean diminuiSistemaEscudo(int pontos) {
        if(pontos <= 0) return false;
        sistemaEscudo = sistemaEscudo - pontos;
        return true;
    }
    
    @Override
    public String toString(){
        return "Sistema de Escudo - " + getSistemaEscudo();
    }
}
