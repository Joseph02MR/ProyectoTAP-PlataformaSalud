package Database;

import Models.Alerta;
import Models.Encuesta;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class EncuestaDAO {

    Connection conn;
    ObservableList<Encuesta> listEncuesta = FXCollections.observableArrayList();

    public EncuestaDAO(Connection conn)
    {
        this.conn = conn;
    }

    /*public ObservableList<Alerta> getAll()
    {
        try {
            String query = "query";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next())
            {
                listEncuesta.add(new Encuesta(
                        rs.getString("")
                ));
            }
        } catch (SQLException ex)
        {
            ex.printStackTrace();

        }
        return listEncuesta;
    }*/

}
