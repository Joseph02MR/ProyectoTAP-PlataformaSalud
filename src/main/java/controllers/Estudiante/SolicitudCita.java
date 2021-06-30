package controllers.Estudiante;


import Database.ArchivoDAO;
import Database.ConsultaDAO;
import Database.MySQLConnection;
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
import java.net.URL;
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
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initbottones();
        initgui();
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
                //aux2= file.getName();
                //aux= file.getAbsolutePath();
                listaarchivos.add(file);
                listanamearchivos.add(file.getName());
            }
            else if(event.getSource()==btnreturn){
                final Node source = (Node) event.getSource();
                final Stage stage = (Stage) source.getScene().getWindow();
                stage.close();
            }else if(event.getSource()==btncreate){
                createconsult();
            }
        }
    };
    private void initgui(){
        LVARCHIVOS.setItems(listanamearchivos);
        Nombre.setText("Ivan");
        Apellido.setText("Leon Palma");
    }

    private void initbottones(){
        btnPruebas.setOnAction(hanlerbuttons);
        btnreturn.setOnAction(hanlerbuttons);
        btncreate.setOnAction(hanlerbuttons);
    }

    private void createconsult(){
        archivo archivoo = new archivo();
        Usuario usuario = new Usuario();
        Consulta consulta = new Consulta();
        usuario.setCveUsuario(1);
        int aux=0;
        consulta.setUsuario(usuario);
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
            archivoo.setNombre(x.getName());
            archivoo.setRuta(x.getAbsolutePath());
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
