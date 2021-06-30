package Models;

public class Alerta {
    private String Descripcion;

    public Alerta() {
    }

    public Alerta(String descripcion) {
        this.Descripcion = descripcion;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String descripcion) {
        Descripcion = descripcion;
    }

    @Override
    public String toString() {
        return this.Descripcion;
    }
}

