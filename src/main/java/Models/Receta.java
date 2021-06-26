package Models;

import Models.Views.Receta.MedicamentoReceta;

import java.time.LocalDate;
import java.util.ArrayList;

public class Receta {
    private int noReceta;
    private LocalDate date;
    private Medico medico;
    private Estudiante estudiante; //String
    private ArrayList<MedicamentoReceta> medicamentos;


    public Receta() {
    }

}

