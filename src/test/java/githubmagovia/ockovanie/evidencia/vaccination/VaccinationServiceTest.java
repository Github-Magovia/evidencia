package githubmagovia.ockovanie.evidencia.vaccination;

import githubmagovia.ockovanie.evidencia.person.PersonRepository;
import githubmagovia.ockovanie.evidencia.person.models.Gender;
import githubmagovia.ockovanie.evidencia.person.models.PersonEntity;
import githubmagovia.ockovanie.evidencia.vaccination.dto.VaccinationDto;
import githubmagovia.ockovanie.evidencia.vaccination.models.VaccinationEntity;
import githubmagovia.ockovanie.evidencia.vaccination.models.VaccinationStatus;
import githubmagovia.ockovanie.evidencia.vaccine.VaccineRepository;
import githubmagovia.ockovanie.evidencia.vaccine.models.VaccineEntity;
import org.junit.Before;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
class VaccinationServiceTest {
    private Long vaccineId;
    private Long personId;

//    @Autowired
//    private VaccinationService vaccinationService;
//    @Autowired
//    private PersonRepository personRepository;
//    @Autowired
//    private VaccineRepository vaccineRepository;
//    @Autowired
//    private VaccinationRepository vaccinationRepository;

//    @BeforeEach
//    void setUp() {
//        VaccineEntity vaccine = new VaccineEntity();
//        vaccine.setName("Tester Vaccine");
//        vaccine.setAmountOfVaccines(100);
//        vaccine.setType("Vector");
//        vaccine.setDurationOfVaccine(180);
//        vaccine.setAmountToCompleteVaccination(2);
//        vaccine.setDaysToFullVaccination(14);
//        vaccine = vaccineRepository.save(vaccine);
//        System.out.println(vaccine.getId());
//        vaccineId = vaccine.getId();
//
//        PersonEntity person = new PersonEntity();
//        person.setFirstName("Tester");
//        person.setLastName("John");
//        person.setDateOfBirth(LocalDate.now());
//        person.setSex(Gender.MALE);
//        person.setStatus(VaccinationStatus.NONE);
//        person = personRepository.save(person);
//        personId = person.getId();
//    }
//
//    @AfterEach
//    void tearDown() {
//        vaccinationRepository.deleteAll(vaccinationService.getVaccinationsByPersonId(personId));
//
//        personRepository.deleteById(personId);
//        personId = null;
//
//        vaccineRepository.deleteById(vaccineId);
//        vaccineId = null;
//    }
//
//    @Test
//    void createValidVaccination() {
//        VaccinationDto vaccination = generateValidVaccinationDTO();
//        vaccination = vaccinationService.createVaccination(vaccination);
//        assertThat(vaccination.getId()).isNotEqualTo(0);
//    }
//
//    @Test
//    void createVaccinationWithInvalidVaccineThrowsException() {
//        VaccinationDto vaccination = generateValidVaccinationDTO();
//        vaccination.setIdVaccine(-1L);
//        assertThatThrownBy(() -> vaccinationService.createVaccination(vaccination));
//    }
//
//    @Test
//    void createVaccinationWithInvalidPersonThrowsException() {
//        VaccinationDto vaccination = generateValidVaccinationDTO();
//        vaccination.setIdPerson(-1L);
//        assertThatThrownBy(() -> vaccinationService.createVaccination(vaccination));
//    }
//
//    @Test
//    void createVaccinationWithInvalidDateThrowsException() {
//        VaccinationDto vaccination = generateValidVaccinationDTO();
//        vaccination.setDateOfVaccination(null);
//        assertThatThrownBy(() -> vaccinationService.createVaccination(vaccination));
//    }

    private VaccinationDto generateValidVaccinationDTO() {
        VaccinationDto vaccination = new VaccinationDto();
        vaccination.setIdPerson(personId);
        vaccination.setIdVaccine(vaccineId);
        vaccination.setDateOfVaccination(LocalDate.now());
        return vaccination;
    }
}
