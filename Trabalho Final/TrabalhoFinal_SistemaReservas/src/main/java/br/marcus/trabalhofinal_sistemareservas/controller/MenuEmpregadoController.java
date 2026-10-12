package br.marcus.trabalhofinal_sistemareservas.controller;

// Importações globais da sua aplicação (Sessão de autenticação, DAOs e Modelos)
import br.marcus.trabalhofinal_sistemareservas.HelloApplication;
import br.marcus.trabalhofinal_sistemareservas.dao.EmpregadoDAO;
import br.marcus.trabalhofinal_sistemareservas.dao.ReservaDAO;
import br.marcus.trabalhofinal_sistemareservas.model.Empregado;
import br.marcus.trabalhofinal_sistemareservas.model.Reserva;

// Importações do JavaFX para manipulação de dados reativos e componentes visuais de tabela/janela
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.util.List;
import java.util.Optional;

public class MenuEmpregadoController {

    // Método disparado ao clicar em "Apagar Conta"
    @FXML
    void apagarContaFuncionario(ActionEvent event) {
        Alert alerta = new Alert(Alert.AlertType.CONFIRMATION);
        alerta.setTitle("Excluir Conta");
        alerta.setHeaderText("Aviso Crítico!");
        alerta.setContentText("Tem certeza que deseja apagar sua conta? Esta ação não pode ser desfeita.");

        Optional<ButtonType> resultado = alerta.showAndWait();
        if (resultado.isPresent() && resultado.get() == ButtonType.OK) {
            try {
                // Captura o ID do funcionário ativo na sessão
                int idempregadoLogado = HelloApplication.idEmpregadoLogado;

                // Executa a deleção na tabela física do banco de dados MySQL
                EmpregadoDAO.excluirEmpregado(idempregadoLogado);

                // Zera o token/ID de login por segurança e redireciona
                HelloApplication.idEmpregadoLogado = 0;
                Alert sucesso = new Alert (Alert.AlertType.INFORMATION);
                sucesso.setTitle("Exclusão bem Sucedida");
                sucesso.setContentText("Registro apagado com sucesso! Voltando para a tela inicial!");
                sucesso.showAndWait();

                HelloApplication.mudarTela("Tela_Inicial");
            } catch (Exception e) {
                mostrarAlerta(Alert.AlertType.ERROR, "Erro ao Apagar", "Não foi possível excluir: " + e.getMessage());
            }
        }
    }

