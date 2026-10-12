public class Motor {
    protected int potencia;
    protected int vidaUtil;

    public Motor(int potencia, int vidaUtil){
        this.potencia = potencia;
        this.vidaUtil = vidaUtil;
    }

    public void gastarCombustivel(double consumo, Nave nave){
        double combustivel = nave.getQtdCombustivel() - consumo;
        if (combustivel < 0){
            throw new CombustivelInsuficienteException("A nave não possui combustível suficiente para a viagem!");
        }
        nave.setQtdCombustivel(combustivel);
    }
}
