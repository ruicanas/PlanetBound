package planetbound.logica.dados;

import java.io.Serializable;
import planetbound.logica.IConstantes;
import planetbound.logica.dados.Setores.Planetas.*;
import planetbound.logica.dados.utilidade.*;
import planetbound.logica.dados.nave.*;
import planetbound.logica.dados.Setores.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import planetbound.logica.dados.Setores.Recursos.Artefacto;
import planetbound.logica.dados.Setores.Recursos.FabricaRecursos;
import planetbound.logica.dados.Setores.Recursos.Recursos;
import planetbound.logica.dados.aliens.Alien;
import planetbound.logica.dados.aliens.FabricaAliens;
import planetbound.logica.dados.exceptions.ConversaoComRecursosIguaisException;
import planetbound.logica.dados.exceptions.QuantidadeTripulantesCheiaException;
import planetbound.logica.dados.nave.drone.Drone;
import planetbound.logica.dados.nave.tipos.Militar;

/**
 *
 * @author Rui
 */
public class dadosJogo implements IConstantes, Serializable{
    private int turno;
    private Nave nave;
    private Setor setorAtual;
    private Eventos eventos;
    private Dados dados;
    private StringBuilder msgLog;
    
    //
    //Construtor
    //
    public dadosJogo(){
        turno = 1;
        nave = null;
        setorAtual = null;
        dados = new Dados();
        msgLog = new StringBuilder();
        eventos = new Eventos();
    }
    
    //
    //Getters e Setters
    //
    private Nave getNave() {
        return nave;
    }
    
    private Drone getDrone(){
        return getNave().getDrone();
    }
    
    private Alien getAlien(){
        return getPlanetaAtual().getAlien();
    }
    
    private boolean setNave(Nave tipo) {
        if(tipo == null) return false;
        nave = tipo;
        return true;
    }

    private Setor getSetorAtual() {
        return setorAtual;
    }

    private boolean setSetorAtual(Setor setorAtual) {
        if(setorAtual == null) return false;
        this.setorAtual = setorAtual;
        return true;
    }
    
    private Planeta getPlanetaAtual(){
        return getSetorAtual().getPlaneta();
    }
    
    private Dados getDados(){
        return dados;
    }
    
    //
    //Gerador de objetos(Planeta, Setor, Alien e Recurso na superficie de um planeta)
    //
    /**
     * Método utilizado para encontrar um determinado planeta por via de probabilidades.
     * @return um planeta se esse mesmo setor tiver sido gerado com sucesso. 
     * Retorna null caso contrário.
     */
    private Planeta geraPlaneta(){
        List<Planeta> planeta = new ArrayList<>();
        for (String cor : CORES) {
            planeta.add(FabricaPlanetas.criaPlaneta(cor));
        }
        Collections.shuffle(planeta);
        return planeta.get(0);
    }
    
    /**
     * Método utilizado para encontrar um determinado setor por via de probabilidades,
     * e de preferência, para que não existam repetições entre as viagens.
     * @return um setor se esse mesmo setor tiver sido gerado com sucesso. 
     * Retorna null caso contrário.
     */
    private Setor geraSetor(){
        List<Setor> Setores = new ArrayList<>();
        Planeta planeta = geraPlaneta();
        for (int i = 0; i < SETOR; i++) {                   //Gera setores para retirar aleatoriamente
            if(i < PROB_SETORVERM) Setores.add(new SetorVermelho(planeta));   //0..2 (3 Setores Vermelhos em 10)
            else Setores.add(new SetorBranco(planeta));          //2..9 (7 Setores Brancos em 10)
        }
        Collections.shuffle(Setores);
        return Setores.get(0);
    }
    
    private Recursos geraRecurso(){
        List<Recursos> rec = new ArrayList<>();
        for (String cor : CORES){
            rec.add(FabricaRecursos.criaRecursos(cor));
        }
        rec.add(FabricaRecursos.criaRecursos(ARTEFACTO));
        Collections.shuffle(rec);
        return rec.get(0);
    }
    
    private Alien geraAliens(){
        List<Alien> alien = new ArrayList<>();
        for (String cor : CORES){
            alien.add(FabricaAliens.criaAlien(cor));
        }
        Collections.shuffle(alien);
        return alien.get(0);
    }
    
    
    
