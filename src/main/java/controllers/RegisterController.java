package controllers;

import Database.*;
import Models.*;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class RegisterController implements Initializable {
    @FXML
    JFXTextField tfNAME,tfLastNAME,tfEmail, tfGENDER, tfRFC_NoC, tfcedprof, tfhorario, tfespecialidad;
    @FXML
    ComboBox cbCarreraDepto;
    @FXML
    ComboBox<String> cbtipoUser;
    @FXML
    ComboBox<Integer> cbSemestre;
    @FXML
    JFXButton btnCreate,btnCancel;
    @FXML
    JFXPasswordField jppassword;
    @FXML
    DatePicker dpBD;
    @FXML
    HBox h1,h2,h3,h4,h5,h6;

    ObservableList<Carrera> carreras;
    ObservableList<Departamento> deptos;

    //DAOS
    CarreraDAO carreraDAO = new CarreraDAO(MySQLConnection.getConnection());
    DepartamentoDAO departamentoDAO = new DepartamentoDAO(MySQLConnection.getConnection());
    //UsuarioDAO usuarioDAO = new UsuarioDAO(MySQLConnection.getConnection());
    EstudianteDAO estudianteDAO = new EstudianteDAO(MySQLConnection.getConnection());
    PersonalDAO personalDAO = new PersonalDAO(MySQLConnection.getConnection());
    MedicoDAO medicoDAO = new MedicoDAO(MySQLConnection.getConnection());

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initGUI();
    }

    private void initGUI(){
        cbtipoUser.getItems().addAll("Estudiante", "Personal", "Medico");
        cbtipoUser.setOnAction(Elementshandler);
        h1.setVisible(false);
        h2.setVisible(false);
        h3.setVisible(false);
        h4.setVisible(false);
        h5.setVisible(false);
        h6.setVisible(false);
        initbuttons();
    }

    private EventHandler<ActionEvent> windowHandler = new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
            if(event.getSource()==btnCreate){
                if(cbtipoUser.getSelectionModel().getSelectedItem().equals("Estudiante")){
                    registroEstudiante();
                }else if (cbtipoUser.getSelectionModel().getSelectedItem().equals("Personal")){
                    registroPersonal();
                }else if (cbtipoUser.getSelectionModel().getSelectedItem().equals("Medico")){
                    registroMedico();
                }
                closeWindow(event);
            }
            else if(event.getSource()==btnCancel) {
                closeWindow(event);
            }
        }
    };

    private void registroEstudiante(){
        Estudiante usuario = new Estudiante();
        usuario.setNombre(tfNAME.getText());
        usuario.setApellidos(tfLastNAME.getText());
        usuario.setGenero(tfGENDER.getText().charAt(0));
        usuario.setEmail(tfEmail.getText());
        usuario.setPassword(jppassword.getText());
        usuario.setEdad(dpBD.getValue());
        usuario.setEdocuenta(0);
        usuario.setEdosalud(0);
        usuario.setPriv(0);
        usuario.setCarrera(cbCarreraDepto.getSelectionModel().getSelectedItem().toString());
        usuario.setNumCtrl(tfRFC_NoC.getText());
        usuario.setSem(cbSemestre.getSelectionModel().getSelectedItem());

        if(estudianteDAO.insert(usuario)) {
            clearinfo();
            showMessage("User Registration","Success");
        }
    }

    private void registroPersonal(){
        Personal usuario = new Personal(
                jppassword.getText(),
                tfEmail.getText(),
                tfNAME.getText(),
                tfLastNAME.getText(),
                tfGENDER.getText().charAt(0),
                dpBD.getValue(), 0, 0, 0,
                tfRFC_NoC.getText(),
                cbCarreraDepto.getSelectionModel().getSelectedItem().toString(),
                false
        );

        if(personalDAO.insert(usuario)) {
            clearinfo();
            showMessage("User Registration","Success");
        }
    }

    private void registroMedico(){
        Medico usuario = new Medico(
                jppassword.getText(),
                tfEmail.getText(),
                tfNAME.getText(),
                tfLastNAME.getText(),
                tfGENDER.getText().charAt(0),
                dpBD.getValue(), 0, 0, 0,
                tfRFC_NoC.getText(),
                cbCarreraDepto.getSelectionModel().getSelectedItem().toString(),
                false,
                tfcedprof.getText(),
                tfespecialidad.getText(),
                tfhorario.getText()
        );

        if(medicoDAO.insert(usuario)) {
            clearinfo();
            showMessage("User Registration","Success");
        }
    }

    private void initbuttons(){
        btnCancel.setOnAction(windowHandler);
        btnCreate.setOnAction(windowHandler);
    }

    private void clearinfo(){
        tfEmail.setText("");
        tfLastNAME.setText("");
        tfGENDER.setText("");
        jppassword.setText("");
        dpBD.getEditor().clear();
        tfNAME.setText("");
        tfRFC_NoC.setText("");
        tfhorario.setText("");
        tfespecialidad.setText("");
        tfcedprof.setText("");
        cbCarreraDepto.getSelectionModel().clearSelection();
        cbSemestre.getSelectionModel().clearSelection();
    }

    private  void  showMessage(String title, String message) {
        Alert alert= new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.show();
    }

    private void closeWindow(Event event){
        final Node source = (Node) event.getSource();
        final Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }

    EventHandler Elementshandler = event -> {
        if(cbtipoUser.getSelectionModel().getSelectedItem().equals("Estudiante")){
            initElementsForStudent();
            h4.setVisible(false);
            h5.setVisible(false);
            h6.setVisible(false);
        }
        else if(cbtipoUser.getSelectionModel().getSelectedItem().equals("Personal")) {
            initElementsForPersonal();
            h3.setVisible(false);
            h4.setVisible(false);
            h5.setVisible(false);
            h6.setVisible(false);
        }
        else if(cbtipoUser.getSelectionModel().getSelectedItem().equals("Medico")) {
            initElementsForPersonal();
            h3.setVisible(false);
            h4.setVisible(true);
            h4.setVisible(true);
            h5.setVisible(true);
            h6.setVisible(true);
        }
    };

    private void initElementsForStudent(){
        h1.setVisible(true);
        h2.setVisible(true);
        h3.setVisible(true);
        tfRFC_NoC.setPromptText("No. de Ctrl");
        cbCarreraDepto.setPromptText("Carrera");
        initComboCarreraDepto(true);
        cbSemestre.setPromptText("Semestre");
        cbSemestre.getItems().addAll(1,2,3,4,5,6,7,8,9,10,11,12,13);
    }

    private void initElementsForPersonal(){
        h1.setVisible(true);
        h2.setVisible(true);
        tfRFC_NoC.setPromptText("RFC");
        cbCarreraDepto.setPromptText("Departamento");
        initComboCarreraDepto(false);
    }

    private void initComboCarreraDepto(boolean type) {
        if(!cbCarreraDepto.getItems().isEmpty()){
            cbCarreraDepto.getItems().clear();
        }

        if(type){
            carreras = carreraDAO.getAll();
            for(var x : carreras){
                x.toString();
            }
            cbCarreraDepto.getItems().addAll(carreras);

        } else {
            deptos = departamentoDAO.getAll();
            for(var x : deptos){
                x.toString();
            }
            cbCarreraDepto.getItems().addAll(deptos);
        }
    }


}
