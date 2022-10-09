package githubmagovia.ockovanie.evidencia.vaccine;

import githubmagovia.ockovanie.evidencia.vaccine.dto.VaccineDto;
import githubmagovia.ockovanie.evidencia.vaccine.models.VaccineEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class VaccineServiceTest {

    @InjectMocks
    private VaccineService vaccineService;

    @Mock
    private VaccineRepository vaccineRepository;

    @Test
    void correctAttributesAreSetForNewVaccineEntity() {
        Long generatedId = 1L;
        VaccineDto vaccineDto = generateValidVaccineDTO("Pfizer");
        when(vaccineRepository.save(any(VaccineEntity.class))).thenReturn(buildFakeVaccine(generatedId, vaccineDto));

        VaccineDto returnedDto = vaccineService.createVaccine(vaccineDto);

        assertThat(generatedId).isEqualTo(returnedDto.getId());
        assertThat(returnedDto.getName()).isEqualTo("Pfizer");
        assertThat(returnedDto.getType()).isEqualTo("Vector");
        assertThat(returnedDto.getAmountOfVaccines()).isEqualTo(5);
        assertThat(returnedDto.getAmountToCompleteVaccination()).isEqualTo(5);
        verify(vaccineRepository, times(1)).save(any());
    }
/*    @Test
    void vaccineCreateFail() {
        VaccineDto vaccine = new VaccineDto().setName("Vakcina");

        IllegalArgumentException exception = (IllegalArgumentException.class, () -> vaccineService.createVaccine(vaccine));
        assertEquals("Author or Title are empty fields", exception.getMessage());

        verify(vaccineRepository, times(0)).save(any());

    }*/

    private VaccineDto generateValidVaccineDTO(String name) {
        VaccineDto vaccine = new VaccineDto();
        vaccine.setName(name);
        vaccine.setType("Vector");
        vaccine.setAmountOfVaccines(5);
        vaccine.setAmountToCompleteVaccination(5);
        return vaccine;
    }

    private VaccineEntity buildFakeVaccine(Long generatedId, VaccineDto dto) {
        return VaccineEntity.builder().id(generatedId).name(dto.getName())
                .type(dto.getType()).amountOfVaccines(dto.getAmountOfVaccines()).amountToCompleteVaccination(dto.getAmountToCompleteVaccination()).build();

    }

}