    //
    //Viagens através de um buraco negro
    //
    /**
     * É feita uma viagem num buraco negro que pode ou não ser feita com sucesso.
     * Esta viagem pode ou não ser feita. Cabe ao utilizador decidir se quer ou não
     * fazer a viagem por esta via
     * @return true se a viagem se efetuar. False caso contrário.
     */
    private boolean fazViagemBuracoNegro(){
        if(getNave().getEscudo()){
            return verificaViagemNave();
        }
        else{
            if(getNave().obtemCombustivel() <= MIN_COMBUSTIVEL_SEM_OFICIAL_BN){
                //Esta alteração é feita para colocar o combustível a 0, uma vez
                //que se a nave não conseguir passar pelo buraco negro, o jogo deve terminar.
                getNave().diminuiCombustivel(getNave().obtemCombustivel()); 
                return false;
            }
            getNave().diminuiCombustivel(COMBUSTIVEL_DIMINUI_BNEGRO_SEM_OFICIAL);
            getNave().diminuiEscudo(ESCUDO_DIMINUI_BNEGRO_SEM_OFICIAL);   
        }
        return true;
    }

    private boolean verificaViagemNave(){
        if(getNave().obtemEscudo() >= MIN_ESCUDO_BN 
                && getNave().obtemCombustivel() > MIN_COMBUSTIVEL_BN){
                getNave().diminuiCombustivel(COMBUSTIVEL_DIMINUI_BNEGRO);
                getNave().diminuiEscudo(ESCUDO_DIMINUI_BNEGRO);
                return true;
        }
        else if(getNave().obtemEscudo() < MIN_ESCUDO_BN 
                && getNave().obtemCombustivel() > MIN_COMBUSTIVEL_BN){
                getNave().morteOficial();
                getNave().diminuiCombustivel(COMBUSTIVEL_DIMINUI_BNEGRO);
                getNave().diminuiEscudo(ESCUDO_DIMINUI_BNEGRO);
                return true;
        }
        else{
            //Esta alteração é feita para colocar o combustível a 0, uma vez
            //que se a nave não conseguir passar pelo buraco negro, o jogo deve terminar.
            getNave().diminuiCombustivel(getNave().obtemCombustivel()); 
            return false;
        }
    }
    
    //
    //Info Acerca do Log
    //
    /**
     * Adicionar informação ao log do jogo.
     * @param msg Mensagem que será adicionada ao log do 
     */
    public void addLog(String msg){
        msgLog.append(msg).append("\n");
    }
    
    /**
     * Mostra o log do jogo ao utilizador. Neste método, vai-se passar toda a informação atual
     * que o log tem para uma string e posteriormente, apaga-se toda a informação que está
     * no StringBuilder.
     * @return o log do jogo em formato String.
     */
    public String mostraLog(){
        String log = msgLog.toString();
        msgLog.delete(0, log.length());
        return log;
    }
    
