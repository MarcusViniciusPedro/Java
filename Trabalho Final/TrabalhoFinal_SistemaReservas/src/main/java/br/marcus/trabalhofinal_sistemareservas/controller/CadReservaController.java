package br.marcus.trabalhofinal_sistemareservas.controller;

// Importações do ecossistema global do projeto (Aplicação, Banco de Dados e Modelo)
import br.marcus.trabalhofinal_sistemareservas.HelloApplication;
import br.marcus.trabalhofinal_sistemareservas.dao.ReservaDAO;
import br.marcus.trabalhofinal_sistemareservas.model.Reserva;

// Importações do JavaFX para gerenciamento da tela e componentes visuais
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

// Importações do pacote java.time (Validações modernas de Data e Hora)
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class CadReservaController {

    // Injeção de componentes visuais do arquivo FXML (campos de entrada de texto)
    @FXML
    private TextField dataReservaField;

    @FXML
    private TextField horaReservaField;

    @FXML
    private TextField infAdicionaisField;

    @FXML
    private TextField mesaField;

    @FXML
    private TextField qtdPessoaField;

    @FXML
    private TextField tipoEventoField;

    // Método principal: Acionado ao clicar no botão para confirmar a reserva
    @FXML
    void irMenuClienteComReserva(ActionEvent event) {
        // 1. Coleta os valores textuais que o cliente digitou na tela
        String dataReserva = dataReservaField.getText();
        String horaReserva = horaReservaField.getText();
        String infAdicionais = infAdicionaisField.getText();
        String mesa = mesaField.getText();
        String qtdPessoa = qtdPessoaField.getText();
        String tipoEvento = tipoEventoField.getText();

        try {
            // 2. VALIDAÇÃO MATEMÁTICA: Converte os textos para inteiros e remove espaços (.trim())
            int prefMesa = Integer.parseInt(mesa.trim());
            int qtdPessoas = Integer.parseInt(qtdPessoa.trim());

            // Regra de Negócio: Limite Físico de Mesas (Máximo 50)
            if (prefMesa > 50) {
                throw new IllegalArgumentException("Infelizmente não possuímos a quantidade desejada de mesas! Nosso limite é de 50 mesas.");
            }
            // Regra de Negócio: Limite de Ocupação da Hamburgueria (Máximo 250 pessoas)
            if (qtdPessoas > 250) {
                throw new IllegalArgumentException("Capacidade Máxima Excedida! O limite de pessoas no estabelecimento é 250.");
            }

            // 3. CONFIGURAÇÃO DE TEMPO: Criando máscaras de leitura no padrão brasileiro
            DateTimeFormatter formatterDate = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            DateTimeFormatter formatterTime = DateTimeFormatter.ofPattern("HH:mm");

            // 4. VALIDAÇÃO TEMPORAL: Tratamento da Data
            LocalDate dataEscolhida;
            try {
                // Tenta transformar o texto em uma data real baseada na máscara (dd/MM/yyyy)
                dataEscolhida = LocalDate.parse(dataReserva, formatterDate);
            } catch (DateTimeParseException e){
                // Interrompe o fluxo se o formato estiver errado (ex: usuário digitou 2026/12/25 ou letras)
                throw new IllegalArgumentException("Formato da data inválido! Digite conforme o exemplo: dd/MM/yyyy");
            }

            // Regra de Negócio: Impede "Viagem no Tempo" (Garante que a data não seja no passado)
            if (dataEscolhida.isBefore(LocalDate.now())) {
                throw new IllegalArgumentException("Não é possível reservar para datas que já passaram");
            }

            // 5. VALIDAÇÃO TEMPORAL: Tratamento da Hora
            LocalTime horaEscolhida;
            try {
                // Tenta transformar o texto em uma hora real baseada na máscara (HH:mm)
                horaEscolhida = LocalTime.parse(horaReserva, formatterTime);
            } catch (DateTimeParseException e){
                throw new IllegalArgumentException("Formato de hora inválido! Digite conforme o exemplo: HH:mm");
            }

            // Regra de Negócio: Horário de Funcionamento (Estabelece abertura às 07:00 e fechamento às 23:00)
            LocalTime openTime = LocalTime.of(7, 0);
            LocalTime closeTime = LocalTime.of(23, 0);

            // Verifica se a hora desejada vem antes da abertura OU depois do fechamento
            if (horaEscolhida.isBefore(openTime) || horaEscolhida.isAfter(closeTime)) {
                throw new IllegalArgumentException("Horário indisponível! Nosso funcionamento é das 7h00 às 23h00.");
            }

            // 6. VALIDAÇÃO DE CONFLITO (Banco de Dados): Garante que não existam 2 eventos no mesmo dia
            if (ReservaDAO.verificarReservaExistentePorData(dataReserva)) {
                throw new IllegalArgumentException("Já existe um evento programado para esse dia, por favor escolha outra data");
            }

            // 7. PERSISTÊNCIA: Se o fluxo chegou aqui sem erros, monta a Reserva e salva no MySQL via DAO
            Reserva reserva = new Reserva(dataReserva, tipoEvento, prefMesa, qtdPessoas, horaReserva, infAdicionais);
            ReservaDAO.cadastrarReserva(reserva);

            // 8. FINALIZAÇÃO: Alerta positivo, limpeza e redirecionamento de tela
            mostrarAlerta(Alert.AlertType.INFORMATION, "Sucesso", "Reserva cadastrada com sucesso!");
            limparCampos();
            HelloApplication.mudarTela("Menu_Cliente");

        } catch (NumberFormatException e) {
            // Capturado se Integer.parseInt falhar (letras nos campos numéricos)
            mostrarAlerta(Alert.AlertType.ERROR, "Erro de Formatação", "Por favor, digite apenas números inteiros nos campos de Mesas e Pessoas!");
        } catch (IllegalArgumentException e) {
            // Captura qualquer uma das nossas regras de negócio infringidas (mensagens personalizadas via e.getMessage())
            mostrarAlerta(Alert.AlertType.WARNING, "Regras do Estabelecimento", e.getMessage());
        } catch (Exception e) {
            // Captura falhas de infraestrutura, conexão com banco ou erros genéricos
            mostrarAlerta(Alert.AlertType.ERROR, "Erro no Banco de Dados", "Não foi possível cadastrar a reserva: " + e.getMessage());
        }
    }

    // Métodos de navegação simples entre telas
    @FXML
    void irMenuClienteSemReserva(ActionEvent event) {
        HelloApplication.mudarTela("Menu_Cliente");
    }

    @FXML
    void voltarTelaIntroducao(ActionEvent event) {
        HelloApplication.mudarTela("Tela_Introdutoria");
    }

    // Método utilitário reaproveitável para criar pop-ups de avisos na tela
    private void mostrarAlerta(Alert.AlertType tipo, String titulo, String mensagem) {
        Alert alerta = new Alert(tipo);
        alerta.setTitle(titulo);
        alerta.setContentText(mensagem);
        alerta.showAndWait();
    }

    // Método utilitário para resetar os campos da interface gráfica
    private void limparCampos() {
        // Observação técnica: .requestFocus() muda o cursor para o campo, mas NÃO apaga o texto.
        // Como o segundo comando sobrescreve o primeiro, o cursor terminará fixado no 'horaReservaField'.
        dataReservaField.requestFocus();
        horaReservaField.requestFocus();
        // Limpa efetivamente os textos dos demais inputs
        infAdicionaisField.clear();
        mesaField.clear();
        qtdPessoaField.clear();
        tipoEventoField.clear();
    }
}