package Database;

import Models.Receta;
import Models.Views.Receta.MedicamentoReceta;
import Models.Views.Receta.RecetaReporte;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.util.List;

public class RecetaDAO {

    Connection conn;
    ObservableList<Receta> listReceta = FXCollections.observableArrayList();
    ObservableList<MedicamentoReceta> listMeds = FXCollections.observableArrayList();


    public RecetaDAO(Connection conn)
    {
        this.conn = conn;
    }

    public ObservableList<Receta> getAll() {
        try {
            String query = "select * from receta";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next())
            {
                listReceta.add(new Receta(
                        rs.getInt("noReceta"),
                        rs.getDate("fechaGen").toLocalDate()
                ));
            }
        } catch (SQLException ex)
        {
            ex.printStackTrace();

        }
        return listReceta;
    }

    public int insert(Date fechaReceta){
        int aux = 0;
        try{
            String query = "insert into receta (fechaGen) values (?)";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setDate(1,fechaReceta);
            ps.execute();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("select last_insert_id() as res;");
            while (rs.next()){
                aux = rs.getInt("res");
            }
            return  aux;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return -1;
    }

    public ObservableList<Receta> getListPerUser(int cveUser){
        try {
            String query = "select * from receta where noReceta in (select noReceta from consulta where cveUsuario ="+cveUser+" );";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next())
            {
                listReceta.add(new Receta(
                        rs.getInt("noReceta"),
                        rs.getDate("fechaGen").toLocalDate()
                ));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return listReceta;
    }

    public RecetaReporte getDataForPrint(int noReceta){
        RecetaReporte receta = null;
        try {
            String query = "select * from recetaReporte where noReceta = "+noReceta+";";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next())
            {
                receta = new RecetaReporte(
                        rs.getInt("noReceta"),
                        rs.getString("Medico"),
                        rs.getString("Paciente"),
                        rs.getString("CedProf"),
                        rs.getDate("Fecha").toLocalDate()
                );
            }
            return receta;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;

    }

    public List<MedicamentoReceta> getMedsForPrescription(int noReceta){
        try {
            String query = "select nombre, dosis from medicamentoReceta where noReceta = "+noReceta+";";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next())
            {
                listMeds.add(new MedicamentoReceta(
                        rs.getString("nombre"),
                        rs.getString("dosis")
                ));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return listMeds;
    }

}
