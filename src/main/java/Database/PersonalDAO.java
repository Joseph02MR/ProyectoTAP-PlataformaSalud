package Database;

import Models.Personal;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

public class PersonalDAO {
    Connection conn;
    ObservableList<Personal> listUsuarios = FXCollections.observableArrayList();
    UsuarioDAO usuarioDAO = new UsuarioDAO(MySQLConnection.getConnection());

    public PersonalDAO(Connection conn) {
        this.conn = conn;
    }

    public Boolean insert(Personal usuario)
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
            int depto = 0;
            query = "select cveUsuario from usuario where email = '" + usuario.getEmail() + "';";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                user = rs.getInt("cveUsuario");
            }
            query = "select cveDepartamento from departamento where nombre = '" + usuario.getDepartamento() + "';";
            st = conn.createStatement();
            rs = st.executeQuery(query);
            while (rs.next()) {
                depto = rs.getInt("cveDepartamento");
            }
            query = "insert into personal (cveUsuario, RFC, esDirectivo, cveDepartamento) VALUES (?,?,?,?);";
            ps = conn.prepareStatement(query);
            ps.setInt(1, user);
            ps.setString(2, usuario.getRFC());
            ps.setBoolean(3, false);
            ps.setInt(4, depto);
            ps.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
