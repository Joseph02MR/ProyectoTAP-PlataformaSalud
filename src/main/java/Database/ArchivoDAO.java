package Database;

import Models.Usuario;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import Models.Consulta;
import Models.archivo;

import java.io.*;
import java.sql.*;

public class ArchivoDAO {
    Connection conn;
    ObservableList<Consulta> listConsulta = FXCollections.observableArrayList();
    ObservableList<archivo> listArchivos = FXCollections.observableArrayList();

    public ArchivoDAO(Connection conn){
        this.conn=conn;
    }

    /*Nuevo*/
    public ObservableList<archivo> getAll(int cveConsulta)
    {
        try {
            String query = "select * from archivo\n" +
                    "where cveConsulta="+cveConsulta;
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next())
            {
                Blob blob = rs.getBlob("file");
                byte [] array = blob.getBytes( 1, ( int ) blob.length() );
                String[] separado=rs.getString("nombre").split("\\.");
                File file = File.createTempFile(separado[0], "."+separado[1], new File("."));
                FileOutputStream out = new FileOutputStream( file );
                out.write( array );
                out.close();
                listArchivos.add(new archivo(
                        searchOne(rs.getInt("cveConsulta")),
                        separado[0],
                        file));
            }
        } catch (SQLException | FileNotFoundException ex)
        {
            ex.printStackTrace();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return listArchivos;
    }

    public Consulta searchOne(int cveConsulta)
    {
        Consulta consulta=new Consulta();
        try {
            String query = "select * \n" +
                    "from consulta\n" +
                    "where  cveConsulta="+cveConsulta;
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next())
            {
                consulta.setCveConsulta(cveConsulta);
                consulta.setUsuario(searchOneUsuario(rs.getInt("cveUsuario")));
            }
        } catch (SQLException ex)
        {
            ex.printStackTrace();

        }
        return consulta;
    }

    public Usuario searchOneUsuario(int cveUsuario)
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

    /*cambio*/
    public Boolean insert(archivo archivo) throws FileNotFoundException {
        FileInputStream fis = new FileInputStream(archivo.getArchivo());
        try{
            String query = "Insert into archivo (nombre,file,cveConsulta) values (?,?,?)";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1,archivo.getNombre());
            ps.setBlob(2,fis,archivo.getArchivo().length());
            ps.setInt(3,archivo.getConsulta().getCveConsulta());
            ps.execute();
            return true;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }
}
