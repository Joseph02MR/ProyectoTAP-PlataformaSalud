package controllers.Estudiante;

import Models.Alerta;
import com.jfoenix.controls.JFXButton;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class Alert_Manager implements Initializable {

    @FXML
    JFXButton btnreturn;
    @FXML
    ListView<Alerta> notifications;
    @FXML
    ImageView logo,logo2;

    ContextMenu contextMenu = new ContextMenu();

    ObservableList<Alerta> A;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Image R = new Image("/Images/Logo/notif.png");
        Image P = new Image("/Images/Logo/profile.jpg");
        logo.setImage(R);
        logo2.setImage(P);
        add();
        initinfo();
        initmenu();
        initbuttons();


    }
    private void initinfo()
    {
        notifications.setItems(A);
    }
    private void add(){
        A= FXCollections.observableArrayList();

        A.add(new Alerta("Recibiste una receta"));
        A.add(new Alerta("Contesta el siguiente formulario"));
        A.add(new Alerta("Recibiste una notificaion"));
        A.add(new Alerta("Tienes cobi"));
        A.add(new Alerta("Recibiste una receta"));
        A.add(new Alerta("Recibiste una receta"));
        A.add(new Alerta("Recibiste una receta"));
        A.add(new Alerta("Recibiste una receta"));
        A.add(new Alerta("Recibiste una receta"));
        A.add(new Alerta("Recibiste una receta"));
    }

    private void initmenu(){
        MenuItem Delete =new MenuItem("Delete");

        FontAwesomeIconView icon = new FontAwesomeIconView(FontAwesomeIcon.TRASH);
        icon.setFill(Color.CRIMSON);
        icon.setGlyphSize(19);
        Delete.setGraphic(icon);

        Delete.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

            }
        });
        contextMenu.getItems().addAll(Delete);
        notifications.setContextMenu(contextMenu);
    }
    private EventHandler<ActionEvent> handler = new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
            if(event.getSource()==btnreturn)
            {
                final Node source = (Node) event.getSource();
                final Stage stage = (Stage) source.getScene().getWindow();
                stage.close();
            }
        }
    };

    private void initbuttons(){
        btnreturn.setOnAction(handler);
    }
}
