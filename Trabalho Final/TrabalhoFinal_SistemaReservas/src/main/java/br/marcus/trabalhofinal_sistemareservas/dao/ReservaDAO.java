package br.marcus.trabalhofinal_sistemareservas.dao;

// Importação do modelo de dados da Reserva e do utilitário de conexão
import br.marcus.trabalhofinal_sistemareservas.model.Reserva;
import br.marcus.trabalhofinal_sistemareservas.util.ConexaoBD;

// Importações JDBC nativas do Java e estruturas de coleções
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReservaDAO {

    // CREATE (C do CRUD): Registra uma nova reserva no banco de dados MySQL
    public static void cadastrarReserva(Reserva reserva) {
        String sql = "INSERT INTO reserva (dataReserva, tipoEvento, prefMesa, qtdPessoa, horaReserva, observacao) VALUES (?, ?, ?, ?, ?, ?)";

        // Try-with-resources: Abre a conexão de rede e pré-compila a query SQL com segurança
        try (Connection connection = ConexaoBD.conectar();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            // Injeção de dados considerando os tipos corretos definidos no banco
            statement.setString(1, reserva.getDataReserva());
            statement.setString(2, reserva.getTipoEvento());
            statement.setInt(3, reserva.getPrefMesa()); // Injeta um tipo primitivo de INT
            statement.setInt(4, reserva.getQtdPessoa()); // Injeta um tipo primitivo de INT
            statement.setString(5, reserva.getHoraReserva());
            statement.setString(6, reserva.getObservacao());
            // Executa a instrução física de INSERT na tabela do MySQL
            statement.executeUpdate();

            System.out.println("Reserva cadastrada com sucesso no Banco de Dados!");

        } catch (SQLException e){
            throw new RuntimeException("Erro ao cadastrar a reserva: " + e.getMessage(), e);
        }
    }

    // READ COLETIVO (R do CRUD): Recupera todas as reservas do banco para alimentar a TableView do funcionário
    public static List<Reserva> listarTodasReservas() {
        // Instancia uma lista dinâmica vazia na memória
        List<Reserva> lista = new ArrayList<>();
        String sql = "SELECT * FROM reserva";

        try (Connection connection = ConexaoBD.conectar();
            PreparedStatement statement = connection.prepareStatement(sql);
             // Executa a consulta trazendo o ponteiro de resultados (ResultSet)
            ResultSet res = statement.executeQuery()) {

            // Varre o cursor do banco de dados linha por linha até o fim da tabela
            while (res.next()) {
                // Instancia um objeto de modelo vazio
                Reserva reserva = new Reserva();

                // Reconstrói o objeto capturando os tipos de dados correspondentes de cada coluna
                reserva.setId(res.getInt("id"));
                reserva.setDataReserva(res.getString("dataReserva"));
                reserva.setTipoEvento(res.getString("tipoEvento"));
                reserva.setPrefMesa(res.getInt("prefMesa")); // Resgata o inteiro da coluna
                reserva.setQtdPessoa(res.getInt("qtdPessoa")); // Resgata o inteiro da coluna
                reserva.setHoraReserva(res.getString("horaReserva"));
                reserva.setObservacao(res.getString("observacao"));
                // Adiciona o objeto totalmente estruturado à lista do relatório
                lista.add(reserva);
            }
        } catch (Exception e) {
            throw new RuntimeException("Erro ao listar a reservas: " + e.getMessage(), e);
        }
        return lista; // Retorna a coleção de objetos preenchida para o controlador
    }

    // REGRA DE NEGÓCIO: Verifica se já existe qualquer agendamento cadastrado em uma data específica
    public static boolean verificarReservaExistentePorData (String dataReserva) {
        // Traz apenas o ID (otimização de tráfego) filtrando pela data fornecida
        String sql = "SELECT id FROM reserva WHERE dataReserva = ?";

        try (Connection connection = ConexaoBD.conectar();
            PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, dataReserva); // Injeta a data recebida por parâmetro

            try (ResultSet resultSet = statement.executeQuery()) {
                // Atalho lógico: .next() retorna true se achar uma linha (data ocupada) ou false se estiver vazia (data livre)
                return resultSet.next();
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao verificar a reserva: " + e.getMessage(), e);
        }
    }
}