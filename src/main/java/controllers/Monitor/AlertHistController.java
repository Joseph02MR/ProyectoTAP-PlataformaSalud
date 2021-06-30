package controllers.Monitor;

import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class AlertHistController implements Initializable {
    private Stage stage;
    @FXML
    JFXButton btnExit;
    TableView<Object> tvAlerts;
    //ObservableList<Alerta> alertas;

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
        //alertas = FXCollections.observableArrayList();
    }
}
