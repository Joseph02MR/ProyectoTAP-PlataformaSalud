package Models;

public class archivo {
    Consulta consulta;
    String nombre,ruta;

    public archivo() {
    }

    public archivo(Consulta consulta, String nombre, String ruta) {
        this.consulta = consulta;
        this.nombre = nombre;
        this.ruta = ruta;
    }

    public Consulta getConsulta() {
        return consulta;
    }

    public void setConsulta(Consulta consulta) {
        this.consulta = consulta;
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

    public void setRuta(String ruta) {
        this.ruta = ruta;
    }


}
