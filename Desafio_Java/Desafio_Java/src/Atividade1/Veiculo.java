package Atividade1;

/* 1. Classe Base: Veiculo
○ Atributos: placa (String), capacidadeTanque (double),
combustivelAtual (double), consumoBaseKmPorLitro (double).
○ Métodos:
■ Um construtor para inicializar todos os atributos.
■ abastecer(double litros): Adiciona combustível ao tanque
(não pode ultrapassar a capacidade máxima).
■ realizarViagem(double distancia): Calcula quantos litros
serão necessários (distância / consumo). Se houver
combustível suficiente, deduz do combustivelAtual, imprime
uma mensagem de sucesso e retorna true. Caso contrário,
imprime um alerta e retorna false. */

public class Veiculo {

    private String placa;
    private double capacidadeTanque;
    private double combustivelAtual;
    private double consumoBaseKmPorLitro;

    public Veiculo () {}

    public Veiculo(String placa, double capacidadeTanque, double combustivelAtual, double consumoBaseKmPorLitro) {
        this.placa = placa;
        this.capacidadeTanque = capacidadeTanque;
        this.combustivelAtual = combustivelAtual;
        this.consumoBaseKmPorLitro = consumoBaseKmPorLitro;
    }

    protected String getPlaca(){
        return placa;
    }
    protected double getCapacidadeTanque(){
        return capacidadeTanque;
    }
    protected double getCombustivelAtual(){
        return combustivelAtual;
    }
    protected double getConsumoBaseKmPorLitro(){
        return consumoBaseKmPorLitro;
    }

    protected void setPlaca (String placa) {
        this.placa = placa;
    }
    protected void setCapacidadeTanque (double capacidadeTanque) {
        this.capacidadeTanque = capacidadeTanque;
    }
    protected void setCombustivelAtual (double combustivelAtual) {
        this.combustivelAtual = combustivelAtual;
    }
    protected void setConsumoBaseKmPorLitro (double consumoBaseKmPorLitro){
        this.consumoBaseKmPorLitro = consumoBaseKmPorLitro;
    }

    public void Abastecer (double litro){
        litro = litro + combustivelAtual;
        if (litro <= capacidadeTanque){
            System.out.println("Os litros abastecidos foram adicionados: " + litro);
        }
        else {
            System.out.println("Os litros excedem o tanque de combustível");
        }
    }
    public void realizarViagem (double distancia) {
        distancia = distancia / consumoBaseKmPorLitro;
        if (distancia <= combustivelAtual) {
            distancia = combustivelAtual - distancia;
            System.out.println("true");
        } else
            System.out.println("A quantidade de combustível é insuficiente");
            System.out.println("\nfalse");
    }
}
