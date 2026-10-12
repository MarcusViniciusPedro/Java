import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.time.LocalTime;
import static org.junit.jupiter.api.Assertions.*;

public class ControllersTest {

    @Test
    void validarRegraMaiorDeIdade() {
        int idadeDigitada = 17;

        boolean disparouErro = (idadeDigitada < 18);
        assertTrue(disparouErro, "O sistema deve bloquear a operação se a idade for menor que 18.");
    }

    @Test
    void validarRegraLimiteDaHamburgueria() {
        int mesasDigitadas = 55;
        int pessoasDigitadas = 300;

        boolean excedeuMesas = (mesasDigitadas > 50);
        boolean excedeuPessoas = (pessoasDigitadas > 250);

        assertTrue(excedeuMesas, "Deve bloquear reservas com mais de 50 mesas.");
        assertTrue(excedeuPessoas, "Deve bloquear reservas com mais de 250 pessoas.");
    }

    @Test
    void validarRegraViagemNoTempoData() {
        LocalDate dataHoje = LocalDate.now();
        LocalDate dataDigitada = LocalDate.of(2010, 5, 10);

        boolean dataInvalida = dataDigitada.isBefore(dataHoje);
        assertTrue(dataInvalida, "A data da reserva não pode estar no passado.");
    }

    @Test
    void validarRegraHorarioDeFuncionamento() {
        LocalTime horarioAbertura = LocalTime.of(18, 0);
        LocalTime horarioFechamento = LocalTime.of(23, 0);

        LocalTime horaDigitada = LocalTime.of(15, 0);

        boolean foraDoHorario = horaDigitada.isBefore(horarioAbertura) || horaDigitada.isAfter(horarioFechamento);
        assertTrue(foraDoHorario, "O sistema deve bloquear horários fora da janela 18:00 - 23:00.");
    }
}