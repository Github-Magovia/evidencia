package githubmagovia.ockovanie.evidencia.person.models;

import githubmagovia.ockovanie.evidencia.vaccination.models.VaccinationStatus;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity
public class PersonEntity {
    @Id
    @GeneratedValue
    private long id;
    private String firstName;
    private String lastName;
    @Column(columnDefinition = "DATE")
    private LocalDate dateOfBirth;
    private Gender sex;
    private VaccinationStatus status;
    @Column(columnDefinition = "DATE")
    private LocalDate vaccineStart;
    @Column(columnDefinition = "DATE")
    private LocalDate vaccineEnd;


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

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Gender getSex() {
        return sex;
    }

    public void setSex(Gender sex) {
        this.sex = sex;
    }

    public VaccinationStatus getStatus() {
        return status;
    }

    public void setStatus(VaccinationStatus status) {
        this.status = status;
    }

    public LocalDate getVaccineStart() {
        return vaccineStart;
    }

    public void setVaccineStart(LocalDate vaccineStart) {
        this.vaccineStart = vaccineStart;
    }

    public LocalDate getVaccineEnd() {
        return vaccineEnd;
    }

    public void setVaccineEnd(LocalDate vaccineEnd) {
        this.vaccineEnd = vaccineEnd;
    }
}
