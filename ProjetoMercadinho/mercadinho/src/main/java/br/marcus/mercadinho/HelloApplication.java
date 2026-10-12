package br.marcus.mercadinho;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    private static Stage stage;

    private static Scene sceneProduto;
    private static Scene sceneFornecedor;

    @Override
    public void start(Stage stageInicial) throws IOException {
        stage = stageInicial;
        FXMLLoader fxmlLoaderProduto = new FXMLLoader(HelloApplication.class.getResource("cadastroProduto.fxml"));
        FXMLLoader fxmlLoaderFornecedor = new FXMLLoader(HelloApplication.class.getResource("cadastroFornecedor.fxml"));
        sceneProduto = new Scene(fxmlLoaderProduto.load(), 500, 500);
        sceneFornecedor = new Scene(fxmlLoaderFornecedor.load(), 500, 500);
        stage.setTitle("Mercadinho do Seu Zé");
        stage.setScene(sceneProduto);
        stage.show();
    }

    public static void mudarTela(String tela) {
        switch (tela) {
            case "produto":
                stage.setScene(sceneProduto);
                break;
            case "fornecedor":
                stage.setScene(sceneFornecedor);
                break;
        }
    }
}