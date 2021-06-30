package controllers.Estudiantes;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class formularioController implements Initializable {
    @FXML
    JFXCheckBox cb1,cb2,cb3,cb4,cb5,cb6,cb7,cb8,cb9,cb10,cb11,cb12,cb13;
    @FXML
    TextField tfSint;
    @FXML
    JFXButton btnEnvi,btnCanc;



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initbuttons();
    }

    private EventHandler<ActionEvent> handler = new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
            if (event.getSource()==btnCanc)
            {
                final Node source = (Node) event.getSource();
                final Stage stage = (Stage) source.getScene().getWindow();
                stage.close();
            }
        }
    };

    private void initbuttons(){
        btnCanc.setOnAction(handler);
    }
}
