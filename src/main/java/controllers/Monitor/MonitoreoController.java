
package controllers.Monitor;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
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

public class MonitoreoController implements Initializable {
    @FXML
    JFXButton btnHistAler,btnEstaUsua,btnGeneAler;
    @FXML
    JFXListView lv;

    int claveUsuario;



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initGUI();
        System.out.println(claveUsuario);
    }

    private void initGUI(){
        btnHistAler.setOnAction(btnHandler);
        btnEstaUsua.setOnAction(btnHandler);
        btnGeneAler.setOnAction(btnHandler);
    }
    public void setcveUsuario(int cveUsuario){
        this.claveUsuario = cveUsuario;
    }

    EventHandler<ActionEvent> btnHandler = event -> {
        try{
            if(event.getSource() == btnHistAler){
                initHistorial();

            } else if(event.getSource() == btnEstaUsua){
                initEstUser();

            } else if(event.getSource() == btnGeneAler){
                System.out.println("Button Pressed");
                initGenAlert();
            }
        } catch (IOException e){
            //TODO handle error
            e.printStackTrace();
            //System.out.println("error");
        }
    };

    private void initHistorial() throws IOException {
        Stage stage = new Stage();
        stage.setTitle("Historial de Alertas");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Accesos/Monitor/alert_hist_view.fxml"));

        AlertHistController histCon = new AlertHistController();
        loader.setController(histCon);
        histCon.setStage(stage);
        Parent root = loader.load();
        //stage.getIcons().add(new Image("/Images/Logo/btq.png"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    private void initEstUser() throws IOException {
        Stage stage = new Stage();
        stage.setTitle("Estado de Usuarios");
        //TODO crear diseño/controlador
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Accesos/Monitor/estadoUser_view.fxml"));
        EstUserController estCon = new EstUserController();
        loader.setController(estCon);
        Parent root = loader.load();
        estCon.setStage(stage);
        //stage.getIcons().add(new Image("/Images/Logo/btq.png"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    private void initGenAlert() throws IOException {
        Stage stage = new Stage();
        stage.setTitle("Generar Alerta");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Accesos/Monitor/alert.fxml"));
        AlertaMonitorController alertCon = new AlertaMonitorController();
        alertCon.usuario.setCveUsuario(claveUsuario);
        loader.setController(alertCon);
        alertCon.setStage(stage);
        Parent root = loader.load();
        //stage.getIcons().add(new Image("/Images/Logo/btq.png"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

}
