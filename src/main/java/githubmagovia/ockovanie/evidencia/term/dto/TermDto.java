package githubmagovia.ockovanie.evidencia.term.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import githubmagovia.ockovanie.evidencia.person.models.Gender;
import githubmagovia.ockovanie.evidencia.vaccination.models.VaccinationStatus;

import java.time.LocalDate;
import java.time.LocalTime;

public class TermDto {
    private long id;
    private String vaccinationCentre;
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate dateOfVaccination;
    @JsonFormat(pattern="HH:mm")
    private LocalTime timeOfVaccination;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getVaccinationCentre() {
        return vaccinationCentre;
    }

    public void setVaccinationCentre(String vaccinationCentre) {
        this.vaccinationCentre = vaccinationCentre;
    }

    public LocalDate getDateOfVaccination() {
        return dateOfVaccination;
    }

    public void setDateOfVaccination(LocalDate dateOfVaccination) {
        this.dateOfVaccination = dateOfVaccination;
    }

    public LocalTime getTimeOfVaccination() {
        return timeOfVaccination;
    }

    public void setTimeOfVaccination(LocalTime timeOfVaccination) {
        this.timeOfVaccination = timeOfVaccination;
    }
}
