package controllers.medic;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;


import java.net.URL;
import java.util.ResourceBundle;

public class SolicitarConsultaController implements Initializable {

    @FXML
    JFXButton btnGeneRece,btnMarcSosp;
    @FXML
    JFXListView lvCons,lvArch;
    @FXML
    TextArea taSint;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
