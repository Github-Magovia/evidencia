package githubmagovia.ockovanie.evidencia.person;

import com.fasterxml.jackson.databind.ObjectMapper;
import githubmagovia.ockovanie.evidencia.exceptions.ServerException;
import githubmagovia.ockovanie.evidencia.person.dto.PersonDto;
import githubmagovia.ockovanie.evidencia.person.models.Gender;
import githubmagovia.ockovanie.evidencia.vaccination.models.VaccinationStatus;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PersonController.class)
@ActiveProfiles("test")
class PersonControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PersonService personService;

    private static final ObjectMapper mapper = new ObjectMapper().findAndRegisterModules();

    @Test
    @SneakyThrows
    void createPerson() {
        PersonDto personDto = generateValidPersonDTO("Simon");
        when(personService.createPerson(any(PersonDto.class))).thenReturn(personDto);

        PersonDto dto = PersonDto.builder().firstName("Tester").lastName("Simon").sex(Gender.MALE).build();
        String json = mapper.writeValueAsString(dto);

        MvcResult result = mockMvc.perform(post("/api/people/")
                        .content(json)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        PersonDto response = mapper.readValue(result.getResponse().getContentAsString(), PersonDto.class);

        assertThat(response.getId()).isZero();
        assertThat(response.getStatus()).isEqualTo(VaccinationStatus.NONE);
        assertThat(response.getVaccineStart()).isNull();
        assertThat(response.getVaccineEnd()).isNull();
        verify(personService, times(1)).createPerson(any());
    }

    @Test
    @SneakyThrows
    void updatePerson() {
        long generatedId = 0L;
        PersonDto personDto = generateValidPersonDTO("Simon");
        when(personService.updatePerson(longThat(id -> id == generatedId), any(PersonDto.class))).thenReturn(personDto);
        when(personService.updatePerson(longThat(id -> id != generatedId), any(PersonDto.class))).thenThrow(ServerException.class);

        PersonDto dto = PersonDto.builder().id(generatedId).firstName(personDto.getFirstName()).lastName("Simon")
                .sex(Gender.MALE).dateOfBirth(personDto.getDateOfBirth()).status(personDto.getStatus())
                .vaccineStart(personDto.getVaccineStart()).vaccineEnd(personDto.getVaccineEnd()).build();
        String json = mapper.writeValueAsString(dto);

        MvcResult result = mockMvc.perform(put("/api/people/" + dto.getId())
                        .content(json)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        PersonDto response = mapper.readValue(result.getResponse().getContentAsString(), PersonDto.class);

        assertThat(response.getId()).isZero();
        assertThat(response.getLastName()).isEqualTo(dto.getLastName());

        mockMvc.perform(put("/api/people/" + (generatedId + 1))
                        .content(json)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(res -> assertTrue(res.getResolvedException() instanceof ServerException));

        verify(personService, times(2)).updatePerson(anyLong(), any(PersonDto.class));
    }

    @Test
    @SneakyThrows
    void getPeople() {
        int bound = new Random().nextInt(20);
        List<PersonDto> personDtos = new ArrayList<>();
        for (int i = 0; i < bound; i++) {
            personDtos.add(generateValidPersonDTO("Pavel" + i));
        }
        when(personService.getPeople()).thenReturn(personDtos);

        MvcResult result = mockMvc.perform(get("/api/people"))
                .andExpect(status().isOk())
                .andReturn();
        PersonDto[] response = mapper.readValue(result.getResponse().getContentAsString(), PersonDto[].class);

        assertThat(response).hasSize(bound);
        verify(personService, times(1)).getPeople();
    }

    @Test
    @SneakyThrows
    void getPersonById() {
        long generatedId = 0L;
        PersonDto personDto = generateValidPersonDTO("Michal");
        when(personService.getPersonById(longThat(id -> id == generatedId))).thenReturn(personDto);
        when(personService.getPersonById(longThat(id -> id != generatedId))).thenReturn(null);


        MvcResult result = mockMvc.perform(get("/api/people/" + generatedId))
                .andExpect(status().isOk())
                .andReturn();
        PersonDto response = mapper.readValue(result.getResponse().getContentAsString(), PersonDto.class);

        assertThat(response.getId()).isZero();

        mockMvc.perform(get("/api/people/" + (generatedId + 1)))
                .andExpect(res -> assertEquals("", res.getResponse().getContentAsString()));

        verify(personService, times(2)).getPersonById(any());
    }

    @Test
    @SneakyThrows
    void deletePerson() {
        long generatedId = 0L;
        doNothing().when(personService).deletePerson(longThat(id -> id == generatedId));

        mockMvc.perform(delete("/api/people/" + generatedId))
                .andExpect(status().isOk())
                .andExpect(res -> assertEquals("", res.getResponse().getContentAsString()));

        verify(personService, times(1)).deletePerson(any());
    }

    private PersonDto generateValidPersonDTO(String lName) {
        PersonDto person = new PersonDto();
        person.setFirstName("Tester");
        person.setLastName(lName);
        person.setStatus(VaccinationStatus.NONE);
        person.setDateOfBirth(LocalDate.now());
        person.setSex(Gender.MALE);
        return person;
    }
}