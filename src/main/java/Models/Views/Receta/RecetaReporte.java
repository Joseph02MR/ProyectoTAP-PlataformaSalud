package Models.Views.Receta;

import java.time.LocalDate;

public class RecetaReporte {
    private int noReceta;
    private String NombreMed;
    private String NombreUser;
    private String cedProf;
    private LocalDate fecha;

    public RecetaReporte() {
    }

    public RecetaReporte(int noReceta, String nombreMed, String nombreUser, String cedProf, LocalDate fecha) {
        this.noReceta = noReceta;
        NombreMed = nombreMed;
        NombreUser = nombreUser;
        this.cedProf = cedProf;
        this.fecha = fecha;
    }

    public int getNoReceta() {
        return noReceta;
    }

    public void setNoReceta(int noReceta) {
        this.noReceta = noReceta;
    }

    public String getNombreMed() {
        return NombreMed;
    }

    public void setNombreMed(String nombreMed) {
        NombreMed = nombreMed;
    }

    public String getNombreUser() {
        return NombreUser;
    }

    public void setNombreUser(String nombreUser) {
        NombreUser = nombreUser;
    }

    public String getCedProf() {
        return cedProf;
    }

    public void setCedProf(String cedProf) {
        this.cedProf = cedProf;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }
}
