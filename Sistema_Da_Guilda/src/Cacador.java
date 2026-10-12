public abstract class Cacador implements DesignadoParaMissao{
    protected String nome;
    protected int nivel;
    protected Arma arma;

    public Cacador(String nome, int nivel, Arma arma) {
        this.nome = nome.trim().toUpperCase();
        this.nivel = nivel;
        this.arma = arma;
    }

    public String getNome() {
        return nome;
    }
    public int getNivel() {
        return nivel;
    }

    public abstract double atacar();
}
