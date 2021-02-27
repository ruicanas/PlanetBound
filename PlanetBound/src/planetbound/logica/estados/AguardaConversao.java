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
public class AguardaConversao extends EstadoAdapter {
    
    public AguardaConversao(dadosJogo dados) {
        super(dados);
    }
    
    @Override
    public IEstado converteRecursosExplorados(int opt){
        if(!getDados().getResponsavelCarga()){
            getDados().addLog("Não é possível efetuar esta ação uma vez que o oficial de Cargas não está disponivel!");
            return this;
        }
        switch(opt){
            case CONVERTER_COMBUSTIVEL: getDados().converteCombustivel();
                                        break;
            case CONVERTER_SARMAS: getDados().converteSistemaArmas();
                                   break;
            case CONVERTER_ESCUDO: getDados().converteEscudo();
                                   break;
            default: break;
        }
        return this;
    }
    
 
    @Override
    public IEstado terminaTurno(){
        getDados().aumentaTurno();
        return new AguardaMovimento(getDados());
    }
    
}
