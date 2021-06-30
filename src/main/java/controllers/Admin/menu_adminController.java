package controllers.Admin;


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

public class menu_adminController implements Initializable {
    @FXML
    JFXButton btnCatList,btnGeneRepo,btnHistRepo,btnHabiCuen,btnout;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initData();
        initGUI();
    }

    private void initGUI(){
        btnCatList.setOnAction(btnHandler);
        btnGeneRepo.setOnAction(btnHandler);
        btnHistRepo.setOnAction(btnHandler);
        btnHabiCuen.setOnAction(btnHandler);
        btnout.setOnAction(btnHandler);
    }

    //TODO add databases names
    private void initData(){
        //cbDBList.getItems().addAll("/* insert databases here*/");
    }

    EventHandler<ActionEvent> btnHandler = event -> {
        try{
            if (event.getSource() == btnCatList) {
                initDBList();
            } else if(event.getSource() == btnHistRepo){
                initHistRepo();
            } else if(event.getSource() == btnGeneRepo){
                initGenReps();
            }else if(event.getSource() == btnHabiCuen){
                initHabiCuen();
            }else if(event.getSource()==btnout){
                final Node source = (Node) event.getSource();
                final Stage stage = (Stage) source.getScene().getWindow();
                stage.close();
                initLogin();
            }
        } catch (IOException e){
            e.printStackTrace();
        }
    };

    private void initDBList() throws IOException {
        Stage stage = new Stage();
        stage.setTitle("Catalogo de Tablas");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Accesos/Admin/dbList_view.fxml"));
        DBListController controller = new DBListController();
        loader.setController(controller);
        controller.setStage(stage);
        Parent root = loader.load();
       // stage.getIcons().add(new Image("/Images/Logo/btq.png"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    private void initHistRepo() throws IOException {
        Stage stage = new Stage();
        stage.setTitle("Historial de Reportes");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Accesos/Admin/repo_hist_view.fxml"));
        ReportHistController controller = new ReportHistController();
        loader.setController(controller);
        controller.setStage(stage);
        Parent root = loader.load();
        stage.getIcons().add(new Image("/Images/Logo/btq.png"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }
    private void initGenReps() throws IOException {
        Stage stage = new Stage();
        stage.setTitle("Generacion de Reportes");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Accesos/General/gen_repo_view.fxml"));
        //controller.setStage(stage);
        Parent root = loader.load();
        stage.getIcons().add(new Image("/Images/Logo/btq.png"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }
    private void initHabiCuen() throws IOException
    {
        Stage stage = new Stage();
        stage.setTitle("Administracion de cuentas");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Accesos/Admin/admin_users.fxml"));
        AdminUsersController controller = new AdminUsersController();
        loader.setController(controller);
        controller.setStage(stage);
        Parent root = loader.load();
        stage.getIcons().add(new Image("/Images/Logo/btq.png"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
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
