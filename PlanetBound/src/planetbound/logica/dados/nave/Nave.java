package planetbound.logica.dados.nave;

import java.io.Serializable;
import planetbound.logica.dados.Setores.Recursos.Recursos;
import planetbound.logica.IConstantes;
import planetbound.logica.dados.exceptions.QuantidadeTripulantesCheiaException;
import planetbound.logica.dados.nave.drone.Drone;


public abstract class Nave implements IConstantes, Serializable{
    private String [] oficiais ;
    private Drone drone;
    
    /**
     * Construtor base da nave espacial. Ao ser criado preenche uma string
     * que contém todos os oficiais(o que é comum, tanto para naves exploratórias
     * como para naves militares).
     */
    public Nave(){
        //Foram utilizadas cores para descrever os oficiais.
        oficiais = TRIPULACAO;
        drone = new Drone();
    }
    
    private String[] getOficiais(){
        return oficiais;
    }
    
    private void setOficiais(String[] oficiais){
        this.oficiais = oficiais;
    }
    
    /**
     * Devolve o nome dos tripulantes (mais usado para efeito de teste)
     * @param i A posição que se pretende obter para retornar o nome
     * @return retorna um nome.
     */
    public String getOficiais(int i){
        if(i >= getOficiais().length) return null;
        return getOficiais()[i];
    }
    
    public Drone getDrone(){
        return drone;
    }
    
    /**
     * Confirma se o capitão ainda está vivo.
     * @return true se o capitão estiver vivo. False caso contrário.
     */
    public boolean getCapitao(){
        return  getOficiais().length >= 1; 
    }
    
    /**
     * Confirma se o oficial de navegação está vivo.
     * @return true se este oficial estiver vivo. False caso contrário.
     */
    public boolean getNavegacao(){
        return  getOficiais().length >= 2; 
    }
    
    /**
     * Confirma se os oficiais do grupo de desembarque estão vivos.
     * @return true se os oficiais estiverem vivos. False caso contrário.
     */
    public boolean getGrupoDesembarque(){
        return  getOficiais().length >= 3; 
    }
    
    /**
     * Confirma se o oficial responsável pelo escudo da nave está vivo.
     * @return true se este oficial estiver vivo. False caso contrário.
     */
    public boolean getEscudo(){
        return  getOficiais().length >= 4;
    }
    
    /**
     * Confirma se o oficial responsável pelas armas está vivo.
     * @return true se este oficial estiver vivo. False caso contrário.
     */
    public boolean getArmeiro(){
        return  getOficiais().length >= 5;
    }
    
    /**
     * Confirma se o oficial responsável pela carga da nave está vivo.
     * @return true se este oficial estiver vivo. False caso contrário.
     */
    public boolean getCargueiro(){
        return  getOficiais().length == 6;
    }
    
    /**
     * Método que informa quantos oficiais se encontram na nave.
     * @return o número de oficiais vivos.
     */
    public int getOficiaisTam(){
        return getOficiais().length;
    }
    
    /**
     * Método responsável por retirar um oficial(matar um oficial)
     * @return true se o oficial tiver sido retirado com sucesso. False
     * caso contrário.
     */
    public boolean morteOficial(){
        int tamAnterior = getOficiaisTam();
        String [] str = new String[getOficiaisTam() - 1];
        System.arraycopy(getOficiais(), 0, str, 0, getOficiaisTam() - 1);
        setOficiais(str);
        return str.length == tamAnterior - 1;
    }
    
    /**
     * Método responsável por resgatar um oficial.
     * @return true se o oficial tiver sido resgatado com sucesso. False
     * caso a tripulação esteja cheia e não haja vaga para a pessoa resgatada
     * @throws planetbound.logica.dados.exceptions.QuantidadeTripulantesCheiaException
     */
    public boolean resgataOficial() throws QuantidadeTripulantesCheiaException{
        int tamAnterior = getOficiaisTam();
        String [] str = new String[getOficiaisTam() + 1];
        if(str.length > 6) throw new QuantidadeTripulantesCheiaException();
        System.arraycopy(getOficiais(), 0, str, 0, getOficiaisTam());
        switch(getOficiaisTam()){
            case 1:str[getOficiaisTam()] = NAVEGACAO;
                    break;
            case 2:str[getOficiaisTam()] = GRUPO_DESEMBARQUE;
                    break;
            case 3:str[getOficiaisTam()] = ESCUDEIRO;
                    break;
            case 4:str[getOficiaisTam()] = ARMEIRO;
                    break;
            case 5:str[getOficiaisTam()] = RESP_CARGA;
                    break;
            case 6: return false;
        }
        setOficiais(str);
        return str.length == tamAnterior + 1;
    }
    
    /**
     * Método que devolve a capacidade atual do sistema de armas
     * @return o número exato de células que o sistema de armas ainda tem.
     */
    abstract public int obtemSistemaArmas();
    
    //Uma vez que só preciso deste método para um dos filhos desta herança
    //Declaro o método sem ser abstract e depois faço-lhe override
    abstract public int obtemSistemaArmas2();
    
