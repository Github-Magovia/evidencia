package githubmagovia.ockovanie.evidencia.vaccine;

import com.fasterxml.jackson.databind.ObjectMapper;
import githubmagovia.ockovanie.evidencia.vaccine.dto.VaccineDto;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;



import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(VaccineController.class)
@ActiveProfiles("test")
class VaccineControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private VaccineService vaccineService;

    private static final ObjectMapper mapper = new ObjectMapper().findAndRegisterModules();

    @Test
    @SneakyThrows
    void createVaccine() {
        VaccineDto vaccineDto = generateValidVaccineDTO("Pfizer");
        when(vaccineService.createVaccine(any(VaccineDto.class))).thenReturn(vaccineDto);

        VaccineDto dto = VaccineDto.builder().name("Pfizer").type("Vector").amountOfVaccines(5).amountToCompleteVaccination(5).build();
        String json = mapper.writeValueAsString(dto);

        MvcResult result = mockMvc.perform(post("/api/vaccines/")
                        .content(json)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        VaccineDto response = mapper.readValue(result.getResponse().getContentAsString(), VaccineDto.class);

        assertThat(response.getId()).isZero();
        assertThat(response.getName()).isEqualTo("Pfizer");
        assertThat(response.getType()).isEqualTo("Vector");
        assertThat(response.getAmountOfVaccines()).isEqualTo(5);
        assertThat(response.getAmountToCompleteVaccination()).isEqualTo(5);
        verify(vaccineService, times(1)).createVaccine(any());
    }



    private VaccineDto generateValidVaccineDTO(String name) {
        VaccineDto vaccine = new VaccineDto();
        vaccine.setName(name);
        vaccine.setType("Vector");
        vaccine.setAmountOfVaccines(5);
        vaccine.setAmountToCompleteVaccination(5);
        return vaccine;
    }

}