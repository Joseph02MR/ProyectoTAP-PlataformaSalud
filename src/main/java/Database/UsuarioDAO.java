package Database;

import Models.Usuario;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

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
                        rs.getInt("cveUsuario"),
                        rs.getString("password"),
                        rs.getString("email"),
                        rs.getString("nombre"),
                        rs.getString("apellidos"),
                        rs.getString("genero").charAt(0),
                        rs.getDate("fechaNa").toLocalDate(),
                        rs.getInt("id_priv"),
                        rs.getInt("edo_salud"),
                        rs.getInt("id_edoCuenta")
                ));
            }
        } catch (SQLException ex)
        {
            ex.printStackTrace();

        }
        return listUsuarios;
    }

    public Boolean insert(Usuario usuario)
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
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public Boolean update(Usuario usuario){
        try{
            String query ="update usuario set nombre=?,apellidos=?,email=?, password=?, genero=?, fechaNa=?, id_priv=?, edo_salud=?, id_edoCuenta=? where cveUsuario =?;";
            PreparedStatement ps =conn.prepareStatement(query);
            ps.setString(1,usuario.getNombre());
            ps.setString(2,usuario.getApellidos());
            ps.setString(3,usuario.getEmail());
            ps.setString(4,usuario.getPassword());
            ps.setString(5, String.valueOf(usuario.getGenero()));
            ps.setDate(6, Date.valueOf(usuario.getEdad()));
            ps.setInt(7,usuario.getPriv());
            ps.setInt(8,usuario.getEdosalud());
            ps.setInt(9,usuario.getEdocuenta());
            ps.setInt(10,usuario.getCveUsuario());
            ps.execute();
            return true;
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }


    public Boolean delete(int cve_int){
        try{
            String query ="delete from usuario where cveUsuario =?";
            PreparedStatement ps=conn.prepareStatement(query);
            ps.setInt(1,cve_int);
            ps.execute();
            return true;
        }catch (SQLException e)
        {
            e.printStackTrace();
        }
        return false;
    }

    public int[] search(String mail, String pw)  {
        int user=-1;
        int priv=0;
        try {
            String query = "select cveUsuario, id_priv from usuario where email='" + mail + "' and password = '"+pw+"';";
            Statement st = conn.createStatement();
            ResultSet rs= st.executeQuery(query);
            while(rs.next()){
                user = rs.getInt("cveUsuario");
                priv = rs.getInt("id_priv");
            }
            return new int[]{user, priv};
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return new int[]{-1, 0};
    }

    public int method(int cveUsuario){
        int result = -1;

        try {
            String query = "select count(*) as res from medico where cveUsuario ="+cveUsuario+";";
            Statement st = conn.createStatement();
            ResultSet rs= st.executeQuery(query);
            while(rs.next()){
                result = rs.getInt("res");
            } if(result == 1){
                return 2;
            } else {
                byte auxDir = -1;
                query = "select count(*) as res,esDirectivo from personal where cveUsuario ="+cveUsuario+";";
                rs = st.executeQuery(query);
                while(rs.next()){
                    result = rs.getInt("res");
                    auxDir = rs.getByte("esDirectivo");

                } if(result == 1 && auxDir==1){
                    return 1;
                } else return 0;
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }
}
