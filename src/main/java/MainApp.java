import controllers.admin.menu_adminController;
import controllers.medic.MainViewMedController;
import controllers.monitor.MonitoreoController;
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
        //initLogin();
        initA();
        initMonitor();

    }

    private void initLogin() throws IOException {
        Stage login = new Stage();
        login.setTitle("MEDICAL SUPERVISION");
        Parent root = FXMLLoader.load(getClass().getResource("/Accesos/General/login_form.fxml"));
        login.getIcons().add(new Image("/Images/Logo/btq.png"));
        Scene scene = new Scene(root);
        scene.getStylesheets().add("/css/DarkTheme2.css");
        login.setScene(scene);
        login.setResizable(false);
        login.show();
    }

    private void initA() throws IOException {
        Stage login = new Stage();
        login.setTitle("PANEL MEDICO");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Accesos/Medico/medicMain_view.fxml"));
        MainViewMedController medicCon = new MainViewMedController();
        loader.setController(medicCon);
        Parent root = loader.load();
        login.getIcons().add(new Image("/Images/Logo/btq.png"));
        Scene scene = new Scene(root);
        login.setScene(scene);
        login.setResizable(false);
        login.show();
    }

    private void initMonitor() throws IOException {
        Stage login = new Stage();
        login.setTitle("PANEL MONITOR");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Accesos/Monitor/monitoreo.fxml"));
        Parent root = loader.load();
        login.getIcons().add(new Image("/Images/Logo/btq.png"));
        Scene scene = new Scene(root);
        login.setScene(scene);
        login.setResizable(false);
        login.show();
    }

    private void initAdmin() throws IOException {
        Stage login = new Stage();
        login.setTitle("PANEL ADMIN");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Accesos/admin/menu_admin.fxml"));
        Parent root = loader.load();
        login.getIcons().add(new Image("/Images/Logo/btq.png"));
        Scene scene = new Scene(root);
        login.setScene(scene);
        login.setResizable(false);
        login.show();
    }
}
