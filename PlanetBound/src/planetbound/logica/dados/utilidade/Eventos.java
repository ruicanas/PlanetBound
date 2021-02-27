/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package planetbound.logica.dados.utilidade;
import java.io.Serializable;
import planetbound.logica.dados.Setores.Recursos.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import static planetbound.logica.IConstantes.COMBUSTIVEL_DIMINUI;
import planetbound.logica.dados.nave.Nave;
import planetbound.logica.dados.exceptions.*;

/**
 *
 * @author Rui
 */
public class Eventos implements Serializable {
    private final StringBuilder msgLog;
    
    public Eventos(){
        msgLog = new StringBuilder();
    }
    
    private Recursos misturaRecursos(){
        List<Recursos> Saco = new ArrayList<>();
        Saco.add(new RecursoVermelho());
        Saco.add(new RecursoAzul());
        Saco.add(new RecursoNegro());
        Saco.add(new RecursoVerde());
        Collections.shuffle(Saco);
        return Saco.get(0);
    }
    
    private Recursos misturaRecursos(Nave nave){
        List<Recursos> Saco = new ArrayList<>();
        if(nave.obterRecursosAzuis() > 0){
            Saco.add(new RecursoAzul());
        }
        if(nave.obterRecursosNegros() > 0){
            Saco.add(new RecursoNegro());
        }
        if(nave.obterRecursosVermelhos() > 0){
            Saco.add(new RecursoVermelho());
        }
        if(nave.obterRecursosVerdes() > 0){
            Saco.add(new RecursoVerde());
        }
        if(Saco.size() > 0){
            Collections.shuffle(Saco);
            return Saco.get(0);
        }
        else
            return null;
    }
    
    /**
     * Método responsável pelo evento 1 - Morte de um oficial/tripulante.
     */
    private void CrewDeath(Nave nave){
        int n = nave.getOficiaisTam();
        String nome = nave.getOficiais(n-1);
        if(nave.morteOficial()){
            msgLog.append("-> Devido a problemas no sistema acabou de morrer"
                    + " o ").append(nome).append(" da tripulação!\n");
        }
    }
    
    /**
     * Método responsável pelo evento 2 - Explorar uma nave abandonada.
     */
    private void SalvageShip(Nave nave, Dados dados){
        Recursos rec = misturaRecursos();
        int nRecurso = dados.joga6();
        msgLog.append("-> O jogador jogou um dado com "
                    + "6 hipóteses e saiu um ").append(nRecurso).append("\n");
        for (int i = 0; i < nRecurso; i++){
            nave.adicionaCarga(rec);
        }
        msgLog.append("-> Foram adicionados ").append(nRecurso).append(" de ")
                .append(rec.toString()).append(" à carga!\n");
    }
    
    /**
     * Método responsável pelo evento 3 - Perda de carga
     */
    private void CargoLoss(Nave nave, Dados dados){
        Recursos rec = misturaRecursos(nave);
        if(rec == null){            //Este primeiro -if usa-se para o caso de ainda não existirem recursos nenhuns.
            msgLog.append("-> Não há recursos para serem retirados!\n");
        }
        else{
            int i;
            int nRecurso = dados.joga3();
            msgLog.append("-> O jogador jogou um dado com "
                    + "3 hipóteses e saiu um ").append(nRecurso).append("\n");
            for (i = 0; i < nRecurso; i++) {
                if(!nave.retiraCarga(rec)) break;
            }
            msgLog.append("-> Foram removidos ").append(i).append(" de ")
                     .append(rec.toString()).append(" da carga!\n");
            if(i != nRecurso){
                msgLog.append("Não existem mais recursos deste tipo que possam ser retirados!");
            }
        }
    }
    
    /**
     * Método responsável pelo evento 4 - Perda de combustível
     */
    private void FuelLoss(Nave nave){
        nave.diminuiCombustivel(COMBUSTIVEL_DIMINUI);
        msgLog.append("-> Usou acidentalmente demasiado combustível "
                + "num teste com a nave! Perdeu 1 ponto de combustível.\n");
    }
    
    /**
     * Método responsável pelo evento 5 - Não acontece nada.
     */
    private void NoEvent(){
        msgLog.append("-> A nave passou sem problemas pelo espaço!\n");
    }
    
    /**
     * Método responsável pelo evento 6 - Resgate de um oficial
     */
    private void CrewRescue(Nave nave){
        try{
            nave.resgataOficial();
            int n = nave.getOficiaisTam();
            msgLog.append("-> Foi resgatado um membro e nave agora "
                    + "existe um novo ").append(nave.getOficiais(n-1)).append("!\n");
        }catch(QuantidadeTripulantesCheiaException ex){
            msgLog.append(ex);
        }
    }
    
    public String devolveMsgLogEventos(){
        String log = msgLog.toString();
        msgLog.delete(0, log.length());
        return log;
    }
    
    /**
     * Vai aplicar os eventos consoante o número gerado.
     * @param i o evento que se deseja aplicar
     * @param nave onde o evento se vai aplicar
     * @param dados
     * @return true se foi aplicado o evento sem problemas. False caso contrário
     */
    public boolean procuraEvento(int i, Nave nave, Dados dados){
        switch(i){
            case 1:CrewDeath(nave);
                   break;
            case 2:SalvageShip(nave, dados);
                   break;
            case 3:CargoLoss(nave, dados);
                   break;
            case 4:FuelLoss(nave);
                   break;
            case 5:NoEvent();
                   break;
            case 6:CrewRescue(nave);
                   break;
            default: return false;
        }
        return true;
    }
    
    public boolean aplicaEvento(int i, Nave nave, Dados dados){
        switch(i){
            case 1:CrewDeath(nave);
                   break;
            case 2:SalvageShip(nave, dados);
                   break;
            case 3:CargoLoss(nave, dados);
                   break;
            case 4:FuelLoss(nave);
                   break;
            case 5:NoEvent();
                   break;
            case 6:CrewRescue(nave);
                   break;
            default: return false;
        }
        return true;
    }
    
}
