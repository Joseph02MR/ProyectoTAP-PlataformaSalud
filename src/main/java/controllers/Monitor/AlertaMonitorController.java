package controllers.Monitor;

import Models.Usuario;
import com.jfoenix.controls.JFXButton;
import Database.AlertaDAO;
import Database.MySQLConnection;
import Database.UsuarioDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import jdk.jfr.Enabled;
import Models.Alerta;
import Models.Usuario;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class AlertaMonitorController implements Initializable {

    @FXML
    JFXButton btnreturn,btnDEST; //btnDest es el de guardad
    @FXML
    ComboBox<String> cbalert;
    @FXML
    ComboBox<Usuario> cbpositivos;
    @FXML
    ListView lvPosit;
    @FXML
    Text txtFecha,txtDests;

    int alertType;


    Usuario usuario = new Usuario();






    ObservableList listaPositivos = FXCollections.observableArrayList();

    ObservableList listaPosibleSospechoso = FXCollections.observableArrayList();
    LocalDate date = LocalDate.now();
    Stage stage;


    AlertaDAO alertaDAO = new AlertaDAO(MySQLConnection.getConnection());
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        combobox();
        initGUI();
        lvSeleccion();
        //initListaPositivos();

        clickReturn(btnreturn);



        clickSave(btnDEST);

        btnDEST.setDisable(true);

    }
    void initComboboxPositivos(){

        UsuarioDAO usuarioDAO = new UsuarioDAO(MySQLConnection.getConnection());
        listaPositivos.removeAll(listaPositivos);//limpiamos la lista
        listaPositivos.addAll(usuarioDAO.getListUsuariosPositivos());
        cbpositivos.setItems(listaPositivos);

        cbpositivos.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                Usuario usuarioSelected = (Usuario) cbpositivos.getSelectionModel().getSelectedItem();
                System.out.println(usuarioSelected);

                if(usuarioSelected==null){
                    //no pasa nada oiga
                }else {

                    initListaSospechosos(usuarioSelected);//lennamos el listView con la lista de sospechosos
                }

            }
        });
    }

    private void initListaPositivos() {
        UsuarioDAO usuarioDAO = new UsuarioDAO(MySQLConnection.getConnection());
        listaPositivos.removeAll(listaPositivos);//limpiamos la lista
        listaPositivos.addAll(usuarioDAO.getListUsuariosPositivos());
        lvPosit.setItems(listaPositivos);


    }
    private void initListaSospechosos(Usuario usuario) {

        UsuarioDAO usuarioDAO = new UsuarioDAO(MySQLConnection.getConnection());
        listaPosibleSospechoso.removeAll(listaPosibleSospechoso);

        if(usuarioDAO.getListaSospechososXPositivos(usuario.getCveUsuario())==null){
            //no hace nada
        }else {

            listaPosibleSospechoso = usuarioDAO.getListaSospechososXPositivos(usuario.getCveUsuario());
            System.out.println("Clave Usuario seleccionado: " + usuario.getCveUsuario());

            for (var x : listaPosibleSospechoso) {
                x.toString();
            }

            lvPosit.setItems(listaPosibleSospechoso);
        }
    }

    private void initGUI() {
        btnreturn.setOnAction(pp);

        //inicializamos la fecha actual

        txtFecha.setText(date.toString());

        //damos valor al texto de destinatario
        txtDests.setText("Sin Destinatario");

    }

    public void setStage(Stage stage){
        this.stage = stage;
    }




    public  EventHandler pp = new EventHandler() {
        @Override
        public void handle(Event event) {

            if(cbalert.getSelectionModel().getSelectedItem()==null){

            }else {
                if (cbalert.getSelectionModel().getSelectedItem().equalsIgnoreCase("Positivo")) {
                    cbpositivos.setDisable(true);
                    initListaPositivos();//cargamos el listview con toda la lista de usuarios positivos
                    alertType = 0;
                    txtDests.setText("Sin Destinatario");
                    btnDEST.setDisable(true);
                } else {
                    cbpositivos.setDisable(false);
                    lvPosit.getItems().clear();
                    lvPosit.setItems(listaPosibleSospechoso);
                    initComboboxPositivos();
                    txtDests.setText("Sin Destinatario");
                    btnDEST.setDisable(true);

                    alertType = 1;

                }
            }
        }
    };



    private void clickSave(Button button){ //tengo que dar de alta la alerta
        button.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Usuario usuarioSelected = (Usuario) lvPosit.getSelectionModel().getSelectedItem();
                //usuarioSelected
                System.out.println("Nombre "+lvPosit.getSelectionModel().getSelectedItem());
                System.out.println("Clave usuario save: "+usuarioSelected.getCveUsuario());

                Alerta alerta = new Alerta();

                alerta.setAlertType(alertType);
                alerta.setDate(date);



                alerta.setCveUsuario(usuarioSelected.getCveUsuario());
                alerta.setCveUsuarioMonitor(usuario.getCveUsuario());



                if(alertaDAO.crearAlerta(alerta)){
                    JOptionPane.showMessageDialog(null,"Se ha dado de alta correctamente la alerta");
                }else{
                    JOptionPane.showMessageDialog(null,"Upsi ha ocurrido un error");
                }



                if(alertaDAO.crearEnvia(alerta)){
                    JOptionPane.showMessageDialog(null,"Se ha dado de alta correctamente ENVIA");
                }else{
                    JOptionPane.showMessageDialog(null,"Upsi ha ocurrido un error");
                }
            }
        });
    }



    private void clickReturn(Button button){
        button.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                stage.close();
            }
        });
    }


    private void lvSeleccion() {

        lvPosit.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {

                if(event.getClickCount()==2){

                    txtDests.setText(lvPosit.getSelectionModel().getSelectedItem().toString());
                    btnDEST.setDisable(false);
                }
            }
        });
    }


    private void combobox(){
        cbalert.getItems().addAll("Positivo","Sospechoso");
        cbalert.setOnAction(pp);

    }
    private void prueba(){
        System.out.println(usuario.getCveUsuario());
    }
}

