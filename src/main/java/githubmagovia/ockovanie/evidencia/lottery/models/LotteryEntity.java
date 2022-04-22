package githubmagovia.ockovanie.evidencia.lottery.models;

import githubmagovia.ockovanie.evidencia.person.models.PersonEntity;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class LotteryEntity {
    @Id
    @GeneratedValue
    private long id;
    @OneToOne
    private PersonEntity person;
    private int amount;
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