    //
    //Informação que deve ser mostrada em certos momentos do jogo.
    //
    /**
     * Mostra informação sobre a nave.
     * @return toda a informação que diga respeito à nave!
     */
    public String getInfoNave(){
        return nave.toString();
    }
    /**
     * Mostra a informação sobre um determinado setor que pode ser pertinente para o utilizador.
     * @return a informação do setor se o setor não for null. Caso seja null, quer 
     * dizer que o utilizador está no inicio da viagem e mostra uma frase a informar
     * dessa situação.
     */
    public String getInfoSetor(){
        if(setorAtual == null) addLog("Encontra-se a iniciar a viagem e está "
                + "à deriva no espaço!");
        return setorAtual.toString();
    }
    public boolean setorIsVermelho(){
        return setorAtual instanceof SetorVermelho;
    }
    /**
     * Obtem informação sobre os recursos que a nave tem na carga
     * @return uma string com toda a informação dos recursos.
     */
    public String getInfoCarga(){
        return getNave().obtemCarga();
    }
    /**
     * Mostra o cenário do jogo no modo de exploração
     * @return 
     */
    public String mostraTabuleiro(){
            return getPlanetaAtual().mostraTabuleiro();
    }
    /**
     * Mostra informação sobre o recurso e a vida do drone
     * @return String com essa informação. Pode retornar uma mensagem 
     * de erro.
     */
    public String mostraInfoTabuleiro(){
            return getPlanetaAtual().mostraInfoTabuleiro();
    }
    /**
     * Obtem o combustível atual da nave.
     * @return o combustível atual da nave
     */
    public int getCombustivel(){
        return getNave().obtemCombustivel();
    }
    /**
     * Obtém o capitão do jogo. Se ele morrer, o jogo deve terminar.
     * @return true caso o capitão esteja vivo. False caso se verifique o contrário
     */
    public boolean getCapitao(){
        return getNave().getCapitao();
    }
    /**
     * Obtém os artefactos que o jogador possui. Se chegar aos 5 artefactos, o jogo
     * deve terminar.
     * @return num de artefactos
     */
    public int getArtefactos(){
        // \/ Esta linha de código é útil uma vez que o utilizador pode querer abandonar o jogo
        // \/ sem ainda sequer ter inicializado as variáveis do jogo. Desta forma, vão se evitar 
        // problemas no estado de Game over
        if(getNave() == null) return 0; 
        return getNave().getNumArtefactos();
    }
    /**
     * Obtém o Grupo de Desembarque. Se eles morrerem, não é possível
     * fazer uma exploração no planeta.
     * @return true caso o G.Desembarque esteja vivo. False caso se verifique o contrário.
     */
    public boolean getGrupoDesembarque(){
        return getNave().getGrupoDesembarque();
    }
    /**
     * Verifica se existe um drone ou se o mesmo se encontra destruido.
     * @return true se o drone existir. False caso contrário
     */
    public boolean verificaDrone(){
        return getNave().getDrone() != null;
    }
    /**
     * Informa para o caso de os dois elementos se encontrarem nalguma posição adjacente
     * @return true se estiverem encontrados. False em caso contrário
     */
    public boolean alienEncontraDrone(){
        return getPlanetaAtual().alienEncontraDrone();
    }
    /**
     * Método que verifica se o planeta tem recursos para serem explorados
     * @return true se tiver recursos, false caso contrário
     */
    public boolean planetaComRecursos(){
        return getPlanetaAtual().temRecursos();
    }
    /**
     * Método que verifica se o drone tem recursos para serem adicionados à carga
     * @return true se tiver recursos, false caso contrário
     */
    public boolean droneComRecursos(){
        return getDrone().isMochilaComRecursos();
    }
    /**
     * Método que verifica se o oficial responsável por cargas está vivo
     * @return true se estiver vivo. False em caso contrário
     */
    public boolean getResponsavelCarga(){
        return getNave().getCargueiro();
    }
    public int getTurno(){
        return turno;
    }
    public void aumentaTurno(){
        turno = turno +1;
    }
    
    //Nova informação adicionada relativamente à meta 1 e que acaba por ser necessária
    //para que consiga fazer o PainelExploracao na GUI
    public boolean setorCriado(){
        if(setorAtual != null){
            return true;
        }
        else{
            return false;
        }
    }
    public int getDroneX(){
        if(getPlanetaAtual().getDrone() != null){
            return getDrone().getX();
        }
        return -1;
    }
    public int getDroneY(){
        if(getPlanetaAtual().getDrone() != null){
            return getDrone().getY();
        }
        return -1;
    }
    public int getVidaDrone(){
        if(getDrone() != null){
            return getDrone().getVida();
        }
        return -1;
    }
    public int getAlienX(){
        if(getPlanetaAtual().getAlien() != null){
            return getAlien().getX();
        }
        return -1;
    }
    public int getAlienY(){
        if(getPlanetaAtual().getAlien() != null){
            return getAlien().getY();
        }
        return -1;
    }
    public String getCorAlien(){
        if(getPlanetaAtual().getAlien() != null){
            return getAlien().getCor();
        }
        return null;      
    }
    public int getXResgate(){
        if(getPlanetaAtual().getDrone() != null){
            return getDrone().getxRecolha();
        }
        return -1;
    }
    public int getYResgate(){
        if(getPlanetaAtual().getDrone() != null){
            return getDrone().getyRecolha();
        }
        return -1;
    }
    public int getXRecurso(){
        if(getPlanetaAtual().isRecNoPlaneta()){
            return getPlanetaAtual().getRecursoGerado().getX();
        }   
        return -1;
    }
    public int getYRecurso(){
        if(getPlanetaAtual().isRecNoPlaneta()){
            return getPlanetaAtual().getRecursoGerado().getY();
        }   
        return -1;
    }
    public boolean recursoApanhado(){
        return getPlanetaAtual().isRecNoPlaneta();
    }
    public String getCorRecurso(){
        if(getPlanetaAtual().getCenario() != null){
            return getPlanetaAtual().getCorRecursoGerado();
        }
        return null;
    }
    
