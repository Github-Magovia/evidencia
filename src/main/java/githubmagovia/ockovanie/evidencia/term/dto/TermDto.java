package githubmagovia.ockovanie.evidencia.term.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import githubmagovia.ockovanie.evidencia.person.models.Gender;
import githubmagovia.ockovanie.evidencia.vaccination.models.VaccinationStatus;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class TermDto {
    private long id;
    private long personId;
    private String vaccineName;
    private String personName;
    private String vaccinationCentre;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime dateOfVaccination;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getPersonId() {
        return personId;
    }

    public void setPersonId(long personId) {
        this.personId = personId;
    }

    public String getVaccineName() {
        return vaccineName;
    }

    public void setVaccineName(String vaccineName) {
        this.vaccineName = vaccineName;
    }

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
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
