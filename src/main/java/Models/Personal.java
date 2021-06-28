package Models;

import java.time.LocalDate;

public class Personal extends Usuario{
    private String RFC;
    private String Departamento;
    private boolean esDirec;

    public Personal(){

    }

    public Personal(String password, String email, String nombre, String apellidos, char genero,
                    LocalDate edad, int priv, int edosalud, int edocuenta, String RFC, String departamento, boolean esDirec) {
        super(password, email, nombre, apellidos, genero, edad, priv, edosalud, edocuenta);
        this.RFC = RFC;
        Departamento = departamento;
        this.esDirec = esDirec;
    }

    public String getRFC() {
        return RFC;
    }

    public void setRFC(String RFC) {
        this.RFC = RFC;
    }

    public String getDepartamento() {
        return Departamento;
    }

    public void setDepartamento(String departamento) {
        Departamento = departamento;
    }

    public boolean isEsDirec() {
        return esDirec;
    }

    public void setEsDirec(boolean esDirec) {
        this.esDirec = esDirec;
    }
}
