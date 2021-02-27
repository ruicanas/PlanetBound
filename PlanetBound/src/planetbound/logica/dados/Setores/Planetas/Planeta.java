/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package planetbound.logica.dados.Setores.Planetas;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import planetbound.logica.IConstantes;
import planetbound.logica.dados.Setores.Recursos.Recursos;
import planetbound.logica.dados.aliens.Alien;
import planetbound.logica.dados.nave.drone.Drone;
import planetbound.logica.dados.utilidade.Dados;


/**
 *
 * @author Rui
 */
public class Planeta implements IConstantes, Serializable {
    private final Set<Recursos> recursos;
    private Recursos recursoGerado;
    private Drone drone;
    private Alien alien;
    private String [][] cenario;
    private boolean RecNoPlaneta;
    private final StringBuilder logPlaneta;
    private final Dados dados;
    
    /**
     * Construtor de planeta, que vai gerar um planeta com um cor aleatória
     * entre as 4 cores que tem disponíveis.
     */
    public Planeta(){
        recursos = new HashSet<>();
        recursoGerado = null;
        alien = null;
        RecNoPlaneta = true;
        logPlaneta = new StringBuilder();
        dados = new Dados();
        //Inicializa o cenário/terreno/tabauleiro
        cenario = new String[TAM_TERRENO][TAM_TERRENO];
        for (int i = 0; i < TAM_TERRENO; i++) {
            for (int j = 0; j < TAM_TERRENO; j++) {
                cenario[i][j] = " ";
            }
        }
    }
    
    public Drone getDrone() {
        return drone;
    }

    public Alien getAlien() {
        return alien;
    }
    
    public String[][] getCenario(){
        return cenario;
    }
    
    private void setDrone(Drone drone) {
        this.drone = drone;
    }

    private void setAlien(Alien alien) {
        this.alien = alien;
    }
    
    public Recursos getRecursoGerado(){
        return recursoGerado;
    }
    
    private void setRecursoGerado(Recursos rec){
        recursoGerado = rec;
    }
    
    private Dados getDados(){
        return dados;
    }
   
    private Set<Recursos> getRecursos() {
        return recursos;
    }
    
    private void addLogPlaneta(String msg){
        logPlaneta.append(msg).append("\n");
    }
    
    /**
     * Quando um recurso foi explorado do planeta, o mesmo deve ser eliminado da lista de recursos
     * @param rec Recurso que vai ser eliminado
     * @return true se a eliminação correu bem. False em caso contrário.
     */
    private boolean removeRecursoDoPlaneta(Recursos rec){
        return getRecursos().remove(rec);
    }
    
    /**
     * Método para ser utilizado quando um recurso for apanhado
     */
    public void recursoApanhado(){
        RecNoPlaneta = false;
    }
    
    /**
     * Volta a colocar a variável 'RecNoPlaneta' a true, ou seja,
     * volta a existir um recurso na superficie do planeta!
     */
    private void resetRecNoPlaneta(){
        RecNoPlaneta = true;
    }
    
    protected boolean addRecursos(Recursos r){
        return getRecursos().add(r);
    }
    
    //
    // Gera posições dos elementos que participam na exploração (Drone, Recurso e Alien)
    //
    private boolean geraPosDrone(){
        int linha = (int)(Math.random()*TAM_TERRENO); //Gera um número aleatório de 0 a 5
        int coluna = (int)(Math.random()*TAM_TERRENO); //Gera um número aleatório de 0 a 5
        drone.setY(linha);
        drone.setX(coluna);
        drone.definePontoRecolha(coluna, linha);
        cenario[linha][coluna] = drone.toStringCenario();
        return true;
    }
    
