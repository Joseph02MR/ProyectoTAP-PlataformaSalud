package controllers;

import Models.Estudiante;
import Models.Medicamento;
import Models.Medico;
import Models.Prescription;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;

public class PrescriptionList implements Initializable {



    @FXML
    ListView<Prescription> lvPrescriptions;

    ObservableList<Prescription> prescriptions;

    ContextMenu contextMenuProducts= new ContextMenu();
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        try {
            initData();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        initMenus();
        initGUI();

    }

    private void initMenus()
    {
        MenuItem menuItemPrescriptionDetails = new MenuItem("View PDF");

        FontAwesomeIconView iconList = new FontAwesomeIconView(FontAwesomeIcon.FILE_PDF_ALT);
        iconList.setFill(Color.RED);
        menuItemPrescriptionDetails.setGraphic(iconList);

        menuItemPrescriptionDetails.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //Generar pdf y abrirlo
            }
        });

        contextMenuProducts.getItems().addAll(menuItemPrescriptionDetails);

       lvPrescriptions.setContextMenu(contextMenuProducts);

    }

    private void initGUI()
    {
        lvPrescriptions.setItems(prescriptions);
    }

    private void initData() throws ParseException {

        prescriptions= FXCollections.observableArrayList();

        SimpleDateFormat formatter1=new SimpleDateFormat("dd/MM/yyyy");

        String sDate1="31/12/1998";

        Date firstDate = new Date();

        ArrayList<Medicamento> medList=new ArrayList<Medicamento>();
        medList.add(new Medicamento());
        medList.add(new Medicamento());
        medList.add(new Medicamento());
        medList.add(new Medicamento());
        medList.add(new Medicamento());

        Date date1=formatter1.parse(sDate1);


        prescriptions.add(new Prescription(new Medico(),new Estudiante(),medList));
        prescriptions.add(new Prescription(new Medico(),new Estudiante(),medList));
        prescriptions.add(new Prescription(new Medico(),new Estudiante(),medList));
        prescriptions.add(new Prescription(new Medico(),new Estudiante(),medList));
        prescriptions.add(new Prescription(new Medico(),new Estudiante(),medList));
        prescriptions.add(new Prescription(new Medico(),new Estudiante(),medList));
        prescriptions.add(new Prescription(new Medico(),new Estudiante(),medList));
        prescriptions.add(new Prescription(new Medico(),new Estudiante(),medList));
        prescriptions.add(new Prescription(new Medico(),new Estudiante(),medList));
        prescriptions.add(new Prescription(new Medico(),new Estudiante(),medList));
        prescriptions.add(new Prescription(new Medico(),new Estudiante(),medList));
        prescriptions.add(new Prescription(new Medico(),new Estudiante(),medList));
        prescriptions.add(new Prescription(new Medico(),new Estudiante(),medList));
        prescriptions.add(new Prescription(new Medico(),new Estudiante(),medList));
        prescriptions.add(new Prescription(new Medico(),new Estudiante(),medList));
        prescriptions.add(new Prescription(new Medico(),new Estudiante(),medList));
        prescriptions.add(new Prescription(new Medico(),new Estudiante(),medList));
        prescriptions.add(new Prescription(new Medico(),new Estudiante(),medList));
        prescriptions.add(new Prescription(new Medico(),new Estudiante(),medList));
        prescriptions.add(new Prescription(new Medico(),new Estudiante(),medList));
        prescriptions.add(new Prescription(new Medico(),new Estudiante(),medList));
        prescriptions.add(new Prescription(new Medico(),new Estudiante(),medList));
        prescriptions.add(new Prescription(new Medico(),new Estudiante(),medList));
        prescriptions.add(new Prescription(new Medico(),new Estudiante(),medList));
        prescriptions.add(new Prescription(new Medico(),new Estudiante(),medList));
        prescriptions.add(new Prescription(new Medico(),new Estudiante(),medList));
        prescriptions.add(new Prescription(new Medico(),new Estudiante(),medList));

    }

}
