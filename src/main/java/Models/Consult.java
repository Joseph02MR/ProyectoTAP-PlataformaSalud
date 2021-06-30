package Models;

public class Consult {
    private String Descripcion;

    public Consult() {
    }

    public Consult(String descripcion) {
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

