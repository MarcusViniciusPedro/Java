import java.io.Closeable;

public class Conta implements AutoCloseable {
    private String titular;
    private double saldo;

    public Conta(String titular, double saldo) {
        this.titular = titular;
        this.saldo = saldo;
    }

    public void sacar (double valor){
        if (saldo < valor){
            throw new SaldoInsuficienteException("Operação Negada: Saldo Insuficiente! Seu saldo é de R$" + this.saldo);
        }
        this.saldo -= valor;
        System.out.println("Saque realizado com Sucesso!");
    }

    public double getSaldo() {
        return saldo;
    }

    @Override
    public void close() throws Exception {

    }
}
