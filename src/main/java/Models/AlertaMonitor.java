package Models;

import java.time.LocalDate;

public class AlertaMonitor {
    private int alertId;
    private String alertType;
    private LocalDate date;
    private String status;
    private String desc;

    String[] type = {"sospechoso","positivo"};
    //TODO generar descripciones
    String[] descArray = {"", ""};
    String[] statusArray = {"Atendida","Pendiente"};

    public AlertaMonitor(){
    }

    public AlertaMonitor(int alertId, String alertType, LocalDate date, String status, String desc) {
        this.alertId = alertId;
        this.alertType = alertType;
        this.date = date;
        this.status = status;
        this.desc = desc;
    }
}
