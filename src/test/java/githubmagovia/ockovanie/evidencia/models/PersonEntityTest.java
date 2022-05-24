package githubmagovia.ockovanie.evidencia.models;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import githubmagovia.ockovanie.evidencia.person.PersonRepository;
import githubmagovia.ockovanie.evidencia.person.models.Gender;
import githubmagovia.ockovanie.evidencia.person.models.PersonEntity;
import githubmagovia.ockovanie.evidencia.vaccination.models.VaccinationStatus;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

@SpringBootTest
public class PersonEntityTest {

    @Autowired
    private PersonRepository personRepository;

    @Test
    public void shouldSavePersonEntity() {
        PersonEntity person = new PersonEntity();
        person.setFirstName("Mark");
        person.setLastName("Mark");
        person.setSex(Gender.MALE);
        person.setDateOfBirth(LocalDate.now());
        person.setStatus(VaccinationStatus.NONE);
        personRepository.save(person);
        assertThat(person.getId()).isNotNull();
    }

    @Test
    public void savingIncompletePersonEntityShouldThrowException() {
        PersonEntity person = new PersonEntity();
        person.setFirstName("Mark");
        assertThatThrownBy(() -> personRepository.save(person));

    }
}
