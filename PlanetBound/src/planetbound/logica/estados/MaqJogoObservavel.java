/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package planetbound.logica.estados;

import java.beans.PropertyChangeSupport;
import java.io.File;
import planetbound.logica.dados.utilidade.FileUtil;

/**
 *
 * @author Rui
 */
public class MaqJogoObservavel extends PropertyChangeSupport{
    private MaqJogo maqJogo;

    public MaqJogoObservavel(MaqJogo maqJogo) {
        super(maqJogo);
        this.maqJogo = maqJogo;
    }
    
    //Métodos da Interface
    public void comecaJogo() {
        maqJogo.comecaJogo();
        
        atualizaPainel();
    }
    public void gameOver() {
        maqJogo.gameOver();
    }
    public void utilizadorTermina() {
        maqJogo.utilizadorTermina();
        maqJogo.gameOver();
        
        atualizaPainel();
    }
    public void voltaJogar() {
        maqJogo.voltaJogar();
        
        atualizaPainel();
    }
    public void selecionaNave(int opt) {
        maqJogo.selecionaNave(opt);
        
        atualizaMovimento();
        atualizaPainel();
    }
    public void fazMovimento(int tipo) {
        maqJogo.fazMovimento();
        
        if(tipo == 1){
            atualizaOrbita();
            atualizaPainel();
        }
        else if(tipo == 2){
            //Só atualiza o INFO_PRE_EXP se o drone tiver vida
            if(maqJogo.getVidaDrone() > 0){
                atualizaPreExploracao();
            }
            atualizaOrbita();
            atualizaPainel();
        }
        
        
    }
    public void determinaEvento() {
        maqJogo.determinaEvento();
        
        atualizaOrbita();
        atualizaPainel();
    }
    public void aplicaEvento(int i) {
        maqJogo.aplicaEvento(i);
    }
    
    public void acessaLoja() {
        maqJogo.acessaLoja();
        
        atualizaLoja();
        atualizaPainel();
    }
    public void fazCompras(int opt) {
        maqJogo.fazCompras(opt);
        
        if(opt == 2){
            atualizaPainel();
        }
        atualizaLoja();
    }
    public void voltaAoMenuAnterior() {
        maqJogo.voltaAoMenuAnterior();
        
        atualizaOrbita();
        atualizaPainel();
    }
    public void escolheRecursos(String aConverter, String Convertido) {
        maqJogo.escolheRecursos(aConverter, Convertido);
        
        atualizaLoja();
        atualizaConvRecurso();
        atualizaPainel();
    }
    public void fazExploracao() {
        maqJogo.fazExploracao();
        
        atualizaPreExploracao();
        atualizaExploracao();
        atualizaPainel();
    }
    public void moveDrone(int i) {
        maqJogo.moveDrone(i);
        
        if(maqJogo.getVidaDrone() > 0){
            atualizaExploracao();
            atualizaPreExploracao();
            atualizaPainel();
        }
        else{
            atualizaOrbita();
            atualizaPainel();
        }
    }
    public void addRecursos() {
        maqJogo.addRecursos();
        
        atualizaPreExploracao();
    }
    public void converteRecursosExplorados(int opt) {
        maqJogo.converteRecursosExplorados(opt);
        
        atualizaFinalTurno();
    }
    public void terminaTurno() {
        maqJogo.terminaTurno();
        
        atualizaMovimento();
        atualizaFinalTurno();
        atualizaPainel();
    }
    //
    
    //Métodos de informações do jogo
    public String mostraNave() {
        return maqJogo.mostraNave();
    }
    public String mostraInfoSetor() {
        return maqJogo.mostraInfoSetor();
    }
    public String mostraCarga() {
        return maqJogo.mostraCarga();
    }
    public String mostraTabuleiro() {
        return maqJogo.mostraTabuleiro();
    }
    public String mostraInfoTabuleiro() {
        return maqJogo.mostraInfoTabuleiro();
    }
    public String mostraLog() {
        return maqJogo.mostraLog();
    }
    public int mostraTurno() {
        return maqJogo.mostraTurno();
    }
    public boolean isGameOver() {
        return maqJogo.isGameOver();
    }
    public boolean isDroneALutar() {
        return maqJogo.isDroneALutar();
    }
    public boolean droneComRecursos() {
        return maqJogo.droneComRecursos();
    }
    public boolean planetaComRecursos() {
        return maqJogo.planetaComRecursos();
    }
    public boolean setorIsVermelho() {
        return maqJogo.setorIsVermelho();
    }
    public boolean isNaveMilitar() {
        return maqJogo.isNaveMilitar();
    }
    public int getDroneX(){
        return maqJogo.getDroneX();
    }
    public int getDroneY(){
        return maqJogo.getDroneY();
    }
    public int getVidaDrone(){
        return maqJogo.getVidaDrone();
    }
    public int getAlienX(){
        return maqJogo.getAlienX();
    }
    public int getAlienY(){
        return maqJogo.getAlienY();
    }
    public String getCorAlien(){
        return maqJogo.getCorAlien();
    }
    public int getXResgate(){
        return maqJogo.getXResgate();
    }
    public int getYResgate(){
        return maqJogo.getYResgate();
    }
    public int getXRecurso(){
        return maqJogo.getXRecurso();
    }
    public int getYRecurso(){
        return maqJogo.getYRecurso();
    }
    public boolean recursoApanhado(){
        return maqJogo.recursoApanhado();
    }
    public String getCorRecurso(){
        return maqJogo.getCorRecurso();
    }
    public final boolean isJogoVencido(){
        return maqJogo.isJogoVencido();
    }
    public boolean setorCriado(){
        return maqJogo.setorCriado();
    }
    //

    public MaqJogo.EstadosDoJogo informaEstados() {
        return maqJogo.informaEstados();
    }
    
    public void save(File file) {
        FileUtil.save(file, maqJogo);
    }

    public void load(File file) {
        Object obj = FileUtil.load(file);
        if(obj == null || !(obj instanceof MaqJogo)){
            return;
        }
        this.maqJogo = (MaqJogo)obj;
        atualiza();
    }
    
    public void atualiza(){
        switch (informaEstados()) {
                case esperaMov:
                    atualizaMovimento();
                    break;
                case decideOrbita:
                    atualizaOrbita();
                    break;
                case aguardaLoja:
                    atualizaLoja();
                    break;
                case escolheRecursos:
                    atualizaConvRecurso();
                    break;
                case aguardaExploracao:
                    atualizaPreExploracao();
                    break;
                case aguardaRecolha:
                    atualizaExploracao();
                    break;
                case aguardaConvers:
                    atualizaFinalTurno();
                    break;
            }
        atualizaPainel();
    }
    
    public void atualizaPainel(){
        firePropertyChange("MUDAPAINEL", null, null);
    }
    
    public void atualizaMovimento(){
        firePropertyChange("ATUALIZA_INFONAVE1", null, null);
    }
    
    public void atualizaOrbita(){
        firePropertyChange("ATUALIZA_INFONAVE2", null, null);
    }
    
    public void atualizaLoja(){
        firePropertyChange("ATUALIZA_INFOLOJA", null, null);
    }
    
    public void atualizaConvRecurso(){
        firePropertyChange("ATUALIZA_INFORECURSOS", null, null);
    }
    
    public void atualizaPreExploracao(){
        firePropertyChange("ATUALIZA_INFOPRE_EXP", null, null);
    }
    
    public void atualizaExploracao(){
        firePropertyChange("ATUALIZA_EXP", null, null);
    }
    
    public void atualizaFinalTurno(){
        firePropertyChange("ATUALIZA_FIM", null, null);
    }
    
}
