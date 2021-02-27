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
public class AguardaMovimento extends EstadoAdapter {
    
    public AguardaMovimento(dadosJogo dados) {
        super(dados);
    }
    
    @Override
    public IEstado fazMovimento(){
        getDados().descobreNovoSetor();
        switch(getDados().fazViagem()){
            case 1: return new ocorreEvento(getDados());
            case 2: return new AguardaOrbita(getDados());
            case -1: return new GameOver(getDados());
        }
        return this;
    }
    
    @Override
    public IEstado utilizadorTermina(){
        return new GameOver(getDados());
    }
}
