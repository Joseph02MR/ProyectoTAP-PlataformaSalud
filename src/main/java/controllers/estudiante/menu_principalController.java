package controllers.estudiante;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class menu_principalController implements Initializable {
    @FXML
    JFXButton btnEncu, btnCons, btnAler, btnRece, btnOrde;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initbuttons();
        ;
    }

    private EventHandler<ActionEvent> handler = new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
            if (event.getSource() == btnEncu) {
                try {
                    Encuesta();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else if (event.getSource() == btnCons) {
                try {
                    consultas();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else if (event.getSource() == btnAler) {
                try {
                    Alert();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else if (event.getSource() == btnRece) {
                try {
                    Recetas();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else if (event.getSource() == btnOrde) {
                try {
                    Ordenprueba();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
    };

    private void Encuesta() throws IOException {

        Stage login = new Stage();
        login.setTitle("Encuesta");
        Parent root = FXMLLoader.load(getClass().getResource("/Accesos/Estudiante/formulario.fxml"));
        Scene scene = new Scene(root);
        login.setScene(scene);
        login.setResizable(false);
        login.show();

    }

    private void consultas() throws IOException {

        Stage login = new Stage();
        login.setTitle("Encuesta");
        Parent root = FXMLLoader.load(getClass().getResource("/Accesos/Estudiante/Solicitar_cita.fxml"));
        Scene scene = new Scene(root);
        login.setScene(scene);
        login.setResizable(false);
        login.show();

    }

    private void Alert() throws IOException {

        Stage login = new Stage();
        login.setTitle("Encuesta");
        Parent root = FXMLLoader.load(getClass().getResource("/Accesos/Estudiante/Alert_manager.fxml"));
        Scene scene = new Scene(root);
        login.setScene(scene);
        login.setResizable(false);
        login.show();

    }

    private void Recetas() throws IOException {

        Stage login = new Stage();
        login.setTitle("Encuesta");
        Parent root = FXMLLoader.load(getClass().getResource("/Accesos/Estudiante/recetas.fxml"));
        Scene scene = new Scene(root);
        login.setScene(scene);
        login.setResizable(false);
        login.show();

    }

    private void Ordenprueba() throws IOException {

        Stage login = new Stage();
        login.setTitle("Encuesta");
        Parent root = FXMLLoader.load(getClass().getResource("/Accesos/Estudiante/testOrder_view.fxml"));
        Scene scene = new Scene(root);
        login.setScene(scene);
        login.setResizable(false);
        login.show();

    }

    private void initbuttons() {
        btnAler.setOnAction(handler);
        btnCons.setOnAction(handler);
        btnEncu.setOnAction(handler);
        btnOrde.setOnAction(handler);
        btnRece.setOnAction(handler);
    }
}
