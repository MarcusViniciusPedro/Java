import java.util.HashSet;
import java.util.Set;

public abstract class Nave implements Rastreavel {
    private String nome;
    private String CodigoDeRegistro;
    private double qtdCombustivel;
    protected Motor motor;
    private static Set<String> tripulacaoCPFs;
    protected int x;
    protected int y;

    public Nave(String nome, String codigoDeRegistro, double qtdCombustivel, Motor motor) {
        this.nome = nome;
        this.CodigoDeRegistro = codigoDeRegistro;
        this.qtdCombustivel = qtdCombustivel;
        this.motor = motor;
        this.tripulacaoCPFs = new HashSet<>();
        this.x = 0; // Posição inicial padrão
        this.y = 0;
    }

    public String getNome() { return nome; }
    public double getQtdCombustivel() { return qtdCombustivel; }
    public void setQtdCombustivel(double qtdCombustivel) { this.qtdCombustivel = qtdCombustivel; }

    public int getX() { return x; }
    public int getY() { return y; }
    public void setPosicao(int x, int y) {
        this.x = x;
        this.y = y;
    }

    // REGISTRO DE TRIPULANTES
    public void adicionarTripulante(String cpf) {
        if (tripulacaoCPFs.add(cpf)) {
            System.out.println("Tripulante " + cpf + " adicionado à nave " + this.nome);
        } else {
            System.out.println("Tripulante " + cpf + " já está registrado nesta nave.");
        }
    }

    public abstract void viajar(double distancia);

    @Override
    public String obterPosicaoAtual(){
        return "Coordenadas (" + x + ", " + y + ")";
    }

    public void tracarRota (String destino){
        System.out.println("Rota direta para: " + Utilitarios.padronizarPlaneta(destino));
    }

    public void tracarRota (String destino, String parada){
        System.out.println("Rota direta para: " + Utilitarios.padronizarPlaneta(destino) + "com parada em: " +  Utilitarios.padronizarPlaneta(parada));
    }
}
