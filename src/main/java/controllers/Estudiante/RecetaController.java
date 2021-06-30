package controllers.Estudiante;

import Database.MySQLConnection;
import Database.RecetaDAO;
import Models.Receta;
import Models.Views.Receta.RecetaReporte;
import Reports.IOMethods;
import Reports.RecetaPDF;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
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

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class RecetaController implements Initializable {

    @FXML
    ImageView Logo,Logo2;
    @FXML
    ListView<Receta> lvrecetas;
    @FXML
    Button btnreturn;
    int cveUser;

    ContextMenu contextMenu = new ContextMenu();
    RecetaDAO recetaDAO = new RecetaDAO(MySQLConnection.getConnection());

    ObservableList<Receta> recetas;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Image R = new Image("/Images/Logo/itc.png");
        Image P = new Image("/Images/Logo/multidisciplinar.png");
        Logo.setImage(R);
        Logo2.setImage(P);

        initData();
        initmenu();
    }

    public void setUser(int cveUsuario){
        this.cveUser = cveUsuario;
    }

    private void initData(){
        recetas = recetaDAO.getListPerUser(cveUser);

        for(var x : recetas){
            x.toString();
        }
        if(recetas.size() >= 0){
            lvrecetas.setItems(recetas);
        }
    }

    private void initmenu(){
        MenuItem imprimir =new MenuItem("Imprimir");

        FontAwesomeIconView icon = new FontAwesomeIconView(FontAwesomeIcon.PRINT);
        icon.setFill(Color.BLACK);
        icon.setGlyphSize(19);
        imprimir.setGraphic(icon);

        imprimir.setOnAction(printhandler);

        contextMenu.getItems().add(imprimir);
        lvrecetas.setContextMenu(contextMenu);
    }

    EventHandler<ActionEvent> printhandler = event ->{
        try {
            printReceipt(lvrecetas.getSelectionModel().getSelectedItem().getNoReceta());
        } catch (IOException e) {
            e.printStackTrace();
        }
    };

    private void printReceipt(int noReceta) throws IOException {
        RecetaReporte receta = recetaDAO.getDataForPrint(noReceta);
        RecetaPDF recetaPDF = new RecetaPDF();
        String aux= recetaPDF.ReceiptGen(receta);
        IOMethods.openFile(aux);
    }
}
