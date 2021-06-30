package controllers.general;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.*;

import java.net.URL;
import java.util.ResourceBundle;

public class Graficas implements Initializable {

    @FXML
    PieChart pieCarrera,pieDepto,pieConsultas;

    @FXML
    BarChart barCarrera,barDepto;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        initData();

    }
    public void initData()
    {
        ObservableList<PieChart.Data> pieCarreraData =
                FXCollections.observableArrayList(
                        new PieChart.Data("Sistemas", 13),
                        new PieChart.Data("Ambiental", 25),
                        new PieChart.Data("Mecatronnica", 10),
                        new PieChart.Data("Electronica", 22),
                        new PieChart.Data("Quimica", 30),
                        new PieChart.Data("Bioquimica", 10),
                        new PieChart.Data("Industrial", 22),
                        new PieChart.Data("Administracion", 30));

        pieCarrera.setData(pieCarreraData);


        ObservableList<PieChart.Data> pieDeptoData =
                FXCollections.observableArrayList(
                        new PieChart.Data("Consejo de Administracion", 13),
                        new PieChart.Data("Direccion", 25),
                        new PieChart.Data("Consejo Escolar", 10),
                        new PieChart.Data("Jefaturas de Estudios", 22));

        pieDepto.setData(pieDeptoData);


        ObservableList<PieChart.Data> pieConsultasData =
                FXCollections.observableArrayList(
                        new PieChart.Data("Atendidas", 13),
                        new PieChart.Data("Restantes", 25));

        pieConsultas.setData(pieConsultasData);



        final CategoryAxis xAxis = new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis();

        final CategoryAxis xAxis1 = new CategoryAxis();
        final NumberAxis yAxis1 = new NumberAxis();




        xAxis.setLabel("Carrera");
        yAxis.setLabel("Total");

        xAxis1.setLabel("Departamento");
        yAxis1.setLabel("Total");

        XYChart.Series series1 = new XYChart.Series();
        series1.setName("Carera");
        series1.getData().add(new XYChart.Data("Sistemas", 20));
        series1.getData().add(new XYChart.Data("Ambiental", 10));
        series1.getData().add(new XYChart.Data("Mecatronica", 15));
        series1.getData().add(new XYChart.Data("Quimica", 50));
        series1.getData().add(new XYChart.Data("Bioquimica", 2));


        XYChart.Series series2 = new XYChart.Series();
        series2.setName("Departamento");
        series2.getData().add(new XYChart.Data("Consejo", 10));
        series2.getData().add(new XYChart.Data("Direccion", 30));
        series2.getData().add(new XYChart.Data("Ciencias basicas", 20));
        series2.getData().add(new XYChart.Data("Jefatura", 70));
        series2.getData().add(new XYChart.Data("Administracion", 20));



        barCarrera.getData().addAll(series1);
        barDepto.getData().addAll(series2);

    }
}
