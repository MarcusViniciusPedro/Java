package br.alunomarcus.projeto_interfacefx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    private static Stage stage;

    private static Scene sceneLogin;
    private static Scene scenePrincipal;
    private static Scene sceneCadastro;


    @Override
    public void start(Stage stageInicial) throws IOException {
        stage = stageInicial;
        FXMLLoader fxmlLoaderLogin = new FXMLLoader(HelloApplication.class.getResource("login.fxml"));
        FXMLLoader fxmlLoaderPrincipal = new FXMLLoader(HelloApplication.class.getResource("cadastro.fxml"));
        FXMLLoader fxmlLoaderCadastro = new FXMLLoader(HelloApplication.class.getResource("principal.fxml"));
        sceneLogin = new Scene(fxmlLoaderLogin.load());
        scenePrincipal = new Scene(fxmlLoaderPrincipal.load());
        sceneCadastro = new Scene(fxmlLoaderCadastro.load());
        stage.setTitle("Wanderlust Viagens");
        stage.setScene(sceneLogin);
        stage.show();
    }

    public static void mudarTela(String tela){
        switch (tela){
            case "login":
                stage.setScene(sceneLogin);
                break;
            case "cadastro":
                stage.setScene(sceneCadastro);
                break;
            case "principal":
                stage.setScene(scenePrincipal);
                break;
        }
    }
}
