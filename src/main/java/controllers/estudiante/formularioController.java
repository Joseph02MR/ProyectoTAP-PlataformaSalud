package controllers.estudiante;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

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

    }
}
