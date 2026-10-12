package br.marcus.trabalhofinal_sistemareservas.dao;

// Importação da entidade de dados e do conector centralizado do banco
import br.marcus.trabalhofinal_sistemareservas.model.Empregado;
import br.marcus.trabalhofinal_sistemareservas.util.ConexaoBD;

// Importação de estruturas nativas do ecossistema JDBC e de coleções do Java
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmpregadoDAO {

    // CREATE (C do CRUD): Grava um novo funcionário no banco de dados local
    public static int cadastrarEmpregado(Empregado empregado) {
        String sql = "INSERT INTO empregado (nome, idade, cpf, telefone, senha, cargo) VALUES (?, ?, ?, ?, ?, ?)";

        // Try-with-resources: Abre a conexão física e prepara a consulta blindada contra SQL Injection
        try (Connection connection = ConexaoBD.conectar();
             // Informa ao MySQL que exigimos o ID gerado pelo AUTO_INCREMENT após a inserção
             PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            // Preenche de forma indexada os placeholders '?' com os dados vindos do objeto
            statement.setString(1, empregado.getNome());
            statement.setString(2, empregado.getIdade());
            statement.setString(3, empregado.getCpf());
            statement.setString(4, empregado.getTelefone());
            statement.setString(5, empregado.getSenha());
            statement.setString(6, empregado.getCargo());
            statement.executeUpdate(); // Dispara o comando de gravação

            // Resgata o ID gerado automaticamente na tabela do banco
            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    // NOTA DE ATENÇÃO: O log diz "Cliente", mas estamos salvando um Empregado! (Apenas um detalhe estético)
                    System.out.println("Cliente cadastrado com sucesso! ID: " + generatedKeys.getInt(1));
                    return generatedKeys.getInt(1); // Retorna a chave primária criada
                }
            }
            return -1;

        } catch (SQLException e){
            // Lança uma exceção em tempo de execução capturando o erro original do banco
            throw new RuntimeException("Erro ao cadastrar o cliente: " + e.getMessage(), e);
        }
    }

    // READ INDIVIDUAL (R do CRUD): Localiza um funcionário específico através do ID primário
    public static Empregado buscarEmpregadoPorId(int id) {
        String sql = "SELECT * FROM empregado WHERE id = ?";

        try (Connection connection = ConexaoBD.conectar();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, id); // Define o critério do filtro de busca
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    // Reconstrói o Objeto Java preenchendo o construtor com as colunas reais do MySQL
                    Empregado empregado = new Empregado(
                            resultSet.getString("nome"),
                            resultSet.getString("idade"),
                            resultSet.getString("cpf"),
                            resultSet.getString("telefone"),
                            resultSet.getString("senha"),
                            resultSet.getString("cargo")
                    );
                    empregado.setId(resultSet.getInt("id")); // Atribui o ID recuperado
                    return empregado; // Retorna o objeto pronto para o controlador
                }
            }
            return null; // Retorna nulo se o funcionário não existir no banco

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar dados do empregado: " + e.getMessage(), e);
        }
    }

    // DELETE (D do CRUD): Remove fisicamente a linha do funcionário baseado no ID informado
    public static void excluirEmpregado(int id) {
        String sql = "DELETE FROM empregado WHERE id = ?";

        try (Connection connection = ConexaoBD.conectar();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, id);
            statement.executeUpdate(); // Commita a exclusão definitiva no banco
            System.out.println("Empregado removido com sucesso através do ID!");

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao deletar a conta do funcionário pelo ID: " + e.getMessage(), e);
        }
    }

    // UPDATE (U do CRUD): Substitui os dados atuais do registro no banco pelos novos valores enviados
    public static void atualizarEmpregado(Empregado empregado) {
        String sql = "UPDATE empregado SET nome = ?, idade = ?, cpf = ?, telefone = ?, senha = ?, cargo = ? WHERE id = ?";

        try (Connection connection = ConexaoBD.conectar();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            // Mapeia todas as propriedades editáveis coletadas no formulário da tela
            statement.setString(1, empregado.getNome());
            statement.setString(2, empregado.getIdade());
            statement.setString(3, empregado.getCpf());
            statement.setString(4, empregado.getTelefone());
            statement.setString(5, empregado.getSenha());
            statement.setString(6, empregado.getCargo());
            statement.setInt(7, empregado.getId()); // Define o alvo da modificação no WHERE
            statement.executeUpdate(); // Atualiza a base de dados MySQL

            System.out.println("Dados do empregado atualizados no Banco de Dados!");

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar dados do empregado: " + e.getMessage(), e);
        }
    }

    // READ COLETIVO: Varre a tabela inteira e extrai todos os registros para popular a TableView
    public static List<Empregado> ListarEmpregados() {
        // Inicializa uma lista dinâmica vazia na memória RAM
        List<Empregado> lista = new ArrayList<>();
        // Otimização: Seleciona apenas as colunas necessárias para o relatório, economizando tráfego de rede
        String sql = "SELECT id, nome, idade, cargo FROM empregado";

        try (Connection connection = ConexaoBD.conectar();
            PreparedStatement statement = connection.prepareStatement(sql);
             // Executa a query trazendo todas as linhas encontradas na tabela do banco
            ResultSet res = statement.executeQuery()){

            // Laço de repetição: Enquanto houver uma próxima linha no cursor do banco, continue rodando
            while (res.next()) {
                // Cria um objeto vazio utilizando o construtor padrão
                Empregado empregado = new Empregado();

                // Popula o objeto linha por linha usando os métodos modificadores (Setters)
                empregado.setId(res.getInt("id"));
                empregado.setNome(res.getString("nome"));
                empregado.setIdade(res.getString("idade"));
                empregado.setCargo(res.getString("cargo"));
                // Adiciona o funcionário estruturado dentro da nossa lista dinâmica
                lista.add(empregado);
            }
        } catch (Exception e) {
            e.printStackTrace(); // Imprime o rastreio do erro no terminal em caso de falha física
        }
        return lista; // Devolve a lista cheia de funcionários (ou vazia, caso dê erro) para a TableView
    }
}