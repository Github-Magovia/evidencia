package githubmagovia.ockovanie.evidencia.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class VaccineEntity {
    @Id
    @GeneratedValue
    private long id;
    private String name;
    private String type;
    private Integer amountToCompleteVaccination;
    private Integer daysToFullVaccination;
    private Integer amountOfVaccines;


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

    public void decrementAmountOfVaccines() { this.amountOfVaccines--; }
}
