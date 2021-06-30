package Models;


import java.time.LocalDate;

public class Receta {
    private int noReceta;
    private LocalDate date;

    public Receta() {
    }

    public Receta(int noReceta, LocalDate date) {
        this.noReceta = noReceta;
        this.date = date;
    }



    public String toString(){
        return "No.Receta: " +noReceta + " - Fecha de Generacion: "+date;
    }

    public int getNoReceta() {
        return noReceta;
    }

    public void setNoReceta(int noReceta) {
        this.noReceta = noReceta;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}

