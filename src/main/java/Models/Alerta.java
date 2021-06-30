package Models;

import java.time.LocalDate;

public class Alerta {
    private int alertId;
    private int cveUsuario;
    private int cveUsuarioMonitor;
    private int alertType;
    private LocalDate date;
    private String status;
    private String desc;

    String[] type = {"sospechoso","positivo"};
    //TODO generar descripciones
    String[] descArray = {"", ""};
    String[] statusArray = {"Atendida","Pendiente"};

    public Alerta(){
    }

    public Alerta(int alertId, int alertType, int cveUsuario, int cveUsuarioMonitor ,LocalDate date, String status, String desc) {
        this.alertId = alertId;
        this.alertType = alertType;
        this.cveUsuarioMonitor = cveUsuarioMonitor;
        this.cveUsuario = cveUsuario;
        this.date = date;
        this.status = status;
        this.desc = desc;
    }

    public int getCveUsuarioMonitor() {
        return cveUsuarioMonitor;
    }

    public void setCveUsuarioMonitor(int cveUsuarioMonitor) {
        this.cveUsuarioMonitor = cveUsuarioMonitor;
    }

    public int getCveUsuario() {
        return cveUsuario;
    }

    public void setCveUsuario(int cveUsuario) {
        this.cveUsuario = cveUsuario;
    }

    public int getAlertId() {
        return alertId;
    }

    public void setAlertId(int alertId) {
        this.alertId = alertId;
    }

    public int getAlertType() {
        return alertType;
    }

    public void setAlertType(int alertType) {
        this.alertType = alertType;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
