package controllers.estudiante;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
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
    Button btnreturn,btnPruebas;
    @FXML
    DatePicker dpfecha;
    @FXML
    TextField tfhora;
    @FXML
    TextArea tasintomas;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initbottones();

    }

private EventHandler<ActionEvent> hanlerbuttons = new EventHandler<ActionEvent>() {
    @Override
    public void handle(ActionEvent event) {
        if(event.getSource()==btnPruebas)
        {
            String aux;
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("open file");
            File file = fileChooser.showOpenDialog(new Stage());
            aux= file.getAbsolutePath();
            System.out.println(aux);

        }
    }
};

    private void initbottones(){
        btnPruebas.setOnAction(hanlerbuttons);
    }
}
