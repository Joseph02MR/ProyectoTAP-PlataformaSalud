package controllers.Medic;

import Models.Encuesta;
import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AnswFormsController implements Initializable {
    private Stage stage;
    @FXML
    JFXButton btnExit;
    TableView<Object> tvAnsForms;
    ObservableList<Encuesta> encuestas;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initGUI();
    }

    public void setStage(Stage stage){
        this.stage = stage;
    }

    private void initGUI(){
        btnExit.setOnAction(handler);
    }

    EventHandler<ActionEvent> handler = event -> {
        if(event.getSource() == btnExit){
            stage.close();
        }
    };

    private void initData(){
        encuestas = FXCollections.observableArrayList();
        encuestas.add(new Encuesta());
        encuestas.add(new Encuesta());
        encuestas.add(new Encuesta());
        encuestas.add(new Encuesta());
    }

    private void filterForms(){
        //TODO define method
    }

    //TODO change var names
    private void initFormDetails(Encuesta encuesta) throws IOException {
        Stage formsStage = new Stage();
        formsStage.setTitle("Encuestas respondidas");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Accesos/Medico/answFormDetails_view.fxml"));
        AnswFormDetController formsCon = new AnswFormDetController();
        loader.setController(formsCon);
        formsCon.setForm(encuesta);
        Parent root = loader.load();
        formsStage.getIcons().add(new Image("/Images/Logo/btq.png"));
        Scene scene = new Scene(root);
        formsStage.setScene(scene);
        formsStage.setResizable(false);
        formsStage.show();
    }
}