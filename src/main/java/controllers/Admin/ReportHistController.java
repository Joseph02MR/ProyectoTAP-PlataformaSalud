package controllers.Admin;

import Database.MySQLConnection;
import Database.Views.UserReportsDAO;
import Database.Views.UserReportsDAO;
import Reports.IOMethods;
import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ReportHistController implements Initializable {
    private Stage stage;
    @FXML
    JFXButton btnExit;
    @FXML
    TableView<Object> tvAlerts;
    //ObservableList<Alerta> alertas;

    UserReportsDAO userReportsDAO = new UserReportsDAO(MySQLConnection.getConnection());

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initGUI();
    }

    public void setStage(Stage stage){
        this.stage = stage;
    }

    private void initGUI(){
        btnExit.setOnAction(handler);
    }

    EventHandler<ActionEvent> handler = event -> {
        if(event.getSource() == btnExit){
            stage.close();
        }
    };

    private void initData(){
        //alertas = FXCollections.observableArrayList();
    }

    private void readFileFromDB(int id) throws IOException {
        String filepath = userReportsDAO.getFile(id);
        IOMethods.openFile(filepath);
    }
}
