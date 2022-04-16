package githubmagovia.ockovanie.evidencia.entity;


import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class VaccinationEntity {
    @Id
    @GeneratedValue
    private long id;

    @OneToOne
    private PersonEntity person;

    @OneToOne
    private VaccineEntity vaccine;
    @Column(columnDefinition = "TIMESTAMP")
    private LocalDateTime dateOfVaccination;
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

    public LocalDateTime getDateOfVaccination() {
        return dateOfVaccination;
    }

    public void setDateOfVaccination(LocalDateTime dateOfVaccination) {
        this.dateOfVaccination = dateOfVaccination;
    }
}
