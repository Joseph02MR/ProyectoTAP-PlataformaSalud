package Models;

import java.time.LocalDate;

public class OrdenPrueba {
    private int cveOrden;
    private String result;
    private LocalDate Orderdate;
    private String testType;

    public static String[] testTypes = {"PCR (Reaccion en Cadena de la Polimerasa)",
            "Prueba Rapida de Antigeno", "Prueba Rapida de Sangre", "Prueba no valida"};
    public static String[] results = {"POSITIVO", "NEGATIVO","SIN RESULTADO"};

    public OrdenPrueba (){
        this.Orderdate = LocalDate.now();
        this.testType = testTypes[3];
        this.result = results[2];
    }

    public OrdenPrueba(int cveOrden, String result, LocalDate orderdate, String testType) {
        this.cveOrden = cveOrden;
        this.result = result;
        Orderdate = orderdate;
        this.testType = testType;
    }

    public int getCveOrden() {
        return cveOrden;
    }

    public void setCveOrden(int cveOrden) {
        this.cveOrden = cveOrden;
    }

    @Override
    public String toString(){
        if(this.result == results[2]){
            return "Fecha de expedicion: " + Orderdate +  " Estado: pendiente";
        } else{
            return "Fecha de expedicion: " + Orderdate +  " Estado: finalizada";
        }
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


