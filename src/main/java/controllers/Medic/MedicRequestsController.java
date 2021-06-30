package controllers.Medic;

import Database.ArchivoDAO;
import Database.ConsultaDAO;
import Database.MySQLConnection;
import Database.UsuarioDAO;
import Models.archivo;
import Models.Consulta;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Pair;

import java.awt.*;

import java.io.*;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Date;
import java.util.Optional;
import java.util.ResourceBundle;

public class MedicRequestsController implements Initializable {
    private Stage stage;


    @FXML
    JFXButton btnGeneRece,btnMarcSosp;

    @FXML
    JFXListView<Consulta> lvCons;

    @FXML
    JFXListView<archivo> lvArch;

    @FXML
    TextArea txtSint;


    ConsultaDAO consultaDAO= new ConsultaDAO(MySQLConnection.getConnection());
    ArchivoDAO archivoDAO= new ArchivoDAO(MySQLConnection.getConnection());

    ContextMenu contextMenuConsulta= new ContextMenu();
    ContextMenu contextMenuArchivos= new ContextMenu();


    ObservableList<archivo> archivos;
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        initData();
        initMenus();
        initGUI();

    }

    private EventHandler<ActionEvent> handlerButtons = new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
            if(event.getSource()==btnGeneRece){

                genRece();

            }else if(event.getSource()==btnMarcSosp){
                marcSosp(lvCons.getSelectionModel().getSelectedItem());
            }

        }
    };

    private void genRece()
    {
        /*
        metodo para abrir la ventana de generar receta
         */
    }

    private void marcSosp(Consulta consulta)
    {
        UsuarioDAO usuarioDAO=new UsuarioDAO(MySQLConnection.getConnection());

        if( usuarioDAO.updateSalu(consulta.getUsuario().getCveUsuario()))
        {
            showMessage("Update","Marcado como sospechoso");
        }else{
            showMessage("Error","Ocurrio un error");
        }
    }


    private void initMenus()
    {
        btnGeneRece.setOnAction(handlerButtons);
        btnMarcSosp.setOnAction(handlerButtons);
        MenuItem menuItemAgendar = new MenuItem("Agendar");

        FontAwesomeIconView iconCalendar = new FontAwesomeIconView(FontAwesomeIcon.CALENDAR);
        iconCalendar.setFill(Color.BLUE);
        menuItemAgendar.setGraphic(iconCalendar);

        menuItemAgendar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                openAgendarDialog();
            }
        });

        contextMenuConsulta.getItems().addAll(menuItemAgendar);
        lvCons.setContextMenu(contextMenuConsulta);

        MenuItem menuItemVer = new MenuItem("Ver");

        FontAwesomeIconView iconView = new FontAwesomeIconView(FontAwesomeIcon.EYE);
        iconView.setFill(Color.GREEN);
        menuItemVer.setGraphic(iconView);

        menuItemVer.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    openFile(lvArch.getSelectionModel().getSelectedItem());
                } catch (SQLException | IOException throwables) {
                    throwables.printStackTrace();
                }
            }
        });

        contextMenuArchivos.getItems().addAll(menuItemVer);
        lvArch.setContextMenu(contextMenuArchivos);


    }

    public void setStage(Stage stage){
        this.stage = stage;
    }


    private void initGUI()
    {
        lvCons.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                archivos=archivoDAO.getAll(1);
                lvArch.getItems().addAll(archivos);
                lvArch.refresh();
                Consulta consulta = lvCons.getSelectionModel().getSelectedItem();
                fillTextAndFiles(consulta);
            }
        });
    }
    private void fillTextAndFiles(Consulta consulta)
    {

        txtSint.setText(consulta.getSintomas());
        try{
            archivos.clear();
            lvArch.getItems().addAll(archivos);
            lvArch.refresh();
            lvArch.getItems().clear();

        }catch (Exception e){

        }

        lvArch.getItems().addAll(archivoDAO.getAll(consulta.getCveConsulta()));
    }

    private void openAgendarDialog()
    {

        Dialog dialog = new Dialog();
        dialog.setTitle("Agendar");
        dialog.setHeaderText("Agendar cita");

        ButtonType AgenButtonType = new ButtonType("Agendar", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(AgenButtonType, ButtonType.CANCEL);

        HBox pane = new HBox(15);
        pane.setPadding(new Insets(0, 150, 50, 150));
        DatePicker datePicker=new DatePicker();
        datePicker.setPromptText("Seleccione una fecha");
        pane.getChildren().addAll(datePicker);

        Node btnAgen = dialog.getDialogPane().lookupButton(AgenButtonType);
        btnAgen.setDisable(true);


        datePicker.getEditor().textProperty().addListener((observable, oldValue, newValue) -> {
            btnAgen.setDisable(newValue.trim().isEmpty());
        });

        dialog.getDialogPane().setContent(pane);

        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == AgenButtonType) {
                agendaCita(datePicker.getValue());
            }
            return null;
        });


        Optional result = dialog.showAndWait();


    }

    private void agendaCita(LocalDate date)
    {
        String fecha=date.getYear()+"/"+date.getMonthValue()+"/"+date.getDayOfMonth();
        if(consultaDAO.update(fecha,lvCons.getSelectionModel().getSelectedItem().getCveConsulta()))
        {
            showMessage("Agendada","Agendada con exito");
        }else
        {
            showMessage("Error","Ocurrió un error");
        }
    }

    private void showMessage(String title, String message)
    {
        Alert alert= new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.show();
    }

    private void initData(){
        lvCons.getItems().addAll(consultaDAO.getAll());
    }

    private void openFile(archivo archivo) throws SQLException, IOException {
        Desktop.getDesktop().open(archivo.getArchivo());
    }
}