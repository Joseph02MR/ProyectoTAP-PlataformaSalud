package Models;

import java.time.LocalDate;

public class Alertamonitor {
    private int alertId;
    private String alertType;
    private LocalDate date;
    private String status;
    private String desc;

    String[] type = {"sospechoso","positivo"};
    //TODO generar descripciones
    String[] descArray = {"", ""};
    String[] statusArray = {"Atendida","Pendiente"};

    public Alertamonitor(){
    }

    public Alertamonitor(int alertId, String alertType, LocalDate date, String status, String desc) {
        this.alertId = alertId;
        this.alertType = alertType;
        this.date = date;
        this.status = status;
        this.desc = desc;
    }
}