    private boolean geraPosRecurso(){
        int linha = (int)(Math.random()*TAM_TERRENO); //Gera um número aleatório de 0 a 5
        int coluna = (int)(Math.random()*TAM_TERRENO); //Gera um número aleatório de 0 a 5
        while(!cenario[linha][coluna].equals(" ")){
            linha = (int)(Math.random()*TAM_TERRENO); //Gera um número aleatório de 0 a 5
            coluna = (int)(Math.random()*TAM_TERRENO); //Gera um número aleatório de 0 a 5
        }
        recursoGerado.setY(linha);
        recursoGerado.setX(coluna);
        cenario[linha][coluna] = recursoGerado.toStringCenario();
        return true;
    }
    
    private boolean geraPosAlien(){
        addLogPlaneta("Os dados vão ser jogados para determinar a posição do Alien...");
        int linha = (int)(Math.random()*TAM_TERRENO); //Gera um número aleatório de 0 a 5
        int coluna = (int)(Math.random()*TAM_TERRENO); //Gera um número aleatório de 0 a 5
        while(!cenario[linha][coluna].equals(" ")){
            addLogPlaneta("A posição (Linha:" + (linha+1) +")(Coluna:"+(coluna+1)+") já se encontra ocupada!");
            addLogPlaneta("Os dados vão voltar a ser jogados para determinar a posição do Alien...");
            linha = (int)(Math.random()*TAM_TERRENO); //Gera um número aleatório de 0 a 5
            coluna = (int)(Math.random()*TAM_TERRENO); //Gera um número aleatório de 0 a 5
        }
        addLogPlaneta("Posição do alien --> (Linha:"+(linha+1)+")(Coluna:"+(coluna+1)+").");
        getAlien().setY(linha);
        getAlien().setX(coluna);
        cenario[linha][coluna] = getAlien().toStringCenario();
        return true;
    }
    
