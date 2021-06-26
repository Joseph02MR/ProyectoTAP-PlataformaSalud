package Database;

import Models.Alerta;
import Models.Consulta;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ConsultaDAO {

    Connection conn;
    ObservableList<Consulta> listConsulta = FXCollections.observableArrayList();

    public ConsultaDAO(Connection conn)
    {
        this.conn = conn;
    }

    public ObservableList<Consulta> getAll()
    {
        try {
            String query = "query";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next())
            {
                listConsulta.add(new Consulta(
                      //  rs.getString("")
                ));
            }
        } catch (SQLException ex)
        {
            ex.printStackTrace();

        }
        return listConsulta;
    }

}