    //
    //Opção para escolher uma nave
    //
    /**
     * Escolha da nave espacial que o utilizador deseja.
     * @param opt Escolha do utilizador.
     * @return true se tudo correr bem. False caso contrário.
     */
    public boolean escolhaNave(int opt){
        if(opt == 1) addLog("Nave Militar escolhida");
        else if(opt == 2) addLog("Nave Exploratória escolhida");
        setNave(FabricaNaves.criaNave(opt));
        return getNave() != null;
    }
    public boolean isNaveMilitar(){
        return getNave() instanceof Militar;
    }
    
    //
    //Aplicação de um evento
    //
    /**
     * Responsável por aplicar um evento à nave espacial.
     * @return true se o evento foi bem aplicado. False caso contrário.
     */
    public boolean aplicaEvento(){
        addLog("Você jogou um dado...");
        int i = dados.joga6();
        addLog("Saiu um " + i + ".");
        if(eventos.procuraEvento(i, getNave(), dados)){
            addLog(eventos.devolveMsgLogEventos());
            return true;
        }else{
            return false;
        }       
    }
    
    public boolean aplicaEvento(int i){
        if(eventos.aplicaEvento(i, getNave(), getDados())){
            addLog(eventos.devolveMsgLogEventos());
            return true;
        }else{
            return false;
        }
    }
    
    //
    //Vai gerar um novo setor e que vai conter um "random" planeta
    //
    /**
     * Vai fazer com que se inicie uma viagem ou se efetue uma viagem de 
     * um planeta para outro.
     * @return true se a viagem foi executada com sucesso. False caso contrário
     */
    public boolean descobreNovoSetor(){
        if(setSetorAtual(geraSetor()) );
        return true;
    }
    
    //
    //Efetuar viagens
    //
    /**
     * Efetuar a viagem.
     * @return true se tudo correu bem. False se algo correu mal(por exemplo
     * acabou a gasolina).
     */
    public int fazViagem(){
        if(dados.joga8() == 8){
            addLog("Viagem através de um buraco negro!");
            if(!fazViagemBuracoNegro()){
                return -1;
            }
            else return 2;
        }
        else{
             addLog("Viagem espacial normal");
            getNave().diminuiCombustivel(COMBUSTIVEL_DIMINUI);
            if(getNave().obtemCombustivel() == 0){
                return -1;
            }
            return 1;
        }
    }
    
    /**
     * Verifica a cor da circunferência do setor
     * @return true se o setor for vermelho. False se o setor for branco.
     */
    public boolean verificaSetor(){
        return getSetorAtual() instanceof SetorVermelho;
    }   
    
    //
    //Faz compras na loja
    //
    public boolean melhoraCarga(){
        if(getNave().melhoraCarga()){
            addLog("Carga Melhorada - Nível " + getNave().obtemNivelCarga());
            return true;
        }
        addLog("Falta de recursos ou carga já atingiu o nível máximo");
        return false;
    }
    public boolean converteRecursos(String aConverter, String ConversaoFinal) throws ConversaoComRecursosIguaisException{
        if(aConverter.equalsIgnoreCase(ConversaoFinal)) throw new ConversaoComRecursosIguaisException();
        if(getNave().converterRecursos(aConverter, ConversaoFinal)){
            addLog("Recursos convertidos com sucesso!");
            return true;
        }
        addLog("Falta de recursos");
        return false;
    }
    public boolean compraTripulante() throws QuantidadeTripulantesCheiaException{
        if(getNave().contrataTripulante()){
            addLog("Tripulante " + getNave().getOficiais(getNave().getOficiaisTam()-1) +
                    " contratado com sucesso");
            return true;
        }
        addLog("Falta de recursos");
        return false;
    }
    public boolean melhoraSistemaArmas(){
        if(getNave().melhoraSistemaArmas()){
            addLog("Sistema de Armas evoluido com sucesso");
            return true;
        }
        addLog("Falta de recursos ou sistema já ativo");
        return false;
    }
    public boolean regeneraDrone(){
        if(getNave().regeneraDrone()){
            addLog("A proteção do drone foi regenerada com sucesso");
            return true;
        }
        addLog("Falta de recursos");
        return false;
    }
    public boolean compraDrone(){
        if(getNave().compraDrone()){
            addLog("Foi adquirido um novo drone com sucesso");
            return true;
        }
        addLog("Falta de recursos");
        return false;
    }
    
