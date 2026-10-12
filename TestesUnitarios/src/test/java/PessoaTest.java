import org.example.Pessoa;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PessoaTest {
    @Test
    void retornaQuemEAPessoa(){
        Pessoa p = new Pessoa("Marcus ");
        Pessoa op = p.quemSouEu();
        assertEquals(p, op);
    }
}
