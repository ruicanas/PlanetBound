/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package planetbound.logica.estados;

import planetbound.logica.dados.dadosJogo;

/**
 *
 * @author Rui
 */
public class AguardaExploracao extends EstadoAdapter{
    
    public AguardaExploracao(dadosJogo dados) {
        super(dados);
    }
    
    @Override
    public IEstado fazExploracao(){
        if(!getDados().planetaComRecursos()){
            if(getDados().getCombustivel() == 0){
                return new GameOver(getDados());
            }
            getDados().addLog("Não é possível explorar este planeta "
                    + "porque já não existem recursos para explorar!");
            return this;    
        }
        else{
            getDados().exploraSuperficie();
            getDados().gastaCombustivelNaExp();
            return new AguardaRecolha(getDados());
        }  
    }
    
    @Override
    public IEstado addRecursos(){
        if(!getDados().cargaObtemRecursos()){
            getDados().addLog("Todos os recursos explorados foram adicionados com sucesso!");
        }
        return this;
    }
    
    @Override
    public IEstado voltaAoMenuAnterior(){
        getDados().addLog("-> A voltar para a orbita do planeta!");
        return new AguardaOrbita(getDados());
    }
}
