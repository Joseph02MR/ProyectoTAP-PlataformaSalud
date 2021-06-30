package Database;

import Models.OrdenPrueba;
import Models.UsuarioLista;
import Models.Views.Receta.OrdenReporte;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

public class OrdenPruebaDAO {

    Connection conn;
    ObservableList<OrdenPrueba> listOrdenPrueba = FXCollections.observableArrayList();

    public OrdenPruebaDAO(Connection conn)
    {
        this.conn = conn;
    }

    public ObservableList<OrdenPrueba> getAll()
    {
        try {
            String query = "query";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next())
            {
                listOrdenPrueba.add(new OrdenPrueba(
                       // rs.getString("")
                ));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return listOrdenPrueba;
    }

    public ObservableList<OrdenPrueba> getOrdersPerUser(int cveUser)
    {
        try {
            String query = " select o.cveOrdenPrueba, o.resultado, o.fechaExp, " +
                    "t.descripcion, o.estado from ordenprueba o inner join tipoprueba t on o.cveTipoPrueba = " +
                    "t.cveTipoPrueba where o.cveUsuario ="+cveUser+";";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next())
            {
                listOrdenPrueba.add(new OrdenPrueba(
                        rs.getInt("cveOrdenPrueba"),
                        rs.getString("resultado"),
                        rs.getDate("fechaExp").toLocalDate(),
                        rs.getString("descripcion"),
                        rs.getString("estado")
                ));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return listOrdenPrueba;
    }

    public ObservableList<OrdenPrueba> getOrdersPerMedic(int cveMed) {
        try {
            String query = " select o.cveOrdenPrueba, o.resultado, o.fechaExp, " +
                    "t.descripcion, o.estado, o.cveUsuario from ordenprueba o inner join tipoprueba t on o.cveTipoPrueba = " +
                    "t.cveTipoPrueba where o.cveUsuarioMed ="+cveMed+";";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next())
            {
                listOrdenPrueba.add(new OrdenPrueba(
                        rs.getInt("cveOrdenPrueba"),
                        new UsuarioLista(rs.getInt("cveUsuario")),
                        rs.getString("resultado"),
                        rs.getDate("fechaExp").toLocalDate(),
                        rs.getString("descripcion"),
                        rs.getString("estado")
                ));
            }
        } catch (SQLException ex)
        {
            ex.printStackTrace();

        }
        return listOrdenPrueba;
    }

    public void updateEstadoAsRealizada(int cveOrden){
        try {
            String query = "update ordenprueba set estado = 'Realizada' where cveOrdenPrueba = ?;";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, cveOrden);
            ps.execute();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public boolean updateEstadoAsFinalizada(int cveOrden, String Resultado){
        System.out.println(cveOrden + Resultado);
        try {
            String query = "update ordenprueba set estado = 'Finalizada', resultado = ? where cveOrdenPrueba = ?;";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1,Resultado);
            ps.setInt(2, cveOrden);
            ps.execute();
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    public OrdenReporte getOrderForPrinting (int noOrden){
        OrdenReporte output = null;
        try {
            String query = "select * from ordenReporte where noOrden = "+noOrden+";";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next())
            {

                output = new OrdenReporte(
                        rs.getInt("noOrden"),
                        rs.getString("Paciente"),
                        rs.getString("Resultado"),
                        rs.getDate("FechaExp").toLocalDate(),
                        rs.getString("TipoPrueba")
                );
            }
            return output;
        } catch (SQLException ex)
        {
            ex.printStackTrace();

        }
        return null;
    }

    public Boolean insert (OrdenPrueba orden){
        try{
            int aux = -1;
            String query = "select cveTipoPrueba from tipoprueba where descripcion = '"+orden.getTestType()+"';";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next())
            {
                aux = rs.getInt("cveTipoPrueba");
            }
            query = "insert into ordenprueba (resultado, estado, fechaExp, cveUsuario, cveTipoPrueba, cveUsuarioMed)" +
                    "values (?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, orden.getResult());
            ps.setString(2, orden.getStatus());
            ps.setDate(3, Date.valueOf(orden.getOrderdate()));
            ps.setInt(4,orden.getUsuario().getCveUsuario());
            ps.setInt(5, aux);
            ps.setInt(6,orden.getCveMed());
            ps.execute();
            return  true;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }

}
