package controllers.Estudiante;

import Database.MySQLConnection;
import Database.OrdenPruebaDAO;
import Models.OrdenPrueba;
import com.jfoenix.controls.JFXButton;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class TestOrdersController implements Initializable {
    ObservableList<OrdenPrueba> orders;
    ContextMenu cmOrders = new ContextMenu();
    OrdenPruebaDAO ordenPruebaDAO = new OrdenPruebaDAO(MySQLConnection.getConnection());
    int cveUser;
    Stage stage;
    @FXML
    JFXButton btnExit;
    @FXML
    ListView<OrdenPrueba> lvOrders;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initGUI();
        initData();
        initMenus();
    }

    public void setStage(Stage stage){
        this.stage = stage;
    }

    EventHandler<ActionEvent> btnHandler = event -> {
        if (event.getSource() == btnExit){
            final Node source = (Node) event.getSource();
            final Stage stage = (Stage) source.getScene().getWindow();
            stage.close();
        }
    };

    public void setUser(int cveUser){
        this.cveUser = cveUser;
    }

    private void initMenus(){
        MenuItem miView = new MenuItem("Ver detalles");
        MenuItem realizar = new MenuItem("Marcar Realizada");
        FontAwesomeIconView search = new FontAwesomeIconView(FontAwesomeIcon.SEARCH);
        FontAwesomeIconView check = new FontAwesomeIconView(FontAwesomeIcon.CHECK_SQUARE_ALT);

        miView.setGraphic(search);
        realizar.setGraphic(check);

        miView.setOnAction(event ->{
            try {
                initOrderDetails(lvOrders.getSelectionModel().getSelectedItem());
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        realizar.setOnAction(event ->{
            setAsDone(lvOrders.getSelectionModel().getSelectedItem().getCveOrden());
        });
        cmOrders.getItems().addAll(miView, realizar);
        lvOrders.setContextMenu(cmOrders);
    }

    private void setAsDone(int cveOrden){
        if(LocalDate.now().isBefore(lvOrders.getSelectionModel().getSelectedItem().getOrderdate())) {
            //error fecha de prueba aun no llega
            System.out.println("opción no disponible");
        } else {
            ordenPruebaDAO.updateEstadoAsRealizada(cveOrden);
        }
    }

    private void initGUI(){
        btnExit.setOnAction(btnHandler);
//        lvOrders.setOnMouseClicked(event -> {
//            if(event.getClickCount() == 2){
//                try {
//                    initOrderDetails(lvOrders.getSelectionModel().getSelectedItem());
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        });
    }

    private void initData(){
        orders = ordenPruebaDAO.getOrdersPerUser(cveUser);

       for(var x: orders){
           x.toString();
       }

        lvOrders.setItems(orders);
    }

    private void initOrderDetails(OrdenPrueba order) throws IOException {
        Stage orderDStage = new Stage();
        orderDStage.setTitle("Detalles de la Orden");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Accesos/Estudiante/testDetails_view.fxml"));
        OrderDetailsController orderDet = new OrderDetailsController();
        loader.setController(orderDet);
        orderDet.setPrueba(order);
        Parent root = loader.load();
        orderDStage.getIcons().add(new Image("/Images/Logo/btq.png"));
        Scene scene = new Scene(root);
        orderDStage.setScene(scene);
        orderDStage.setResizable(false);
        orderDStage.show();
    }
}
