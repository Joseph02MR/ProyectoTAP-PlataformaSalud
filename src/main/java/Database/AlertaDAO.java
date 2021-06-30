package Database;

import Models.Alerta;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

public class AlertaDAO {

    Connection conn;
    ObservableList<Alerta> listAlertas = FXCollections.observableArrayList();

    public AlertaDAO(Connection conn)
    {
        this.conn = conn;
    }


    public Boolean crearAlerta(Alerta alerta)
    {
        try {
            String query = "insert into alerta (cveTipoAlerta, fechaGen, estado , cveUsuario ) values (? , ? , 'pendiente' , ? )";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, alerta.getAlertType());
            ps.setDate(2, Date.valueOf(alerta.getDate()));
            ps.setInt(3,alerta.getCveUsuario());
            ps.execute();
            return true;
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
        return false;
    }

    public Boolean crearEnvia(Alerta alerta)
    {
        int aux= 0;
        try {


            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("select last_insert_id() as res;");

            while (rs.next()){
                aux = rs.getInt("res");
            }

            String query = "insert into envia (cveUsuarioMon,cveAlerta) values (? , ? )";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1,alerta.getCveUsuarioMonitor());
            ps.setInt(2, aux);

            ps.execute();


            return true;
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
        return false;
    }


}