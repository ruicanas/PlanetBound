/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package planetbound.logica.dados.Setores.Recursos;

import java.io.Serializable;
import java.util.Objects;


/**
 *
 * @author Rui
 */
public class Recursos implements Serializable {
    private String cor;
    private int x;
    private int y;

    public Recursos(String cor) {
        this.cor = cor;
    }

    public String getCor() {
        return cor;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
    
    
    
    /**
     * Obter a informação sobre o recurso para o cenário.
     * @return 
     */
    public String toStringCenario(){
        return "R";
    }
    
    @Override
    public String toString(){
        return "Recurso " + getCor();
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 17 * hash + Objects.hashCode(this.cor);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Recursos other = (Recursos) obj;
        if (!Objects.equals(this.cor, other.cor)) {
            return false;
        }
        return true;
    }
    
    
}
