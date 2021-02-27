/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package planetbound.logica.dados.nave.divisoes;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import planetbound.logica.IConstantes;
import planetbound.logica.dados.Setores.Recursos.*;

/**
 *
 * @author Rui
 */
public class Carga implements IConstantes, Serializable{
    private int nivelCarga;
    private final List<RecursoAzul> cargaRAzul;
    private final List<RecursoNegro> cargaRNegro;
    private final List<RecursoVermelho> cargaRVermelho;
    private final List<RecursoVerde> cargaRVerde;
    private final List<Artefacto> cargaArtefactos;
    

    public Carga() {
        nivelCarga = 0;
        cargaRAzul = new ArrayList<>();
        cargaRNegro = new ArrayList<>();
        cargaRVermelho = new ArrayList<>();
        cargaRVerde = new ArrayList<>();
        cargaArtefactos = new ArrayList<>();
        
        //Efeitos de teste
//        for (int i = 0; i < 6; i++) {
//            cargaRVermelho.add(new RecursoVermelho());
//            cargaRAzul.add(new RecursoAzul());
//            cargaRNegro.add(new RecursoNegro());
//            cargaRVerde.add(new RecursoVerde());
//        }
        //
    }

    public int getNivelCarga() {
        return nivelCarga;
    }

    public List<RecursoAzul> getCargaRAzul() {
        return cargaRAzul;
    }

    public List<RecursoNegro> getCargaRNegro() {
        return cargaRNegro;
    }

    public List<RecursoVermelho> getCargaRVermelho() {
        return cargaRVermelho;
    }

    public List<RecursoVerde> getCargaRVerde() {
        return cargaRVerde;
    }
    
    public List<Artefacto> getCargaArtefactos(){
        return cargaArtefactos;
    }
    
    public int getRecAzulTam(){
        return getCargaRAzul().size();
    }
     
    public int getRecNegroTam(){
        return getCargaRNegro().size();
    }
    
    public int getRecVerdeTam(){
        return getCargaRVerde().size();
    }
    
    public int getRecVermelhoTam(){
        return getCargaRVermelho().size();
    }
    
    public int getArtefactosTam(){
        if (getCargaArtefactos() == null) return 0;
        return getCargaArtefactos().size();
    }
    
    public int getEspacoMaximo(){
        return ESPACO * (nivelCarga+1);
    }
    
    /**
     * Uma vez que os espaços da carga vão de 6 em 6, sempre que
     * o tamanho da carga em questão for menor que o (ESPAÇO * nível da carga +1)
     * vai ser possível adicionar um recurso à carga.
     * Exemplo: 3 itens na carga e quero adicionar mais 1. Estou no nível 0,
     * então 3 é menor que 6*(0+1)? Sim é, então há espaço suficiente, sem que
     * se ultrapasse o limite de carga estipulado!
     * @param rec um tipo de recurso.
     * @return true se o recurso foi adicionado. False caso contrário.
     */
    public boolean adicionaCarga(Recursos rec) {
        if(rec instanceof RecursoAzul){
            if(getRecAzulTam() < getEspacoMaximo())   
                getCargaRAzul().add((RecursoAzul)rec);
            else return false;
        } else if(rec instanceof RecursoVermelho){
            if(getRecVermelhoTam() < getEspacoMaximo())   
                getCargaRVermelho().add((RecursoVermelho)rec);
            else return false;
        } else if(rec instanceof RecursoNegro){
            if(getRecNegroTam() < getEspacoMaximo())   
                getCargaRNegro().add((RecursoNegro)rec);
            else return false;
        } else if(rec instanceof RecursoVerde){
            if(getRecVerdeTam()< getEspacoMaximo())   
                getCargaRVerde().add((RecursoVerde)rec);
            else return false;
        } else if(rec instanceof Artefacto){
            if(getArtefactosTam() < MAX_ARTEFACTOS)   
                return getCargaArtefactos().add((Artefacto)rec);
        }   
        return true;
    }
    
