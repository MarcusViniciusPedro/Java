package br.marcus.mercadinho.controller;

import br.marcus.mercadinho.HelloApplication;
import br.marcus.mercadinho.dao.ProdutoDao;
import br.marcus.mercadinho.model.Produto;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

public class CadProdutoController {

    @FXML
    private TextField descricaoField;

    @FXML
    private TextField precoField;

    @FXML
    private TextField quantidadeField;

    @FXML
    void CadastrarClique(ActionEvent event) {
        String descricao = descricaoField.getText();
        String preco = precoField.getText();
        String quantidade = quantidadeField.getText();

        try {
            double precoNum = Double.parseDouble(preco.replace(",", ","));
            int quantidadeNum = Integer.parseInt(quantidade);

            Produto produto = new Produto(descricao, precoNum, quantidadeNum);
            ProdutoDao.cadastrar(produto);
            mostrarAlerta(Alert.AlertType.INFORMATION, "Sucesso", "Produto cadastrado com sucesso!");
            limparCampos();

        } catch(NumberFormatException e){
            mostrarAlerta(Alert.AlertType.ERROR, "Erro de Formatação", "Por favor digite um valor numérico válido!");
        } catch (Exception e){
            mostrarAlerta(Alert.AlertType.ERROR, "Erro no Banco de Dados", "Não foi possível cadastrar: " + e.getMessage());
        }
    }

    @FXML
    void CadastrarFornecedor(ActionEvent event) {
        HelloApplication.mudarTela("fornecedor");
    }

    private void mostrarAlerta(Alert.AlertType tipo, String titulo, String mensagem){
        Alert alerta = new Alert(tipo);
        alerta.setTitle(titulo);
        alerta.setContentText(mensagem);
        alerta.showAndWait();
    }

    private void limparCampos(){
        descricaoField.clear();
        precoField.clear();
        quantidadeField.clear();
        descricaoField.requestFocus();
    }

}