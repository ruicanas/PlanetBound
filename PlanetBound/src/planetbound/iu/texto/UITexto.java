/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package planetbound.iu.texto;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;
import static planetbound.logica.IConstantes.SAI;
import planetbound.logica.estados.MaqJogo;

/**
 * Interface de texto que tem como objetivo ir testando todos os métodos do jogo
 * @author Rui
 */
public class UITexto {
    MaqJogo jogo;
    boolean end;

    public UITexto(MaqJogo jogo) {
        this.jogo = jogo;
        end = false;
    }
    
    private void leLog(){
        System.out.println(jogo.mostraLog());
    }
    
    private int leInt() {
        Scanner sc = new Scanner(System.in);
        try {
            int num = sc.nextInt();
            return num;
        } catch (InputMismatchException ex) {
            System.out.println("[ERRO] Deve introduzir um numero inteiro!");
            return -1;
        }
    }

    private String leString() {
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        return str;
    }

    public void corre() {
        while(!end){
            leLog();
            switch (jogo.informaEstados()) {
                case inicio: 
                    uiInicio();
                    break;
                case escolhaNave: 
                    uiEscolhaNave();
                    break;
                case esperaMov:
                    uiAguardaMov();
                    break;
                case ocorreEvento:
                    uiOcorreEvento();
                    break;
                case decideOrbita:
                    uiDecisaoOrbita();
                    break;
                case aguardaLoja:
                    uiAguardaLoja();
                    break;
                case escolheRecursos:
                    uiEscolheRecurso();
                    break;
                case aguardaExploracao:
                    uiExplora();
                    break;
                case aguardaRecolha:
                    uiRecolhaRecurso();
                    break;
                case aguardaConvers:
                    uiConversaoRecursos();
                    break;
                case gameOver:
                    uiGameOver();
                    break;
            }
        }
    }
    
    public String mostraNave(){
        return jogo.mostraNave();
    }
    
    public String mostraTurno(){
        return "Turno: " + jogo.mostraTurno();
    }

    //Métodos necessários para correr o programa
    private void uiInicio() {
        System.out.println("Bem-Vindo ao Planet Bound");
        System.out.println("1 -> Começar o jogo\n2 -> Sair do Jogo");
        System.out.print(">> ");
        int opt = leInt();
        switch(opt){
            case 1: jogo.comecaJogo();
                    break;
            case 2: end = true;
        }
    }
    
    private String optEscolhaNave(){
        StringBuilder sb = new StringBuilder();
        sb.append("1 - Nave Militar\n");
        sb.append("2 - Nave Exploratória\n");
        sb.append("3 - Sair");
        return sb.toString();
    }
    private void uiEscolhaNave() {
        System.out.println("\n##Escolha de Naves##");
        System.out.println(optEscolhaNave());
        System.out.print(">> ");
        int opt = leInt();
        switch(opt){
            case 1: jogo.selecionaNave(1);
                    break;
            case 2: jogo.selecionaNave(2);
                    break;
            case 3: jogo.utilizadorTermina();
                    break;
        }
    }
    
    private String optAguardaMov(){
        StringBuilder sb = new StringBuilder();
        sb.append("1 - Viaja para um planeta\n");
        sb.append("2 - Sair");
        return sb.toString();
    }
    private void uiAguardaMov(){
        System.out.println(mostraNave());
        System.out.println("\n##Aguarda Movimento##");
        System.out.println(mostraTurno());
        System.out.println(optAguardaMov());
        System.out.print(">> ");
        int opt = leInt();
        switch(opt){
            case 1: jogo.fazMovimento();
                    break;
            case 2: jogo.utilizadorTermina();
                    break;
        }
    }
    
    private String optOcorreEvento(){
        StringBuilder sb = new StringBuilder();
        sb.append("1 - Jogar um dado e determinar evento\n");
        sb.append("2 - Aplicar um evento");
        return sb.toString();
    }
    private int escolhaEvento(){
        System.out.println("Insira um número de 1 a 6");
        int i = leInt();
        return i;
    }
    private void uiOcorreEvento(){
        System.out.println(mostraNave());
        System.out.println("\n##Ocorre Evento##");
        System.out.println(mostraTurno());
        System.out.println(optOcorreEvento());
        System.out.print(">> ");
        int opt = leInt();
        switch(opt){
            case 1: jogo.determinaEvento();
                    break;
            case 2: jogo.aplicaEvento(escolhaEvento());
                    break;
        }
    }
    
