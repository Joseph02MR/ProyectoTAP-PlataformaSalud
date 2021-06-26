package Database;

import Models.Estudiante;
import Models.Usuario;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UsuarioDAO {

    Connection conn;
    ObservableList<Usuario> listUsuarios = FXCollections.observableArrayList();

    public UsuarioDAO(Connection conn)
    {
        this.conn = conn;
    }

    public ObservableList<Usuario> getAll()
    {
        try {
            String query = "select * from Usuario";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next())
            {
                listUsuarios.add(new Usuario(
/*
                        rs.getString("cveUsuario"),
                        rs.getString("password"),
                        rs.getString("nombres"),
                        rs.getString("apellidos"),
                        rs.getDate("fechaNa"),
                        rs.getString("genero")
*/
                        ));
            }
        } catch (SQLException ex)
        {
            ex.printStackTrace();

        }
        return listUsuarios;
    }


}
