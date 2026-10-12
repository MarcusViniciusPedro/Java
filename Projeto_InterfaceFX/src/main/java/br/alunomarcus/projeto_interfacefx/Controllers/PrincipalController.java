package br.alunomarcus.projeto_interfacefx.Controllers;

import br.alunomarcus.projeto_interfacefx.HelloApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class PrincipalController {

    @FXML
    void FinalizarSessão(ActionEvent event) {
        HelloApplication.mudarTela("login");
    }

}

