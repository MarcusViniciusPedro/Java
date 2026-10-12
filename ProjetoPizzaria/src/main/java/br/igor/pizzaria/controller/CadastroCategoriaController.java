package br.igor.pizzaria.controller;

import br.igor.pizzaria.dao.CategoriaDAO;
import br.igor.pizzaria.model.Categoria;
import br.igor.pizzaria.util.Navegador;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

public class CadastroCategoriaController {

    @FXML private TextField txtNomeCategoria;
    private CategoriaDAO categoriaDAO = new CategoriaDAO();

    @FXML
    public void salvarCategoria() {
        String nome = txtNomeCategoria.getText();
        if (nome == null || nome.trim().isEmpty()) {
            mostrarAlerta(Alert.AlertType.WARNING, "Aviso", "O nome da categoria não pode estar vazio.");
            return;
        }

        Categoria novaCategoria = new Categoria(nome);
        if (categoriaDAO.cadastrar(novaCategoria)) {
            mostrarAlerta(Alert.AlertType.INFORMATION, "Sucesso", "Categoria cadastrada!");
            txtNomeCategoria.clear();
        } else {
            mostrarAlerta(Alert.AlertType.ERROR, "Erro", "Não foi possível cadastrar a categoria.");
        }
    }

    @FXML
    public void voltarMenu(ActionEvent event) {
        Navegador.trocarTela(event, "/br/igor/pizzaria/menuPrincipal.fxml");
    }

    private void mostrarAlerta(Alert.AlertType tipo, String titulo, String mensagem) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensagem);
        alert.showAndWait();
    }
}