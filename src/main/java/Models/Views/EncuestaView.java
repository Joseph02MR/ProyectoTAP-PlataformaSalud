package Models.Views;

import Models.Encuesta;

import java.time.LocalDate;

public class EncuestaView {
    private int cveEncuesta;
    private String nombreUsuario;
    private LocalDate fechaRealizacion;
    private String presentaSintomas;

    public EncuestaView(int cveEncuesta, String nombreUsuario, String presentaSintomas) {
        this.cveEncuesta = cveEncuesta;
        this.nombreUsuario = nombreUsuario;
        this.presentaSintomas = presentaSintomas;
    }

    public EncuestaView(int cveEncuesta, String nombreUsuario, LocalDate fechaRealizacion, String presentaSintomas) {
        this.cveEncuesta = cveEncuesta;
        this.nombreUsuario = nombreUsuario;
        this.fechaRealizacion = fechaRealizacion;
        this.presentaSintomas = presentaSintomas;
    }

    public boolean evalSintomas(Encuesta e){
        if(e.isCb1() || e.isCb2() || e.isCb3() || e.isCb4() || e.isCb5() || e.isCb6() || e.isCb7() || e.isCb8() || e.isCb9() || e.isCb10() || e.isCb11() || e.isCb12() || e.isCb13()){
            return true;
        }
        if(e.getSintomas().length() > 0){
            return true;
        }
        return false;
    }

    public LocalDate getFechaRealizacion() {
        return fechaRealizacion;
    }

    public void setFechaRealizacion(LocalDate fechaRealizacion) {
        this.fechaRealizacion = fechaRealizacion;
    }

    public int getCveEncuesta() {
        return cveEncuesta;
    }

    public void setCveEncuesta(int cveEncuesta) {
        this.cveEncuesta = cveEncuesta;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getPresentaSintomas() {
        return presentaSintomas;
    }

    public void setPresentaSintomas(String presentaSintomas) {
        this.presentaSintomas = presentaSintomas;
    }
}
