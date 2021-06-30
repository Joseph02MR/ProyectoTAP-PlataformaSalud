package Models;

public class UsuarioLista {
    private int cveUsuario;
    private String nombre;

    @Override
    public String toString() {
        return this.nombre;
    }

    public UsuarioLista(int cveUsuario, String nombre) {
        this.cveUsuario = cveUsuario;
        this.nombre = nombre;
    }

    public UsuarioLista(int cveUsuario) {
        this.cveUsuario = cveUsuario;
    }

    public int getCveUsuario() {
        return cveUsuario;
    }

    public void setCveUsuario(int cveUsuario) {
        this.cveUsuario = cveUsuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
