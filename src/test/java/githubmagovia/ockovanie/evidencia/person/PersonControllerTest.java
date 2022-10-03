package githubmagovia.ockovanie.evidencia.person;

import com.fasterxml.jackson.databind.ObjectMapper;
import githubmagovia.ockovanie.evidencia.person.dto.PersonDto;
import githubmagovia.ockovanie.evidencia.person.models.Gender;
import githubmagovia.ockovanie.evidencia.vaccination.models.VaccinationStatus;
import lombok.SneakyThrows;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PersonController.class)
class PersonControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PersonService personService;

    private static ObjectMapper mapper = new ObjectMapper();

    @Test
    @SneakyThrows
    public void createPerson() {
        PersonDto personDto = generateValidPersonDTO("Simon");
        when(personService.createPerson(any())).thenReturn(personDto);

        PersonDto dto = PersonDto.builder().firstName("Tester").lastName("Simon")
                .sex(Gender.MALE).status(VaccinationStatus.NONE).build();
        String json = mapper.writeValueAsString(dto);

        mockMvc.perform(
                        post("/api/people")
                                .content(json)
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(personService, times(1)).createPerson(any());
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