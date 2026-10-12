public class Arqueiro extends Cacador{
    private int flechas;

    public Arqueiro (String nome, int nivel, Arma arma, int flechas){
        super(nome, nivel, arma);
        this.flechas = flechas;
    }

    @Override
    public double atacar() {
        if (flechas <= 0) {
            System.out.println(nome + " está sem flechas!");
            return 0;
        }
        flechas--;
        double dano = arma.getDanoBase() + (nivel * 1.5);
        double chanceCritico = Math.random();

        if (chanceCritico > 0.8) {
            System.out.println("Acerto crítico! Dano de " + nome + " foi dobrado para: " + dano);
            dano *= 2;
        }
        return dano;
    }

    @Override
    public void iniciarMissao (int nivelMissao) throws MissaoInvalidaException {
        if (nivelMissao > nivel + 5){
            throw new MissaoInvalidaException("A missão de nível " + nivelMissao);
        }
        System.out.println(nome + "(Arqueiro) iniciou a missão furtivamente.");
    }
}