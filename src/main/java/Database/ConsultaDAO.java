package Database;

import Models.Alerta;
import Models.Consulta;
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

    /*public ObservableList<Consulta> getAll()
    {
        try {
            String query = "query";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next())
            {
                listConsulta.add(new Consulta(
                        rs.getString()
                ));
            }
        } catch (SQLException ex)
        {
            ex.printStackTrace();

        }
        return listConsulta;
    }*/

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
            String query = "select max(cveConsulta) as clave from consulta where cveUsuario = 1 group by cveConsulta" ;
            Statement ps = conn.createStatement();
            ResultSet rs = ps.executeQuery(query);
            while(rs.next()) {
                consulta.setCveConsulta(rs.getInt("clave"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void setReceta(int cveReceta, int noReceta){
        try{
            String query = "update consulta set noReceta = ? where cveConsulta = ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1,noReceta);
            ps.setInt(2,cveReceta);
            ps.execute();

        }catch (SQLException e){
            e.printStackTrace();
        }
    }

}
