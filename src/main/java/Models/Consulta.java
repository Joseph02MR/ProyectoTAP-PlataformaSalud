package Models;

import java.time.LocalDate;
import java.util.List;

public class Consulta {
    String username;
    LocalDate date;
    String sintomas;
    String tipoConsulta;
    List<String> archivos;
    String[] tipos = {"VIRTUAL", "PRESENCIAL"};
}
