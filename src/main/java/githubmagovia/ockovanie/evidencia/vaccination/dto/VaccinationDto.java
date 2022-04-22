package githubmagovia.ockovanie.evidencia.vaccination.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class VaccinationDto {
    private long id;
    private long idPerson;
    private long idVaccine;
    private String firstName;
    private String lastName;
    private String type;
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate dateOfVaccination;

    public long getIdPerson() {
        return idPerson;
    }

    public void setIdPerson(long idPerson) {
        this.idPerson = idPerson;
    }

    public long getIdVaccine() {
        return idVaccine;
    }

    public void setIdVaccine(long idVaccine) {
        this.idVaccine = idVaccine;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public LocalDate getDateOfVaccination() {
        return dateOfVaccination;
    }

    public void setDateOfVaccination(LocalDate dateOfVaccination) {
        this.dateOfVaccination = dateOfVaccination;
    }
}
