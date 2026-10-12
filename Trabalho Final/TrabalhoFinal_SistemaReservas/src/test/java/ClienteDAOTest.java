import org.junit.jupiter.api.Test;
import br.marcus.trabalhofinal_sistemareservas.dao.ClienteDAO;
import br.marcus.trabalhofinal_sistemareservas.model.Cliente;

import static org.junit.jupiter.api.Assertions.*;

class ClienteDAOTest {

    @Test
    void cadastrarCliente() {
        Cliente c = new Cliente("Marcus", "21", "12345678910", "marcus.pedro@aluno.ifsp.edu.br", "11956375950", "senha123");
        int idGerado = ClienteDAO.cadastrarCliente(c);
        try {
            assertTrue(idGerado > 0, "O ID gerado deve ser um número positivo maior que 0");
        } finally {
            ClienteDAO.excluirCliente(idGerado);
        }
    }

    @Test
    void buscarClientePorId() {
        Cliente c = new Cliente("Parker", "45", "10987654321", "parker@gmail.com", "1123456789", "senhaForte");
        int id = ClienteDAO.cadastrarCliente(c);
        try {
            Cliente buscaCliente = ClienteDAO.buscarClientePorId(id);
            assertNotNull(buscaCliente);
            assertEquals("Parker", buscaCliente.getNome());
        } finally {
            ClienteDAO.excluirCliente(id);
        }
    }

    @Test
    void excluirCliente() {
        Cliente c = new Cliente("Marcus", "21", "1235678910", "marcus.pedro@aluno.ifsp.edu.br", "11956375950", "senha123");
        int id = ClienteDAO.cadastrarCliente(c);

        ClienteDAO.excluirCliente(id);
        Cliente clienteExcluido = ClienteDAO.buscarClientePorId(id);
        assertNull(clienteExcluido);
    }

    @Test
    void atualizarCliente() {
        Cliente c = new Cliente("Parker", "45", "1098654321", "parker@gmail.com", "1123456789", "senhaForte");
        int id = ClienteDAO.cadastrarCliente(c);
        c.setId(id);
        try {
            c.setNome("Marcus");
            ClienteDAO.atualizarCliente(c);
            Cliente clienteAtualizado = ClienteDAO.buscarClientePorId(id);
            assertEquals("Marcus", clienteAtualizado.getNome());
        } finally {
            ClienteDAO.excluirCliente(id);
        }
    }

    @Test
    void verificacaoDeEmailExistente() {
        Cliente c = new Cliente("Marcus", "21", "1235678910", "marcus.pedro@aluno.ifsp.edu.br", "11956375950", "senha123");
        int id = ClienteDAO.cadastrarCliente(c);

        try {
            assertTrue(ClienteDAO.verificacaoDeEmailExistente("marcus.pedro@aluno.ifsp.edu.br"));
            assertFalse(ClienteDAO.verificacaoDeEmailExistente("marcus.moraes@aluno.ifsp.edu.br"));
        } finally {
            ClienteDAO.excluirCliente(id);
        }
    }
}