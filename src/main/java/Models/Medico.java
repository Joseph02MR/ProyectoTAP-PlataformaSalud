package Models;

import java.time.LocalDate;

public class Medico extends Personal {
    private String cedProf;
    private String espec;
    private String horarioAten;

    public Medico(){

    }

    public Medico(String password, String email, String nombre, String apellidos, char genero,
                  LocalDate edad, int priv, int edosalud, int edocuenta, String RFC, String departamento,
                  boolean esDirec, String cedProf, String espec, String horarioAten) {
        super(password, email, nombre, apellidos, genero, edad, priv, edosalud, edocuenta, RFC, departamento, esDirec);
        this.cedProf = cedProf;
        this.espec = espec;
        this.horarioAten = horarioAten;
    }

    public String getCedProf() {
        return cedProf;
    }

    public void setCedProf(String cedProf) {
        this.cedProf = cedProf;
    }

    public String getEspec() {
        return espec;
    }

    public void setEspec(String espec) {
        this.espec = espec;
    }

    public String getHorarioAten() {
        return horarioAten;
    }

    public void setHorarioAten(String horarioAten) {
        this.horarioAten = horarioAten;
    }
}
