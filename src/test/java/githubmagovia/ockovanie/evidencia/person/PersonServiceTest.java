package githubmagovia.ockovanie.evidencia.person;

import githubmagovia.ockovanie.evidencia.exceptions.ServerException;
import githubmagovia.ockovanie.evidencia.person.dto.PersonDto;
import githubmagovia.ockovanie.evidencia.person.models.Gender;
import githubmagovia.ockovanie.evidencia.person.models.PersonEntity;
import githubmagovia.ockovanie.evidencia.vaccination.models.VaccinationStatus;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PersonServiceTest {

    @InjectMocks
    private PersonService personService;

    @Mock
    private PersonRepository personRepository;

    @Test
    void correctAttributesAreSetForNewPersonEntity() {
        Long generatedId = 1L;
        PersonDto personDto = generateValidPersonDTO("Mark");
        when(personRepository.save(any(PersonEntity.class))).thenReturn(buildFakePerson(generatedId, personDto));

        PersonDto returnedDto = personService.createPerson(personDto);

        assertThat(generatedId).isEqualTo(returnedDto.getId());
        assertThat(returnedDto.getStatus()).isEqualTo(VaccinationStatus.NONE);
        assertThat(returnedDto.getVaccineStart()).isNull();
        assertThat(returnedDto.getVaccineEnd()).isNull();
        verify(personRepository, times(1)).save(any());
    }

//    @Test
//    void getCorrectPersonById() {
//        PersonDto person = generateValidPersonDTO("Thomas");
//        person = personService.createPerson(person);
//        assertThat(personService.getPersonById(person.getId()).getId()).isEqualTo(person.getId());
//    }

    @Test
    void personWithNonexistentIdShouldReturnNull() {
        PersonEntity fakePerson = buildFakePerson(1L, generateValidPersonDTO("Marcus"));
        when(personRepository.findById(longThat(id -> id < 0))).thenReturn(Optional.empty());
        when(personRepository.findById(longThat(id -> id >= 0))).thenReturn(Optional.of(fakePerson));

        assertThat(personService.getPersonById(-1L)).isNull();
        assertThat(personService.getPersonById(0L)).isNotNull();
    }

//    @Test
//    void updateExistingPersonEntity() {
//        PersonDto person = generateValidPersonDTO("Json");
//        person = personService.createPerson(person);
//        person.setLastName("Jason");
//        assertThat(personService.updatePerson(person.getId(), person).getLastName()).isEqualTo(person.getLastName());
//    }

    @Test
    void updatingNonexistentPersonEntityThrowsException() {
        PersonDto person = generateValidPersonDTO("Peter");
        assertThatThrownBy(() -> personService.updatePerson(-1L, person)).isExactlyInstanceOf(ServerException.class);
    }

//    @Test
//    void deleteExistingPersonEntity() {
//        PersonDto person = generateValidPersonDTO("Richard");
//        person = personService.createPerson(person);
//        personService.deletePerson(person.getId());
//        assertThat(personService.getPersonById(person.getId())).isNull();
//    }
    @Test
    void findAndReturnAll() {
        when(personRepository.findAll()).thenReturn(List.of(new PersonEntity(), new PersonEntity()));

        assertThat(personRepository.findAll()).hasSize(2);
        verify(personRepository, times(1)).findAll();
        verifyNoMoreInteractions(personRepository);
    }
    @Test
    void deleteOnePerson() {
        doNothing().when(personRepository).deleteById(anyLong());

        personService.deletePerson(getRandomLong());
        verify(personRepository, times(1)).deleteById(anyLong());
        verifyNoMoreInteractions(personRepository);
    }



    private PersonDto generateValidPersonDTO(String lName) {
        PersonDto person = new PersonDto();
        person.setFirstName("Tester");
        person.setLastName(lName);
        person.setDateOfBirth(LocalDate.now());
        person.setSex(Gender.MALE);
        return person;
    }

    private PersonEntity buildFakePerson(Long generatedId, PersonDto dto) {
        return PersonEntity.builder().id(generatedId).firstName(dto.getFirstName())
                .lastName(dto.getLastName()).dateOfBirth(dto.getDateOfBirth())
                 .sex(dto.getSex()).status(VaccinationStatus.NONE).build();
    }

    private long getRandomLong() {
        return new Random().longs(1, 10).findFirst().getAsLong();
    }
}