    /**
     * Retira somente um elemento da carga
     * @param rec o recurso que se pretende retirar
     * @return true se o recurso tiver sido retirado com sucesso. False
     * em caso contrário.
     */
    public boolean retiraCarga(Recursos rec) {
        if(rec instanceof RecursoAzul){
            if(getCargaRAzul().size() > 0)   
                getCargaRAzul().remove(0);
            else return false;
        } else if(rec instanceof RecursoVermelho){
            if(getCargaRVermelho().size() > 0)   
                getCargaRVermelho().remove(0);
            else return false;
        } else if(rec instanceof RecursoNegro){
            if(getCargaRNegro().size() > 0)   
                getCargaRNegro().remove(0);
            else return false;
        } else{
            if(getCargaRVerde().size() > 0)   
                getCargaRVerde().remove(0);
            else return false;
        }
        return true;
    }
    
    /**
     * Retira um determinado número de todos os recursos existentes
     * @param num o número de recursos que se pretende retirar de todos os sitios
     * @return true se tudo correu bem, false em caso contrário.
     */
    public boolean retiraTodosCarga(int num){
        for (int i = 0; i < num; i++) {
            if(getCargaRAzul().size() > 0) getCargaRAzul().remove(0);
            if(getCargaRNegro().size() > 0) getCargaRNegro().remove(0);
            if(getCargaRVerde().size() > 0) getCargaRVerde().remove(0);
            if(getCargaRVermelho().size() > 0) getCargaRVermelho().remove(0);
        }
        return true;
    }
    
    /**
     * Retira da carga os recursos necessários para que seja possível aumentar o combustível
     * @return true se forem retirados. False caso contrário
     */
    public boolean retiraCargaCombustivel(){
        if(getCargaRNegro().isEmpty() || getCargaRVerde().isEmpty() || getCargaRVermelho().isEmpty()){
            return false;
        }
        getCargaRNegro().remove(0);
        getCargaRVerde().remove(0);
        getCargaRVermelho().remove(0);
        return true;
    }
    /**
     * Retira da carga os recursos necessários para que seja possível aumentar o Sistema de Armas
     * @return true se forem retirados. False caso contrário
     */
    public boolean retiraCargaSistemaArmas(){
        if(getCargaRNegro().isEmpty() || getCargaRAzul().isEmpty()){
            return false;
        }
        getCargaRNegro().remove(0);
        getCargaRAzul().remove(0);
        return true;
    }
     /**
     * Retira da carga os recursos necessários para que seja possível aumentar o Escudo
     * @return true se forem retirados. False caso contrário
     */
    public boolean retiraCargaEscudo(){
        if(getCargaRNegro().isEmpty() || getCargaRVerde().isEmpty() || getCargaRAzul().isEmpty()){
            return false;
        }
        getCargaRNegro().remove(0);
        getCargaRVerde().remove(0);
        getCargaRAzul().remove(0);
        return true;
    }
    
    public boolean evoluiCarga(){
        nivelCarga++;
        return true;
    }
    
    public boolean fazConversao(String aConverter, String Convertido){
        Recursos converte = FabricaRecursos.criaRecursos(aConverter);
        Recursos convertido = FabricaRecursos.criaRecursos(Convertido);
        if(!retiraCarga(converte)) return false;
        return adicionaCarga(convertido);
    }
    
    public boolean verificaRecursos(int valor){
        return (getRecAzulTam() >= valor && getRecNegroTam() >= valor 
                && getRecVerdeTam() >= valor && getRecVermelhoTam() >= valor);
    }
    
    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("Nivel da Carga: ").append(getNivelCarga()).append("\n");
        sb.append("Recursos Azuis: ").append(getRecAzulTam()).append("/")
                .append(getEspacoMaximo()).append("\n");
        sb.append("Recursos Negros: ").append(getRecNegroTam()).append("/")
                .append(getEspacoMaximo()).append("\n");
        sb.append("Recursos Verdes: ").append(getRecVerdeTam()).append("/")
                .append(getEspacoMaximo()).append("\n");
        sb.append("Recursos Vermelhos: ").append(getRecVermelhoTam()).append("/")
                .append(getEspacoMaximo());
        sb.append("\nArtefactos: ").append(getArtefactosTam()).append("/")
                .append(MAX_ARTEFACTOS);
        return sb.toString();
    }
}
