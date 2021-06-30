package controllers.medic;

import Database.MySQLConnection;
import Database.UsuarioDAO;
import Models.Encuesta;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;


import java.net.URL;
import java.util.ResourceBundle;

public class AnswFormDetController implements Initializable {


    private Stage stage;

    @FXML
    CheckBox chkP1,chkP2,chkP3,chkP4,chkP5,chkP6,chkP7,chkP8,chkP9,chkP10,chkP11,chkP12,chkP13;

    @FXML
    Label lblOtros,lblName,lblStatus;

    @FXML
    ImageView btnBack,imgDetailsIco;

    UsuarioDAO usuarioDAO = new UsuarioDAO(MySQLConnection.getConnection());

    Encuesta encuesta;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
            initGUI();
            initData();
    }

    void initGUI()
    {
        Image image = new Image(getClass().getResource("/Images/Logo/back_btn.png").toString());
        btnBack.setImage(image);

        Image image2 = new Image(getClass().getResource("/Images/Logo/details_image.png").toString());
        imgDetailsIco.setImage(image2);

        btnBack.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            stage.close();
            event.consume();
        });

    }

    private void initData(){
        lblName.setText("Nombre: " +usuarioDAO.getName(encuesta.getCveUsuario()));
        encuesta.getStatusSintomas();
        lblStatus.setText("Status: "+encuesta.getStatusSintomatico());
        chkP1.setSelected(encuesta.isCb1());
        chkP2.setSelected(encuesta.isCb2());
        chkP3.setSelected(encuesta.isCb3());
        chkP4.setSelected(encuesta.isCb4());
        chkP5.setSelected(encuesta.isCb5());
        chkP6.setSelected(encuesta.isCb6());
        chkP7.setSelected(encuesta.isCb7());
        chkP8.setSelected(encuesta.isCb8());
        chkP9.setSelected(encuesta.isCb9());
        chkP10.setSelected(encuesta.isCb10());
        chkP11.setSelected(encuesta.isCb11());
        chkP12.setSelected(encuesta.isCb12());
        chkP13.setSelected(encuesta.isCb13());
        if(encuesta.getSintomas().equals("")){
            lblOtros.setText("Ninguno");
        } else{
            lblOtros.setText(encuesta.getSintomas());
        }
    }


    public void setForm(Encuesta encuesta){
        this.encuesta = encuesta;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }
}
