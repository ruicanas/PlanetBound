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
public class AguardaRecolha extends EstadoAdapter {
    
    public AguardaRecolha(dadosJogo dados) {
        super(dados);
    }
    
    @Override
    public IEstado moveDrone(int i){
        if(!getDados().alienEncontraDrone()){
            switch (i) {
                case CIMA:
                    getDados().moveDroneCima();
                    break;
                case DIREITA:
                    getDados().moveDroneDireita();
                    break;
                case ESQUERDA:
                    getDados().moveDroneEsquerda();
                    break;
                case BAIXO:
                    getDados().moveDroneBaixo();
                    break;
                default:
                    break;
            }
            if(getDados().droneSaiDoPlaneta()){
                return new AguardaExploracao(getDados());
            }
        }else{
            if(getDados().droneAtaca()){
                getDados().addLog("Oh não! Um novo alien está a chegar!");
                if(getDados().addNovoAlien()){
                    getDados().addLog("Este novo Alien já se encontra no planeta!");
                }
            }else{
                if(getDados().alienAtaca()){
                    getDados().addLog("O Alien acabou de destruir o seu drone de exploração!");
                    return new AguardaOrbita(getDados());
                }
            }
        }
        return this;
    }
}
