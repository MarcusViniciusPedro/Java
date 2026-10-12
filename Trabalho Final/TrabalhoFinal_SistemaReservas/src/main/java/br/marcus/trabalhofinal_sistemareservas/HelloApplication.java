package br.marcus.trabalhofinal_sistemareservas;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class HelloApplication extends Application {

    public static Stage stage;
    public static int idUsuarioLogado;
    public static int idEmpregadoLogado;

    private static Scene sceneCadCliente;
    private static Scene sceneCadEmpregado;
    private static Scene sceneCadReserva;
    private static Scene sceneMenuCliente;
    private static Scene sceneMenuEmpregado;
    private static Scene sceneTelaInicial;
    private static Scene sceneTelaIntrodutoria;

    @Override
    public void start(Stage stageInicial) throws IOException {
        stage = stageInicial;
        FXMLLoader fxmlLoaderCadCliente = new FXMLLoader(HelloApplication.class.getResource("Cadastro_Cliente.fxml"));
        FXMLLoader fxmlLoaderCadEmpregado = new FXMLLoader(HelloApplication.class.getResource("Cadastro_Empregado.fxml"));
        FXMLLoader fxmlLoaderCadReserva = new FXMLLoader(HelloApplication.class.getResource("Cadastro_Reserva.fxml"));
        FXMLLoader fxmlLoaderMenuCliente = new FXMLLoader(HelloApplication.class.getResource("Menu_Cliente.fxml"));
        FXMLLoader fxmlLoaderMenuEmpregado = new FXMLLoader(HelloApplication.class.getResource("Menu_Empregado.fxml"));
        FXMLLoader fxmlLoaderTelaInicial = new FXMLLoader(HelloApplication.class.getResource("Tela_Inicial.fxml"));
        FXMLLoader fxmlLoaderTelaIntrodutoria = new FXMLLoader(HelloApplication.class.getResource("Tela_Introdutoria.fxml"));
        sceneCadCliente = new Scene(fxmlLoaderCadCliente.load());
        sceneCadEmpregado = new Scene(fxmlLoaderCadEmpregado.load());
        sceneCadReserva = new Scene(fxmlLoaderCadReserva.load());
        sceneMenuCliente = new Scene(fxmlLoaderMenuCliente.load());
        sceneMenuEmpregado = new Scene(fxmlLoaderMenuEmpregado.load());
        sceneTelaInicial = new Scene(fxmlLoaderTelaInicial.load());
        sceneTelaIntrodutoria = new Scene(fxmlLoaderTelaIntrodutoria.load());
        stage.setTitle("Burger Clown Grille");
        stage.setScene(sceneTelaIntrodutoria);
        stage.show();
    }

    public static void mudarTela (String tela){
        switch (tela) {
            case "Cadastro_Cliente":
                stage.setScene(sceneCadCliente);
                break;
            case "Cadastro_Empregado":
                stage.setScene(sceneCadEmpregado);
                break;
            case "Cadastro_Reserva":
                stage.setScene(sceneCadReserva);
                break;
            case "Menu_Cliente":
                stage.setScene(sceneMenuCliente);
                break;
            case "Menu_Empregado":
                stage.setScene(sceneMenuEmpregado);
                break;
            case "Tela_Inicial":
                stage.setScene(sceneTelaInicial);
                break;
            case "Tela_Introdutoria":
                stage.setScene(sceneTelaIntrodutoria);
                break;
        }
    }
}