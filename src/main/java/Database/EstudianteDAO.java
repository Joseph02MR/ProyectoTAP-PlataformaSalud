package Database;

import Models.Estudiante;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

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
            String query = "select * from estudiante";
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


    public Boolean insert(Estudiante usuario)
    {
        try {
            String query = "insert into usuario ( nombre, apellidos, email, password, genero, fechaNa, id_priv, edo_salud, id_edoCuenta ) values ( ?, ?, ?, ?, ?, ?, ?, ?,?)";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1,usuario.getNombre());
            ps.setString(2,usuario.getApellidos());
            ps.setString(3,usuario.getEmail());
            ps.setString(4,usuario.getPassword());
            ps.setString(5, String.valueOf(usuario.getGenero()));
            ps.setDate(6, Date.valueOf(usuario.getEdad()));
            ps.setInt(7,usuario.getPriv());
            ps.setInt(8,usuario.getEdosalud());
            ps.setInt(9,usuario.getEdocuenta());
            ps.execute();

            int user = 0;
            int carrera=0;
            query = "select cveUsuario from usuario where email = '"+usuario.getEmail()+"';";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                user = rs.getInt("cveUsuario");
            }
            query = "select cveCarrera from carrera where nombre = '"+usuario.getCarrera()+"';";
            st = conn.createStatement();
            rs = st.executeQuery(query);
            while (rs.next()) {
                carrera = rs.getInt("cveCarrera");
            }
            query = "insert into estudiante (cveUsuario, semestre, noControl, cveCarrera) VALUES (?,?,?,?);";
            ps = conn.prepareStatement(query);
            ps.setInt(1,user);
            ps.setInt(2,usuario.getSem());
            ps.setString(3,usuario.getNumCtrl());
            ps.setInt(4,carrera);
            ps.execute();
            return true;
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
        return false;
    }


}
