/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package planetbound.logica.dados.Setores.Recursos;

/**
 *
 * @author Rui
 */
public class FabricaRecursos {
    public static Recursos criaRecursos(String cor){
        if(cor.equalsIgnoreCase("AZUL")){
            return new RecursoAzul();
        } else if (cor.equalsIgnoreCase("NEGRO")){
            return new RecursoNegro();
        } else if (cor.equalsIgnoreCase("VERDE")){
            return new RecursoVerde();
        } else if (cor.equalsIgnoreCase("VERMELHO")){
            return new RecursoVermelho();
        } else if (cor.equalsIgnoreCase("ROXO")){
            return new Artefacto();
        }
        return null;
    }
}
