package githubmagovia.ockovanie.evidencia.term.models;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
public class TermEntity {
    @Id
    @GeneratedValue
    private long id;
    private String vaccinationCentre;
    @Column(columnDefinition = "DATE")
    private LocalDate dateOfVaccination;
    @Column(columnDefinition = "TIME")
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

    public void setDateOfVaccination(LocalDate date) {
        this.dateOfVaccination = date;
    }

    public LocalTime getTimeOfVaccination() {
        return timeOfVaccination;
    }

    public void setTimeOfVaccination(LocalTime timeOfVaccination) {
        this.timeOfVaccination = timeOfVaccination;
    }
}
