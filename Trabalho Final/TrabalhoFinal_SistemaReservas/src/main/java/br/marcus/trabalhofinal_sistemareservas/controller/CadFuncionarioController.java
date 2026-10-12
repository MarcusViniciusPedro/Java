package br.marcus.trabalhofinal_sistemareservas.controller;

// Importações locais das regras de negócio, persistência e estado global do sistema
import br.marcus.trabalhofinal_sistemareservas.HelloApplication;
import br.marcus.trabalhofinal_sistemareservas.dao.EmpregadoDAO;
import br.marcus.trabalhofinal_sistemareservas.model.Empregado;

// Importações do ecossistema gráfico JavaFX
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class CadFuncionarioController {

    // A anotação @FXML vincula magneticamente estes objetos com as caixas de texto desenhadas no Scene Builder
    @FXML
    private TextField cargoField;

    @FXML
    private TextField cpfField;

    @FXML
    private TextField idadeField;

    @FXML
    private TextField nomeField;

    @FXML
    private PasswordField senhaField; // Oculta a digitação da senha por motivos de segurança

    @FXML
    private TextField telefoneField;

    // Método principal chamado quando o usuário clica no botão para concluir o cadastro do funcionário
    @FXML
    void irMenuFuncionario(ActionEvent event) {
        // 1. Extrai os textos de dentro dos componentes visuais da tela
        String cargo = cargoField.getText();
        String cpf = cpfField.getText();
        String idade = idadeField.getText();
        String nome = nomeField.getText();
        String senha = senhaField.getText();
        String telefone = telefoneField.getText();

        try {
            // 2. Validação de Campos Obrigatórios: Bloqueia caso haja qualquer campo em branco
            if (nome.isBlank() || cpf.isBlank() || idade.isBlank() || telefone.isBlank() || senha.isEmpty() || cargo.isBlank()) {
                throw new Exception("Preencha todos os campos!");
            }

            // 3. Regra de Negócio Trabalhista: Converte o texto da idade para número e valida
            int idadeNum = Integer.parseInt(idade);
            if (idadeNum < 18) {
                // Interrompe o fluxo lançando uma exceção de argumento inválido
                throw new IllegalArgumentException("É proibida a contratação de menores de idade!");
            }

            // 4. Instanciação e Persistência: Cria o objeto de modelo se as regras passaram
            Empregado empregado = new Empregado(nome, idade, cpf, telefone, senha, cargo);
            // Grava o funcionário no banco e resgata a Chave Primária (ID Auto_Increment) gerada pelo MySQL
            int idGerado = EmpregadoDAO.cadastrarEmpregado(empregado);
            // Define o ID global da sessão ativa como o do funcionário recém-criado
            HelloApplication.idEmpregadoLogado = idGerado;

            // 5. Sucesso e Navegação
            mostrarAlerta(Alert.AlertType.INFORMATION, "Sucesso", "Empregado cadastrado com sucesso!");
            limparCampos(); // Reseta os dados digitados na tela
            HelloApplication.mudarTela("Menu_Empregado"); // Direciona para o painel administrativo correspondente

        } catch (NumberFormatException e) {
            // Disparado automaticamente se o usuário digitar letras ou símbolos no campo idade
            mostrarAlerta(Alert.AlertType.WARNING, "Idade Inválida", "A idade deve ser informada apenas com números.");
        } catch (IllegalArgumentException e) {
            // Captura o bloqueio da contratação de menores de idade
            mostrarAlerta(Alert.AlertType.WARNING, "Aviso de Cadastro", e.getMessage());
        } catch (Exception e) {
            // Captura erros gerais de infraestrutura e restrições UNIQUE do Banco de Dados (ex: CPF já cadastrado)
            if (e.getMessage().contains("Duplicate entry") && e.getMessage().contains("cpf")) {
                mostrarAlerta(Alert.AlertType.WARNING, "CPF já Cadastrado", "Este CPF de funcionário já existe!");
            } else {
                mostrarAlerta(Alert.AlertType.ERROR, "Erro no Banco de Dados", "Não foi possível cadastrar: " + e.getMessage());
            }
        }
    }

    // Métodos para controle de rotas e navegação retroativa do usuário
    @FXML
    void irTelaInicial(ActionEvent event) {
        HelloApplication.mudarTela("Tela_Inicial");
    }

    @FXML
    void voltarTelaIntroducao(ActionEvent event) {
        HelloApplication.mudarTela("Tela_Introdutoria");
    }

    // Método utilitário centralizado para fabricar caixas de mensagens flutuantes na tela
    private void mostrarAlerta(Alert.AlertType tipo, String titulo, String mensagem){
        Alert alerta = new Alert(tipo);
        alerta.setTitle(titulo);
        alerta.setContentText(mensagem);
        alerta.showAndWait(); // Exibe a janela de forma modal (bloqueia o clique atrás até ser fechada)
    }

    // Método utilitário para resetar os estados dos inputs
    private void limparCampos(){
        nomeField.clear(); // Limpa o Texto
        cpfField.requestFocus(); // Requisita o foco do teclado para este campo (não apaga o texto)
        cargoField.clear(); // Limpa o texto
        senhaField.requestFocus(); // Requisita o foco do teclado para este campo (não apaga o texto)
        telefoneField.clear(); // Limpa o texto
        idadeField.clear(); // Limpa o texto
    }
}