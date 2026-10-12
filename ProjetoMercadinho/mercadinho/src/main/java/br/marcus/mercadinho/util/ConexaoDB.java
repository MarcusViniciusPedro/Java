package br.marcus.mercadinho.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoDB {

    private static final String URL = "jdbc:mysql://localhost:3306/mercadinho";
    private static final String USUARIO = "root";
    private static final String SENHA = "root";

    public static Connection conectar(){
        try{
            return DriverManager.getConnection(URL, USUARIO, SENHA);
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao conectar com o Banco de Dados:" + e.getMessage(), e);
        }
    }

}
