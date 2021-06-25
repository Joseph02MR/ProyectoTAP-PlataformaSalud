package controllers.medic;

import Models.OrdenPrueba;
import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class TestResultsController implements Initializable {
    private Stage stage;
    @FXML
    JFXButton btnExit;
    TableView<Object> tvTests;
    ObservableList<OrdenPrueba> pruebas;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initGUI();
    }

    public void setStage(Stage stage){
        this.stage = stage;
    }

    private void initGUI(){
        btnExit.setOnAction(handler);
    }

    EventHandler<ActionEvent> handler = event -> {
        if(event.getSource() == btnExit){
            stage.close();
        }
    };

    private void initData(){
        pruebas = FXCollections.observableArrayList();
        pruebas.add(new OrdenPrueba());
        pruebas.add(new OrdenPrueba());
        pruebas.add(new OrdenPrueba());
        pruebas.add(new OrdenPrueba());
    }

    private void filterForms(){
        //TODO define method
    }

}
