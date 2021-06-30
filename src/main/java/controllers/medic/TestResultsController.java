package controllers.medic;

import Database.MySQLConnection;
import Database.OrdenPruebaDAO;
import Database.UsuarioDAO;
import Models.OrdenPrueba;
import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class TestResultsController implements Initializable {
    private Stage stage;
    @FXML
    JFXButton btnExit,btnSave;
    @FXML
    Label LabelnumOrder, LabelTestType, LabelOrderDate;
    @FXML
    ComboBox<String> cbResult;
    @FXML
    TableView<OrdenPrueba> tvTests;
    int cveMed;

    public void setMed(int cveMed){
        this.cveMed = cveMed;
    }

    ObservableList<OrdenPrueba> orders;
    OrdenPruebaDAO ordenPruebaDAO = new OrdenPruebaDAO(MySQLConnection.getConnection());
    UsuarioDAO usuarioDAO = new UsuarioDAO(MySQLConnection.getConnection());


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
        btnSave.setOnAction(handler);
        TableColumn Col1 = new TableColumn("Fecha");
        TableColumn Col2 = new TableColumn("Estado");

        Col1.setCellValueFactory(new PropertyValueFactory<>("Orderdate"));
        Col2.setCellValueFactory(new PropertyValueFactory<>("status"));

        Col1.setPrefWidth(155);
        Col2.setPrefWidth(155);
        tvTests.getColumns().addAll(Col1,Col2);
        tvTests.setOnMouseClicked(event ->{
            initInfo(tvTests.getSelectionModel().getSelectedItem());
        });
    }

    private void initInfo(OrdenPrueba selected){
        LabelOrderDate.setText(usuarioDAO.getName(selected.getUsuario().getCveUsuario()));
        LabelnumOrder.setText(selected.getCveOrden() + "");
        LabelTestType.setText(selected.getTestType());
        cbResult.getSelectionModel().selectFirst();
        while(!cbResult.getSelectionModel().getSelectedItem().equals(selected.getResult())){
            cbResult.getSelectionModel().selectNext();
        }
    }

    EventHandler<ActionEvent> handler = event -> {
        if(event.getSource() == btnExit){
            stage.close();
        }
        if(event.getSource() == btnSave){
            updateResult();
            tvTests.getItems().clear();
            orders = ordenPruebaDAO.getOrdersPerMedic(cveMed);
            tvTests.setItems(orders);
        }
    };

    private void updateResult(){
        if(tvTests.getSelectionModel().getSelectedItem().getStatus().equals("Realizada")){
            if(ordenPruebaDAO.updateEstadoAsFinalizada(tvTests.getSelectionModel().getSelectedItem().getCveOrden(), cbResult.getSelectionModel().getSelectedItem())){
                //success message
                System.out.println("actualizacion exitosa");
            }
        }else{
            System.out.println("operacion no disponible");
        }
    }

    private void initData(){
        ObservableList<String> aux = FXCollections.observableArrayList(OrdenPrueba.results);
        cbResult.setItems(aux);
        orders = ordenPruebaDAO.getOrdersPerMedic(cveMed);
        tvTests.setItems(orders);
    }

    private void filterForms(){
        //TODO define method
    }


}
