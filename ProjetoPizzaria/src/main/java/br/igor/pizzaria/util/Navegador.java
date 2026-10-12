package br.igor.pizzaria.util;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class Navegador { //Mesma dinâmica da troca de tela
    public static void trocarTela(ActionEvent event, String caminhoFxml) { //Toda vez que é feita uma ação o ActionEvent se ativa e muda a tela baseado no evento
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(Navegador.class.getResource(caminhoFxml)));
            // Usando um mesmo stage é possível trocar uma cena
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow(); // O event.getSource retornará um objeto do tipo node, pega o nó que aconteceu aquele evento e retorna o evento correspondente
            stage.setScene(new Scene(root)); // Peg onde ocorreu o evento guardando as cenas
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Erro ao carregar a tela: " + caminhoFxml);
        }
    }
}
