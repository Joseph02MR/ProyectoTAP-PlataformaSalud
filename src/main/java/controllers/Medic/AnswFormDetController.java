package controllers.Medic;

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

    Encuesta encuesta;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
            initGUI();

    }

    void initGUI()
    {
        Image image = new Image(getClass().getResource("/Images/back_btn.png").toString());
        btnBack.setImage(image);

        Image image2 = new Image(getClass().getResource("/Images/details_image.png").toString());
        imgDetailsIco.setImage(image2);

         chkP1.setSelected(true);
         chkP2.setSelected(true);
         chkP3.setSelected(true);
         chkP4.setSelected(true);
         chkP5.setSelected(true);
         chkP6.setSelected(true);
         chkP7.setSelected(true);
         chkP8.setSelected(true);
         chkP9.setSelected(true);
         chkP10.setSelected(true);
         chkP11.setSelected(true);
         chkP12.setSelected(true);
         chkP12.setSelected(true);
         chkP13.setSelected(true);

        lblOtros.setText("F EN EL CHAT");
        lblName.setText("RICARDO ELPIDO GARCIA FELIX");
        lblStatus.setText("COVID-19 POSITIVO!");


        btnBack.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            stage.close();
            event.consume();
        });


    }

    public void setForm(Encuesta encuesta){
        this.encuesta = encuesta;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }
}
