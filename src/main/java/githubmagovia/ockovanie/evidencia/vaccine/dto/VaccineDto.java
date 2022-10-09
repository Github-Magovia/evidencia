package githubmagovia.ockovanie.evidencia.vaccine.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
public class VaccineDto {
    private long id;
    private String name;
    private String type;
    private Integer amountOfVaccines;
    private Integer amountToCompleteVaccination;
    private Integer daysToFullVaccination;
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

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getAmountOfVaccines() {
        return amountOfVaccines;
    }

    public void setAmountOfVaccines(Integer amountOfVaccines) {
        this.amountOfVaccines = amountOfVaccines;
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

    public Integer getdurationOfVaccine() {
        return durationOfVaccine;
    }

    public void setdurationOfVaccine(Integer daysUntilStatusLost) {
        this.durationOfVaccine = daysUntilStatusLost;
    }
}
