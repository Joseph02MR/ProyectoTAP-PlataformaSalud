package controllers.Monitor;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import jdk.jfr.Enabled;

import javax.swing.border.EmptyBorder;
import java.net.URL;
import java.util.ResourceBundle;

public class AlertaMonitor implements Initializable {

    @FXML
    JFXButton btnreturn,btnDEST;
    @FXML
    ComboBox<String> cbalert,cbpositivos;
    @FXML
    ListView LvPosit;
    @FXML
    Text txtFecha,txtDests;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        combobox();;




    }

  private  EventHandler pp = new EventHandler() {
      @Override
      public void handle(Event event) {
          if(cbalert.getSelectionModel().getSelectedItem().toString().equalsIgnoreCase("Sospechoso")){
              cbpositivos.setDisable(true);
          }
          else
          {
              cbpositivos.setDisable(false);
          }
      }
  };


    private void combobox(){
        cbalert.getItems().addAll("Positivo","Sospechoso");
        cbalert.setOnAction(pp);

    }
}


