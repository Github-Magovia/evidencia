package githubmagovia.ockovanie.evidencia.controllers.dto;

public class VaccineDto {
    private long id;
    private String name;
    private String type;
    private Integer amountOfVaccines;
    private Integer amountToCompleteVaccination;

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
}
