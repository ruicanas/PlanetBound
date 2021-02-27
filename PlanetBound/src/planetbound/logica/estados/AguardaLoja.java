/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package planetbound.logica.estados;

import planetbound.logica.dados.dadosJogo;
import planetbound.logica.dados.exceptions.QuantidadeTripulantesCheiaException;

/**
 *
 * @author Rui
 */
public class AguardaLoja extends EstadoAdapter {
    
    public AguardaLoja(dadosJogo dados) {
        super(dados);
    }
    
    @Override
    public IEstado fazCompras(int opt){
        if(isNaveMilitar()){
            switch(opt){
                case 1: getDados().melhoraCarga();
                        break;
                case 2: return new AguardaEscolhaRecursos(getDados());
                case 3: try {
                        getDados().compraTripulante();
                        } catch (QuantidadeTripulantesCheiaException ex) {
                            getDados().addLog(ex.toString());
                        }  
                        break;
                case 4: getDados().melhoraSistemaArmas();
                        break;
                case 5: getDados().regeneraDrone();
                        break;
                case 6: getDados().compraDrone();
                        break;
            }
        }
        else{
            switch(opt){
                case 1: getDados().melhoraCarga();
                        break;
                case 2: return new AguardaEscolhaRecursos(getDados());
                case 3: try {
                        getDados().compraTripulante();
                        } catch (QuantidadeTripulantesCheiaException ex) {
                            getDados().addLog(ex.toString());
                        }  
                        break;
                case 4: getDados().regeneraDrone();
                        break;
                case 5: getDados().compraDrone();
                        break;
            }
        }
        return this;
    }
    
    @Override
    public IEstado voltaAoMenuAnterior(){
        getDados().addLog("-> A voltar para a orbita do planeta!");
        return new AguardaOrbita(getDados());
    }
    
    
    public boolean isNaveMilitar(){
        return getDados().isNaveMilitar();
    }
 
}
