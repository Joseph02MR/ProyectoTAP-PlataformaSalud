package controllers;

import Database.MySQLConnection;
import Database.UsuarioDAO;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import controllers.Estudiante.menu_principalController;
import controllers.medic.MainViewMedController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class login implements Initializable {

    @FXML
    ImageView logo;
    @FXML
    JFXButton btnLogin,btnRegister;
    @FXML
    JFXTextField tfUser;
    @FXML
    JFXPasswordField tfPassword;
    int[] TuplaAux;

    //DAOS
    UsuarioDAO usuarioDAO = new UsuarioDAO(MySQLConnection.getConnection());

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Image logo1 = new Image("/Images/Logo/itc.png");
        logo.setImage(logo1);
         btnLogin.setOnAction(eventHandler);
         btnRegister.setOnAction(eventHandler);
    }
    EventHandler<ActionEvent> eventHandler=new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
            try{
                if (event.getSource() == btnLogin) {
                    getData();
                } else {
                    initUser();
                }
            } catch (IOException e){
                e.printStackTrace();
            }
        }
    };

    public void initGUI() throws IOException {
        switch (TuplaAux[1]){
            case 2: initAdminView(); break;
            case 1: initMonitorView(); break;
            case 0: {
                int aux = usuarioDAO.method(TuplaAux[0]);
                switch (aux){
                    case 2: initMedView(); break;
                    case 1: initDirView(); break;
                    case 0: initNormalView(); break;
                }
            }
        }
    }

    public void getData() throws IOException {
        String user = tfUser.getText();
        String pw = tfPassword.getText();
        TuplaAux = usuarioDAO.search(user, pw);
        if(TuplaAux[0] == -1){
            //Error
            System.out.println("login error");
            return;
        }
        initGUI();
    }

    private void initNormalView() throws IOException {
        System.out.println("entered Normal view");
        Stage stage = new Stage();
        stage.setTitle("Menu Principal");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Accesos/Estudiante/menu_principal.fxml"));
        menu_principalController controller = new menu_principalController();
        controller.setCveUsuario(TuplaAux[0]);
        loader.setController(controller);
        Parent root = loader.load();
        stage.getIcons().add(new Image("/Images/Logo/btq.png"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    private void initUser() throws IOException {
            Stage login = new Stage();
            login.setTitle("Creating User");
            Parent root = FXMLLoader.load(getClass().getResource("/Accesos/General/register_form.fxml"));
            Scene scene = new Scene(root);
            login.setScene(scene);
            login.setResizable(false);
            login.show();
    }

    private void initDirView() throws IOException {
        System.out.println("entered Dir view");
        Stage login = new Stage();
        login.setTitle("Creating User");
        Parent root = FXMLLoader.load(getClass().getResource("/Accesos/General/DirectionDashboard.fxml"));
        Scene scene = new Scene(root);
        login.setScene(scene);
        login.setResizable(false);
        login.show();
    }

    private void initMedView() throws IOException {
        System.out.println("entered med view");
        Stage login = new Stage();
        login.setTitle("PANEL MEDICO");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Accesos/Medico/medicMain_view.fxml"));
        MainViewMedController medicCon = new MainViewMedController();
        loader.setController(medicCon);
        medicCon.setCveUsuario(TuplaAux[0]);
        Parent root = loader.load();
        login.getIcons().add(new Image("/Images/Logo/btq.png"));
        Scene scene = new Scene(root);
        login.setScene(scene);
        login.setResizable(false);
        login.show();
    }

    private void initMonitorView() throws IOException {
        System.out.println("entered monitor view");
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

    private void initAdminView() throws IOException {
        System.out.println("entered admin view");
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
