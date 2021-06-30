package Database;

import Models.Encuesta;
import Models.Views.EncuestaView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

public class EncuestaDAO {

    Connection conn;
    ObservableList<Encuesta> listEncuesta = FXCollections.observableArrayList();
    ObservableList<EncuestaView> listEncuestaVista = FXCollections.observableArrayList();


    public EncuestaDAO(Connection conn)
    {
        this.conn = conn;
    }

    public ObservableList<Encuesta> getAll() {
        try {
            String query = "select * from encuesta";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next())
            {
                listEncuesta.add(new Encuesta(
                        rs.getInt("cveUsuario"),
                        getboolean(rs.getInt("pregunta1")),
                        getboolean(rs.getInt("pregunta2")),
                        getboolean(rs.getInt("pregunta3")),
                        getboolean(rs.getInt("pregunta4")),
                        getboolean(rs.getInt("pregunta5")),
                        getboolean(rs.getInt("pregunta6")),
                        getboolean(rs.getInt("pregunta7")),
                        getboolean(rs.getInt("pregunta8")),
                        getboolean(rs.getInt("pregunta9")),
                        getboolean(rs.getInt("pregunta10")),
                        getboolean(rs.getInt("pregunta11")),
                        getboolean(rs.getInt("pregunta12")),
                        getboolean(rs.getInt("pregunta13")),
                        rs.getString("sintomas"),
                        rs.getDate("fechaRespuesta").toLocalDate()
                ));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return listEncuesta;
    }

    public ObservableList<EncuestaView> getAllForVista()
    {
        try {
            String query = "select * from vistaEncuestaActual";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next())
            {
                listEncuestaVista.add(new EncuestaView(
                        rs.getInt("cveEncuesta"),
                        rs.getString("nombreUsuario"),
                        rs.getDate("fechaRealizacion").toLocalDate(),
                        rs.getString("presentaSintomas")
                ));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return listEncuestaVista;
    }

    public boolean getboolean(int i){
        if (i == 0) return false;
        else return true;
    }

    public Encuesta getEncuestaFromVista(int cveEncuesta){
        Encuesta encuesta = null;
        try {
            String query = "select * from encuesta where cveEncuesta = "+cveEncuesta+";";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next())
            {
                encuesta = new Encuesta(
                        rs.getInt("cveUsuario"),
                        getboolean(rs.getInt("pregunta1")),
                        getboolean(rs.getInt("pregunta2")),
                        getboolean(rs.getInt("pregunta3")),
                        getboolean(rs.getInt("pregunta4")),
                        getboolean(rs.getInt("pregunta5")),
                        getboolean(rs.getInt("pregunta6")),
                        getboolean(rs.getInt("pregunta7")),
                        getboolean(rs.getInt("pregunta8")),
                        getboolean(rs.getInt("pregunta9")),
                        getboolean(rs.getInt("pregunta10")),
                        getboolean(rs.getInt("pregunta11")),
                        getboolean(rs.getInt("pregunta12")),
                        getboolean(rs.getInt("pregunta13")),
                        rs.getString("sintomas"),
                        rs.getDate("fechaRespuesta").toLocalDate()
                );
            }
            return encuesta;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public boolean insert (Encuesta encuesta){
        try{
            String query = "Insert into encuesta (pregunta1, pregunta2, pregunta3, pregunta4, pregunta5, pregunta6, " +
                    "pregunta7, pregunta8, pregunta9, pregunta10, pregunta11, pregunta12, pregunta13,cveUsuario, sintomas, fechaRespuesta) " +
                    "values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setBoolean(1,encuesta.isCb1());
            ps.setBoolean(2,encuesta.isCb2());
            ps.setBoolean(3,encuesta.isCb3());
            ps.setBoolean(4,encuesta.isCb4());
            ps.setBoolean(5,encuesta.isCb5());
            ps.setBoolean(6,encuesta.isCb6());
            ps.setBoolean(7,encuesta.isCb7());
            ps.setBoolean(8,encuesta.isCb8());
            ps.setBoolean(9,encuesta.isCb9());
            ps.setBoolean(10,encuesta.isCb10());
            ps.setBoolean(11,encuesta.isCb11());
            ps.setBoolean(12,encuesta.isCb12());
            ps.setBoolean(13,encuesta.isCb13());
            ps.setInt(14,encuesta.getCveUsuario());
            ps.setString(15, encuesta.getSintomas());
            ps.setDate(16,Date.valueOf(encuesta.getDate()));
            ps.execute();

            return  true;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }

}
