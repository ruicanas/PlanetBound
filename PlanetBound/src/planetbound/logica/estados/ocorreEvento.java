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
public class ocorreEvento extends EstadoAdapter {
    
    public ocorreEvento(dadosJogo dados) {
        super(dados);
    }

//    @Override
//    public IEstado aplicaEvento(int opt){
//        getDados().aplicaEvento(opt);
//        return this;
//    }
    
    @Override
    public IEstado determinaEvento(){
        getDados().aplicaEvento();
        if(getDados().isGameOver()){
            return new GameOver(getDados());
        }
        return new AguardaOrbita(getDados());
    }
    
    @Override
    public IEstado aplicaEvento(int i){
        getDados().aplicaEvento(i);
        if(getDados().isGameOver()){
            return new GameOver(getDados());
        }
        return new AguardaOrbita(getDados());
    }
    
}
