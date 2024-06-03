package sample;

import analisador.AnalisadorLexico;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.io.StringReader;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {



    private AnalisadorLexico analisadorLexico;
    @FXML
    TextArea txtCode;
    @FXML
    Button btn;

    private ObservableList<Dado> listaTokens = FXCollections.observableArrayList();

    @FXML
    TableView tabelaTokens;

    @FXML
    TableColumn<Dado, String> key;
    @FXML
    TableColumn<Dado, String> token;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        key.setCellValueFactory(new PropertyValueFactory<>("key"));
        token.setCellValueFactory(new PropertyValueFactory<>("token"));

        btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                analiseCode();
            }
        });

        tabelaTokens.setItems(listaTokens);

    }


    public void analiseCode() {
        listaTokens.clear();
        try {
            analisadorLexico = new AnalisadorLexico(new StringReader(txtCode.getText()), new AnalisadorLexico.ListenerTokens() {
                @Override
                public void getTokens(String keyword, String tokens) {
                    listaTokens.add(new Dado(keyword,tokens));
                    tabelaTokens.refresh();
                }

                @Override
                public void end() {
                    System.out.println("Terminou");
                }

                @Override
                public void error(Object object) {
                    System.out.println("Erro!");
                }
            });

            while (analisadorLexico.yylex() != null){
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
