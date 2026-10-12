package br.igor.pizzaria.dao;

import br.igor.pizzaria.model.Categoria;
import br.igor.pizzaria.model.Produto;
import br.igor.pizzaria.util.ConexaoBD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProdutoDAO {
    public boolean cadastrar(Produto produto) {
        String sql = "INSERT INTO produtos (categoria, id, nome, descricao, preco) VALUES (?, ?, ?, ?)";
        try (Connection conn = ConexaoBD.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, produto.getCategoria().getNome());
            stmt.setString(2, produto.getNome());
            stmt.setString(3, produto.getDescricao());
            stmt.setDouble(4, produto.getPreco());

            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.err.println("Erro ao cadastrar produto: " + e.getMessage());
            return false;
        }
    }

    public List<Produto> listar() {
        List<Produto> produtos = new ArrayList<>();
        String sql = "SELECT p.*, c.nome FROM produtos p INNER JOIN categorias c ON p.categoria_id = c.id"; // Mostrará a tabela do BD
        try (Connection conn = ConexaoBD.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            ResultSet resultado = stmt.executeQuery();
            while (resultado.next()) {
                Categoria categoria = new Categoria(resultado.getInt("categoria_id"), resultado.getString("nome_categoria"));
                Produto produto = new Produto(
                        resultado.getInt("id"),
                        resultado.getString("nome"),
                        resultado.getDouble("preco"),
                        resultado.getString("descricao"),
                        categoria
                );
                produtos.add(produto);
            } // Vai captar os dados lá do BD
            System.out.println(produtos);
        } catch (SQLException e) {
            System.err.println("Erro ao cadastrar produto: " + e.getMessage());
        }
        return produtos;
    }
}
