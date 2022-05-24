package githubmagovia.ockovanie.evidencia.lottery.models;

import githubmagovia.ockovanie.evidencia.person.models.PersonEntity;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
public class LotteryEntity {
    @Id
    @GeneratedValue
    private long id;
    @OneToOne
    private PersonEntity person;
    @Min(value = 1, message = "amount must be atleast 1")
    private int amount;
    @NotNull(message = "date is a mandatory field")
    @Column(columnDefinition = "DATE")
    private LocalDate date;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public PersonEntity getPerson() {
        return person;
    }

    public void setPerson(PersonEntity personId) {
        this.person = personId;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}