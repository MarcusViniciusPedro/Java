package br.igor.pizzaria;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class PizzariaApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(PizzariaApplication.class.getResource("menuPrincipal.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Pizzaria do SPOLPG1!");
        stage.setScene(scene);
        stage.show();
    }
}
