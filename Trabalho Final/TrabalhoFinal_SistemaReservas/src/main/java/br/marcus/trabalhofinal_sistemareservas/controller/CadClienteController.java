package br.marcus.trabalhofinal_sistemareservas.controller;

// Importações das suas classes locais (Modelos, DAO e Aplicação Principal)
import br.marcus.trabalhofinal_sistemareservas.HelloApplication;
import br.marcus.trabalhofinal_sistemareservas.dao.ClienteDAO;
import br.marcus.trabalhofinal_sistemareservas.model.Cliente;

// Importações do JavaFX (Interface Gráfica)
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class CadClienteController {

    // A anotação @FXML "conecta" a variável do código com o componente visual lá no Scene Builder
    @FXML
    private TextField cpfField;

    @FXML
    private TextField emailField;

    @FXML
    private TextField idadeField;

    @FXML
    private TextField nomeField;

    @FXML
    private PasswordField senhaField; // PasswordField oculta os caracteres digitados (***)

    @FXML
    private TextField telefoneField;

    // Método disparado quando o usuário clica no botão de "Cadastrar" ou "Avançar"
    @FXML
    void irMenuCliente(ActionEvent event) {
        // 1. Captura os textos digitados pelo usuário na tela
        String cpf = cpfField.getText();
        String email = emailField.getText();
        String idade = idadeField.getText();
        String nome = nomeField.getText();
        String senha = senhaField.getText();
        String telefone = telefoneField.getText();

        try {
            // 2. Validação Básica: Verifica se algum campo foi deixado em branco
            if (nome.isBlank() || cpf.isBlank() || senha.isBlank() || telefone.isEmpty() || idade.isBlank() || email.isBlank()) {
                throw new Exception("Preencha todos os campos!");
            }
            // 3. Regras de Negócio de Idade
            int idadeNum = Integer.parseInt(idade); // Transforma a idade de Texto (String) para Número (int)
            if (idadeNum < 0 || idadeNum > 100) {
                throw new IllegalArgumentException("Digite uma idade correspondente ao intervalo de 0 a 100 anos");
            }
            if (idadeNum < 18) {
                throw new IllegalArgumentException("Apenas maiores de 18 anos podem se registrar, peça para um responsável realizar o cadastro!");
            }
            // 4. Regra de Negócio de E-mail Único (consulta o Banco de Dados)
            if (ClienteDAO.verificacaoDeEmailExistente(email)) {
                throw new IllegalArgumentException("Este e-mail já está em uso! Por favor, utilize um e-mail diferente!");
            }

            // 5. Criação e Salvamento: Monta o objeto Cliente com os dados aprovados
            Cliente cliente = new Cliente(nome, idade, cpf, email, telefone, senha);
            // Manda salvar no banco e recebe de volta o ID gerado para ele
            int idGerado = ClienteDAO.cadastrarCliente(cliente);
            // Salva esse ID "globalmente" para o sistema saber quem está logado
            HelloApplication.idUsuarioLogado = idGerado;

            // 6. Finalização com Sucesso
            mostrarAlerta(Alert.AlertType.INFORMATION, "Sucesso", "Cliente cadastrado com sucesso!");
            limparCampos(); // Esvazia os text fields
            HelloApplication.mudarTela("Menu_Cliente"); // Redireciona o usuário para o Menu

        } catch (NumberFormatException e) {
            // Cai aqui se o Integer.parseInt falhar (ex: usuário digitou letras na idade)
            mostrarAlerta(Alert.AlertType.WARNING, "Idade Inválida", "A idade deve ser informada apenas com números.");
        } catch (IllegalArgumentException e) {
            // Cai aqui se bater nas regras de idade ou email repetido
            mostrarAlerta(Alert.AlertType.WARNING, "Aviso de Cadastro", e.getMessage());
        } catch (Exception e) {
            // Cai aqui se for um erro do banco de dados (ex: CPF já cadastrado que é regra UNIQUE do MySQL)
            if (e.getMessage().contains("Duplicate entry") && e.getMessage().contains("cpf")) {
                mostrarAlerta(Alert.AlertType.WARNING, "CPF já Cadastrado", "Este CPF já existe no Banco de Dados! Por gentileza, digite outro CPF.");
            } else {
            mostrarAlerta(Alert.AlertType.ERROR, "Erro no Banco de Dados", "Não foi possível cadastrar: " + e.getMessage());
            }
        }
    }

    // Método para os botões de voltar/cancelar
    @FXML
    void irTelaInicial(ActionEvent event) {
        HelloApplication.mudarTela("Tela_Inicial");
    }

    @FXML
    void voltarTelaIntroducao(ActionEvent event) {
        HelloApplication.mudarTela("Tela_Introdutoria");
    }

    // Método utilitário que "fabrica" janelas de aviso padronizadas
    private void mostrarAlerta(Alert.AlertType tipo, String titulo, String mensagem){
        Alert alerta = new Alert(tipo);
        alerta.setTitle(titulo);
        alerta.setContentText(mensagem);
        alerta.showAndWait(); // Pausa o programa até o usuário fechar o alerta
    }

    // Método utilitário para resetar os campos da tela
    private void limparCampos(){
        nomeField.clear();
        cpfField.clear();
        emailField.clear();
        senhaField.clear();
        telefoneField.clear();
        idadeField.clear();
    }
}