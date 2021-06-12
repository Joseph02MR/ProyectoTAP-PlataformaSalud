package controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class RegisterController implements Initializable {
    @FXML
    JFXTextField tfUName,tfPass,tfNAME,tfLastNAME,tfGENDER,tfAGE;

    @FXML

    JFXButton btnCreate,btnCancel;




    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
