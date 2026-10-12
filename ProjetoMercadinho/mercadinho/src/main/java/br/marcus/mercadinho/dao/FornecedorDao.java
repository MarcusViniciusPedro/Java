package br.marcus.mercadinho.dao;

import br.marcus.mercadinho.model.Fornecedor;
import br.marcus.mercadinho.util.ConexaoDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class FornecedorDao {

    public static void salvar(Fornecedor fornecedor) {
        String sql = "INSERT INTO fornecedores (cnpj, nome_fantasia, telefone) VALUES (?, ?, ?)";

        try (Connection connection = ConexaoDB.conectar();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, fornecedor.getCnpj());
            statement.setString(2, fornecedor.getNomeFantasia());
            statement.setString(3, fornecedor.getTelefone());
            statement.executeUpdate();

            System.out.println("Fornecedor cadastrado com sucesso no banco de dados!");

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao cadastrar o fornecedor: " + e.getMessage(), e);
        }
    }
}
