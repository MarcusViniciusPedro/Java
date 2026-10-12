package Atividade1;

/* 2. Subclasse 1: Caminhao
○ Herda de: Veiculo
○ Atributo extra: cargaAtualToneladas (double).
○ Sobrescrita (Override): Sobrescreva o método realizarViagem.
Para o caminhão, o consumo base piora (diminui) em 5% para
cada tonelada de carga que ele está carregando antes de
calcular o gasto da viagem. Ex: Se o consumo base é 10 km/l e ele
leva 2 toneladas, o consumo efetivo cai 10%, passando a ser 9
km/l. */

public class Caminhao extends Veiculo {

    private double cargaAtualToneladas;

    public Caminhao (String placa, double capacidadeTanque, double combustivelAtual, double consumoBaseKmPorLitro) {
        super (placa, capacidadeTanque, combustivelAtual, consumoBaseKmPorLitro);
        this.cargaAtualToneladas = cargaAtualToneladas;
    }

    protected double getCargaAtualToneladas(){
        return cargaAtualToneladas;
    }
    protected void setCargaAtualToneladas (double cargaAtualToneladas){
        this.cargaAtualToneladas = cargaAtualToneladas;
    }

    @Override
    public void realizarViagem (double distancia){
        setConsumoBaseKmPorLitro(getConsumoBaseKmPorLitro() - (getConsumoBaseKmPorLitro() * ((cargaAtualToneladas * 5) / 100)));
        distancia = distancia / getConsumoBaseKmPorLitro();
        if (distancia <= getConsumoBaseKmPorLitro()){
            distancia = getCombustivelAtual() - distancia;
            System.out.println("true");
        }
        else
            System.out.println("A quantidade de combustível é insuficiente");
            System.out.println("\nfalse");
    }
}
