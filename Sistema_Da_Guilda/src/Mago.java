public class Mago extends Cacador{
    private int mana;

    public Mago(String nome, int nivel, Arma arma, int mana){
        super(nome, nivel, arma);
        this.mana = mana;
    }

    @Override
    public double atacar(){
        if (mana < 10){
            throw new ManaInsuficienteException("Mana insuficiente para conjurar feitiços");
        }
        mana -= 10;
        return arma.getDanoBase() + (nivel * 2.5);
    }

    @Override
    public void iniciarMissao (int nivelMissao) throws MissaoInvalidaException {
        if (nivelMissao > nivel + 5){
            throw new MissaoInvalidaException("A missão de nível " + nivelMissao + " é letal demais para o nível do Mago.");
        }
        System.out.println(nome + " (Mago) teleportou-se para a missão.");
    }
}