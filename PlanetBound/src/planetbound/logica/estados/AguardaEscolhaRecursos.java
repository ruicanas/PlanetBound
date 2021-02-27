/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package planetbound.logica.estados;

import planetbound.logica.dados.dadosJogo;
import planetbound.logica.dados.exceptions.ConversaoComRecursosIguaisException;

/**
 *
 * @author Rui
 */
public class AguardaEscolhaRecursos extends EstadoAdapter {
    
    public AguardaEscolhaRecursos(dadosJogo dados) {
        super(dados);
    }
    
    @Override
    public IEstado escolheRecursos(String aConverter, String Convertido){
        if(!aConverter.isEmpty() && !Convertido.isEmpty()){
            try {
                getDados().converteRecursos(aConverter, Convertido);
            } catch (ConversaoComRecursosIguaisException ex) {
                getDados().addLog(ex.toString());
            }
        }
        return new AguardaLoja(getDados());
    }
    
    @Override
    public IEstado voltaAoMenuAnterior(){
        return new AguardaLoja(getDados());
    }
    
}
