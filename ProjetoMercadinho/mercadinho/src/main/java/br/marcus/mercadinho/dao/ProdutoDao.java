package br.marcus.mercadinho.dao;

import br.marcus.mercadinho.model.Produto;
import br.marcus.mercadinho.util.ConexaoDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ProdutoDao {

    public static void cadastrar(Produto produto){
        String sql = "INSERT INTO produtos (descricao, preco, quantidade) VALUES (?, ?, ?)";

        try (Connection connection = ConexaoDB.conectar();
             PreparedStatement statement = connection.prepareStatement(sql)){

            statement.setString(1, produto.getDescricao());
            statement.setDouble(2, produto.getPreco());
            statement.setInt(3, produto.getQuantidade());
            statement.executeUpdate();

            System.out.println("Produto cadastrado com sucesso no banco de dados!");

        } catch(SQLException e) {
            throw new RuntimeException("Erro ao cadastrar o produto: " + e.getMessage(), e);
        }
    }

}