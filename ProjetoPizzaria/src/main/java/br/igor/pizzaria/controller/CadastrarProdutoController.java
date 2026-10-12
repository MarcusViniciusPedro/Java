package br.igor.pizzaria.controller;
import br.igor.pizzaria.dao.CategoriaDAO;
import br.igor.pizzaria.dao.ProdutoDAO;
import br.igor.pizzaria.model.Categoria;
import br.igor.pizzaria.model.Produto;
import br.igor.pizzaria.util.Navegador;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.util.List;

public class CadastrarProdutoController {

    @FXML
    private ComboBox<Categoria> cbCategoria;

    @FXML
    private TextField txtDescricaoProduto;

    @FXML
    private TextField txtPreco;

    @FXML
    private TextField txtnomeProduto;

    private CategoriaDAO categoriaDAO = new CategoriaDAO();
    private ProdutoDAO produtoDAO = new ProdutoDAO();

    @FXML
    public void initialize() {
        List<Categoria> categorias = categoriaDAO.listar();
        cbCategoria.setItems(FXCollections.observableArrayList(categorias));
    }

    @FXML
    void CadastrarProduto(ActionEvent event) {
        String nome = txtnomeProduto.getText();
        Double preco = Double.parseDouble(txtPreco.getText());
        String descricao = txtDescricaoProduto.getText();
        Categoria categoria = cbCategoria.getValue();

        Produto novoProduto = new Produto(nome, preco, descricao, categoria);
        produtoDAO.cadastrar(novoProduto);
        Navegador.trocarTela(event, "/br/igor/pizzaria/menuPrincipal.fxml");
    }

    @FXML
    void IrAdicionarCategoria(ActionEvent event) {
        Navegador.trocarTela(event, "/br/igor/pizzaria/cadastroCategoria.fxml");
    }

    @FXML
    void VoltarMenuPrincipal(ActionEvent event) {
        Navegador.trocarTela(event, "/br/igor/pizzaria/menuPrincipal.fxml");
    }

}
