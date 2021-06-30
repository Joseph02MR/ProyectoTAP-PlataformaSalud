package Database.Views;

import Models.Views.ReporteView;
import Models.Views.UserReports;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.*;
import java.sql.*;
import java.util.List;

public class UserReportsDAO {
    Connection conn;
    ObservableList<UserReports> listUsers = FXCollections.observableArrayList();

    public UserReportsDAO(Connection conn)
    {
        this.conn = conn;
    }

    public List<UserReports> getEstudiantes()
    {
        try {
            //TODO query for students
            String query = "select nombre, fechaNac, genero, cveCarrera from alumno;";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next())
            {
                listUsers.add(new UserReports(
                        rs.getString(1),
                        rs.getDate(2).toLocalDate(),
                        rs.getString(3),
                        rs.getString(4))
                );
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return listUsers;
    }

    public List<UserReports> getPersonal()
    {
        try {
            //TODO query for staff
            String query = "select nombre, fechaNac, genero, cveCarrera from alumno;";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next())
            {
                listUsers.add(new UserReports(
/*
                        rs.getString("name"),
                        rs.getDate("date").toLocalDate(),
                        rs.getString("result"),
                        rs.getString("depto"))
*/
                                rs.getString(1),
                                rs.getDate(2).toLocalDate(),
                                rs.getString(3),
                                rs.getString(4))
                );
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return listUsers;
    }

    public boolean insertFile(ReporteView rep){
        try {
            String query = "insert into reporte(nombre, archivo) values (?,?)";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, rep.getNombre());
            ps.setString(2, rep.getRuta());
            ps.execute();
            return true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    public String getFile(int cve){
        String output = "";
        try{
            String query = "select ruta from reporte where cveReporte = "+cve+";";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()){
                output = rs.getString(1);
            }
            return output;
        } catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    private byte[] convertFileToByte(File f) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream objOstream = new ObjectOutputStream(baos);
        objOstream.writeObject(f);
        objOstream.flush();
        objOstream.close();
        byte[] bArray = baos.toByteArray();
        return bArray;
    }

    public File readObjectFromBlob(int id){
        try{
            Blob result = null;
            InputStream in = null;
            File output = null;
            String query = "select archivo from reporte where cveReporte = "+id+";";
            PreparedStatement ps = conn.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                result = (Blob) rs.getBlob(1);
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                byte[] buffer = new byte[1024];
                in = result.getBinaryStream();

                int n = 0;
                while ((n = in.read(buffer)) >= 0){
                    baos.write(buffer,0,n);
                }
                byte[] bytes = baos.toByteArray();
                ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
                ObjectInput in1 = new ObjectInputStream(bis);
                Object o = in1.readObject();
                if(o!= null){
                    output = (File) o;
                }
                return output;
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
