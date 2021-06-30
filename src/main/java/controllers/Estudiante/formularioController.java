package controllers.Estudiante;

import Database.EncuestaDAO;
import Database.MySQLConnection;
import Models.Encuesta;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class formularioController implements Initializable {
    @FXML
    JFXCheckBox cb1,cb2,cb3,cb4,cb5,cb6,cb7,cb8,cb9,cb10,cb11,cb12,cb13;
    @FXML
    TextField tfSint;
    @FXML
    JFXButton btnEnvi,btnCanc;
    private int cveUsuario;

    EncuestaDAO encuestaDAO = new EncuestaDAO(MySQLConnection.getConnection());

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initGUI();
    }

    public void setUser(int cveUsuario){
        this.cveUsuario = cveUsuario;
    }

    private void saveForm(){
        Encuesta encuesta = new Encuesta(
                cveUsuario,
                cb1.isSelected(),
                cb2.isSelected(),
                cb3.isSelected(),
                cb4.isSelected(),
                cb5.isSelected(),
                cb6.isSelected(),
                cb7.isSelected(),
                cb8.isSelected(),
                cb9.isSelected(),
                cb10.isSelected(),
                cb11.isSelected(),
                cb12.isSelected(),
                cb13.isSelected(),
                tfSint.getText()
        );

        if(encuestaDAO.insert(encuesta)){
            System.out.println("encuesta guardada");
        }
    }

    private void initGUI(){
        btnEnvi.setOnAction(handler);
        btnCanc.setOnAction(handler);
    }

    EventHandler<ActionEvent> handler = event ->{
        if(event.getSource() == btnEnvi){
            saveForm();
        }
        closeWindow(event);
    };

    private void closeWindow(ActionEvent event){
        final Node source = (Node) event.getSource();
        final Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }
}
