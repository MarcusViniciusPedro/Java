package br.igor.pizzaria.controller;

import br.igor.pizzaria.util.Navegador;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class MenuPrincipalController {

    @FXML
    public void abrirCadastroCategoria(ActionEvent event) {
        Navegador.trocarTela(event, "/br/igor/pizzaria/cadastroCategoria.fxml");
    }
    public void abrirCadastroProduto(ActionEvent event) {
        Navegador.trocarTela(event, "/br/igor/pizzaria/cadastroProduto.fxml");
    }
}
