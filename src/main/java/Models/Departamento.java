package Models;

public class Departamento {
    int cveDepartamento;
    String nombre;

    public Departamento() {
    }


    @Override
    public String toString(){
        return this.nombre;
    }

    public Departamento(int cveDepartamento, String nombre) {
        this.cveDepartamento = cveDepartamento;
        this.nombre = nombre;
    }

    public int getCveDepartamento() {
        return cveDepartamento;
    }

    public void setCveDepartamento(int cveDepartamento) {
        this.cveDepartamento = cveDepartamento;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
