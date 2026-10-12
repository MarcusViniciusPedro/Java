import br.alunomarcus.ContaBancaria;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ContaBancariaTest {

    @Test
    void IniciarComZero() { // Mantém todos os valores iniciais em zero
        ContaBancaria conta = new ContaBancaria();
        assertEquals(0, conta.getSaldo());
    }

    @Test
    void DepositarValor() { // Para colocar o "dinheiro" na conta
        ContaBancaria conta = new ContaBancaria();
        conta.depositar(100.0);
        assertEquals(100.0, conta.getSaldo());
    }

    @Test
    void SacarValor() { // Redução do valor ao tirar um valor da conta
        ContaBancaria conta = new ContaBancaria();
        conta.depositar(100.0);
        conta.sacar(40.0);
        assertEquals(60.0, conta.getSaldo());
    }

    @Test
    void ExcecaoAoSacarValorMaior() { // Para o caso da pessoa tentar tirar mais do que tem na conta
        ContaBancaria conta = new ContaBancaria();
        conta.depositar(50.0);
        assertThrows(IllegalArgumentException.class, () -> {
            conta.sacar(60.0);
        });
    }
}