    //
    //Exploração do planeta
    //
    public boolean gastaCombustivelNaExp(){
        return getNave().diminuiCombustivel(COMBUSTIVEL_DIMINUI);
    }
    /**
     * Vai inicializar a exploração, ou seja, vai enviar todos os elementos que são
     * necessários para se efetuar a exploração para o planeta.
     * @return true se tudo foi enviado com sucesso. False em caso contrário.
     */
    public boolean exploraSuperficie(){
        Recursos rec = geraRecurso();
        Alien alien = geraAliens();
        while(!getPlanetaAtual().recursoExiste(rec)){
            rec = geraRecurso();
        }
        while(alien.getCor().equalsIgnoreCase(rec.getCor())){
            alien = geraAliens();
        }
        if(!getPlanetaAtual().obtemDrone(getNave().getDrone())) return false;
        if(!getPlanetaAtual().obtemRecursoGerado(rec)) return false;
        if(!getPlanetaAtual().obtemAlien(alien)) return false;
        addLog(getPlanetaAtual().obtemLogPlaneta());
        return true;
    }
    /**
     * Responsável por adicionar um novo alien, caso o anterior já tenha morrido.
     * @return true se o alien foi adicionado com sucesso. False caso contrário.
     */
    public boolean addNovoAlien(){
        Alien alien = geraAliens();
        while(alien.getCor().equalsIgnoreCase(getPlanetaAtual().getCorRecursoGerado())){
            alien = geraAliens();
        }
        if(getPlanetaAtual().obtemAlien(alien)){
            getPlanetaAtual().atualizaTabuleiro();
            addLog(getPlanetaAtual().obtemLogPlaneta());
            return true;
        }
        addLog(getPlanetaAtual().obtemLogPlaneta());
        return false;
    }
    
    public boolean droneAtaca(){
        addLog("Você jogou os dados...");
        int n = getDados().joga6();
        if(getAlien().alienMorre(n)){
            addLog("Saiu o número " + n + " no primeiro dado e o alien foi exterminado!");
            getPlanetaAtual().atualizaTabuleiro();
            return true;
        }
        addLog("Saiu o número " + n + " no primeiro dado e o drone falhou o tiro!");
        getPlanetaAtual().atualizaTabuleiro();
        return false;
    }
    public boolean alienAtaca(){
        int n = getDados().joga6();
        if(getAlien().alienAtaca(n)){
            addLog("Saiu o número " + n + " no segundo dado e o drone perdeu vida!");
            getDrone().perdeVida();
            if(getDrone().getVida() == 0){
                getNave().dronePartido();
                getPlanetaAtual().atualizaTabuleiro();
                getPlanetaAtual().finalizaCenario();
                return true;
            }
        }else{
        addLog("Saiu o número " + n + " no segundo dado e o alien falhou o ataque!");
        }
        getPlanetaAtual().atualizaTabuleiro();
        return false;
    }
    
    public boolean droneSaiDoPlaneta(){
        if(getDrone().isPontoDeRecolha() && !getPlanetaAtual().isRecNoPlaneta()){
            addLog("O drone conseguiu fugir do planeta com um recurso!");
            getPlanetaAtual().finalizaCenario();
            return true;
        }
        return false;
    }
    
