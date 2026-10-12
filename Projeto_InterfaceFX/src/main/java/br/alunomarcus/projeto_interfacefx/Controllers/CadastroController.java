package br.alunomarcus.projeto_interfacefx.Controllers;

import br.alunomarcus.projeto_interfacefx.HelloApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class CadastroController {

    @FXML
    private PasswordField txtConfirmeSenha;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtIdade;

    @FXML
    private TextField txtNome;

    @FXML
    private PasswordField txtSenha;

    @FXML
    void FinalizaCadastro(ActionEvent event) {
        HelloApplication.mudarTela("login");
    }

}