    // Método disparado ao clicar em "Atualizar Dados"
    @FXML
    void atualizarDadosFuncionario(ActionEvent event) {
        try {
            int idempregadoLogado = HelloApplication.idEmpregadoLogado;

            // Puxa as informações atuais do funcionário direto do banco de dados
            Empregado empregadoAtual = EmpregadoDAO.buscarEmpregadoPorId(idempregadoLogado);

            if (empregadoAtual == null) {
                throw new Exception("Empregado não encontrado");
            }

            // Geração de Formulário Pop-Up Dinâmico via código
            Dialog<ButtonType> dialog = new Dialog<>();
            dialog.setTitle("Atualizar Dados");
            dialog.setHeaderText("Atualize os Dados que deseja atualizar e pressione Salvar:");

            ButtonType botaoSalvar = new ButtonType("Salvar", ButtonBar.ButtonData.OK_DONE);
            dialog.getDialogPane().getButtonTypes().add(botaoSalvar);

            // Caixas de input já nascem preenchidas com as Strings originais recuperadas do banco
            TextField txtNome = new TextField(empregadoAtual.getNome());
            TextField txtIdade = new TextField(empregadoAtual.getIdade());
            TextField txtCpf = new TextField(empregadoAtual.getCpf());
            TextField txtTelefone = new TextField(empregadoAtual.getTelefone());
            TextField txtSenha = new TextField(empregadoAtual.getSenha());
            TextField txtCargo = new TextField(empregadoAtual.getCargo());

            // Grid invisível de Linhas e Colunas para organizar as Labels e TextFields
            javafx.scene.layout.GridPane grid = new javafx.scene.layout.GridPane();
            grid.setHgap(10);
            grid.setVgap(10);
            grid.add(new javafx.scene.control.Label("Nome: "), 0, 0);
            grid.add(txtNome, 1, 0);
            grid.add(new javafx.scene.control.Label("Idade: "), 0, 1);
            grid.add(txtIdade, 1, 1);
            grid.add(new javafx.scene.control.Label("CPF: "), 0, 2);
            grid.add(txtCpf, 1, 2);
            grid.add(new javafx.scene.control.Label("Telefone: "), 0, 3);
            grid.add(txtTelefone, 1, 3);
            grid.add(new javafx.scene.control.Label("Senha: "), 0, 4);
            grid.add(txtSenha, 1, 4);
            grid.add(new javafx.scene.control.Label("Cargo: "), 0, 5);
            grid.add(txtCargo, 1, 5);

            dialog.getDialogPane().setContent(grid);
            Optional<ButtonType> resultado = dialog.showAndWait();

            if (resultado.isPresent() && resultado.get() == botaoSalvar) {
                // Validação de Barreira: Nome e CPF vazios interrompem o salvamento
                if (txtNome.getText().isBlank() || txtCpf.getText().isBlank()) {
                    mostrarAlerta(Alert.AlertType.WARNING, "Aviso", "Nome e CPF devem ser preenchidos!");
                    return;
                }

                // Altera as propriedades internas do objeto com as novas edições do usuário
                empregadoAtual.setNome(txtNome.getText());
                empregadoAtual.setIdade(txtIdade.getText());
                empregadoAtual.setCpf(txtCpf.getText());
                empregadoAtual.setTelefone(txtTelefone.getText());
                empregadoAtual.setSenha(txtSenha.getText());
                empregadoAtual.setCargo(txtCargo.getText());

                // Commita as atualizações disparando o UPDATE SQL do DAO
                EmpregadoDAO.atualizarEmpregado(empregadoAtual);

                Alert sucesso = new Alert(Alert.AlertType.INFORMATION);
                sucesso.setTitle("Sucesso");
                sucesso.setContentText("Empregado atualizado com sucesso!");
                sucesso.showAndWait();
            }
        } catch (Exception e) {
            e.printStackTrace();
            mostrarAlerta(Alert.AlertType.ERROR, "Erro", "Erro detalhado: " + e.toString());
        }
    }

    @FXML
    void irTelaInicial(ActionEvent event) {
        HelloApplication.mudarTela("Tela_Inicial");
    }

    // Exibe em formato de listagem de texto os dados individuais do funcionário ativo
    @FXML
    void visualizarContaFuncionario(ActionEvent event) {
        try {
            int idempregadoLogado = HelloApplication.idEmpregadoLogado;

            Empregado empregado = EmpregadoDAO.buscarEmpregadoPorId(idempregadoLogado);

            if (empregado != null) {
                String dados = "ID da Conta: " + empregado.getId() + "\n"
                        + "Nome: " + empregado.getNome() + "\n"
                        + "Idade: " + empregado.getIdade() + "\n"
                        + "CPF: " + empregado.getCpf() + "\n"
                        + "Telefone: " + empregado.getTelefone() + "\n"
                        + "Cargo: " + empregado.getCargo();

                Alert alerta = new Alert(Alert.AlertType.INFORMATION);
                alerta.setTitle("Meus Dados");
                alerta.setHeaderText("Informações da sua Conta");
                alerta.setContentText(dados);
                alerta.showAndWait();
            } else {
                mostrarAlerta(Alert.AlertType.ERROR, "Erro", "Conta não encontrada!");
            }
        } catch (Exception e){
            mostrarAlerta(Alert.AlertType.ERROR, "Erro de Sistema", e.getMessage());
        }
    }

