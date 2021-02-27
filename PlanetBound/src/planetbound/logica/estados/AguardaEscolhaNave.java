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
public class AguardaEscolhaNave extends EstadoAdapter{
    
    public AguardaEscolhaNave(dadosJogo dados){
        super(dados);
    }
    
    @Override
    public IEstado selecionaNave(int opt){
        if(getDados().escolhaNave(opt))             //Se a escolha for bem sucedida, passa para o próximo estado
            return new AguardaMovimento(getDados());
        else return this;                           //Caso contrário, permanece neste estado.
    }
    
    @Override
    public IEstado utilizadorTermina(){
        return new GameOver(getDados());
    }
}
    
