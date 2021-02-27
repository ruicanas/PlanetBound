/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package planetbound.logica.dados.nave.drone;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import planetbound.logica.IConstantes;
import planetbound.logica.dados.Setores.Planetas.Planeta;
import planetbound.logica.dados.Setores.Recursos.Recursos;

/**
 *
 * @author Rui
 */
public class Drone implements IConstantes, Serializable{
    private int vida;
    private int x;
    private int y;
    private List<Recursos> Mochila;
    private boolean apanhouRecurso;
    private int xRecolha;
    private int yRecolha;
    
    public Drone(){
        vida = VIDA_DRONE;
        x = 0;
        y = 0;
        Mochila = new ArrayList<>();
    }
    
    //
    //Métodos relativos às coordenadas do drone.
    //
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean setX(int x) {
        if(x < 0 || x > 5) return false;
        this.x = x;
        return true;
    }

    public boolean setY(int y) {
        if(y < 0 || y > 5) return false;
        this.y = y;
        return true;
    }
    
    public void definePontoRecolha(int x, int y){
        xRecolha = x;
        yRecolha = y;
    }
    
    public boolean isPontoDeRecolha(){
        return getX() == getxRecolha() && getY() == getyRecolha();
    }

    public int getxRecolha() {
        return xRecolha;
    }

    public int getyRecolha() {
        return yRecolha;
    }
    
    //
    //Método relativos à vida do drone
    //
    public int getVida(){
        return vida;
    }
    
    private boolean setVida(int pontos){
        if(pontos < 0) return false;
        vida = pontos;
        return true;
    }
    
    public boolean regeneraVida(){
        setVida(VIDA_DRONE);
        return true;
    }
    
    public boolean perdeVida(){
        setVida(getVida() - PERDE_VIDA);
        return true;
    }
    
    //
    //Movimentos do Drone
    //
    public boolean moverCima(){
        if(getY() > 0){
            y -= 1;
            return true;
        }
        return false;
    }
    
    public boolean moverBaixo(){
        if(getY() < TAM_TERRENO-1){
            y += 1;
            return true;
        }
        return false;
    }
    
    public boolean moverEsquerda(){
        if(getX() > 0){
            x -= 1;
            return true;
        }
        return false;
    }
    
    public boolean moverDireita(){
        if(getX() < TAM_TERRENO-1){
            x += 1;
            return true;
        }
        return false;
    }
    
    //
    //Drone apanha recursos
    //
    private List<Recursos> getMochila() {
        return Mochila;
    }
    
    public boolean isMochilaComRecursos(){
        return !getMochila().isEmpty();
    }
    
    public boolean isRecursoApanhado(Recursos rec){
        return getMochila().contains(rec);
    }

    public boolean apanhaRecurso(Planeta planeta) {
        if(planeta.getCenario()[getY()][getX()].equalsIgnoreCase(RECURSO_TAB)){
            planeta.recursoApanhado();
            getMochila().add(planeta.getRecursoGerado());
            return true;
        }
        return false;
    }
    
    public Recursos devolveRecurso(){
        if(!getMochila().isEmpty()){
            Recursos rec = getMochila().get(getMochila().size() - 1);
            getMochila().remove(rec);
            return rec;
        }
        else{
            return null;
        }
    }
    
    //
    //Info importante acerca do drone
    //
    public String toStringCenario(){
        return DRONE_TAB;
    }
    
}
