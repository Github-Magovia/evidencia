package githubmagovia.ockovanie.evidencia.term.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import githubmagovia.ockovanie.evidencia.person.models.Gender;
import githubmagovia.ockovanie.evidencia.vaccination.models.VaccinationStatus;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class TermDto {
    private long id;
    private String vaccinationCentre;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime dateOfVaccination;

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

    public LocalDateTime getDateOfVaccination() {
        return dateOfVaccination;
    }

    public void setDateOfVaccination(LocalDateTime dateOfVaccination) {
        this.dateOfVaccination = dateOfVaccination;
    }
}
