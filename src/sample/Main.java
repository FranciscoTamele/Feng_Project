package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


/**
 * Pode gerar o analisar usando o comando abaixo caso JFLEX não esteja configurando no variaveis de ambiente
 * java -jar libs\jflex-full-1.9.1.jar src\analisador\analiser.flex
 *
 * Usa este comando caso JFLEX esteja configurando no ambiente
 * jflex src\analisador\analiser.flex
 */



public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 1366, 768));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
