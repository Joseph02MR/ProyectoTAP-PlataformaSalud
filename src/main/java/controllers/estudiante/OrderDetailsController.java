package controllers.estudiante;

import Database.MySQLConnection;
import Database.OrdenPruebaDAO;
import Models.OrdenPrueba;
import Models.Views.Receta.OrdenReporte;
import Reports.IOMethods;
import Reports.OrdenPruebaPDF;
import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class OrderDetailsController implements Initializable {
    @FXML
    JFXButton btnPrint;
    @FXML
    Label LabelTestType, LabelResult, LabelOrderDate, labelnumOrder;
    OrdenPrueba prueba;
    OrdenPruebaDAO ordenPruebaDAO = new OrdenPruebaDAO(MySQLConnection.getConnection());

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initGUI();
    }
    public void setPrueba(OrdenPrueba prueba){
        this.prueba = prueba;
    }

    private void initGUI(){
        labelnumOrder.setText(prueba.getCveOrden() +"");
        LabelResult.setText(prueba.getResult());
        LabelTestType.setText(prueba.getTestType());
        LabelOrderDate.setText(prueba.getOrderdate().toString());
        btnPrint.setOnAction(handler);
    }

    EventHandler<ActionEvent> handler = event -> {
        try {
            String aux = printOrder();
            IOMethods.openFile(aux);
        } catch (IOException e) {
            e.printStackTrace();
        }
    };

    private String printOrder() throws IOException {
        OrdenReporte orden = ordenPruebaDAO.getOrderForPrinting(prueba.getCveOrden());
        OrdenPruebaPDF aux = new OrdenPruebaPDF();
        String filename = aux.ordenReporteGen(orden);
        return filename;
    }
}
