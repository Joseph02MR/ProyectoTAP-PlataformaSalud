package Database;

import Models.Receta;
import Models.Views.Receta.MedicamentoReceta;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
            String query = "";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next())
            {
                listReceta.add(new Receta(
    //                    rs.getString("genero")
                ));
            }
        } catch (SQLException ex)
        {
            ex.printStackTrace();

        }
        return listReceta;
    }

    public List<MedicamentoReceta> getMedsForPrescription(int noReceta){
        try {
            String query = "select nombre, dosis from medicamentoReceta where noReceta = "+noReceta+";";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next())
            {
                listMeds.add(new MedicamentoReceta(
                        rs.getString(1),
                        rs.getString(2)
                ));
            }
        } catch (SQLException ex)
        {
            ex.printStackTrace();

        }
        return listMeds;
    }

}
