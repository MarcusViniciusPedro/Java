package br.igor.pizzaria.dao;

import br.igor.pizzaria.model.Categoria;
import br.igor.pizzaria.util.ConexaoBD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoriaDAO {

    public boolean cadastrar(Categoria categoria) {
        String sql = "INSERT INTO categorias (nome) VALUES (?)";
        try (Connection conn = ConexaoBD.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, categoria.getNome());
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.err.println("Erro ao cadastrar categoria: " + e.getMessage());
            return false;
        }
    }

    public List<Categoria> listar() {
        List<Categoria> categorias = new ArrayList<>();
        String sql = "SELECT * FROM categorias"; // Mostrará a tabela do BD
        try (Connection conn = ConexaoBD.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
                ResultSet resultado = stmt.executeQuery();
                while (resultado.next()) {
                    categorias.add(new Categoria(resultado.getInt("id"), resultado.getString("nome")));
                } // Vai captar os dados lá do BD
            System.out.println(categorias);
        } catch (SQLException e) {
            System.err.println("Erro ao cadastrar categoria: " + e.getMessage());
        }
        return categorias;
    }
}