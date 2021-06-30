package Models;

import java.time.LocalDate;

public class OrdenPrueba {
    private int cveOrden;
    private UsuarioLista usuario;
    private int cveMed;
    private String result;
    private LocalDate Orderdate;
    private String testType;
    private String status;

    public static String[] testTypes = {"PCR (Reaccion en Cadena de la Polimerasa)",
            "Prueba Rapida de Antigeno", "Prueba Rapida de Sangre", "Prueba no valida"};
    public static String[] results = {"POSITIVO", "NEGATIVO","SIN RESULTADO"};

    public OrdenPrueba (){
        this.Orderdate = LocalDate.now();
        this.testType = testTypes[3];
        this.result = results[2];
    }
    //usada para insertar en la base de datos
    public OrdenPrueba(UsuarioLista usuario, int cveMed, String result, LocalDate orderdate, String testType) {
        this.usuario = usuario;
        this.cveMed = cveMed;
        this.result = result;
        Orderdate = orderdate;
        this.testType = testType;
        this.status = "Pendiente";
    }

    public OrdenPrueba(int cveOrden, UsuarioLista usuario, String result, LocalDate orderdate, String testType, String status) {
        this.cveOrden = cveOrden;
        this.usuario = usuario;
        this.result = result;
        Orderdate = orderdate;
        this.testType = testType;
        this.status = status;
    }

    @Override
    public String toString(){
        return "Fecha: " + Orderdate +  " Estado: "+status;
    }

    //usada para recibir info de la base de datos
    public OrdenPrueba(int cveOrden, String result, LocalDate orderdate, String testType, String status) {
        this.cveOrden = cveOrden;
        this.result = result;
        Orderdate = orderdate;
        this.testType = testType;
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public UsuarioLista getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioLista usuario) {
        this.usuario = usuario;
    }

    public int getCveMed() {
        return cveMed;
    }

    public void setCveMed(int cveMed) {
        this.cveMed = cveMed;
    }


    public int getCveOrden() {
        return cveOrden;
    }

    public void setCveOrden(int cveOrden) {
        this.cveOrden = cveOrden;
    }


    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public LocalDate getOrderdate() {
        return Orderdate;
    }

    public void setOrderdate(LocalDate orderdate) {
        Orderdate = orderdate;
    }

    public String getTestType() {
        return testType;
    }

    public void setTestType(String testType) {
        this.testType = testType;
    }
}


