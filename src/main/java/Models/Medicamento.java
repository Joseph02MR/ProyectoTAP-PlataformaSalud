package Models;

public class Medicamento {
    private int cveMed;
    private String nombre;
    private String dosis;

    public Medicamento() {
    }

    public Medicamento(int cveMed, String nombre) {
        this.cveMed = cveMed;
        this.nombre = nombre;
    }

    public Medicamento(int cveMed, String nombre, String dosis) {
        this.cveMed = cveMed;
        this.nombre = nombre;
        this.dosis = dosis;
    }

    public String getDosis() {
        return dosis;
    }

    public void setDosis(String dosis) {
        this.dosis = dosis;
    }

    @Override
    public String toString() {
        return nombre;
    }

    public int getCveMed() {
        return cveMed;
    }

    public void setCveMed(int cveMed) {
        this.cveMed = cveMed;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

}
