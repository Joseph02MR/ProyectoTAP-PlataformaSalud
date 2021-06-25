package controllers.estudiante;

import Models.OrdenPrueba;
import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class OrderDetailsController implements Initializable {
    @FXML
    JFXButton btnPrint;
    @FXML
    Label LabelTestType, LabelResult, LabelOrderDate;
    OrdenPrueba prueba;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initGUI();
    }
    public void setPrueba(OrdenPrueba prueba){
        this.prueba = prueba;
    }

    private void initGUI(){
        LabelResult.setText(prueba.getResult());
        LabelTestType.setText(prueba.getTestType());
        LabelOrderDate.setText(prueba.getOrderdate().toString());
        btnPrint.setOnAction(handler);
    }

    EventHandler<ActionEvent> handler = event -> {
        printOrder();
    };

    private void printOrder(){
    }
}
