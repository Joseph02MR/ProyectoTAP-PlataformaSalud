package Models.Views.Receta;

import java.time.LocalDate;

public class OrdenReporte {
    private int noReporte;
    private String Paciente;
    private String Resultado;
    private LocalDate FechaExp;
    private String tipoPrueba;

    public OrdenReporte() {
    }

    public OrdenReporte(int noReporte, String paciente, String resultado, LocalDate fechaExp, String tipoPrueba) {
        this.noReporte = noReporte;
        Paciente = paciente;
        Resultado = resultado;
        FechaExp = fechaExp;
        this.tipoPrueba = tipoPrueba;
    }

    public int getNoReporte() {
        return noReporte;
    }

    public void setNoReporte(int noReporte) {
        this.noReporte = noReporte;
    }

    public String getPaciente() {
        return Paciente;
    }

    public void setPaciente(String paciente) {
        Paciente = paciente;
    }

    public String getResultado() {
        return Resultado;
    }

    public void setResultado(String resultado) {
        Resultado = resultado;
    }

    public LocalDate getFechaExp() {
        return FechaExp;
    }

    public void setFechaExp(LocalDate fechaExp) {
        FechaExp = fechaExp;
    }

    public String getTipoPrueba() {
        return tipoPrueba;
    }

    public void setTipoPrueba(String tipoPrueba) {
        this.tipoPrueba = tipoPrueba;
    }
}
