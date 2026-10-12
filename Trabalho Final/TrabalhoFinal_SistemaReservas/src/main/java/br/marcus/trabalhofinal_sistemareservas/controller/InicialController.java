package br.marcus.trabalhofinal_sistemareservas.controller;

import br.marcus.trabalhofinal_sistemareservas.HelloApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class InicialController {

    // Métodos de navegação simples entre telas
    @FXML
    void irCadastroCliente(ActionEvent event) {
        HelloApplication.mudarTela("Cadastro_Cliente");
    }

    @FXML
    void irCadastroFuncionario(ActionEvent event) {
        HelloApplication.mudarTela("Cadastro_Empregado");
    }

    @FXML
    void voltarTelaIntroducao(ActionEvent event) {
        HelloApplication.mudarTela("Tela_Introdutoria");
    }

}