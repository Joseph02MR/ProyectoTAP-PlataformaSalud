package Models;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public class Consulta {

    private Usuario usuario;
    private LocalDate fecha;
    private Receta receta;
    private int tipocionsulta;
    private Medico medico;
    private String Estado;
    private String Sintomas;
    private int cveConsulta;

    /*
    private String tipoConsulta;
     */


    public Consulta() {
    }

    public Consulta(Usuario usuario, LocalDate fecha, Receta receta, int tipocionsulta, Medico medico, String estado, String Sintomas) {
        this.usuario = usuario;
        this.fecha = fecha;
        this.receta = receta;
        this.tipocionsulta = tipocionsulta;
        this.medico = medico;
        Estado = estado;
        this.Sintomas = Sintomas;
    }

    public Consulta(Usuario usuario, int tipocionsulta, String estado, String Sintomas) {
        this.usuario = usuario;
        this.tipocionsulta = tipocionsulta;
        Estado = estado;
        this.Sintomas = Sintomas;
    }

    public Consulta(Usuario usuario, LocalDate fecha, Receta receta, int tipocionsulta, Medico medico, String estado, String sintomas, int cveConsulta) {
        this.usuario = usuario;
        this.fecha = fecha;
        this.receta = receta;
        this.tipocionsulta = tipocionsulta;
        this.medico = medico;
        Estado = estado;
        Sintomas = sintomas;
        this.cveConsulta = cveConsulta;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public Receta getReceta() {
        return receta;
    }

    public void setReceta(Receta receta) {
        this.receta = receta;
    }

    public int getTipocionsulta() {
        return tipocionsulta;
    }

    public void setTipocionsulta(int tipocionsulta) {
        this.tipocionsulta = tipocionsulta;
    }

    public Medico getMedico() {
        return medico;
    }

    public void setMedico(Medico medico) {
        this.medico = medico;
    }

    public String getEstado() {
        return Estado;
    }

    public void setEstado(String estado) {
        Estado = estado;
    }

    public String getSintomas() {
        return Sintomas;
    }

    public void setSintomas(String sintomas) {
        Sintomas = sintomas;
    }

    public int getCveConsulta() {
        return cveConsulta;
    }

    public void setCveConsulta(int cveConsulta) {
        this.cveConsulta = cveConsulta;
    }

    @Override
    public String toString() {
        return "Paciente: "+usuario.getNombre()+" "+usuario.getApellidos();
    }
}
