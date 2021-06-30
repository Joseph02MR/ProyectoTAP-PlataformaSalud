package Models;

import java.io.File;

public class archivo {
    Consulta consulta;
    String nombre;
    File archivo;

    public archivo() {
    }

    public archivo(Consulta consulta, String nombre, File file) {
        this.consulta = consulta;
        this.nombre = nombre;
        this.archivo = file;
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

    public File getArchivo() {
        return archivo;
    }

    public void setArchivo(File archivo) {
        this.archivo = archivo;
    }

    @Override
    public String toString() {
        return this.nombre;
    }
}