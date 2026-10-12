package br.marcus.trabalhofinal_sistemareservas.controller;

import br.marcus.trabalhofinal_sistemareservas.HelloApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class IntroducaoController {

    // Método de navegação simples entre telas
    @FXML
    void irTelaInicial(ActionEvent event) {
        HelloApplication.mudarTela("Tela_Inicial");
    }

}