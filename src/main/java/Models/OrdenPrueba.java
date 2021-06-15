package Models;

import java.time.LocalDate;

public class OrdenPrueba {
    private String result;
    private LocalDate Orderdate;
    private String testType;

    public static String[] testTypes = {"PCR (Reaccion en Cadena de la Polimerasa",
            "Prueba Rapida de Antígeno", "Prueba Rapida de Sangre", "Prueba no valida"};
    public static String[] results = {"POSITIVO", "NEGATIVO","SIN RESULTADO"};

    public OrdenPrueba (){
        this.Orderdate = LocalDate.now();
        this.testType = testTypes[3];
        this.result = results[2];
    }

    public OrdenPrueba(String result, LocalDate orderdate, String testType) {
        this.result = result;
        Orderdate = orderdate;
        this.testType = testType;
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

