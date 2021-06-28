package Database;

import Models.Carrera;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CarreraDAO {

    Connection conn;
    ObservableList<Carrera> listCarrera = FXCollections.observableArrayList();

    public CarreraDAO(Connection conn)
    {
        this.conn = conn;
    }

    public ObservableList<Carrera> getAll()
    {
        try {
            String query = "select * from carrera";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                listCarrera.add(new Carrera(
                        rs.getInt("cveCarrera"),
                        rs.getString("nombre")
                ));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return listCarrera;
    }

}
