package Models;

public class Medicamento {
    private String cveMed;
    private String nombre;
    private String unidadMedida;



    public Medicamento() {
    }

    public Medicamento(String cveMed, String nombre, String unidadMedida) {
        this.cveMed = cveMed;
        this.nombre = nombre;
        this.unidadMedida = unidadMedida;
    }

    public String getCveMed() {
        return cveMed;
    }

    public void setCveMed(String cveMed) {
        this.cveMed = cveMed;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUnidadMedida() {
        return unidadMedida;
    }

    public void setUnidadMedida(String unidadMedida) {
        this.unidadMedida = unidadMedida;
    }
}
