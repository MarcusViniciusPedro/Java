package br.marcus.mercadinho.controller;

import br.marcus.mercadinho.HelloApplication;
import br.marcus.mercadinho.dao.FornecedorDao;
import br.marcus.mercadinho.model.Fornecedor;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

public class CadFornecedorController {

    @FXML
    private TextField cnpjField;

    @FXML
    private TextField nomefantasiaField;

    @FXML
    private TextField telefoneField;

    @FXML
    void CadastrarCliqueF(ActionEvent event) {
        String Cnpj = cnpjField.getText();
        String NomeFantasia = nomefantasiaField.getText();
        String Telefone = telefoneField.getText();

        try {
            Fornecedor fornecedor = new Fornecedor(Cnpj, NomeFantasia, Telefone);
            FornecedorDao.salvar(fornecedor);
            mostrarAlerta(Alert.AlertType.INFORMATION, "Sucesso", "Fornecedo cadastrado com sucesso!");
            limparCampos();

        } catch(NumberFormatException e){
            mostrarAlerta(Alert.AlertType.ERROR, "Erro de Formatação", "Por favor digite um valor numérico válido!");
        } catch (Exception e){
            mostrarAlerta(Alert.AlertType.ERROR, "Erro no Banco de Dados", "Não foi possível cadastrar: " + e.getMessage());
        }
    }

    @FXML
    void CadastrarProdutoF(ActionEvent event) {
        HelloApplication.mudarTela("produto");
    }

    private void mostrarAlerta(Alert.AlertType tipo, String titulo, String mensagem){
        Alert alerta = new Alert(tipo);
        alerta.setTitle(titulo);
        alerta.setContentText(mensagem);
        alerta.showAndWait();
    }

    private void limparCampos(){
        cnpjField.clear();
        nomefantasiaField.clear();
        telefoneField.clear();
        cnpjField.requestFocus();
    }

}