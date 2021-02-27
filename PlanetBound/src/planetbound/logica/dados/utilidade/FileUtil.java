/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package planetbound.logica.dados.utilidade;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Rui
 */
public class FileUtil {
    
    /**
     * Guardar 
     * @param file - Ficheiro recbido
     * @param maquina - Objeto recebido
     */
    public static void save (File file, Object maquina){
        ObjectOutputStream out = null;
        
        try {
            out = new ObjectOutputStream(new FileOutputStream(file, false));
            out.writeObject(maquina);   //Escreve o objeto
            out.close();                //Fecha o fileOutputStream
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FileUtil.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(FileUtil.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            try{
                out.close();
            }catch(IOException ex){
                Logger.getLogger(FileUtil.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public static Object load (File file){
        ObjectInputStream in = null;
        
        try {
            in = new ObjectInputStream(new FileInputStream(file));
            Object obj = in.readObject();   //Escreve o objeto
            in.close();                //Fecha o fileOutputStream
            return obj;
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FileUtil.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(FileUtil.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            try{
                in.close();
            }catch(IOException ex){
                Logger.getLogger(FileUtil.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }
}
