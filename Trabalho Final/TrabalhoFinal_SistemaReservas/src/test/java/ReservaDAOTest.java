import org.junit.jupiter.api.Test;
import br.marcus.trabalhofinal_sistemareservas.dao.ReservaDAO;
import br.marcus.trabalhofinal_sistemareservas.model.Reserva;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ReservaDAOTest {

    @Test
    void cadastrarReserva() {
        Reserva r = new Reserva("10/12/2026", "Aniversário", 5, 10, "20:00", "Bolo Surpresa");
        ReservaDAO.cadastrarReserva(r);
        assertTrue(true);
    }

    @Test
    void listarTodasReservas() {
        List<Reserva> lista = ReservaDAO.listarTodasReservas();
        assertNotNull(lista);
    }

    @Test
    void verificarReservaExistentePorData() {
        Reserva r = new Reserva("10/12/2028", "Aniversário", 5, 10, "20:00", "Bolo Surpresa");
        ReservaDAO.cadastrarReserva(r);

        assertTrue(ReservaDAO.verificarReservaExistentePorData("10/12/2028"));
        assertFalse(ReservaDAO.verificarReservaExistentePorData("30/10/2028"));
    }
}