    /**
     * Método que retira pontos ao sistema de armas.
     * @param pontos quantidade que se pretende remover dos sistema de armas
     * @return true se for uma valor positivo e maior que 0.
     */
    abstract public boolean diminuiSistemaArmas();
    /**
     * Método que aumenta pontos ao sistema de armas.
     * @param pontos quantidade que se pretende aumentar dos sistema de armas
     * @return true se for uma valor positivo e maior que 0.
     */
    abstract public boolean aumentaSistemaArmas();
    /**
     * Método que devolve a capacidade atual do Escudo
     * @return o número exato de células que o Escudo ainda tem.
     */
    abstract public int obtemEscudo();
    /**
     * Método que retira pontos ao escudo.
     * @param pontos quantidade que se pretende remover dos escudo
     * @return true se for uma valor positivo e maior que 0.
     */
    abstract public boolean diminuiEscudo(int pontos);   
    /**
     * Método que aumenta pontos ao sistema de Escudo.
     * @param pontos quantidade que se pretende aumentar dos sistema do Escudo
     * @return true se for uma valor positivo e maior que 0.
     */
    abstract public boolean aumentaEscudo();    
    /**
     * Método que devolve a capacidade atual do Combustivel
     * @return o número exato de células de Combustivel que ainda tem.
     */
    abstract public int obtemCombustivel();   
    /**
     * Método que retira pontos ao Combustivel.
     * @param pontos quantidade que se pretende remover dos Combustivel
     * @return true se for uma valor positivo e maior que 0.
     */
    abstract public boolean diminuiCombustivel(int pontos);    
    /**
     * Método que aumenta pontos ao Combustivel.
     * @param pontos quantidade que se pretende aumentar de Combustivel
     * @return true se for uma valor positivo e maior que 0.
     */
    abstract public boolean aumentaCombustivel();
    abstract public String obtemCarga();
    abstract public int obtemNivelCarga();
    /**
     * Adiciona um determinado recurso à carga da nave espacial
     * @param rec Recurso que se pretende adicionar
     * @return true se o recurso tiver sido adicionar. False caso não exista
     * espaço para se adicionar este recurso.
     */
    abstract public boolean adicionaCarga(Recursos rec);
    /**
     * Retira um determinado recurso à carga da nave espacial
     * @param rec Recurso que se pretende retirar
     * @return true se o recurso tiver sido retirado. False caso não existam
     * recursos para se remover
     */
    abstract public boolean retiraCarga(Recursos rec);
    /**
     * Obtem o recursos azuis
     * @return número de recursos negros disponiveis
     */
    abstract public int obterRecursosAzuis();
    /**
     * Obtem o recursos vermelhos
     * @return número de recursos negros disponiveis
     */
    abstract public int obterRecursosVermelhos();
    /**
     * Obtem o recursos verdes
     * @return número de recursos negros disponiveis
     */
    abstract public int obterRecursosVerdes();
    /**
     * Obtem o recursos negros
     * @return número de recursos negros disponiveis
     */
    abstract public int obterRecursosNegros(); 
    /**
     * Evolui a capacidade da carga
     * @return true caso tenha evoluido. False em caso contrário
     */
    abstract public boolean melhoraCarga();
    /**
     * Converte um determinado recurso para outro determinado recurso
     * @param aConverter recurso que vai ser convertido 
     * @param Convertido o recurso final que se quer no final da operação
     * @return true caso a operação tenha sucedido. False em caso contrário
     */
    abstract public boolean converterRecursos(String aConverter, String Convertido);
    /**
     * Vai habilitar o segundo sistema de armas da nave
     * @return true se a operação ocorreu com sucesso. Falso em caso contrário
     */
    abstract public boolean melhoraSistemaArmas();
    /**
     * Vai contratar um tripulante que ficou perdido.
     * @return true se a ação acontecer, false em caso contrário
     * @throws planetbound.logica.dados.exceptions.QuantidadeTripulantesCheiaException
     */
    abstract public boolean contrataTripulante() throws QuantidadeTripulantesCheiaException;
    
    //
    //Código que diz respeito ao drone
    //
    abstract public boolean regeneraDrone();
    
    abstract public boolean compraDrone();
    
    protected void novoDrone(){
        drone = new Drone();
    }
    
    public void dronePartido(){
        drone = null;
    }
    
    public boolean droneMovimenta(int opt){
        switch(opt){
            case CIMA: getDrone().moverCima();
                       return true;
            case DIREITA: getDrone().moverDireita();
                       return true;
            case ESQUERDA:getDrone().moverEsquerda();
                       return true;
            case BAIXO:getDrone().moverBaixo();
                       return true;
        }
        return false;
    }
    
    //
    //Artefactos
    //
    abstract public int getNumArtefactos();
    
    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        for (String oficial : getOficiais()) {
            sb.append("-->").append(oficial).append("\n");
        }
        return sb.toString();
    }
}
