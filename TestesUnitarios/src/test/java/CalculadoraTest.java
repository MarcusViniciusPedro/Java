import org.example.Calculadora;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CalculadoraTest {
    @Test
    void deveSomarDoisNumeros(){
        Calculadora c = new Calculadora();
        int r = c.somar(2, 3);
        assertEquals(5, r);
    }
}
