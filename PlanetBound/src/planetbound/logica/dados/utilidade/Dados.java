package planetbound.logica.dados.utilidade;

import java.io.Serializable;

public class Dados implements Serializable{
    private static final int MAX = 6;
    private static final int MIN = 1;
    private int dado;

    public Dados() {
    }

    private int getDado() {
        return dado;
    }

    private void setDado(int dado) {
        this.dado = dado;
    }
    
    public int joga6(){
        int range = MAX - MIN + 1;
        int a = (int)(Math.random()* range) + MIN ; //Gera um número aleatório de 1 a 6
        return a;
    }
    
    public int joga8(){
        int max = MAX + 2;
        int range = max - MIN + 1;
        int a = (int)(Math.random()* range) + MIN ; //Gera um número aleatório de 1 a 8
        return a;
    }
    
    public int joga3(){
        int max = MAX - 3;
        int range = max - MIN + 1;
        int a = (int)(Math.random()* range) + MIN ; //Gera um número aleatório de 1 a 3
        return a;
    }
}
