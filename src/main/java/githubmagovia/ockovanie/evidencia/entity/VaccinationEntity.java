package githubmagovia.ockovanie.evidencia.entity;


import javax.persistence.*;
import java.util.Date;

@Entity
public class VaccinationEntity {
    @Id
    @GeneratedValue
    private Long id;

    @JoinColumn
    @ManyToOne
    private PersonEntity person;

    @JoinColumn
    @ManyToOne
    private VaccineEntity vaccine;

    private Date dateOfVaccination;

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

    public Date getDateOfVaccination() {
        return dateOfVaccination;
    }

    public void setDateOfVaccination(Date dateOfVaccination) {
        this.dateOfVaccination = dateOfVaccination;
    }
}
