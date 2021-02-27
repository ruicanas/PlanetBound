/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package planetbound.logica.dados.exceptions;

/**
 *
 * @author Rui
 */
public class ConversaoComRecursosIguaisException extends Exception{
    public ConversaoComRecursosIguaisException(){
        super("Conversão de recursos inválida");
    }
}
