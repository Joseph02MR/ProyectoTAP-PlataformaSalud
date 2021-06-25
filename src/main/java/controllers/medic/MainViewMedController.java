package controllers.medic;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainViewMedController implements Initializable {

    @FXML
    JFXButton btnOrders, btnForms, btnResults, btnRequests;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initGUI();
    }

    private void initGUI(){
        btnForms.setOnAction(btnHandler);
        btnOrders.setOnAction(btnHandler);
        btnResults.setOnAction(btnHandler);
        btnRequests.setOnAction(btnHandler);
    }

    EventHandler<ActionEvent> btnHandler = event ->{
        if(event.getSource() == btnForms){
            try {
                initForms();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if(event.getSource() == btnRequests){
            try {
                initRequests();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if(event.getSource() == btnResults){
            try {
                initResults();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if(event.getSource() == btnOrders){
            try {
                initOrders();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    };

    private void initForms() throws IOException {
        Stage formsStage = new Stage();
        formsStage.setTitle("Encuestas respondidas");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Accesos/Medico/answForms_view.fxml"));
        AnswFormsController formsCon = new AnswFormsController();
        loader.setController(formsCon);
        formsCon.setStage(formsStage);
        Parent root = loader.load();
        formsStage.getIcons().add(new Image("/Images/Logo/btq.png"));
        Scene scene = new Scene(root);
        formsStage.setScene(scene);
        formsStage.setResizable(false);
        formsStage.show();
    }

    private void initOrders() throws IOException {
        Stage ordersStage = new Stage();
        ordersStage.setTitle("Levantar Orden");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Accesos/Medico/medicOrders_view.fxml"));
        MedicOrdersController ordersCon = new MedicOrdersController();
        loader.setController(ordersCon);
        ordersCon.setStage(ordersStage);
        Parent root = loader.load();
        ordersStage.getIcons().add(new Image("/Images/Logo/btq.png"));
        Scene scene = new Scene(root);
        ordersStage.setScene(scene);
        ordersStage.setResizable(false);
        ordersStage.show();
    }

    private void initResults() throws IOException {
        Stage resStage = new Stage();
        resStage.setTitle("Resultados de Pruebas");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Accesos/Medico/testResults_view.fxml"));
        TestResultsController resCon = new TestResultsController();
        loader.setController(resCon);
        resCon.setStage(resStage);
        Parent root = loader.load();
        resStage.getIcons().add(new Image("/Images/Logo/btq.png"));
        Scene scene = new Scene(root);
        resStage.setScene(scene);
        resStage.setResizable(false);
        resStage.show();

    }

    private void initRequests() throws IOException {
        Stage reqsStage = new Stage();
        reqsStage.setTitle("Solicitudes de Consulta");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Accesos/Medico/medicReqs_view.fxml"));
        MedicRequestsController reqsCon = new MedicRequestsController();
        loader.setController(reqsCon);
        reqsCon.setStage(reqsStage);
        Parent root = loader.load();
        reqsStage.getIcons().add(new Image("/Images/Logo/btq.png"));
        Scene scene = new Scene(root);
        reqsStage.setScene(scene);
        reqsStage.setResizable(false);
        reqsStage.show();
    }
}
