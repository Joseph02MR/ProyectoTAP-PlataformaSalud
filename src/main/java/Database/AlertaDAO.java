package Database;

import Models.Alerta;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class AlertaDAO {

    Connection conn;
    ObservableList<Alerta> listAlertas = FXCollections.observableArrayList();

    public AlertaDAO(Connection conn)
    {
        this.conn = conn;
    }

    public ObservableList<Alerta> getAll()
    {
        try {
            String query = "query";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next())
            {
                listAlertas.add(new Alerta(
                      //  rs.getString("cveUsuario")
                ));
            }
        } catch (SQLException ex)
        {
            ex.printStackTrace();

        }
        return listAlertas;
    }

}
