package githubmagovia.ockovanie.evidencia.term.models;

import githubmagovia.ockovanie.evidencia.person.models.PersonEntity;
import githubmagovia.ockovanie.evidencia.vaccine.models.VaccineEntity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
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
    @NotBlank(message = "vaccinationCentre is a mandatory field")
    private String vaccinationCentre;
    @NotNull(message = "dateOfVaccination is a mandatory field")
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
