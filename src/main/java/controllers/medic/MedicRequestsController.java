package controllers.medic;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class MedicRequestsController implements Initializable {
    private Stage stage;

    @FXML

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initGUI();
    }

    public void setStage(Stage stage){
        this.stage = stage;
    }

    private void initGUI(){
    }

    EventHandler<ActionEvent> handler = event -> {

    };

    private void initData(){

    }
}
