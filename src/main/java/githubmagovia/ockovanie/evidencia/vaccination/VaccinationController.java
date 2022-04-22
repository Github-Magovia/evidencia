package githubmagovia.ockovanie.evidencia.vaccination;


import githubmagovia.ockovanie.evidencia.vaccination.dto.VaccinationDto;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class VaccinationController {
    private final VaccinationService vaccinationService;

    public VaccinationController(VaccinationService vaccinationService) {
        this.vaccinationService = vaccinationService;
    }

    @PostMapping("/api/vaccinations")
    public VaccinationDto createVaccination(@RequestBody VaccinationDto vaccination){
        return vaccinationService.createVaccination(vaccination);
    }

    @GetMapping("/api/vaccinations")
    public List<VaccinationDto> getVaccinations(){
        return vaccinationService.getVaccinations();
    }

    @GetMapping("/api/vaccinations/{vaccinationId}")
    public VaccinationDto getVaccinationById(@PathVariable long vaccinationId){
        return vaccinationService.getVaccinationById(vaccinationId);
    }

    @DeleteMapping("api/vaccinations/{vaccinationId}")
    public void deleteVaccination(@PathVariable long vaccinationId){
        vaccinationService.deleteVaccination(vaccinationId);
    }
}
