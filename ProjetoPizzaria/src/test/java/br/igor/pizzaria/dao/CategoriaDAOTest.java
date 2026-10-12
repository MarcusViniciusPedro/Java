package br.igor.pizzaria.dao;

import br.igor.pizzaria.model.Categoria;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class CategoriaDAOTest {

    @Test
    public void deveSalvarCategoriaComSucesso() {
        CategoriaDAO dao = new CategoriaDAO();

        // Usamos o System.currentTimeMillis() no nome para gerar uma string única a cada teste.
        // Isso evita que o banco de dados bloqueie o teste por causa da restrição "UNIQUE" da coluna nome.
        String nomeCategoria = "Categoria Teste " + System.currentTimeMillis();
        Categoria novaCategoria = new Categoria(nomeCategoria);

        boolean resultado = dao.cadastrar(novaCategoria);

        // O teste passa se o DAO retornar true
        assertTrue(resultado, "A categoria deveria ser salva com sucesso no banco de dados.");
    }
}