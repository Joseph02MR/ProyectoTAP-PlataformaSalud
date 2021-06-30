package Models.Views;

import java.time.LocalDate;

public class UserReports {
    private String nombre;
    private LocalDate DetDate;
    private String Result;
    private String depCarrera;

    public UserReports() {
    }

    public UserReports(String nombre, LocalDate detDate, String result, String depCarrera) {
        this.nombre = nombre;
        DetDate = detDate;
        Result = result;
        this.depCarrera = depCarrera;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public LocalDate getDetDate() {
        return DetDate;
    }

    public void setDetDate(LocalDate detDate) {
        DetDate = detDate;
    }

    public String getResult() {
        return Result;
    }

    public void setResult(String result) {
        Result = result;
    }

    public String getDepCarrera() {
        return depCarrera;
    }

    public void setDepCarrera(String depCarrera) {
        this.depCarrera = depCarrera;
    }
}
