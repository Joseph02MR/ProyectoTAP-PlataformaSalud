package controllers.medic;

import Database.*;
import Models.Medicamento;
import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class CreateRecipeController implements Initializable {

    @FXML
    JFXButton  btnAddmed,btnsave;
    @FXML
    TextField tfmedico,tfcedula,tfpaciente,tfdosis;
    @FXML
    ComboBox<Medicamento> cbmed;
    @FXML
    ListView<Medicamento> lvmeds;
    @FXML
    ImageView mm;


    ObservableList<Medicamento> meds;
    ObservableList<Medicamento> medsList = FXCollections.observableArrayList();
    private int cveMed;
    private int cveUser;
    private int cveConsulta;

    //DAOS
    UsuarioDAO usuarioDAO = new UsuarioDAO(MySQLConnection.getConnection());
    MedicoDAO medicoDAO = new MedicoDAO(MySQLConnection.getConnection());
    RecetaDAO recetaDAO = new RecetaDAO(MySQLConnection.getConnection());
    MedicamentoDAO medicamentoDAO = new MedicamentoDAO(MySQLConnection.getConnection());
    ConsultaDAO consultaDAO = new ConsultaDAO(MySQLConnection.getConnection());

    //TODO mandar a llamar al instanciar controlador
    public void setClaves(int med, int user, int consulta){
        this.cveMed = med;
        this.cveUser = user;
        this.cveConsulta = consulta;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Image logo1 = new Image("/Images/Logo/meds.png");
        mm.setImage(logo1);
        initbuttons();
        initdata();
    }

    private EventHandler<ActionEvent> handler = new EventHandler<>() {
        @Override
        public void handle(ActionEvent event) {
            if(event.getSource()==btnAddmed) {
                addMed();
            }else if(event.getSource()==btnsave) {
                saveReceipt();
            }
        }
    };

    private void saveReceipt(){
        int cveReceta = recetaDAO.insert(Date.valueOf(LocalDate.now()));
        if(cveReceta != -1){
            if(medicamentoDAO.insertMedsFromReceipt(cveReceta, lvmeds.getItems())){
                consultaDAO.setReceta(cveConsulta, cveReceta);
            }
        }
    }

    private void initbuttons(){
        btnsave.setOnAction(handler);
        btnAddmed.setOnAction(handler);
    }

    private void initdata(){
        tfmedico.setText(usuarioDAO.getName(cveMed));
        tfpaciente.setText(usuarioDAO.getName(cveUser));
        tfcedula.setText(medicoDAO.getCedProf(cveMed));
        meds = medicamentoDAO.getAll();
        cbmed.setItems(meds);
        lvmeds.setItems(medsList);
    }

    private void addMed(){
        medsList.add(new Medicamento(
            cbmed.getSelectionModel().getSelectedItem().getCveMed(),
                cbmed.getSelectionModel().getSelectedItem().getNombre(),
                tfdosis.getText()
        ));
        lvmeds.refresh();
        clearMed();
    }
    private void clearMed(){
        tfdosis.setText("");
        cbmed.getSelectionModel().clearSelection();
    }
}
