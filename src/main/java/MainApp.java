import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class MainApp extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
       initLogin();
        //Mmenuprinc();
        //cMenuAdmin();
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

    private void iniUser() throws IOException {
        Stage login = new Stage();
        login.setTitle("Creating User");
        Parent root = FXMLLoader.load(getClass().getResource("/Accesos/General/register_form.fxml"));
        Scene scene = new Scene(root);
        //scene.getStylesheets().add("/css/DarkTheme2.css");
        //scene.getStylesheets().add(BootstrapFX.bootstrapFXStylesheet());
        login.setScene(scene);
        login.setResizable(false);
        login.show();
    }

    private void ALertsManager() throws IOException {
        Stage login = new Stage();
        login.setTitle("Notifications");
        Parent root = FXMLLoader.load(getClass().getResource("/Accesos/Estudiante/Alert_manager.fxml"));
        Scene scene = new Scene(root);
        //scene.getStylesheets().add("/css/DarkTheme2.css");
        //scene.getStylesheets().add(BootstrapFX.bootstrapFXStylesheet());
        login.setScene(scene);
        login.setResizable(false);
        login.show();
    }

    private void Consultas() throws IOException {
        Stage login = new Stage();
        login.setTitle("Consult_Maker");
        Parent root = FXMLLoader.load(getClass().getResource("/Accesos/Estudiante/Recetas.fxml"));
        Scene scene = new Scene(root);
        login.setScene(scene);
        login.setResizable(false);
        login.show();
    }

    private void SolicitarConsultas() throws IOException {
        Stage login = new Stage();
        login.setTitle("Consult_Maker");
        Parent root = FXMLLoader.load(getClass().getResource("/Accesos/Estudiante/Solicitar_cita.fxml"));
        Scene scene = new Scene(root);
        login.setScene(scene);
        login.setResizable(false);
        login.show();
    }


    private void  Dashboard() throws IOException {
        Stage login = new Stage();
        login.setTitle("Consult_Maker");
        Parent root = FXMLLoader.load(getClass().getResource("/Accesos/DirectionDashboard.fxml"));
        Scene scene = new Scene(root);
        login.setScene(scene);
        login.setResizable(false);
        login.show();
    }

    private void  MonitorEP() throws IOException {
        Stage login = new Stage();
        login.setTitle("Consult_Maker");
        Parent root = FXMLLoader.load(getClass().getResource("/Accesos/Monitor/alert.fxml"));
        Scene scene = new Scene(root);
        login.setScene(scene);
        login.setResizable(false);
        login.show();
    }

    private void  Mmenuprinc() throws IOException {
        Stage login = new Stage();
        login.setTitle("Menú Principal");
        Parent root = FXMLLoader.load(getClass().getResource("/Accesos/Estudiante/menu_principal.fxml"));
        Scene scene = new Scene(root);
        login.setScene(scene);
        login.setResizable(false);
        login.show();
    }

    private void  createrecipe() throws IOException {
        Stage login = new Stage();
        login.setTitle("Consult_Maker");
        Parent root = FXMLLoader.load(getClass().getResource("/Accesos/Medico/Medic_create_recipe.fxml"));
        Scene scene = new Scene(root);
        login.setScene(scene);
        login.setResizable(false);
        login.show();
    }

    private void  cMenuAdmin() throws IOException {
        Stage login = new Stage();
        login.setTitle("Consult_Maker");
        Parent root = FXMLLoader.load(getClass().getResource("/Accesos/Admin/menu_admin.fxml"));
        Scene scene = new Scene(root);
        login.setScene(scene);
        login.setResizable(false);
        login.show();
    }


}