    // Método Relatório: Abre uma janela externa contendo uma tabela estruturada de funcionários
    @FXML
    void visualizarFuncionarios(ActionEvent event) {
        try {
            // 1. Cria um novo palco virtual (Janela independente)
            Stage stage = new Stage();
            // Define o bloqueio de modalidade (o usuário não consegue clicar na janela de trás até fechar esta)
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Lista de Funcionários Registrados");

            // 2. Instancia a tabela gráfica do JavaFX
            TableView<Empregado> tabela = new TableView<>();

            // 3. Fabricação e parametrização das colunas da tabela
            TableColumn<Empregado, Integer> id = new TableColumn<>("ID");
            id.setCellValueFactory(new PropertyValueFactory<>("id")); // Linka com getId()

            TableColumn<Empregado, String> nome = new TableColumn<>("Nome");
            nome.setCellValueFactory(new PropertyValueFactory<>("nome")); // Linka com getNome()

            TableColumn<Empregado, String> idade = new TableColumn<>("Idade");
            idade.setCellValueFactory(new PropertyValueFactory<>("idade")); // Linka com getIdade()

            TableColumn<Empregado, String> cargo = new TableColumn<>("Cargo");
            cargo.setCellValueFactory(new PropertyValueFactory<>("cargo")); // Linka com getCargo()

            // Encaixa as colunas fabricadas dentro do componente de tabela
            tabela.getColumns().addAll(id, nome, idade, cargo);

            // 4. Mapeamento de dados: Busca a lista crua de funcionários e injeta na tabela de forma observável
            List<Empregado> funcionarios = EmpregadoDAO.ListarEmpregados();
            tabela.setItems(FXCollections.observableArrayList(funcionarios));

            // Configuração estética para autoajustar e expandir o tamanho das colunas na janela
            tabela.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY_FLEX_LAST_COLUMN);

            // 5. Montagem da Janela: Encaixa a tabela no painel de layout, define a cena e exibe a tela
            StackPane tela = new StackPane(tabela);
            Scene scene = new Scene(tela);
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
            mostrarAlerta(Alert.AlertType.ERROR, "Erro", "Não foi possível carregar os funcionários.");
        }

    }

    // Método Relatório: Mesma estrutura do anterior, focado em mapear e exibir os dados de Reservas do banco
    @FXML
    void visualizarReservas(ActionEvent event) {
        try {
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Lista de Reservas");

            TableView<Reserva> tabela = new TableView<>();

            // Mapeamento de propriedades das colunas de Reserva
            TableColumn<Reserva, Integer> id = new TableColumn<>("ID");
            id.setCellValueFactory(new PropertyValueFactory<>("id"));

            TableColumn<Reserva, String> data = new TableColumn<>("Data");
            data.setCellValueFactory(new PropertyValueFactory<>("dataReserva"));

            TableColumn<Reserva, String> evento = new TableColumn<>("Evento");
            evento.setCellValueFactory(new PropertyValueFactory<>("tipoEvento"));

            TableColumn<Reserva, String> mesa = new TableColumn<>("Quantidade de Mesas");
            mesa.setCellValueFactory(new PropertyValueFactory<>("prefMesa"));

            TableColumn<Reserva, String> qtdpessoas = new TableColumn<>("Quantidade de Pessoas");
            qtdpessoas.setCellValueFactory(new PropertyValueFactory<>("qtdPessoa"));

            TableColumn<Reserva, String> hora = new TableColumn<>("Horário");
            hora.setCellValueFactory(new PropertyValueFactory<>("horaReserva"));

            TableColumn<Reserva, String> observacao = new TableColumn<>("Observações Adicionais");
            observacao.setCellValueFactory(new PropertyValueFactory<>("observacao"));

            tabela.getColumns().addAll(id, data, evento, mesa, qtdpessoas, hora, observacao);

            // Coleta a lista corporativa de reservas vindas das tabelas SQL e popula a TableView
            List<Reserva> reservas = ReservaDAO.listarTodasReservas();
            tabela.setItems(FXCollections.observableArrayList(reservas));

            tabela.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY_FLEX_LAST_COLUMN);

            StackPane tela = new StackPane(tabela);
            Scene scene = new Scene(tela);
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void voltarTelaIntroducao(ActionEvent event) {
        HelloApplication.mudarTela("Tela_Introdutoria");
    }

    // Método utilitário centralizador para emissão de pop-ups de avisos padronizados
    private void mostrarAlerta(Alert.AlertType tipo, String titulo, String mensagem){
        Alert alerta = new Alert(tipo);
        alerta.setTitle(titulo);
        alerta.setContentText(mensagem);
        alerta.showAndWait();
    }
}