    private String optDecisaoOrbita(){
        StringBuilder sb = new StringBuilder();
        sb.append("1 - Explorar o planeta\n");
        if(jogo.setorIsVermelho()){
            sb.append("2 - Ir à Space Station(LOJA)\n");
            sb.append("3 - Terminar turno\n");
            sb.append("4 - Sair");
        }
        else{
            sb.append("2 - Terminar turno\n");
            sb.append("3 - Sair");
        }
        return sb.toString();
    }
    private void uiDecisaoOrbita() {
        System.out.println(mostraNave());
        System.out.println("\n##Aguarda Decisão num Planeta##");
        System.out.println(jogo.mostraInfoSetor());
        System.out.println(mostraTurno());
        System.out.println(optDecisaoOrbita());
        System.out.print(">> ");
        int opt = leInt();
        if(jogo.setorIsVermelho()){
            switch(opt){
                case 1: jogo.fazMovimento();
                        break;
                case 2: jogo.acessaLoja();
                        break;
                case 3: jogo.terminaTurno();
                        break;
                case 4: jogo.utilizadorTermina();
                        break;
            }
        }
        else{
            switch(opt){
                case 1: jogo.fazMovimento();
                        break;
                case 2: jogo.terminaTurno();
                        break;
                case 3: jogo.utilizadorTermina();
                        break;
                case 4: System.out.println(jogo.mostraNave());
                        break;
            }
        }
    }
        
    private String optAguardaLoja(){
        StringBuilder sb = new StringBuilder();
        sb.append("1 - Melhor o nível da carga\n");
        sb.append("2 - Converte recursos\n");
        sb.append("3 - Contrata um membro perdido\n");
        if(jogo.isNaveMilitar()){
            sb.append("4 - Evolui o sistema de armas\n");
            sb.append("5 - Regenera o drone\n");
            sb.append("6 - Compra um drone\n");
            sb.append("7 - Voltar para a orbita do planeta\n");
        }else{
            sb.append("4 - Regenera o drone\n");
            sb.append("5 - Compra um drone\n");
            sb.append("6 - Voltar para a orbita do planeta\n");
        }
        return sb.toString();
    }
    private void uiAguardaLoja() {
        System.out.println("\n--> Space Station <--");
        System.out.println(optAguardaLoja());
        System.out.print(">> ");
        int opt = leInt();
        if(jogo.isNaveMilitar()){
            if(opt < 7) jogo.fazCompras(opt);
            else if(opt == 7) jogo.voltaAoMenuAnterior();
        }
        else{
            if(opt < 6) jogo.fazCompras(opt);
            else if(opt == 6) jogo.voltaAoMenuAnterior();
        }
    }
    
    private String infoCarga(){
        StringBuilder sb = new StringBuilder();
        sb.append("-- Carga --\n");
        sb.append(jogo.mostraCarga()).append("\n");
        sb.append("-----------\n");
        return sb.toString();
    }
    private String optEscolheRecurso(){
        StringBuilder sb = new StringBuilder();
        sb.append("1 - AZUL\n");
        sb.append("2 - NEGRO\n");
        sb.append("3 - VERMELHO\n");
        sb.append("4 - VERDE\n");
        sb.append("5 - Voltar ao menu anterior");
        return sb.toString();
    }
    private String optEscolheConversor(int i){
        String str = null;
        int opt = 0;
        if(i==0) System.out.println("Escolha uma cor que quer converter\n");
        else System.out.println("Escolha uma cor que quer convertida\n");
        System.out.print(">> ");
        while(opt > 5 || opt <= 0){
            opt = leInt();
            switch(opt){
                        case 1: str = "AZUL";
                                break;
                        case 2: str = "NEGRO";
                                break;
                        case 3: str = "VERMELHO";
                                break;
                        case 4: str = "VERDE";
                                break;
                        case 5: jogo.voltaAoMenuAnterior();
                                str = SAI;
                                break;
            }
        }
        return str;
    }
    private void uiEscolheRecurso(){
        int i = 0;
        String str1 = null, str2 = null;
        while(i < 2){
            System.out.println(infoCarga());
            System.out.println("\n--> Conversão de Recursos <--");
            System.out.println(optEscolheRecurso());
            if(i==0){
                str1 = optEscolheConversor(i);
            }else{
                str2 = optEscolheConversor(i);
            }
            if(SAI.equals(str2) || SAI.equals(str1)) break;
            i++;
            
        }
        if(!SAI.equals(str2) || !SAI.equals(str1)){
            jogo.escolheRecursos(str1,str2);
        }
    }
    
