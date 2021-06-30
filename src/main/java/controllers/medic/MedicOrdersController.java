package controllers.medic;

import Database.MySQLConnection;
import Database.OrdenPruebaDAO;
import Database.UsuarioDAO;
import Models.OrdenPrueba;
import Models.Usuario;
import Models.UsuarioLista;
import com.jfoenix.controls.JFXButton;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class MedicOrdersController implements Initializable {
    Stage stage;
    @FXML
    ComboBox<String> cbTestType;
    @FXML
    JFXButton btnExit, btnSave;
    @FXML
    ListView<UsuarioLista> lvSuspects;
    @FXML
    Text tName;
    @FXML
    DatePicker dpDate;

    ObservableList<UsuarioLista> lista;
    UsuarioDAO usuarioDAO = new UsuarioDAO(MySQLConnection.getConnection());
    OrdenPruebaDAO ordenPruebaDAO = new OrdenPruebaDAO(MySQLConnection.getConnection());
    int cveMed;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initGUI();

        lvSuspects.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) {
                tName.setText(lvSuspects.getSelectionModel().getSelectedItem().getNombre());
            }
        });
    }

    public void setCveMed(int cveMed){
        this.cveMed = cveMed;
    }
    public void setStage(Stage stage){
        this.stage = stage;
    }

    EventHandler<ActionEvent> btnHandler = event -> {
        if (event.getSource() == btnExit){
            stage.close();
        }
        if(event.getSource() == btnSave){
            saveOrder();
        }

    };

    private void initGUI(){
        btnExit.setOnAction(btnHandler);
        btnSave.setOnAction(btnHandler);
        String[] aux = OrdenPrueba.testTypes;
        cbTestType.getItems().addAll(aux[0],aux[1], aux[2]);

        lista = usuarioDAO.getAllSospechososPendientes();
        for ( var x : lista){
            x.toString();
        }
        lvSuspects.setItems(lista);
    }

    private void saveOrder(){
        OrdenPrueba orden = new OrdenPrueba(
            lvSuspects.getSelectionModel().getSelectedItem(),
               cveMed,
                OrdenPrueba.results[2],
                dpDate.getValue(),
                cbTestType.getValue()
        );

        if(ordenPruebaDAO.insert(orden)){
            //success
            System.out.println("insercion de orden prueba exitosa");
            clearFields();
        }
    }

    private void clearFields(){
        tName.setText("");
        lvSuspects.getSelectionModel().clearSelection();
        cbTestType.getSelectionModel().clearSelection();
        dpDate.getEditor().clear();
    }
}
