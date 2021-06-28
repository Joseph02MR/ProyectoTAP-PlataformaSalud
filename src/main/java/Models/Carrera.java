package Models;

public class Carrera {
    int cveCarrera;
    String nombre;

    @Override
    public String toString(){
        return this.nombre;
    }

    public Carrera(int cveCarrera, String nombre) {
        this.cveCarrera = cveCarrera;
        this.nombre = nombre;
    }

    public int getCveCarrera() {
        return cveCarrera;
    }

    public void setCveCarrera(int cveCarrera) {
        this.cveCarrera = cveCarrera;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
