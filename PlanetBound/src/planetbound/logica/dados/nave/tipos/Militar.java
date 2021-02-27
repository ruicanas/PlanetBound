package planetbound.logica.dados.nave.tipos;

import planetbound.logica.dados.Setores.Recursos.*;
import planetbound.logica.dados.exceptions.QuantidadeTripulantesCheiaException;
import planetbound.logica.dados.nave.Nave;
import planetbound.logica.dados.nave.divisoes.*;

public class Militar extends Nave{
    private final SistemaArmas sistemaArmas1;
    private final SistemaArmas sistemaArmas2;
    private final Escudo sistemaEscudo;
    private final Combustivel combustivel;
    private final Carga carga;
    
    public Militar(){
        super();
        sistemaArmas1 = new SistemaArmas(CELULAS_SISTEMA_ARMAS, 1);
        sistemaArmas2 = new SistemaArmas(CELULAS_SISTEMA_ARMAS, 2);
        sistemaEscudo = new Escudo(PROTECAO_MILITAR);
        combustivel = new Combustivel(COMBUSTIVEL_MILITAR);
        carga = new Carga();
    }

    public SistemaArmas getSistemaArmas1() {
        return sistemaArmas1;
    }

    public SistemaArmas getSistemaArmas2() {
        return sistemaArmas2;
    }

    public Escudo getSistemaEscudo() {
        return sistemaEscudo;
    }

    public Combustivel getCombustivel() {
        return combustivel;
    }

    public Carga getCarga() {
        return carga;
    }
    
    @Override
    public int obtemSistemaArmas() {
        return getSistemaArmas1().getSistemaArmas();
    }
    
    /**
     * Método que devolve a capacidade atual do sistema de armas 2
     * @return o número exato de células que o sistema de armas 2 ainda tem.
     */
    @Override
    public int obtemSistemaArmas2(){
        return getSistemaArmas2().getSistemaArmas();
    }
    
    @Override
    public boolean diminuiSistemaArmas(){
        if(getSistemaArmas2().isDisponibilidade() && getSistemaArmas2().getSistemaArmas() > 0){
            getSistemaArmas2().diminuiSistemaArmas(SARMAS_DIMINUI);
            return true;
        }else if(getSistemaArmas1().getSistemaArmas() > 0){
            getSistemaArmas1().diminuiSistemaArmas(SARMAS_DIMINUI);
            return true;
        }
        return false;
    }

    @Override
    public boolean aumentaSistemaArmas() {
        if(getSistemaArmas2().isDisponibilidade() && getSistemaArmas2().getSistemaArmas() == CELULAS_SISTEMA_ARMAS){
            if(!getCarga().retiraCargaSistemaArmas()){
                return false;
            }else{
                getSistemaArmas2().aumentaSistemaArmas(AUMENTA_SA);
            }
        }else{
            if(!getCarga().retiraCargaSistemaArmas()){
                return false;
            }else{
                getSistemaArmas1().aumentaSistemaArmas(AUMENTA_SA);
            }
        }
        return true;
    }

    @Override
    public int obtemEscudo() {
        return getSistemaEscudo().getSistemaEscudo();
    }

    @Override
    public boolean diminuiEscudo(int pontos){
        if(obtemEscudo() == 0){
            return false;
        }
        getSistemaEscudo().diminuiSistemaEscudo(pontos);
        return true;
    }

    @Override
    public boolean aumentaEscudo() {
        if(!getCarga().retiraCargaEscudo()){
            return false;
        }else{
            getSistemaEscudo().aumentaSistemaEscudo(AUMENTA_ESCUDO);
        }
        return true;
    }

    @Override
    public int obtemCombustivel() {
        return getCombustivel().getCombustivel();
    }

    @Override
    public boolean diminuiCombustivel(int pontos) {
        return getCombustivel().diminuiCombustivel(pontos);
    }

    @Override
    public boolean aumentaCombustivel() {
        if(!getCarga().retiraCargaCombustivel()){
            return false;
        }else{
            getCombustivel().aumentaCombustivel(AUMENTA_COMBUSTIVEL);
        }
        return true;
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
    @Override
    public boolean adicionaCarga(Recursos rec) {
        return getCarga().adicionaCarga(rec);
    }
    
     @Override
    public boolean retiraCarga(Recursos rec) {
        return getCarga().retiraCarga(rec);
    }

    @Override
    public int obterRecursosAzuis() {
        return getCarga().getRecAzulTam();
    }

    @Override
    public int obterRecursosVermelhos() {
        return getCarga().getRecVermelhoTam();
    }

    @Override
    public int obterRecursosVerdes() {
        return getCarga().getRecVerdeTam();
    }

    @Override
    public int obterRecursosNegros() {
        return getCarga().getRecNegroTam();
    }
    
    @Override
    public boolean melhoraCarga(){
        if(!getCarga().verificaRecursos(UPGRADE_CARGA) || getCarga().getNivelCarga() == MAX_CARGA_MILITAR){
            return false;
        }
        getCarga().retiraTodosCarga(UPGRADE_CARGA);
        return getCarga().evoluiCarga();
    }
    
    @Override
    public boolean converterRecursos(String aConverter, String Convertido){
        return getCarga().fazConversao(aConverter, Convertido);
    }
    
    @Override
    public boolean melhoraSistemaArmas() {
        if(getSistemaArmas2().isDisponibilidade()) return false;
        if(!getCarga().verificaRecursos(UPGRADE_SISTEMA_ARMAS))
            return false;
        getCarga().retiraTodosCarga(UPGRADE_SISTEMA_ARMAS);
        getSistemaArmas2().ativaDisponibilidade();
        return true;
    }
    
    @Override
    public boolean contrataTripulante() throws QuantidadeTripulantesCheiaException{
        if(!getCarga().verificaRecursos(CONTRATAR_MEMBRO_PERDIDO))
            return false;
        getCarga().retiraTodosCarga(CONTRATAR_MEMBRO_PERDIDO);
        super.resgataOficial();
        return true;
    }
    
    @Override
    public String obtemCarga(){
        return getCarga().toString();
    }
    
    @Override
    public int obtemNivelCarga(){
        return getCarga().getNivelCarga();
    }
    
    @Override
    public boolean regeneraDrone(){
        if(!getCarga().verificaRecursos(PREENCHE_PROTECAO))
            return false;
        getCarga().retiraTodosCarga(PREENCHE_PROTECAO);
        super.getDrone().regeneraVida();
        return true;
    }
    
    @Override
    public boolean compraDrone(){
        if(!getCarga().verificaRecursos(COMPRA_DRONE))
            return false;
        getCarga().retiraTodosCarga(COMPRA_DRONE);
        super.novoDrone();
        return true;
    }
    
    @Override
    public int getNumArtefactos(){
        return getCarga().getArtefactosTam();
    }
        
    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
//        sb.append("\n##Nave Espacial Militar##\n");
        sb.append("Com ").append(super.getOficiaisTam()).append(" oficiais\n");
        sb.append(getSistemaArmas1().toString()).append("\n");
        sb.append(getSistemaArmas2().toString()).append("\n");
        sb.append(getSistemaEscudo().toString()).append("\n");
        sb.append(getCombustivel().toString()).append("\n").append("\n");
//        sb.append("-- Carga --\n");
//        sb.append(getCarga().toString()).append("\n");
//        sb.append("-----------");
        return sb.toString();
    }

    
}
