package planetbound.logica.estados;
import planetbound.logica.dados.dadosJogo;

public class EstadoAdapter implements IEstado {
    private final dadosJogo dados;

    public dadosJogo getDados() {
        return dados;
    }
    
    public EstadoAdapter(dadosJogo dados){
        this.dados = dados;
    }
    
    @Override
    public IEstado comecaJogo() {
        return this;
    }

    @Override
    public IEstado selecionaNave(int opt) {
        return this;
    }

    @Override
    public IEstado gameOver() {
        return this;
    }

    @Override
    public IEstado fazMovimento() {
        return this;
    }

    @Override
    public IEstado determinaEvento() {
        return this;
    }

    @Override
    public IEstado acessaLoja() {
        return this;
    }

    @Override
    public IEstado fazCompras(int opt) {
        return this;
    }

    @Override
    public IEstado voltaAoMenuAnterior() {
        return this;
    }

    @Override
    public IEstado escolheRecursos(String aConverter, String Convertido) {
        return this;
    }

    @Override
    public IEstado utilizadorTermina() {
        return this;
    }

    @Override
    public IEstado voltaJogar() {
        return this;
    }

    @Override
    public IEstado fazExploracao() {
        return this;
    }

    @Override
    public IEstado moveDrone(int mov) {
        return this;
    }

    @Override
    public IEstado addRecursos() {
        return this;
    }

    @Override
    public IEstado converteRecursosExplorados(int opt){
        return this;
    }
    
    @Override
    public IEstado terminaTurno() {
        return this;
    }

    @Override
    public IEstado aplicaEvento(int i) {
        return this;
    }
    
}
