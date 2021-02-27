package planetbound.logica.estados;

import java.io.Serializable;
import planetbound.logica.IConstantes;

public interface IEstado extends IConstantes, Serializable{
    IEstado comecaJogo();
    IEstado gameOver();
    IEstado utilizadorTermina();
    IEstado voltaJogar();
    IEstado terminaTurno();
    
    IEstado selecionaNave(int opt);
    
    IEstado determinaEvento();
    IEstado aplicaEvento(int i);
    
    IEstado fazMovimento();
    IEstado fazExploracao();
    IEstado moveDrone(int mov);
    IEstado addRecursos();
    
    IEstado converteRecursosExplorados(int opt);
    
    IEstado voltaAoMenuAnterior();
    
    IEstado acessaLoja();
    IEstado fazCompras(int opt);
    
    IEstado escolheRecursos(String aConverter, String Convertido);
}
