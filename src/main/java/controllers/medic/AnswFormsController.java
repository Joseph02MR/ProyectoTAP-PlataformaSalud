package controllers.medic;

import Database.EncuestaDAO;
import Database.MySQLConnection;
import Models.Encuesta;
import Models.Views.EncuestaView;
import com.jfoenix.controls.JFXButton;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AnswFormsController implements Initializable {
    private Stage stage;
    @FXML
    JFXButton btnExit;
    @FXML
    TableView<EncuestaView> tvForms;

    ObservableList<EncuestaView> encuestas;
    EncuestaDAO encuestaDAO = new EncuestaDAO(MySQLConnection.getConnection());

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initGUI();
        initData();
    }

    public void setStage(Stage stage){
        this.stage = stage;
    }

    private void initGUI(){
        btnExit.setOnAction(handler);
        TableColumn colcveConsulta = new TableColumn("Clave Consulta");
        TableColumn colNombre = new TableColumn("Nombre Usuario");
        TableColumn colSintomas = new TableColumn("Presenta Sintomas");
        TableColumn colFecha = new TableColumn("Fecha de Realizacion");

        colcveConsulta.setCellValueFactory(new PropertyValueFactory<>("cveEncuesta"));
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombreUsuario"));
        colSintomas.setCellValueFactory(new PropertyValueFactory<>("presentaSintomas"));
        colFecha.setCellValueFactory(new PropertyValueFactory<>("fechaRealizacion"));

        tvForms.getColumns().addAll(colcveConsulta,colNombre, colFecha, colSintomas);

        tvForms.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) {
                try {
                    openEncuestaFromTable();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    EventHandler<ActionEvent> handler = event -> {
        if(event.getSource() == btnExit){
            stage.close();
        }
    };

    private void initData(){
        encuestas = encuestaDAO.getAllForVista();
        tvForms.setItems(encuestas);
    }

/*
    private void filterForms(){
       // el filtrado se realiza presionando el la columna de sintomas de la tabla
    }
*/

    private void initFormDetails(Encuesta encuesta) throws IOException {
        Stage stage = new Stage();
        stage.setTitle("Encuestas respondidas");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Accesos/Medico/answFormDetails_view.fxml"));
        AnswFormDetController controller = new AnswFormDetController();
        loader.setController(controller);
        controller.setForm(encuesta);
        controller.setStage(stage);
        Parent root = loader.load();
        stage.getIcons().add(new Image("/Images/Logo/btq.png"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    private void openEncuestaFromTable() throws IOException {
        int aux = tvForms.getSelectionModel().getSelectedItem().getCveEncuesta();
        initFormDetails(getEncuesta(aux));
    }

    private Encuesta getEncuesta(int cveEncuesta){
        Encuesta encuesta = encuestaDAO.getEncuestaFromVista(cveEncuesta);
        return encuesta;
    }
}
