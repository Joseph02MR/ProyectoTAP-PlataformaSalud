package Database;

import Models.Alerta;
import Models.Encuesta;
import Models.OrdenPrueba;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class OrdenPruebaDAO {

    Connection conn;
    ObservableList<OrdenPrueba> listOrdenPrueba = FXCollections.observableArrayList();

    public OrdenPruebaDAO(Connection conn)
    {
        this.conn = conn;
    }

    public ObservableList<OrdenPrueba> getAll()
    {
        try {
            String query = "query";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next())
            {
                listOrdenPrueba.add(new OrdenPrueba(
                       // rs.getString("")
                ));
            }
        } catch (SQLException ex)
        {
            ex.printStackTrace();

        }
        return listOrdenPrueba;
    }

}
