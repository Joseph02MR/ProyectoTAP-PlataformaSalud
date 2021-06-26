package Database;

import Models.Departamento;
import Models.Receta;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DepartamentoDAO {
    Connection conn;
    ObservableList<Departamento> listDepartamento = FXCollections.observableArrayList();

    public DepartamentoDAO(Connection conn)
    {
        this.conn = conn;
    }

    public ObservableList<Departamento> getAll()
    {
        try {
            String query = "";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next())
            {
                listDepartamento.add(new Departamento(
                        //rs.getString("genero")
                ));
            }
        } catch (SQLException ex)
        {
            ex.printStackTrace();

        }
        return listDepartamento;
    }
}
