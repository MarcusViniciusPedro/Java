abstract class Nave implements Analisavel {
    protected String nome;
    private int idRegistro; // Encapsulado (private)

    // Construtor
    public Nave(String nome, int idRegistro) {
        // Uso de API String (Deixa o nome sempre maiúsculo e sem espaços sobrando)
        this.nome = nome.trim().toUpperCase();
        this.idRegistro = idRegistro;
    }

    public int getIdRegistro() {
        return idRegistro;
    }

    // Método Abstrato (as filhas são obrigadas a implementar)
    public abstract void viajar();
}