    private String avisaRecursosNoPlaneta(){
        StringBuilder sb = new StringBuilder();
        sb.append("ATENÇÃO: Existem recursos para ser explorados no planeta!");
        return sb.toString();
    }
    private String avisaRecursos(){
        StringBuilder sb = new StringBuilder();
        sb.append("ATENÇÃO: Existem recursos para serem adicionados à carga!");
        return sb.toString();
    }
    private String optUiExplora(){
        StringBuilder sb = new StringBuilder();
        sb.append("1 - Envia drone para o planeta\n");
        if(jogo.droneComRecursos()){
            sb.append("2 - Guardar recursos já explorados\n");
        }else{
            sb.append("2 - Voltar para a órbita do planeta\n");
        }
        return sb.toString();
    }
    private void uiExplora(){
        System.out.println(mostraNave());
        System.out.println("\n## Exploração de um planeta ##");
        if(jogo.droneComRecursos()) System.out.println(avisaRecursos());
        if(jogo.planetaComRecursos()) System.out.println(avisaRecursosNoPlaneta());
        System.out.println(optUiExplora());
        int opt = leInt();
        if(!jogo.droneComRecursos()){
            switch(opt){
                case 1: jogo.fazExploracao();
                        break;
                case 2: jogo.voltaAoMenuAnterior();
                        break;
                case 3: jogo.utilizadorTermina();
                        break;
            }
        }else{
            switch(opt){
                case 1: jogo.fazExploracao();
                        break;
                case 2: jogo.addRecursos();
                        break;
                case 3: jogo.utilizadorTermina();
                        break;
            }
        }
    }
    
    private String optLutaEspacial(){
        StringBuilder sb = new StringBuilder();
        sb.append("Foste ao encontro de um alien!\n");
        sb.append("1 - Lutar!\n");
        return sb.toString();
    }    
    private String optUiRecolhaRecurso(){
        StringBuilder sb = new StringBuilder();
        sb.append("1 - Cima\n");
        sb.append("2 - Direita\n");
        sb.append("3 - Esquerda\n");
        sb.append("4 - Baixo\n");
        return sb.toString();
    }
    private void uiRecolhaRecurso() {
        System.out.println(jogo.mostraTabuleiro());
        System.out.println(jogo.mostraInfoTabuleiro());
        System.out.println("\n## Recolha do Recurso ##");
        if(!jogo.isDroneALutar()){
            System.out.println(optUiRecolhaRecurso());
            int opt = leInt();
            switch(opt){
            case 1: jogo.moveDrone(opt);
                    break;
            case 2: jogo.moveDrone(opt);
                    break;
            case 3: jogo.moveDrone(opt);
                    break;
            case 4: jogo.moveDrone(opt);
                    break;
            }
        }else{
            System.out.println(optLutaEspacial());
            int opt = leInt();
            switch(opt){
            case 1: jogo.moveDrone(opt);
                    break;
            }
        }   
    }
    
    private void qualquerTeclaParaContinuar(){ 
        try
        {
            System.in.read();
        }  
        catch(IOException e)
        {}
     }
    private String optUiConversaoRecursos(){
        StringBuilder sb = new StringBuilder();
        sb.append("1 - Aumentar combustivel\n");
        sb.append("2 - Aumentar Sistema de Armas\n");
        sb.append("3 - Aumentar o Escudo de proteção\n\n");
        sb.append("4 - Retomar a Jornada Espacial\n");
        return sb.toString();
    }
    private void uiConversaoRecursos(){
        System.out.println(mostraNave());
        System.out.println("\n## Final do Turno / Conversão de Recursos ##");
        System.out.println(mostraTurno());
        System.out.println(optUiConversaoRecursos());
        int opt = leInt();
        switch(opt){
        case 1: jogo.converteRecursosExplorados(opt);
                break;
        case 2: jogo.converteRecursosExplorados(opt);
                break;
        case 3: jogo.converteRecursosExplorados(opt);
                break;
        case 4: System.out.println("\t----- A preparar a nave para a viagem espacial... -----");
                System.out.println("\t----- Pressione alguma tecla para continuar. -----");
                qualquerTeclaParaContinuar();
                jogo.terminaTurno();
                break;
        }
    }
    
    private String optGameOver(){
        StringBuilder sb = new StringBuilder();
        sb.append("1 - Voltar a Jogar\n");
        sb.append("2 - Sair do Jogo\n");
        return sb.toString();
    }
    private void uiGameOver(){
        jogo.gameOver();    //Para que possa informar o jogador o motivo pelo qual o mesmo saiu.
        leLog();            //Para que a informação seja disponibilizada no ecrã
        System.out.println(optGameOver());
        int opt = leInt();
        switch(opt){
            case 1: jogo.voltaJogar();
                    break;
            case 2: end = true;
                    break;
        }
    }
}
