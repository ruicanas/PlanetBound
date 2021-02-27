/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package planetbound.logica.dados.Setores;

import java.io.Serializable;
import planetbound.logica.dados.Setores.Planetas.Planeta;

/**
 *
 * @author Rui
 */
public class Setor implements Serializable{
    private final Planeta planeta;
    
    public Setor(Planeta planeta){
        this.planeta = planeta;
    }
    
    public Planeta getPlaneta() {
        return planeta;
    }
    
    public String getPlanetaCor(){
        return getPlaneta().toString();
    }    
}
