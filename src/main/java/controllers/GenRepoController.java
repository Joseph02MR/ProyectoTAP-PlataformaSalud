package controllers;


import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class GenRepoController implements Initializable {

    @FXML
    JFXButton btnPrintRep1,btnPrintRep2,btnPrintRep3,btnPrintRep4,btnviewGraph;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initGUI();
    }
    private void initGUI()
    {
        btnPrintRep1.setOnAction(handler);
        btnPrintRep2.setOnAction(handler);
        btnPrintRep3.setOnAction(handler);
        btnPrintRep4.setOnAction(handler);
        btnviewGraph.setOnAction(handler);
    }
    EventHandler<ActionEvent> handler = event -> {
        if(event.getSource() == btnPrintRep1){

        }else if(event.getSource()==btnPrintRep2){

        }else if(event.getSource()==btnPrintRep3){

        }else if(event.getSource()==btnPrintRep4){

        }else if(event.getSource()==btnviewGraph){
            try {
                showWindowGraphics();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    };
    private void showWindowGraphics() throws IOException
    {
        Stage login = new Stage();
        login.setTitle("Graficas");
        Parent root = FXMLLoader.load(getClass().getResource("/Accesos/Graficas.fxml"));
        Scene scene = new Scene(root);
        login.setScene(scene);
        login.setResizable(false);
        login.setMaximized(true);
        login.show();
    }

}
