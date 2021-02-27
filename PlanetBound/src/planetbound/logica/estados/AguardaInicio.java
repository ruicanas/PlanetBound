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
public class AguardaInicio extends EstadoAdapter{
    public AguardaInicio(dadosJogo dados) {
        super(dados);
    }
    
    @Override
    public IEstado comecaJogo(){
        return new AguardaEscolhaNave(getDados());
    }
    
}

