package br.marcus.trabalhofinal_sistemareservas.dao;

// Importação do modelo de dados puro (A entidade que transporta os registros)
import br.marcus.trabalhofinal_sistemareservas.model.Cliente;

// Importação do utilitário centralizador de conexões do projeto
import br.marcus.trabalhofinal_sistemareservas.util.ConexaoBD;

// Importação das especificações da API JDBC oficial do Java
import java.sql.*;

public class ClienteDAO {

    // CREATE (C do CRUD): Insere um novo cliente no banco de dados e recupera seu ID autogerado
    public static int cadastrarCliente(Cliente cliente) {
        String sql = "INSERT INTO cliente (nome, idade, cpf, email, telefone, senha) VALUES (?, ?, ?, ?, ?, ?)";

        // Try-with-resources: Garante que a Conexão e o Statement serão FECHADOS automaticamente ao fim do bloco
        try (Connection connection = ConexaoBD.conectar();
             // Statement.RETURN_GENERATED_KEYS avisa ao MySQL que queremos capturar de volta a chave primária criada por ele
             PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            // Preenche de forma segura os placeholders '?' mapeados na String SQL para evitar ataques de SQL Injection
            statement.setString(1, cliente.getNome());
            statement.setString(2, cliente.getIdade());
            statement.setString(3, cliente.getCpf());
            statement.setString(4, cliente.getEmail());
            statement.setString(5, cliente.getTelefone());
            statement.setString(6, cliente.getSenha());
            // Executa a instrução no banco de dados
            statement.executeUpdate();

            // Abre uma mini-tabela temporária para resgatar os IDs criados nesta transação
            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    System.out.println("Cliente cadastrado com sucesso! ID: " + generatedKeys.getInt(1));
                    return generatedKeys.getInt(1); // Retorna o ID gerado pelo auto_increment do banco
                }
            }
            return -1; // Retorna flag de falha caso não consiga resgatar o ID

        } catch (SQLException e){
            // Relança como RuntimeException para evitar poluir as assinaturas dos métodos nos controladores
            throw new RuntimeException("Erro ao cadastrar o cliente: " + e.getMessage(), e);
        }
    }

    // READ (R do CRUD): Busca na tabela os registros de um cliente específico baseado no ID
    public static Cliente buscarClientePorId(int id) {
        String sql = "SELECT * FROM cliente WHERE id = ?";

        try (Connection connection = ConexaoBD.conectar();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, id); // Injeta o ID procurado na query SQL
            // Executa a busca e devolve o cursor do banco de dados contendo as linhas encontradas
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    // Instancia e preenche o modelo com as colunas reais retornadas da tabela do MySQL
                    Cliente cliente = new Cliente(
                            resultSet.getString("nome"),
                            resultSet.getString("idade"),
                            resultSet.getString("cpf"),
                            resultSet.getString("email"),
                            resultSet.getString("telefone"),
                            resultSet.getString("senha")
                    );
                    // Atribui manualmente o ID obtido da coluna primária do banco
                    cliente.setId(resultSet.getInt("id"));
                    return cliente; // Retorna o objeto completamente preenchido para o controlador
                }
            }
            return null; // Retorna nulo se o ID não corresponder a nenhum registro no banco

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar dados do cliente: " + e.getMessage(), e);
        }
    }

    // DELETE (D do CRUD): Apaga fisicamente a linha de registro do cliente no banco baseado no ID
    public static void excluirCliente(int id) {
        String sql = "DELETE FROM cliente WHERE id = ?";

        try (Connection connection = ConexaoBD.conectar();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, id);
            statement.executeUpdate(); // Executa o comando de deleção
            System.out.println("Cliente removido com sucesso através do ID!");

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao deletar o cliente pelo ID: " + e.getMessage(), e);
        }
    }

    // UPDATE (U do CRUD): Atualiza as colunas de dados de um registro existente baseado no ID do objeto enviado
    public static void atualizarCliente(Cliente cliente) {
        String sql = "UPDATE cliente SET nome = ?, idade = ?, cpf = ?, email = ?, telefone = ?, senha = ? WHERE id = ?";

        try (Connection connection = ConexaoBD.conectar();
            PreparedStatement statement = connection.prepareStatement(sql)) {

            // Mapeia todas as propriedades que podem ter sofrido alterações na tela de edição
            statement.setString(1, cliente.getNome());
            statement.setString(2, cliente.getIdade());
            statement.setString(3, cliente.getCpf());
            statement.setString(4, cliente.getEmail());
            statement.setString(5, cliente.getTelefone());
            statement.setString(6, cliente.getSenha());
            statement.setInt(7, cliente.getId()); // Critério de filtro (Cláusula WHERE)
            statement.executeUpdate(); // Commita as alterações nas tabelas do MySQL

            System.out.println("Dados do cliente atualizados no Banco de Dados!");

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar dados do cliente: " + e.getMessage(), e);
        }
    }

    // Regra de Integridade: Valida se um e-mail específico já está em uso na base de dados
    public static boolean verificacaoDeEmailExistente(String email) {
        String sql = "SELECT id FROM cliente WHERE email = ?";

        try (Connection connection = ConexaoBD.conectar();
            PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, email);
            try (ResultSet resultSet = statement.executeQuery()) {
                // .next() retorna true se o ponteiro encontrar uma linha correspondente, indicando que o e-mail já existe
                return resultSet.next();
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao verificar dados do cliente: " + e.getMessage(), e);
        }
    }
}