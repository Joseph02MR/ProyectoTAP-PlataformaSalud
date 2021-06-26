package controllers.general;

import Database.MySQLConnection;
import Database.Views.UserReportsDAO;
import Models.Views.ReporteView;
import Reports.IOMethods;
import Reports.ReportsPDF;
import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class GenRepoController implements Initializable {

    @FXML
    JFXButton btnPrintRep;

    ReportsPDF reportsPDF;
    UserReportsDAO userReportsDAO;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initGUI();
        initObjects();
    }

    private void initObjects(){
        reportsPDF = new ReportsPDF();
        userReportsDAO = new UserReportsDAO(MySQLConnection.getConnection());

    }

    private void initGUI(){
        btnPrintRep.setOnAction(handler);
    }

    EventHandler<ActionEvent> handler = event -> {
        if(event.getSource() == btnPrintRep){
            reportsPDF.updatePath(true);
            try {
                //create file
                String aux = reportsPDF.ListaCasosEst();
                //open file
                IOMethods.openFile(aux);
                //add file to DB
                uploadFile(new File(aux));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    };

    private void uploadFile( File file){
        ReporteView reporte = new ReporteView(file.getName(), file.getAbsolutePath());
        userReportsDAO.insertFile(reporte);
    }


}
