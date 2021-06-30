package controllers.Estudiantes;

import Models.Alerta;
import Models.Consult;
import de.jensd.fx.glyphs.emojione.EmojiOne;
import de.jensd.fx.glyphs.emojione.EmojiOneView;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

import java.net.URL;
import java.util.ResourceBundle;

public class Consults implements Initializable {

    @FXML
    ImageView Logo,Logo2;
    @FXML
    ListView consults;
    @FXML
    Button btnreturn;

    ContextMenu contextMenu = new ContextMenu();

    ObservableList<Consult> C;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Image R = new Image("/Images/Logo/itc.png");
        Image P = new Image("/Images/Logo/multidisciplinar.png");
        Logo.setImage(R);
        Logo2.setImage(P);

        add();
        initinfo();
        initmenu();
    }
    private void initinfo()
    {
        consults.setItems(C);
    }
    private void add(){
        C= FXCollections.observableArrayList();

        C.add(new Consult("Ivan Leon Consulta"));
        C.add(new Consult("Ivan Leon Consulta"));
        C.add(new Consult("Ivan Leon Consulta"));
        C.add(new Consult("Ivan Leon Consulta"));
        C.add(new Consult("Ivan Leon Consulta"));

    }

    private void initmenu(){
        MenuItem Agendar =new MenuItem("Agendar");
        MenuItem Responder =new MenuItem("Responder");

        EmojiOneView icon = new EmojiOneView(EmojiOne.FILE_FOLDER);
        icon.setFill(Color.BLACK);
        icon.setGlyphSize(19);
        Agendar.setGraphic(icon);

        EmojiOneView icon2 = new EmojiOneView(EmojiOne.INCOMING_ENVELOPE);
        icon2.setFill(Color.BLACK);
        icon2.setGlyphSize(19);
        Responder.setGraphic(icon2);

        contextMenu.getItems().addAll(Agendar,Responder);
        consults.setContextMenu(contextMenu);
    }
}
