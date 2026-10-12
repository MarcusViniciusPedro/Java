package br.marcus.trabalhofinal_sistemareservas.controller;

// Importações globais da sua aplicação (Sessão, Camada de Acesso ao Banco e Entidade)
import br.marcus.trabalhofinal_sistemareservas.HelloApplication;
import br.marcus.trabalhofinal_sistemareservas.dao.ClienteDAO;
import br.marcus.trabalhofinal_sistemareservas.model.Cliente;

// Importações do JavaFX para tratamento de eventos e gerenciamento de elementos de interface
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

// Importação nativa para lidar com fluxos de dados opcionais com segurança
import java.util.Optional;

public class MenuClienteController {

    // Método disparado quando o usuário clica no botão "Apagar Conta"
    @FXML
    void apagarConta(ActionEvent event) {
        // 1. Cria uma caixa de alerta do tipo CONFIRMATION (Exibe botões de "OK" e "Cancelar")
        Alert alerta = new Alert(Alert.AlertType.CONFIRMATION);
        alerta.setTitle("Excluir Conta");
        alerta.setHeaderText("Aviso Crítico!");
        alerta.setContentText("Tem certeza que deseja apagar sua conta? Esta ação não pode ser desfeita.");

        // 2. Abre a janela e fica congelado aguardando a resposta do usuário
        Optional<ButtonType> resultado = alerta.showAndWait();
        // Verifica se o usuário de fato clicou no botão "OK"
        if (resultado.isPresent() && resultado.get() == ButtonType.OK) {
            try {
                // Captura quem é o usuário logado no momento
                int idlogado = HelloApplication.idUsuarioLogado;

                // Executa a query de DELETE no banco através do DAO
                ClienteDAO.excluirCliente(idlogado);

                // IMPORTANTE: Reseta a sessão de login para 0 (Desloga o usuário do sistema)
                HelloApplication.idUsuarioLogado = 0;

                // Exibe mensagem de aviso positivo de encerramento
                Alert sucesso = new Alert (Alert.AlertType.INFORMATION);
                sucesso.setTitle("Exclusão bem Sucedida");
                sucesso.setContentText("Registro apagado com sucesso! Voltando para a tela inicial!");
                sucesso.showAndWait();

                // Redireciona o usuário para a tela inicial do software
                HelloApplication.mudarTela("Tela_Inicial");
            } catch (Exception e) {
                mostrarAlerta(Alert.AlertType.ERROR, "Erro ao Apagar", "Não foi possível excluir: " + e.getMessage());
            }
        }
    }