    //
    //Movimentos do drone
    //
    public boolean moveDroneCima(){
        getNave().droneMovimenta(CIMA);
        if(getDrone().apanhaRecurso(getPlanetaAtual())){
            addLog("O drone apanhou o recurso com sucesso!");
        }
        getPlanetaAtual().avisaAlien();
        getPlanetaAtual().atualizaTabuleiro();
        return true;
    }
    public boolean moveDroneDireita(){
        getNave().droneMovimenta(DIREITA);
        if(getDrone().apanhaRecurso(getPlanetaAtual())){
            addLog("O drone apanhou o recurso com sucesso!");
        }
        getPlanetaAtual().avisaAlien();
        getPlanetaAtual().atualizaTabuleiro();
        return true;
    }
    public boolean moveDroneEsquerda(){
        getNave().droneMovimenta(ESQUERDA);
        if(getDrone().apanhaRecurso(getPlanetaAtual())){
            addLog("O drone apanhou o recurso com sucesso!");
        }
        getPlanetaAtual().avisaAlien();
        getPlanetaAtual().atualizaTabuleiro();
        return true;
    }
    public boolean moveDroneBaixo(){
        getNave().droneMovimenta(BAIXO);
        if(getDrone().apanhaRecurso(getPlanetaAtual())){
            addLog("O drone apanhou o recurso com sucesso!");
        }
        getPlanetaAtual().avisaAlien();
        getPlanetaAtual().atualizaTabuleiro();
        return true;
    }
    
    //
    //Drone retorna à nave 
    //
    public boolean cargaObtemRecursos(){
        int n;
        if(!getDrone().isMochilaComRecursos()) return false;
        Recursos rec = getDrone().devolveRecurso();
        if(rec instanceof Artefacto){
            getNave().adicionaCarga(rec);
            addLog("Foi explorado e adicionado um artefacto à nave!");
        }
        else{
            n = getDados().joga6();
            addLog("Você jogou o dado.");
            addLog("Saiu um " + n + "!");
            for (int i = 0; i < n; i++) {
                getNave().adicionaCarga(rec);  
            }
            if(n == 1)
                addLog("Foi explorado e adicionado " + n + " recurso " + rec.getCor() + " à nave!");
            else if(n > 1)
                addLog("Foram explorados e adicionados " + n + " recursos " + rec.getCor() + " à nave!");
            }
        return true;
    }
    
    //
    //Converter recursos
    //
    public boolean converteCombustivel(){
        if(getNave().aumentaCombustivel()){
            addLog("A conversão ocorreu com sucesso! Foi adicionado " + AUMENTA_COMBUSTIVEL + ""
                    + " ao combustível da nave!");
            return true;
        }
        addLog("A conversão não foi efetuada por falta de recursos");
        return false;
    }
    public boolean converteEscudo(){
        if(getNave().aumentaEscudo()){
            addLog("A conversão ocorreu com sucesso! Foi adicionado " + AUMENTA_ESCUDO + ""
                    + " ao sistem de proteção da nave!");
            return true;
        }
        addLog("A conversão não foi efetuada por falta de recursos");
        return false;
    }
    public boolean converteSistemaArmas(){
        if(getNave().aumentaSistemaArmas()){
            addLog("A conversão ocorreu com sucesso! Foi adicionado " + AUMENTA_SA + ""
                    + " ao sistema de armas da nave!");
            return true;
        }
        addLog("A conversão não foi efetuada por falta de recursos");
        return false;
    }
    
    
    //
    //Game Over e Reset Dados
    //
    /**
     * Verifica se o jogo terminou seja porque tenha ficado sem o capitão
     * ou seja por ter ficado sem combustível.
     * Caso o jogador queira logo sair do jogo quando estiver na seleção de naves
     * uma vez que as naves ainda não estão construidas e para que se possa
     * informar devidamente o jogador do motivo de ter ido para o estado de GameOver
     * retornamos este método como false.
     * @return true, se o jogo tiver chegado ao fim
     */
    public final boolean isGameOver(){
        if(nave == null) return false;
        return !getCapitao() || getCombustivel() <= 0 || getArtefactos() == MAX_ARTEFACTOS;
    }
    
    public final boolean isJogoVencido(){
        return getArtefactos() == MAX_ARTEFACTOS;
    }
    
    /**
     * Método para da reset aos dados e os colocar tudo predefinido. Essencial
     * para que se possa realizar um novo jogo caso a partida atual tenha terminado
     * por alguma razão.
     */
    public void resetDados(){
        nave = null;
        setorAtual = null;
        dados = new Dados();
        msgLog = new StringBuilder();
        eventos = new Eventos();
        turno = 1;
    }
    
}
