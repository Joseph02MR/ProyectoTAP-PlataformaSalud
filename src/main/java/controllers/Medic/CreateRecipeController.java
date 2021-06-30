package controllers.Medic;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

public class CreateRecipeController implements Initializable {

    @FXML
    JFXButton  btnAddmed,btnsave;
    @FXML
    TextField tfmedico,tfcedula,tfpaciente,tfdosis;
    @FXML
    ComboBox cbmed;
    @FXML
    ListView lvmeds;
    @FXML
    ImageView mm;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Image logo1 = new Image("/Images/Logo/meds.png");
        mm.setImage(logo1);
        initbuttons();
    }

    private EventHandler<ActionEvent> handler = new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
            if(event.getSource()==btnAddmed)
            {

            }else if(event.getSource()==btnsave)
            {

            }
        }
    };

    private void initbuttons(){
        btnsave.setOnAction(handler);
        btnAddmed.setOnAction(handler);
    }

    private void initdata(){
        lvmeds.getItems();
    }
}