    // Método disparado quando o usuário clica no botão de "Atualizar Dados"
    @FXML
    void atualizarDadosConta(ActionEvent event) {
        try {
            // Busca o ID de quem está conectado e baixa do banco o objeto Cliente completo dele
            int idLogado = HelloApplication.idUsuarioLogado;
            Cliente clienteAtual = ClienteDAO.buscarClientePorId(idLogado);

            if (clienteAtual == null) {
                throw new Exception("Cliente não encontrado");
            }

            // 1. Criação Dinâmica de Interface Gráfica por Código (Sem arquivo FXML!)
            Dialog<ButtonType> dialog = new Dialog<>();
            dialog.setTitle("Atualizar Dados");
            dialog.setHeaderText("Atualize os Dados que deseja atualizar e pressione Salvar:");

            // Cria e injeta o botão de "Salvar" dentro do painel do Dialog
            ButtonType botaoSalvar = new ButtonType("Salvar", ButtonBar.ButtonData.OK_DONE);
            dialog.getDialogPane().getButtonTypes().add(botaoSalvar);

            // Cria caixas de texto pré-preenchidas com os dados ATUAIS que vieram do banco de dados
            TextField txtNome = new TextField(clienteAtual.getNome());
            TextField txtIdade = new TextField(clienteAtual.getIdade());
            TextField txtCpf = new TextField(clienteAtual.getCpf());
            TextField txtEmail = new TextField(clienteAtual.getEmail());
            TextField txtTelefone = new TextField(clienteAtual.getTelefone());
            TextField txtSenha = new TextField(clienteAtual.getSenha());

            // 2. Organização Visual: Cria uma tabela invisível de linhas e colunas (GridPane)
            javafx.scene.layout.GridPane grid = new javafx.scene.layout.GridPane();
            grid.setHgap(10); // Espaçamento horizontal entre colunas
            grid.setVgap(10); // Espaçamento vertical entre linhas

            // Adiciona rótulos (Labels) e inputs no formato (Elemento, Coluna, Linha)
            grid.add(new javafx.scene.control.Label("Nome: "), 0, 0);
            grid.add(txtNome, 1, 0);
            grid.add(new javafx.scene.control.Label("Idade: "), 0, 1);
            grid.add(txtIdade, 1, 1);
            grid.add(new javafx.scene.control.Label("CPF: "), 0, 2);
            grid.add(txtCpf, 1, 2);
            grid.add(new javafx.scene.control.Label("E-Mail: "), 0, 3);
            grid.add(txtEmail, 1, 3);
            grid.add(new javafx.scene.control.Label("Telefone: "), 0, 4);
            grid.add(txtTelefone, 1, 4);
            grid.add(new javafx.scene.control.Label("Senha: "), 0, 5);
            grid.add(txtSenha, 1, 5);

            // Define a tabela que criamos como o conteúdo visível do pop-up
            dialog.getDialogPane().setContent(grid);

            // Trava a tela e aguarda o clique do usuário
            Optional<ButtonType> resultado = dialog.showAndWait();

            // 3. Processamento do Retorno da Atualização
            if (resultado.isPresent() && resultado.get() == botaoSalvar) {
                // Validação de barreira rápida (Campos críticos não podem ficar em branco)
                if (txtNome.getText().isBlank() || txtCpf.getText().isBlank()) {
                    mostrarAlerta(Alert.AlertType.WARNING, "Aviso", "Nome e CPF devem ser preenchidos!");
                    return; // Cancela a atualização se falhar aqui
                }

                // Injeta os novos valores digitados pelo usuário de volta no objeto Java
                clienteAtual.setNome(txtNome.getText());
                clienteAtual.setIdade(txtIdade.getText());
                clienteAtual.setCpf(txtCpf.getText());
                clienteAtual.setEmail(txtEmail.getText());
                clienteAtual.setTelefone(txtTelefone.getText());
                clienteAtual.setSenha(txtSenha.getText());

                // Envia o objeto modificado para rodar a instrução de UPDATE SQL no MySQL
                ClienteDAO.atualizarCliente(clienteAtual);

                Alert sucesso = new Alert(Alert.AlertType.INFORMATION);
                sucesso.setTitle("Sucesso");
                sucesso.setContentText("Cliente atualizado com sucesso!");
                sucesso.showAndWait();
            }
        } catch (Exception e) {
            e.printStackTrace(); // Imprime o rastreio do erro no terminal para debug do desenvolvedor
            mostrarAlerta(Alert.AlertType.ERROR, "Erro", "Erro detalhado: " + e.toString());
        }
    }

    // Métodos simples de controle de rotas
    @FXML
    void irCadastroReserva(ActionEvent event) {
        HelloApplication.mudarTela("Cadastro_Reserva");
    }

    @FXML
    void irTelaIncial(ActionEvent event) {
        HelloApplication.mudarTela("Tela_Inicial");
    }

    // Método disparado ao clicar em "Visualizar Meus Dados"
    @FXML
    void visualizarDadosConta(ActionEvent event) {
        try {
            int idLogado = HelloApplication.idUsuarioLogado;

            // Busca os dados atualizados do usuário no banco
            Cliente cliente = ClienteDAO.buscarClientePorId(idLogado);

            if (cliente != null) {
                // Concatena as variáveis do objeto em uma única String pulando linhas (\n)
                String dados = "ID da Conta: " + cliente.getId() + "\n"
                        + "Nome: " + cliente.getNome() + "\n"
                        + "CPF: " + cliente.getCpf() + "\n"
                        + "Idade: " + cliente.getIdade() + "\n"
                        + "Telefone: " + cliente.getTelefone() + "\n"
                        + "E-mail: " + cliente.getEmail();

                // Cospe o bloco de texto formatado elegante dentro de um Alerta Informativo
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

    @FXML
    void voltarTelaIntroducao(ActionEvent event) {
        HelloApplication.mudarTela("Tela_Introdutoria");
    }

    // Método utilitário reaproveitável para fabricar mensagens na tela de forma otimizada
    private void mostrarAlerta(Alert.AlertType tipo, String titulo, String mensagem){
        Alert alerta = new Alert(tipo);
        alerta.setTitle(titulo);
        alerta.setContentText(mensagem);
        alerta.showAndWait();
    }
}