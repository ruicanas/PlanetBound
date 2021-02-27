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
public class SistemaArmas implements Serializable{
    private int sistemaArmas;
    private boolean disponibilidade;
    private final int nivel;

    public SistemaArmas(int sistemaArmas, int nivel) {
        this.sistemaArmas = sistemaArmas;
        this.nivel = nivel;
        if (nivel == 1) disponibilidade = true;
        else disponibilidade = false;
    }

    public int getSistemaArmas() {
        return sistemaArmas;
    }
    
    public boolean aumentaSistemaArmas(int pontos){
        if(pontos <= 0) return false;
        sistemaArmas = sistemaArmas + pontos;
        return true;
    }
    
    public boolean diminuiSistemaArmas(int pontos){
        if(pontos <= 0) return false;
        sistemaArmas = sistemaArmas - pontos;
        return true;
    }
    
    public boolean isDisponibilidade() {
        return disponibilidade;
    }

    public void ativaDisponibilidade() {
        disponibilidade = true;
    }
    
    public int getNivel(){
        return nivel;
    }
    
    @Override
    public String toString(){
        if(isDisponibilidade())
            return "Sistema Armas " + getNivel() + " - " + getSistemaArmas();
        else
            return "Sistema Armas " + getNivel() + " não disponível.";
    }
}
