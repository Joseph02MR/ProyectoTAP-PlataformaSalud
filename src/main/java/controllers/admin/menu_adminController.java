package controllers.admin;

import controllers.general.GenRepoController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class menu_adminController implements Initializable {
    @FXML
    Button btnEstu,btnPers,btnCarr,btnDepa,btnMedi,btnHabiCuen,btnCont,btnGeneRepo,btnHistRepo;
    @FXML
    ComboBox cbDBList;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initData();
        initGUI();
    }

    private void initGUI(){
        btnEstu.setOnAction(btnHandler);
        btnGeneRepo.setOnAction(btnHandler);
        btnHistRepo.setOnAction(btnHandler);
        btnCont.setOnAction(btnHandler);
        btnHabiCuen.setOnAction(btnHandler);
    }

    //TODO add databases names
    private void initData(){
        //cbDBList.getItems().addAll("/* insert databases here*/");
    }

    EventHandler<ActionEvent> btnHandler = event -> {
        try{
            if (event.getSource() == btnEstu) {
                initDBList();
            } else if(event.getSource() == btnHistRepo){
                initHistRepo();
            } else if(event.getSource() == btnGeneRepo){
                initGenReps();
            }
        } catch (IOException e){
            e.printStackTrace();
        }
    };

    private void initDBList() throws IOException {
        Stage stage = new Stage();
        stage.setTitle("Catalogo de Tablas");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Accesos/admin/dbList_view.fxml"));
        DBListController controller = new DBListController();
        loader.setController(controller);
        controller.setStage(stage);
        Parent root = loader.load();
        stage.getIcons().add(new Image("/Images/Logo/btq.png"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    private void initHistRepo() throws IOException {
        Stage stage = new Stage();
        stage.setTitle("Historial de Reportes");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Accesos/admin/repo_hist_view.fxml"));
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
}
