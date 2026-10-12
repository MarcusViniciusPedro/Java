import org.junit.jupiter.api.Test;
import br.marcus.trabalhofinal_sistemareservas.dao.EmpregadoDAO;
import br.marcus.trabalhofinal_sistemareservas.model.Empregado;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class EmpregadoDAOTest {

    @Test
    void cadastrarEmpregado() {
        Empregado e = new Empregado("Marcus", "21", "12345678910", "11956375950", "senha123", "Gerente");
        int id = EmpregadoDAO.cadastrarEmpregado(e);
        try {
            assertTrue(id > 0);
        } finally {
            EmpregadoDAO.excluirEmpregado(id);
        }
    }

    @Test
    void buscarEmpregadoPorId() {
        Empregado e = new Empregado("Parker", "45", "10987654321", "1123456789", "senhaForte", "Auxiliar Administrativo");
        int id =  EmpregadoDAO.cadastrarEmpregado(e);
        try {
            Empregado empregado = EmpregadoDAO.buscarEmpregadoPorId(id);
            assertEquals("Parker", empregado.getNome());
        } finally {
            EmpregadoDAO.excluirEmpregado(id);
        }
    }

    @Test
    void excluirEmpregado() {
        Empregado e = new Empregado("Marcus", "21", "12345678910", "11956375950", "senha123", "Gerente");
        int id  = EmpregadoDAO.cadastrarEmpregado(e);

        EmpregadoDAO.excluirEmpregado(id);
        assertNull(EmpregadoDAO.buscarEmpregadoPorId(id));
    }

    @Test
    void atualizarEmpregado() {
        Empregado e = new Empregado("Parker", "45", "10987654321", "1123456789", "senhaForte", "Auxiliar Administrativo");
        int id = EmpregadoDAO.cadastrarEmpregado(e);
        e.setId(id);
        try {
            e.setCargo("Supervisor");
            EmpregadoDAO.atualizarEmpregado(e);

            Empregado empregado = EmpregadoDAO.buscarEmpregadoPorId(id);
            assertEquals("Supervisor", empregado.getCargo());
        } finally {
            EmpregadoDAO.excluirEmpregado(id);
        }
    }

    @Test
    void listarEmpregados() {
        List<Empregado> lista = EmpregadoDAO.ListarEmpregados();
        assertNotNull(lista);
    }
}