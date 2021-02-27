/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package planetbound.logica.dados.Setores.Planetas;

import planetbound.logica.dados.Setores.Recursos.FabricaRecursos;

/**
 *
 * @author Rui
 */
public class PlanetaNegro extends Planeta {
    
    public PlanetaNegro(){
        super.addRecursos(FabricaRecursos.criaRecursos(NEGRO));
        super.addRecursos(FabricaRecursos.criaRecursos(AZUL));
    }
    
    @Override
    public String toString(){
        return "Negro";
    }
}
