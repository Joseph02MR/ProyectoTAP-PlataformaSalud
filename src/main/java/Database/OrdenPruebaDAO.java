package Database;

import Models.OrdenPrueba;
import Models.Views.Receta.OrdenReporte;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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
        } catch (SQLException ex)
        {
            ex.printStackTrace();

        }
        return listOrdenPrueba;
    }

    public ObservableList<OrdenPrueba> getOrdersPerUser(int cveUser)
    {
        try {
            String query = " select o.cveOrdenPrueba, o.resultado, o.fechaExp, " +
                    "t.descripcion from ordenprueba o inner join tipoprueba t on o.cveTipoPrueba = " +
                    "t.cveTipoPrueba where o.cveUsuario ="+cveUser+";";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next())
            {
                listOrdenPrueba.add(new OrdenPrueba(
                        rs.getInt("cveOrdenPrueba"),
                        rs.getString("resultado"),
                        rs.getDate("fechaExp").toLocalDate(),
                        rs.getString("descripcion")
                ));
            }
        } catch (SQLException ex)
        {
            ex.printStackTrace();

        }
        return listOrdenPrueba;
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

}
