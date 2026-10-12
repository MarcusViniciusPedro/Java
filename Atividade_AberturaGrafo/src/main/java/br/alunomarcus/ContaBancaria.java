package br.alunomarcus;

public class ContaBancaria {
    private double saldo = 0; // Inicia com saldo zero

    public double getSaldo() {
        return saldo; // Retorna o saldo quando requisitado
    }

    public void depositar(double valor) {
        this.saldo += valor; // Saldo aumenta para cada valor adicionado
    }

    public void sacar(double valor) {
        // Lança exceção se o saque for maior que o saldo
        if (valor > this.saldo) {
            throw new IllegalArgumentException("Saldo insuficiente para o saque.");
        }
        this.saldo -= valor; // Saldo diminuirá ao retirar um valor
    }
}