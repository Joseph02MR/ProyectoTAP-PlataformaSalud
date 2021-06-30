package controllers.Estudiantes;

import Models.Usuario;
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

public class menu_principalController implements Initializable {
    @FXML
    JFXButton btnEncu,btnCons,btnAler,btnRece,btnOrde,btnout;


    int cveUsuario;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initbuttons();;
    }

    private EventHandler<ActionEvent> handler = new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
            if(event.getSource()==btnEncu){
                try {
                    Encuesta();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            else if(event.getSource()==btnCons)
            {
                try {
                    consultas();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            else if(event.getSource()==btnAler){
                try {
                    Alert();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            else if(event.getSource()==btnRece){
                try {
                    Recetas();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            else if(event.getSource()==btnOrde){
                try {
                    Ordenprueba();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            else if(event.getSource()==btnout){
                final Node source = (Node) event.getSource();
                final Stage stage = (Stage) source.getScene().getWindow();
                stage.close();
                try {
                    initLogin();
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

        Stage stage = new Stage();
        stage.setTitle("Consultas");
        FXMLLoader LOADER= new FXMLLoader(getClass().getResource("/Accesos/Estudiante/Solicitar_cita.fxml"));
        SolicitudCita controller = new SolicitudCita();
        controller.user.setCveUsuario(cveUsuario);
        LOADER.setController(controller);
        Parent root = LOADER.load();
        stage.getIcons().add(new Image("/Images/Logo/btq.png"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    private void Alert() throws IOException {

        Stage login = new Stage();
        login.setTitle("Notif");
        Parent root = FXMLLoader.load(getClass().getResource("/Accesos/Estudiante/Alert_manager.fxml"));
        Scene scene = new Scene(root);
        login.setScene(scene);
        login.setResizable(false);
        login.show();

    }

    private void Recetas() throws IOException {

        Stage login = new Stage();
        login.setTitle("Receta");
        Parent root = FXMLLoader.load(getClass().getResource("/Accesos/Estudiante/Recetas.fxml"));
        Scene scene = new Scene(root);
        login.setScene(scene);
        login.setResizable(false);
        login.show();

    }

    private void Ordenprueba() throws IOException {

        Stage login = new Stage();
        login.setTitle("Ordenes de Prueba");
        Parent root = FXMLLoader.load(getClass().getResource("/Accesos/Estudiante/testOrder_view.fxml"));
        Scene scene = new Scene(root);
        login.setScene(scene);
        login.setResizable(false);
        login.show();

    }

    private void initbuttons(){
        btnAler.setOnAction(handler);
        btnCons.setOnAction(handler);
        btnEncu.setOnAction(handler);
        btnOrde.setOnAction(handler);
        btnRece.setOnAction(handler);
        btnout.setOnAction(handler);
    }

    public void setcveUsuario(int cveUsuario){
        this.cveUsuario = cveUsuario;
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
