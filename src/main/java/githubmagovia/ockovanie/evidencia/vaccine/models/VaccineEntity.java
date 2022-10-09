package githubmagovia.ockovanie.evidencia.vaccine.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VaccineEntity {
    @Id
    @GeneratedValue
    private long id;
    @NotBlank(message = "Name is a mandatory field")
    private String name;
    @NotBlank(message = "Type is a mandatory field")
    private String type;
    @NotNull(message = "amountToCompleteVaccination is a mandatory field")
    @Min(value = 1, message = "amountToCompleteVaccination needs to be atleast 1")
    private Integer amountToCompleteVaccination;
    @NotNull(message = "daysToFullVaccination is a mandatory field")
    @Min(value = 1, message = "daysToFullVaccination needs to be atleast 1")
    private Integer daysToFullVaccination;
    @NotNull(message = "amountOfVaccines is a mandatory field")
    @Min(value = 0, message = "amountOfVaccines needs to be atleast 0")
    private Integer amountOfVaccines;
    @NotNull(message = "durationOfVaccine is a mandatory field")
    @Min(value = 1, message = "durationOfVaccine needs to be atleast 1")
    private Integer durationOfVaccine;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public VaccineEntity setName(String name) {
        this.name = name;
        return null;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getAmountToCompleteVaccination() {
        return amountToCompleteVaccination;
    }

    public void setAmountToCompleteVaccination(Integer amountToCompleteVaccination) {
        this.amountToCompleteVaccination = amountToCompleteVaccination;
    }

    public Integer getDaysToFullVaccination() {
        return daysToFullVaccination;
    }

    public void setDaysToFullVaccination(Integer daysToFullVaccination) {
        this.daysToFullVaccination = daysToFullVaccination;
    }

    public Integer getAmountOfVaccines() {
        return amountOfVaccines;
    }

    public void setAmountOfVaccines(Integer amountOfVaccines) {
        this.amountOfVaccines = amountOfVaccines;
    }

    public Integer getDurationOfVaccine() {
        return durationOfVaccine;
    }

    public void setDurationOfVaccine(Integer durationOfVaccine) {
        this.durationOfVaccine = durationOfVaccine;
    }

    public void decrementAmountOfVaccines() { this.amountOfVaccines--; }
}
