package Models;

import java.sql.Date;
import java.util.ArrayList;

public class Prescription {
   // private Date date;
    private Medico medico;
    private Estudiante estudiante;
    private ArrayList<Medicamento> medicamentos;


    public Prescription() {
    }

    public Prescription(Medico medico, Estudiante estudiante, ArrayList<Medicamento> medicamentos) {
        this.medico = medico;
        this.estudiante = estudiante;
        this.medicamentos = medicamentos;
    }

    public Medico getMedico() {
        return medico;
    }

    public void setMedico(Medico medico) {
        this.medico = medico;
    }

    public Estudiante getEstudiante() {
        return estudiante;
    }

    public void setEstudiante(Estudiante estudiante) {
        this.estudiante = estudiante;
    }

    public ArrayList<Medicamento> getMedicamentos() {
        return medicamentos;
    }

    public void setMedicamentos(ArrayList<Medicamento> medicamentos) {
        this.medicamentos = medicamentos;
    }


    @Override
    public String toString() {
        return medico.toString();
    }
}
