package githubmagovia.ockovanie.evidencia.vaccination;

import com.fasterxml.jackson.databind.ObjectMapper;
import githubmagovia.ockovanie.evidencia.person.PersonController;
import githubmagovia.ockovanie.evidencia.person.PersonService;
import githubmagovia.ockovanie.evidencia.person.dto.PersonDto;
import githubmagovia.ockovanie.evidencia.person.models.Gender;
import githubmagovia.ockovanie.evidencia.vaccination.dto.VaccinationDto;
import githubmagovia.ockovanie.evidencia.vaccination.models.VaccinationStatus;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;
import static org.keycloak.util.JsonSerialization.mapper;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(VaccinationController.class)
@ActiveProfiles("test")
class VaccinationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private VaccinationService vaccinationService;


    private static final ObjectMapper mapper = new ObjectMapper().findAndRegisterModules();




    @Test
    @SneakyThrows
    void getVaccinations() {
        int bound = new Random().nextInt(20);
        List<VaccinationDto> vaccinationDtos = new ArrayList<>();
        for (int i = 0; i < bound; i++) {
            vaccinationDtos.add(generateValidVaccinationDTO("Pavel" + i, i));
        }
        when(vaccinationService.getVaccinations()).thenReturn(vaccinationDtos);

        MvcResult result = mockMvc.perform(get("/api/vaccinations"))
                .andExpect(status().isOk())
                .andReturn();
        VaccinationDto[] response = mapper.readValue(result.getResponse().getContentAsString(), VaccinationDto[].class);

        assertThat(response).hasSize(bound);
        verify(vaccinationService, times(1)).getVaccinations();
    }

    private VaccinationDto generateValidVaccinationDTO(String vName, Integer id) {
        VaccinationDto vaccination = new VaccinationDto();
        vaccination.setVaccineName(vName);
        vaccination.setIdVaccine(id);
        return vaccination;
    }
}
