package Atividade1;

/* 3. Subclasse 2: Motocicleta
○ Herda de: Veiculo
○ Atributo extra: cilindradas (int).
○ Sobrescrita (Override): Sobrescreva o método realizarViagem.
Motocicletas sofrem desgaste em viagens longas. Se a distância
da viagem solicitada for maior que 80 km, o consumo da moto
piora em 15% apenas para aquela viagem. */

public class Motocicleta extends Veiculo{

    private int cilindradas;

    public Motocicleta (String placa, double capacidadeTanque, double combustivelAtual, double consumoBaseKmPorLitro) {
        super (placa, capacidadeTanque, combustivelAtual, consumoBaseKmPorLitro);
        this.cilindradas = cilindradas;
    }
    protected int getCilindradas(){
        return cilindradas;
    }
    protected void setCilindradas (int cilindradas){
        this.cilindradas = cilindradas;
    }

    @Override
    public void realizarViagem (double distancia){
        if (distancia > 80){
            setConsumoBaseKmPorLitro(getConsumoBaseKmPorLitro() - getConsumoBaseKmPorLitro() * (15 / 100));
            if (distancia <= getConsumoBaseKmPorLitro()){
                distancia = getCombustivelAtual() - distancia;
                System.out.println("true");
            }
            else
                System.out.println("A quantidade de combustível é insuficiente");
            System.out.println("\nfalse");
        }
        else {
            if (distancia <= getConsumoBaseKmPorLitro()){
                distancia = getCombustivelAtual() - distancia;
                System.out.println("true");
            }
            else
                System.out.println("A quantidade de combustível é insuficiente");
                System.out.println("\nfalse");
        }
    }
}
