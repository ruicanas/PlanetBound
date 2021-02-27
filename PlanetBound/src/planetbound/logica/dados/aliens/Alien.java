/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package planetbound.logica.dados.aliens;

import java.io.Serializable;
import planetbound.logica.IConstantes;

/**
 *
 * @author Rui
 */
public class Alien implements IConstantes, Serializable{
    private final String cor;
    private final int [] numAtaque;
    private final int [] numMorte;
    private int x;
    private int y;

    public Alien(String cor, int [] numAtaque, int [] numMorte) {
        this.cor = cor;
        this.numAtaque = numAtaque;
        this.numMorte = numMorte;
        x = 0;
        y = 0;
    }
    
    public int [] getNumAtaque(){
        return numAtaque;
    }
    
    public int [] getNumMorte(){
        return numMorte;
    }
    
    public String getCor(){
        return cor;
    }
    
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean setX(int x) {
        if(x < 0 || x > 5) return false;
        this.x = x;
        return true;
    }

    public boolean setY(int y) {
        if(y < 0 || y > 5) return false;
        this.y = y;
        return true;
    }
    
    /**
     * Alien vai decidir como se movimentar através da posição do drone
     * @param x Linha do drone
     * @param y coluna do drone
     * @return true se for executado um movimento válido
     */
    public boolean movimentoAlien(int x, int y){
        if(getX() < x){                                     //Se o alien estiver numa linha anterior ao drone
            if(getY() < y) return setY(getY()+1);           //caso se encontre numa coluna anterior ao drone, move-se para a próxima coluna
            else if(getY() > y) return setY(getY()-1);      //se não, caso se encontre numa linha posterior ao drone, move-se para a coluna anterior
            else return setX(getX()+1);                     //se não, se estiver na mesma coluna que o drone, move-se para a próxima linha.
        }else if(getX() > x){                               //Se o alien estiver numa linha posterior ao drone
            if(getY() < y) return setY(getY()+1);           //caso se encontre numa coluna anterior ao drone, move-se para a próxima coluna
            else if(getY() > y) return setY(getY()-1);      //se não, caso se encontre numa linha posterior ao drone, move-se para a coluna anterior
            else return setX(getX()-1);                     //se não, se estiver na mesma coluna que o drone, move-se para a linha anterior à que está.
        }else{                                              //Se o alien estiver na mesma linha que o drone
            if(getY() < y) return setY(getY()+1);           //caso se encontre numa coluna anterior ao drone, move-se para a próxima coluna
            else if(getY() > y) return setY(getY()-1);      //se não, move-se para a coluna posterior ao drone.
        }
        return false;
    }
    
    //
    //Interações na luta
    //
    /**
     * Método que verifica o ataque do alien
     * @param n número do dado que saiu que vai ser verificado
     * @return true se o alien tiver atacado com sucesso o drone. False
     * caso contrário.
     */
    public boolean alienAtaca(int n){
        for (int i = 0; i < getNumAtaque().length; i++) {
            if(getNumAtaque()[i] == n){
                return true;
            }
        }
        return false;
    }
    /**
     * Método que verifica se o Alien foi morto
     * @param n número do dado que saiu que vai ser verificado
     * @return true se o alien tiver sido atacado e, por consequência, morto. False
     * caso contrário.
     */
    public boolean alienMorre(int n){
        for (int i = 0; i < getNumMorte().length; i++) {
            if(getNumMorte()[i] == n){
                return true;
            }
        }
        return false;
    }
    
    @Override
    public String toString(){
        return "Alien de cor " + cor;
    }
    
    public String toStringCenario(){
        return ALIEN_TAB;
    }
}
