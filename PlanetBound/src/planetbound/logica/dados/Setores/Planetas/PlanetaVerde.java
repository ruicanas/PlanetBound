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
public class PlanetaVerde extends Planeta{
    
    public PlanetaVerde(){
        super.addRecursos(FabricaRecursos.criaRecursos(VERMELHO));
        super.addRecursos(FabricaRecursos.criaRecursos(VERDE));
    }
    
    @Override
    public String toString(){
        return "Verde";
    }
}
