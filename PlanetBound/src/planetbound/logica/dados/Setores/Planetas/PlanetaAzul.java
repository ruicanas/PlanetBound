/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package planetbound.logica.dados.Setores.Planetas;
import planetbound.logica.dados.Setores.Recursos.*;

/**
 *
 * @author Rui
 */
public class PlanetaAzul extends Planeta {
    
    public PlanetaAzul(){
        super.addRecursos(FabricaRecursos.criaRecursos(NEGRO));
        super.addRecursos(FabricaRecursos.criaRecursos(VERDE));
        super.addRecursos(FabricaRecursos.criaRecursos(AZUL));
        super.addRecursos(FabricaRecursos.criaRecursos(ARTEFACTO));
    }
    
    @Override
    public String toString(){
        return "Azul";
    }
}
