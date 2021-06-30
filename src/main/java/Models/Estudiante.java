package Models;

import java.time.LocalDate;

public class Estudiante extends Usuario{
    private String numCtrl;
    private String carrera;
    private int sem;

    public Estudiante(){
    }

    public Estudiante(String password, String email, String nombre, String apellidos, char genero,
                      LocalDate edad, int priv, int edosalud, int edocuenta, String numCtrl, String carrera, int sem) {
        super(password, email, nombre, apellidos, genero, edad, priv, edosalud, edocuenta);
        this.numCtrl = numCtrl;
        this.carrera = carrera;
        this.sem = sem;
    }

    public Estudiante(int cveUsuario, String numCtrl, String carrera, int sem) {
        super(cveUsuario);
        this.numCtrl = numCtrl;
        this.carrera = carrera;
        this.sem = sem;
    }

    public String getNumCtrl() {
        return numCtrl;
    }

    public void setNumCtrl(String numCtrl) {
        this.numCtrl = numCtrl;
    }

    public String getCarrera() {
        return carrera;
    }

    public void setCarrera(String carrera) {
        this.carrera = carrera;
    }

    public int getSem() {
        return sem;
    }

    public void setSem(int sem) {
        this.sem = sem;
    }
}
