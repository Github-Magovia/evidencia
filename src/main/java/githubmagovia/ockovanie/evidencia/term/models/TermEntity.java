package githubmagovia.ockovanie.evidencia.term.models;

import githubmagovia.ockovanie.evidencia.person.models.PersonEntity;
import githubmagovia.ockovanie.evidencia.vaccine.models.VaccineEntity;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
public class TermEntity {
    @Id
    @GeneratedValue
    private long id;
    @OneToOne
    private PersonEntity person;
    @OneToOne
    private VaccineEntity vaccine;
    private String vaccinationCentre;
    @Column(columnDefinition = "TIMESTAMP")
    private LocalDateTime dateOfVaccination;

    public long getId() {
        return id;
    }

    public void setId(long id) {
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
