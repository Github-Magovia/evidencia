package githubmagovia.ockovanie.evidencia.person;

import githubmagovia.ockovanie.evidencia.person.dto.PersonDto;
import githubmagovia.ockovanie.evidencia.person.models.Gender;
import githubmagovia.ockovanie.evidencia.vaccination.models.VaccinationStatus;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
public class PersonServiceTest {

    @Autowired
    private PersonService personService;

    @Test
    public void correctAttributesAreSetForNewPersonEntity() {
        PersonDto person = generateValidPersonDTO("Mark");
        person = personService.createPerson(person);
        assertThat(person.getStatus()).isEqualTo(VaccinationStatus.NONE);
        assertThat(person.getVaccineStart()).isNull();
        assertThat(person.getVaccineEnd()).isNull();
        personService.deletePerson(person.getId());
    }

    @Test
    public void getCorrectPersonById() {
        PersonDto person = generateValidPersonDTO("Thomas");
        person = personService.createPerson(person);
        assertThat(personService.getPersonById(person.getId()).getId()).isEqualTo(person.getId());
        personService.deletePerson(person.getId());
    }

    @Test
    public void personWithNonexistentIdShouldReturnNull() {
        assertThat(personService.getPersonById(-1L)).isNull();
    }

    @Test
    public void updateExistingPersonEntity() {
        PersonDto person = generateValidPersonDTO("Json");
        person = personService.createPerson(person);
        person.setLastName("Jason");
        assertThat(personService.updatePerson(person.getId(), person).getLastName()).isEqualTo(person.getLastName());
        personService.deletePerson(person.getId());
    }

    @Test
    public void updatingNonexistentPersonEntityThrowsException() {
        PersonDto person = generateValidPersonDTO("Peter");
        assertThatThrownBy(() -> personService.updatePerson(-1L, person));
    }

    @Test
    public void deleteExistingPersonEntity() {
        PersonDto person = generateValidPersonDTO("Richard");
        person = personService.createPerson(person);
        personService.deletePerson(person.getId());
        assertThat(personService.getPersonById(person.getId())).isNull();
    }

    private PersonDto generateValidPersonDTO(String lName) {
        PersonDto person = new PersonDto();
        person.setFirstName("Tester");
        person.setLastName(lName);
        person.setDateOfBirth(LocalDate.now());
        person.setSex(Gender.MALE);
        return person;
    }
}
