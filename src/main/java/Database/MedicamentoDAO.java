package Database;

import Models.Medicamento;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

public class MedicamentoDAO {

    Connection conn;
    ObservableList<Medicamento> listMeds = FXCollections.observableArrayList();

    public MedicamentoDAO(Connection conn) {
        this.conn = conn;
    }

    public ObservableList<Medicamento> getAll() {
        try {
            String query = "select * from medicamento";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                listMeds.add(new Medicamento(
                        rs.getInt("cveMedicamento"),
                        rs.getString("nombre")
                ));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return listMeds;
    }

    public boolean insertMedsFromReceipt (int noReceta, ObservableList<Medicamento> lista){
        try{
            String query = "insert into compone (noReceta, cveMedicamento, dosis) values (?,?,?)";
            PreparedStatement ps = conn.prepareStatement(query);
            for(var x : lista){
                ps.setInt(1, noReceta);
                ps.setInt(2,x.getCveMed());
                ps.setString(3,x.getDosis());
                ps.execute();
            }
            return true;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }

}
