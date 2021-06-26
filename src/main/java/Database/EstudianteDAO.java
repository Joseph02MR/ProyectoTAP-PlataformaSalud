package Database;

import Models.Estudiante;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class EstudianteDAO {

    Connection conn;
    ObservableList<Estudiante> listEstudiantes = FXCollections.observableArrayList();

    public EstudianteDAO(Connection conn)
    {
        this.conn = conn;
    }

    public ObservableList<Estudiante> getAll()
    {
        try {
            String query = "select * from Estudiante";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next())
            {
                listEstudiantes.add(new Estudiante(
/*
                        rs.getInt("emp_no"),
                        rs.getDate("birth_date"),
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getString("gender").toString().charAt(0),
                        rs.getDate("hire_date")
*/
                ));
            }
        } catch (SQLException ex)
        {
            ex.printStackTrace();

        }
        return listEstudiantes;
    }



}
