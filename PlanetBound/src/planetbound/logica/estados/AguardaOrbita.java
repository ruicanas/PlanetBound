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
public class AguardaOrbita extends EstadoAdapter {
    
    public AguardaOrbita(dadosJogo dados){    
        super(dados);
    }
    
    @Override
    public IEstado fazMovimento(){
        if(!getDados().getGrupoDesembarque()){
            getDados().addLog("Não é possível explorar um planeta uma vez"
                    + " que não possui nenhum grupo de desembarque!");
            return this;
        }
        else if(!getDados().verificaDrone()){
            getDados().addLog("Não é possível explorar um planeta uma vez"
                    + " que não possui nenhum drone!");
            return this;
        }
        else{
           return new AguardaExploracao(getDados()); 
        }
    }   
    
    @Override
    public IEstado acessaLoja(){
        if(getDados().verificaSetor()){
            getDados().addLog("-> A sua nave está a dirigir-se para a Space"
                    + " Station!");
            return new AguardaLoja(getDados());
        }
        return this;
    }
    
    @Override
    public IEstado terminaTurno(){
        if(getDados().isGameOver()){
            return new GameOver(getDados());
        }else{
            return new AguardaConversao(getDados());
        }
    }
    
    
    public boolean setorIsVermelho(){
        return getDados().verificaSetor();
    }
    
    @Override
    public IEstado utilizadorTermina(){
        return new GameOver(getDados());
    }
    
}

