package controllers.Medic;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainViewMedController implements Initializable {

    @FXML
    JFXButton btnOrders, btnForms, btnResults, btnRequests, btnout;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initGUI();
    }

    private void initGUI(){
        btnForms.setOnAction(btnHandler);
        btnOrders.setOnAction(btnHandler);
        btnResults.setOnAction(btnHandler);
        btnRequests.setOnAction(btnHandler);
        btnout.setOnAction(btnHandler);
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
        if(event.getSource() == btnout){
            final Node source = (Node) event.getSource();
            final Stage stage = (Stage) source.getScene().getWindow();
            stage.close();
            try {
                initLogin();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    };

    private void initForms() throws IOException {
        Stage formsStage = new Stage();
        formsStage.setTitle("Encuestas respondidas");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Accesos/Estudiante/testDetails_view.fxml"));
        ReviewFormsController formsCon = new ReviewFormsController();
        //loader.setController(orderDet);
        //formsCon.setPrueba(order);
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
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Accesos/Estudiante/testDetails_view.fxml"));
        ReviewFormsController ordersCon = new ReviewFormsController();
        //loader.setController(orderDet);
        //formsCon.setPrueba(order);
        Parent root = loader.load();
        ordersStage.getIcons().add(new Image("/Images/Logo/btq.png"));
        Scene scene = new Scene(root);
        ordersStage.setScene(scene);
        ordersStage.setResizable(false);
        ordersStage.show();

    }

    private void initResults() throws IOException {
        Stage formsStage = new Stage();
        formsStage.setTitle("Resultados de Pruebas");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Accesos/Estudiante/testDetails_view.fxml"));
        ReviewFormsController resCon = new ReviewFormsController();
        //loader.setController(orderDet);
        //formsCon.setPrueba(order);
        Parent root = loader.load();
        formsStage.getIcons().add(new Image("/Images/Logo/btq.png"));
        Scene scene = new Scene(root);
        formsStage.setScene(scene);
        formsStage.setResizable(false);
        formsStage.show();

    }

    private void initRequests() throws IOException {
        Stage formsStage = new Stage();
        formsStage.setTitle("Solicitudes de Consulta");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Accesos/Medico/medicReqs_view.fxml"));
        MedicRequestsController reqsCon = new MedicRequestsController();
        loader.setController(reqsCon);
        //formsCon.setPrueba(order);
        Parent root = loader.load();
        // formsStage.getIcons().add(new Image("/Images/Logo/btq.png"));
        Scene scene = new Scene(root);
        formsStage.setScene(scene);
        formsStage.setResizable(false);
        formsStage.show();

    }

    private void initLogin() throws IOException {
        Stage login = new Stage();
        login.setTitle("MEDICAL SUPERVISION");
        Parent root = FXMLLoader.load(getClass().getResource("/Accesos/General/login_form.fxml"));
        login.getIcons().add(new Image("/Images/Logo/btq.png"));
        Scene scene = new Scene(root);
        //scene.getStylesheets().add("/css/DarkTheme2.css");
        //scene.getStylesheets().add(BootstrapFX.bootstrapFXStylesheet());
        login.setScene(scene);
        login.setResizable(false);
        login.show();
    }
}
