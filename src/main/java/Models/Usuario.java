package Models;


import java.time.LocalDate;

public class Usuario {
    private int cveUsuario;
    private String password;
    private String email;
    private String nombre;
    private String apellidos;
    private char genero;
    private LocalDate edad;
    private int priv;
    private  int edosalud;
    private int edocuenta;

    public Usuario() {
    }

    public Usuario(String password, String email, String nombre, String apellidos, char genero, LocalDate edad, int priv, int edosalud, int edocuenta) {
        this.password = password;
        this.email = email;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.genero = genero;
        this.edad = edad;
        this.priv = priv;
        this.edosalud = edosalud;
        this.edocuenta = edocuenta;
    }

    public Usuario(int cveUsuario, String password, String email, String nombre, String apellidos, char genero, LocalDate edad, int priv, int edosalud, int edocuenta) {
        this.cveUsuario = cveUsuario;
        this.password = password;
        this.email = email;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.genero = genero;
        this.edad = edad;
        this.priv = priv;
        this.edosalud = edosalud;
        this.edocuenta = edocuenta;
    }

    @Override
    public String toString() {
        return this.nombre+" "+this.apellidos;
    }

    public Usuario(int cveUsuario){
        this.cveUsuario = cveUsuario;
    }

    public int getCveUsuario() {
        return cveUsuario;
    }

    public void setCveUsuario(int cveUsuario) {
        this.cveUsuario = cveUsuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public char getGenero() {
        return genero;
    }

    public void setGenero(char genero) {
        this.genero = genero;
    }

    public LocalDate getEdad() {
        return edad;
    }

    public void setEdad(LocalDate edad) {
        this.edad = edad;
    }

    public int getPriv() {
        return priv;
    }

    public void setPriv(int priv) {
        this.priv = priv;
    }

    public int getEdosalud() {
        return edosalud;
    }

    public void setEdosalud(int edosalud) {
        this.edosalud = edosalud;
    }

    public int getEdocuenta() {
        return edocuenta;
    }

    public void setEdocuenta(int edocuenta) {
        this.edocuenta = edocuenta;
    }
}
