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
public class PlanetaVermelho extends Planeta {
    
    public PlanetaVermelho(){
        super.addRecursos(FabricaRecursos.criaRecursos(AZUL));
        super.addRecursos(FabricaRecursos.criaRecursos(VERMELHO));
    }
    
    @Override
    public String toString(){
        return "Vermelho";
    }
}
