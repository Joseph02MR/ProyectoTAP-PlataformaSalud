package Database;

import Models.Alerta;
import Models.Consulta;
import Models.Usuario;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

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
            String query = "select *\n" +
                    "from consulta";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next())
            {
                listConsulta.add(new Consulta(
                        searchOne(rs.getInt("cveUsuario")),
                        null,
                        null,
                        rs.getInt("cveTipoCon"),
                        null,
                        rs.getString("estado"),
                        rs.getString("sintomas"),
                        rs.getInt("cveConsulta")
                ));
            }
        } catch (SQLException ex)
        {
            ex.printStackTrace();

        }
        return listConsulta;
    }

    public Usuario searchOne(int cveUsuario)
    {
        Usuario usuario=new Usuario();
        try {
            String query = "select * \n" +
                    "from usuario\n" +
                    "where  cveUsuario="+cveUsuario;
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next())
            {
                usuario.setCveUsuario(rs.getInt("cveUsuario"));
                usuario.setNombre(rs.getString("nombre"));
                usuario.setApellidos(rs.getString("apellidos"));
                usuario.setEmail(rs.getString("email"));
                usuario.setPassword(rs.getString("password"));
                usuario.setGenero(rs.getString("genero").charAt(0));
                usuario.setEdad(rs.getDate("fechaNa").toLocalDate());
                usuario.setPriv(rs.getInt("id_priv"));
                usuario.setEdosalud(rs.getInt("edo_salud"));
                usuario.setEdocuenta(rs.getInt("id_edoCuenta"));
            }
        } catch (SQLException ex)
        {
            ex.printStackTrace();

        }
        return usuario;

    }

    public Boolean update(String fecha,int cveConsulta){
        try{
            String query ="update consulta set fechaRe='"+fecha+"'" +
                    "where cveConsulta="+cveConsulta+";";
            PreparedStatement ps =conn.prepareStatement(query);
            ps.execute();
            return true;
        }catch (SQLException e)
        {
            e.printStackTrace();
        }
        return false;
    }
    public Boolean insert (Consulta consulta){
        try{
            String query = "Insert into consulta (estado, fechaRe, sintomas, cveUsuario, noReceta, cveTipoCon, cveUsuarioMed) values (?, null,?, ?, null, ?, null)";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1,"pendiente");
            ps.setString(2,consulta.getSintomas());
            ps.setInt(3,consulta.getUsuario().getCveUsuario());
            ps.setInt(4,consulta.getTipocionsulta());
            ps.execute();

            return  true;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    public void CVECONS ( Consulta consulta){
        try
        {
            String query = "select max(cveConsulta) as clave from consulta where cveUsuario = "+ consulta.getUsuario().getCveUsuario() +" group by cveConsulta" ;
            Statement ps = conn.createStatement();
            ResultSet rs = ps.executeQuery(query);
            while(rs.next()) {
                consulta.setCveConsulta(rs.getInt("clave"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



}
