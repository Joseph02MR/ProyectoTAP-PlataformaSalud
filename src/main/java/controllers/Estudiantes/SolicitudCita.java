package controllers.Estudiantes;

import Database.ArchivoDAO;
import Database.ConsultaDAO;
import Database.MySQLConnection;
import Database.UsuarioDAO;
import Models.Consulta;
import Models.Usuario;
import Models.archivo;
import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;


import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class SolicitudCita implements Initializable {

    @FXML
    Text Nombre,Apellido;
    @FXML
    JFXButton btnreturn,btnPruebas,btncreate;
    @FXML
    TextArea tasintomas;
    @FXML
    ListView LVARCHIVOS;
    @FXML
    ComboBox cbtipoconsulta;

    ObservableList<File>listaarchivos = FXCollections.observableArrayList();
    ObservableList<String>listanamearchivos = FXCollections.observableArrayList();
    ConsultaDAO consultaDAO = new ConsultaDAO(MySQLConnection.getConnection());
    ArchivoDAO archivoDAO = new ArchivoDAO(MySQLConnection.getConnection());
    UsuarioDAO usuarioDAO = new UsuarioDAO(MySQLConnection.getConnection());
    Usuario user= new Usuario();
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initbottones();
        try {
            initgui();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        initcombobox();

    }

    private EventHandler<ActionEvent> hanlerbuttons = new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
            if(event.getSource()==btnPruebas)
            {
                String aux,aux2;
                FileChooser fileChooser = new FileChooser();
                fileChooser.setTitle("open file");
                File file = fileChooser.showOpenDialog(new Stage());
                listaarchivos.add(file);
                listanamearchivos.add(file.getName());
            }
            else if(event.getSource()==btnreturn){
                final Node source = (Node) event.getSource();
                final Stage stage = (Stage) source.getScene().getWindow();
                stage.close();
            }else if(event.getSource()==btncreate){
                try {
                    createconsult();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                final Node source = (Node) event.getSource();
                final Stage stage = (Stage) source.getScene().getWindow();
                stage.close();
            }
        }
    };
    private void initgui() throws SQLException {
        LVARCHIVOS.setItems(listanamearchivos);
        Nombre.setText(usuarioDAO.searchfields(user.getCveUsuario()).getNombre());
        Apellido.setText(usuarioDAO.searchfields(user.getCveUsuario()).getApellidos());
    }

    private void initbottones(){
        btnPruebas.setOnAction(hanlerbuttons);
        btnreturn.setOnAction(hanlerbuttons);
        btncreate.setOnAction(hanlerbuttons);
    }

    private void createconsult() throws FileNotFoundException {
        archivo archivoo = new archivo();
        Consulta consulta = new Consulta();
        int aux=0;
        consulta.setUsuario(user);
        consulta.setEstado("pendiente");
        consulta.setSintomas(tasintomas.getText());
        if(cbtipoconsulta.getSelectionModel().getSelectedItem().toString().equalsIgnoreCase("Presencial")){
            aux=1;
        }else{
            aux=0;
        }
        consulta.setTipocionsulta(aux);
        if(consultaDAO.insert(consulta)){
            showMessage("Consulta","Consulta registrada");
        }
        archivoo.setConsulta(consulta);
        consultaDAO.CVECONS(consulta);
        for (var x : listaarchivos
        ) {
            /*cambio*/
            archivoo.setNombre(x.getName());
            archivoo.setArchivo(x);
            if(archivoDAO.insert(archivoo)){
                showMessage("Archivo(s) nuevo","se han agregado los archivos");
            }
        }
    }

    private  void  showMessage(String tittle, String message)
    {
        Alert alert= new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(tittle);
        alert.setContentText(message);
        alert.show();
    }

    private void initcombobox(){
        cbtipoconsulta.getItems().addAll("Presencial","Virtual");
    }

}