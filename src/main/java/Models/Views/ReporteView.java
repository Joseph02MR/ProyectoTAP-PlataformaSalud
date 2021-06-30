package Models.Views;

import java.io.File;

public class ReporteView {
    int cveReporte;
    String nombre;
    String ruta;

    public ReporteView( String nombre, String ruta) {
        this.nombre = nombre;
        this.ruta = ruta;
    }

    public int getCveReporte() {
        return cveReporte;
    }

    public void setCveReporte(int cveReporte) {
        this.cveReporte = cveReporte;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getRuta() {
        return ruta;
    }

    public void setArchivo(String ruta) {
        this.ruta = ruta;
    }
}