    //
    //Passar informação acerca do tabuleiro para o gameData
    //
    /**
     * Mostra o tabuleiro/cenario/carta ao utilizador
     * @return tabuleiro em formato de string
     */
    public String mostraTabuleiro(){
        StringBuilder sb = new StringBuilder();
        sb.append("//||||| CENARIO |||||\\\\ \n");
        sb.append("||||||||||||||||||||||| \n");
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j++) {
                switch (j) {
                    case 0:
                        sb.append("|||").append(cenario[i][j]).append(" |");
                        break;
                    case 5:
                        sb.append(" ").append(cenario[i][j]).append("|||");
                        break;
                    default:
                        sb.append(cenario[i][j]).append(" |");
                        break;
                }
            }
            sb.append("\n");
        }
        sb.append("|||||||||||||||||||||||\n");
        sb.append("\\\\|||||||||||||||||||//\n");
        return sb.toString();
    }
    
    /**
     * Mostra a cor do recurso, o nome do planeta e a vida do Drone
     * @return String com toda a informação acima
     */
    public String mostraInfoTabuleiro(){
        StringBuilder sb = new StringBuilder();
        sb.append("--------- INFORMAÇÃO ---------\n");
        if(getDrone() != null && getRecursoGerado() != null){
            sb.append("\tPlaneta ").append(this.toString()).append("\n");
            sb.append("\tD (Vida do Drone) - ").append(getDrone().getVida()).append("HP");
            sb.append("\n").append("\tR - ").append(getRecursoGerado().toString());
            sb.append("\n").append("\tX - Ponto de Recolha");
            if (getAlien() != null){
                sb.append("\n").append("\tA - ").append(getAlien().toString());
            }
        }
        else{
            sb.append("Não existe informação para mostrar.");
        }
        return sb.toString();
    }
    
    //
    //Útil para verificar o recursoGerado (verificar se o planeta
    //tem determinado recurso).
    //
    public boolean recursoExiste(Recursos rec){        
        return getRecursos().contains(rec);
    }
    
    //
    // Obtem os elementos que são necessário para que ocorra a exploração.
    //
    public boolean obtemRecursoGerado(Recursos rec){
        recursoGerado = rec;
        removeRecursoDoPlaneta(getRecursoGerado());
        return geraPosRecurso();
    }
    
    public boolean obtemAlien(Alien alien){
        this.alien = alien;
        return geraPosAlien();
    }
    
    public boolean obtemDrone(Drone drone){
        this.drone = drone;
        return geraPosDrone();
    }
    
    //
    //Um aviso recebido pelo gameData quando o Drone faz algum movimento
    //para que o alien se movimente também atrás do Drone.
    //
    /**
     * Após o drone se movimentar, o alien é "avisado" da posição atual do Drone,
     * e vai se deslocando até ele
     * @return true se tudo o movimento do alien foi feito com sucesso.
     * False caso contrário.
     */
    public boolean avisaAlien(){
        if(!alienEncontraDrone())
            return getAlien().movimentoAlien(getDrone().getX(), getDrone().getY());
        return false;
    }
    
    //
    //Métodos necessários para dar informações ao utilizador
    //
    public boolean atualizaTabuleiro(){
        for (int i = 0; i < TAM_TERRENO; i++) {
            for (int j = 0; j < TAM_TERRENO; j++){
                    cenario[i][j] = " ";
            }
        }
        if(isRecNoPlaneta()){
            cenario[getRecursoGerado().getY()][getRecursoGerado().getX()] = "R";
        }
        cenario[getDrone().getyRecolha()][getDrone().getxRecolha()] = "X";
        cenario[getDrone().getY()][getDrone().getX()] = "D";
        cenario[getAlien().getY()][getAlien().getX()] = "A";
        return true;
    }
    
    public String getCorRecursoGerado(){
        return getRecursoGerado().getCor();
    }
    
    public boolean alienEncontraDrone(){
        if(getAlien().getX()+1 < TAM_TERRENO){
            if(getAlien().getX()+1 == getDrone().getX() && getAlien().getY() == getDrone().getY()) 
                return true;
        }
        if(getAlien().getY()+1 < TAM_TERRENO){
            if(getAlien().getX() == getDrone().getX() && getAlien().getY()+1 == getDrone().getY()) 
            return true;
        }
        if(getAlien().getX()-1 >= 0){
            if(getAlien().getX()-1 == getDrone().getX() && getAlien().getY() == getDrone().getY()) 
                return true;
        }
        if(getAlien().getY()-1 >= 0){
            if(getAlien().getX() == getDrone().getX() && getAlien().getY()-1 == getDrone().getY())
            return true;
        }
        return false;
    }
    
    /**
     * Tudo o que se tive passado no planeta deve ser obtido pelo log principal
     * que está nos dados do jogo.
     * @return devolve uma string com o log do planeta atual.
     */
    public String obtemLogPlaneta(){
        String log = logPlaneta.toString();
        logPlaneta.delete(0, log.length());
        return log;
    }
    
    /**
     * Quando deixar de ser necessário ver este cenário, é feito um reset dos dados
     */
    public void finalizaCenario(){
        //Código bastante importante uma vez que, caso o drone seja
        //eliminado e tenha algum recurso ou caso seja eliminado e o recurso esteja por apanhar
        //esse mesmo recurso deve voltar aos recursos disponiveis no planeta e portanto,
        //é o que este pedaço de código vai permitir
        if(getDrone().isMochilaComRecursos() && getDrone().getVida() == 0){
            addRecursos(getDrone().devolveRecurso());
        }
        else if(!getDrone().isMochilaComRecursos() && getDrone().getVida() == 0){
            addRecursos(getRecursoGerado());
        }
        //
        
        setAlien(null);
        setDrone(null);
        resetRecNoPlaneta();
        //Limpa 
        for (int i = 0; i < TAM_TERRENO; i++) {
            for (int j = 0; j < TAM_TERRENO; j++){
                    cenario[i][j] = " ";
            }
        }
    }
    
    /**
     * Verifica se o recurso ainda está no planeta
     * @return true se estiver. False se não estiver.
     */
    public boolean isRecNoPlaneta(){
        return RecNoPlaneta;
    }
    
    /**
     * Verifica se o planeta ainda tem recursos.
     * @return true se tiver recursos. False em caso contrário
     */
    public boolean temRecursos(){
        return !getRecursos().isEmpty();
    }
}