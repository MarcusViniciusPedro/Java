package br.alunomarcus.projeto_interfacefx.Controllers;

import br.alunomarcus.projeto_interfacefx.HelloApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginController {

    @FXML
    private PasswordField txtSenha;

    @FXML
    private TextField txtUsuario;

    @FXML
    void ClicarCadastrar(ActionEvent event) {
        HelloApplication.mudarTela("principal");
    }

    @FXML
    void ClicarEntrar(ActionEvent event) {
        HelloApplication.mudarTela("cadastro");
    }
}

