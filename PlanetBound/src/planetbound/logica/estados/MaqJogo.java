package planetbound.logica.estados;

import java.io.Serializable;
import planetbound.logica.dados.dadosJogo;

public class MaqJogo implements Serializable{
    private IEstado estado;
    private final dadosJogo dados;
    
    /**
     * Construtor da Máquina de Estados
     */
    public MaqJogo() {
        dados = new dadosJogo();
        estado = new AguardaInicio(dados);
    }
    
    private dadosJogo getDados() {
        return dados;
    }
    private IEstado getEstado(){
        return estado;
    }
    private void setEstado(IEstado estado){
        this.estado = estado;
    }
    
    //Métodos da Interface
    public void comecaJogo(){
        setEstado(getEstado().comecaJogo());
    }
    public void gameOver(){
        setEstado(getEstado().gameOver());
    }
    public void utilizadorTermina(){
        setEstado(getEstado().utilizadorTermina());
    }
    public void voltaJogar(){
        setEstado(getEstado().voltaJogar());
    }
    public void selecionaNave(int opt){
        setEstado(getEstado().selecionaNave(opt));
    }
    public void fazMovimento(){
        setEstado(getEstado().fazMovimento());
    }
    public void determinaEvento(){
        setEstado(getEstado().determinaEvento());
    }
    public void aplicaEvento(int i){
        setEstado(getEstado().aplicaEvento(i));
    }
    public void acessaLoja(){
        setEstado(getEstado().acessaLoja());
    }
    public void fazCompras(int opt){
        setEstado(getEstado().fazCompras(opt));
    }
    public void voltaAoMenuAnterior(){
        setEstado(getEstado().voltaAoMenuAnterior());
    }
    public void escolheRecursos(String aConverter, String Convertido){
        setEstado(getEstado().escolheRecursos(aConverter, Convertido));
    }
    public void fazExploracao(){
        setEstado(getEstado().fazExploracao());
    }
    public void moveDrone(int i){
        setEstado(getEstado().moveDrone(i));
    }
    public void addRecursos(){
        setEstado(getEstado().addRecursos());
    }
    public void converteRecursosExplorados(int opt){
        setEstado(getEstado().converteRecursosExplorados(opt));
    }
    public void terminaTurno(){
        setEstado(getEstado().terminaTurno());
    }
    //
    
    //Métodos de informações do jogo
    public String mostraNave(){
        return getDados().getInfoNave();
    }
    public String mostraInfoSetor(){
        return getDados().getInfoSetor();
    }
    public String mostraCarga(){
        return getDados().getInfoCarga();
    }
    public String mostraTabuleiro(){
        return getDados().mostraTabuleiro();
    }
    public String mostraInfoTabuleiro(){
        return getDados().mostraInfoTabuleiro();
    }
    public String mostraLog(){
        return getDados().mostraLog();
    }
    public int mostraTurno(){
        return getDados().getTurno();
    }
    public boolean isGameOver(){
        return getDados().isGameOver();
    }
    public boolean isDroneALutar(){
        return getDados().alienEncontraDrone();
    }
    public boolean droneComRecursos(){
        return getDados().droneComRecursos();
    }
    public boolean planetaComRecursos(){
        return getDados().planetaComRecursos();
    }
    public boolean setorIsVermelho(){
        return getDados().setorIsVermelho();
    }
    public boolean isNaveMilitar(){
        return getDados().isNaveMilitar();
    }
    public int getDroneX(){
        return getDados().getDroneX();
    }
    public int getDroneY(){
        return getDados().getDroneY();
    }
    public int getVidaDrone(){
        return getDados().getVidaDrone();
    }
    public int getAlienX(){
        return getDados().getAlienX();
    }
    public int getAlienY(){
        return getDados().getAlienY();
    }
    public String getCorAlien(){
        return getDados().getCorAlien();
    }
    public int getXResgate(){
        return getDados().getXResgate();
    }
    public int getYResgate(){
        return getDados().getYResgate();
    }
    public int getXRecurso(){
        return getDados().getXRecurso();
    }
    public int getYRecurso(){
        return getDados().getYRecurso();
    }
    public boolean recursoApanhado(){
        return getDados().recursoApanhado();
    }
    public String getCorRecurso(){
        return getDados().getCorRecurso();
    }
    public final boolean isJogoVencido(){
        return getDados().isJogoVencido();
    }
    public boolean setorCriado(){
        return getDados().setorCriado();
    }
    //
    
    //Obtenção dos Estados - Importante para a UI
    public enum EstadosDoJogo{
        inicio, escolhaNave, esperaMov, ocorreEvento, decideOrbita, 
        aguardaLoja, escolheRecursos, gameOver, aguardaExploracao,
        aguardaRecolha, aguardaConvers;
    }
    public EstadosDoJogo informaEstados(){
        IEstado estadoInfo = getEstado();
        
        if(estadoInfo instanceof AguardaInicio){
            return EstadosDoJogo.inicio;
        } 
        else if(estadoInfo instanceof AguardaEscolhaNave){
            return EstadosDoJogo.escolhaNave;
        }else if(estadoInfo instanceof AguardaMovimento){
            return EstadosDoJogo.esperaMov;
        }else if(estadoInfo instanceof ocorreEvento){
            return EstadosDoJogo.ocorreEvento;
        }else if(estadoInfo instanceof AguardaOrbita){
            return EstadosDoJogo.decideOrbita;
        }else if(estadoInfo instanceof AguardaLoja){
            return EstadosDoJogo.aguardaLoja;
        }else if(estadoInfo instanceof AguardaEscolhaRecursos){
            return EstadosDoJogo.escolheRecursos;
        }else if(estadoInfo instanceof AguardaExploracao){
            return EstadosDoJogo.aguardaExploracao;
        }else if(estadoInfo instanceof AguardaRecolha){
            return EstadosDoJogo.aguardaRecolha;
        }else if(estadoInfo instanceof AguardaConversao){
            return EstadosDoJogo.aguardaConvers;
        }
            return EstadosDoJogo.gameOver;
    }
    
    
}
