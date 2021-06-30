package controllers.Medic;

import Models.OrdenPrueba;
import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class MedicOrdersController implements Initializable {
    Stage stage;
    @FXML
    ComboBox<String> cbTestType;
    @FXML
    JFXButton btnExit, btnSave;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initGUI();
        initData();
    }
    public void setStage(Stage stage){
        this.stage = stage;
    }

    EventHandler<ActionEvent> btnHandler = event -> {
        if (event.getSource() == btnExit){
            stage.close();
        }
        if(event.getSource() == btnSave){
            //TODO define method
            saveOrder();
        }

    };

    private void initGUI(){
        btnExit.setOnAction(btnHandler);
    }

    private void initData(){
        String[] aux = OrdenPrueba.testTypes;
        cbTestType.getItems().addAll(aux[0],aux[1], aux[2]);

    }

    private void saveOrder(){

    }
}