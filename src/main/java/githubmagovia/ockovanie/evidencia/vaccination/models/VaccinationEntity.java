package githubmagovia.ockovanie.evidencia.vaccination.models;


import githubmagovia.ockovanie.evidencia.vaccine.models.VaccineEntity;
import githubmagovia.ockovanie.evidencia.person.models.PersonEntity;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
public class VaccinationEntity {
    @Id
    @GeneratedValue
    private long id;

    @OneToOne
    private PersonEntity person;

    @OneToOne
    private VaccineEntity vaccine;

    @NotNull(message = "dateOfVaccination is a mandatory field")
    @Column(columnDefinition = "DATE")
    private LocalDate dateOfVaccination;

    @NotNull(message = "amountOfVaccines is a mandatory field")
    @Min(value = 0, message = "amountOfVaccines needs to be atleast 0")
    private int shotNumber;

    public int getShotNumber() {
        return shotNumber;
    }

    public void setShotNumber(int shotNumber) {
        this.shotNumber = shotNumber;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PersonEntity getPerson() {
        return person;
    }

    public void setPerson(PersonEntity person) {
        this.person = person;
    }

    public VaccineEntity getVaccine() {
        return vaccine;
    }

    public void setVaccine(VaccineEntity vaccine) {
        this.vaccine = vaccine;
    }

    public LocalDate getDateOfVaccination() {
        return dateOfVaccination;
    }

    public void setDateOfVaccination(LocalDate dateOfVaccination) {
        this.dateOfVaccination = dateOfVaccination;
    }
}
