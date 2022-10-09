package githubmagovia.ockovanie.evidencia.person.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import githubmagovia.ockovanie.evidencia.person.models.Gender;
import githubmagovia.ockovanie.evidencia.vaccination.models.VaccinationStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PersonDto {
    private long id;
    private String firstName;
    private String lastName;
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate dateOfBirth;
    private Gender sex;
    private VaccinationStatus status;
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate vaccineStart;
    @JsonFormat(pattern="yyyy-MM-dd")
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

    public PersonDto setFirstName(String firstName) {
        this.firstName = firstName;
        return null;
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
