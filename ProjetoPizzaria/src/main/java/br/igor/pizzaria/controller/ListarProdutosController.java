package br.igor.pizzaria.controller;
import br.igor.pizzaria.dao.ProdutoDAO;
import br.igor.pizzaria.model.Produto;
import br.igor.pizzaria.util.Navegador;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.List;

public class ListarProdutosController {

    @FXML
    private TableView<Produto> tvProdutos;

    private ProdutoDAO produtoDAO = new ProdutoDAO();

    @FXML
    public void initialize() {
        TableColumn<Produto, String> colNome = new TableColumn<>("Nome");
        colNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        TableColumn<Produto, String> colDescricao = new TableColumn<>("Descricao");
        colDescricao.setCellValueFactory(new PropertyValueFactory<>("descricao"));
        TableColumn<Produto, Double> colPreco = new TableColumn<>("Preco");
        colPreco.setCellValueFactory(new PropertyValueFactory<>("preco"));
        TableColumn<Produto, String> colCategoria = new TableColumn<>("Categoria");
        colCategoria.setCellValueFactory(cellData->new SimpleStringProperty(cellData.getValue().getCategoria().getNome()));

        tvProdutos.getColumns().addAll(colNome, colDescricao, colPreco, colCategoria);

        List<Produto> produtos = produtoDAO.listar();

        tvProdutos.setItems(FXCollections.observableArrayList(produtos));
    }

    @FXML
    void irCadastrarProduto(ActionEvent event) {
        Navegador.trocarTela(event, "/br/igor/pizzaria/cadastroCategoria.fxml");
    }

    @FXML
    void voltarMenuPrincipal(ActionEvent event) {
        Navegador.trocarTela(event, "/br/igor/pizzaria/menuPrincipal.fxml");
    }

}
