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
public class GameOver extends EstadoAdapter {
    
    public GameOver(dadosJogo dados) {
        super(dados);
    }
    
    @Override
    public IEstado gameOver(){
        if(getDados().isGameOver()){
            //getDados().addLog("--------------------------------- PERDEU ---------------------------------");
            if(!getDados().getCapitao()){
                getDados().addLog("O capitão da nave espacial acabou de morrer e a humanidade foi extinta.\n");
            }
            else if(getDados().getCombustivel() <= 0){
                getDados().addLog("O combustível acabou e ficou a tripulação ficou à deriva\n no espaço, acabando assim por morrer. A humanidade foi extinta.\n");
            }
            getDados().addLog("Não desistas já! Podes voltar a jogar e tentar a tua sorte!\n");
        }
        else if(getDados().getArtefactos() == MAX_ARTEFACTOS){
            //getDados().addLog("--------------------------------- VENCEU ---------------------------------");
            getDados().addLog("Conseguiste! Com a tua liderança, a tripulação conseguiu alcançar um novo planeta! A humanidade não será extinta graças ao teu trabalho!\n");
            getDados().addLog("Mas será que consegues vencer o jogo mais uma vez? Tenta outra vez!\n");
        }else{
            //getDados().addLog("-------------------------------- ABANDONOU --------------------------------\n");
            getDados().addLog("Por alguma razão decidiste terminar o jogo. Uma nova tripulação estará à tua espera caso queiras voltar!\n");
        }
        return this;
    }
    
    @Override
    public IEstado voltaJogar(){
        getDados().resetDados();
        return new AguardaInicio(getDados());
    }
    
}
