package Database;

import Models.Reporte;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ReporteDAO {

    Connection conn;
    ObservableList<Reporte> listReporte = FXCollections.observableArrayList();

    public ReporteDAO(Connection conn)
    {
        this.conn = conn;
    }

    public ObservableList<Reporte> getAll()
    {
        try {
            String query = "";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next())
            {
                listReporte.add(new Reporte(
                      //  rs.getString("")
                ));
            }
        } catch (SQLException ex)
        {
            ex.printStackTrace();

        }
        return listReporte;
    }

}
