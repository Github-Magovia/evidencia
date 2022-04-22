package githubmagovia.ockovanie.evidencia.vaccine;

import githubmagovia.ockovanie.evidencia.vaccine.dto.VaccineDto;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class VaccineController {
    private final VaccineService service;

    public VaccineController(VaccineService service){
        this.service = service;
    }

    @PostMapping("/api/vaccines")
    public VaccineDto createVaccine(@RequestBody VaccineDto vaccine){
        return service.createVaccine(vaccine);
    }

    @GetMapping("/api/vaccines")
    public List<VaccineDto> getVaccines(){
        return service.getVaccines();
    }

    @GetMapping("/api/vaccines/{vaccineId}")
    public VaccineDto getVaccineById(@PathVariable Long vaccineId){
        return service.getVaccineById(vaccineId);
    }

    @PutMapping("/api/vaccines/{vaccineId}")
    public VaccineDto updateVaccine(@PathVariable Long vaccineId, @RequestBody VaccineDto vaccine){
        return service.updateVaccine(vaccineId,vaccine);
    }

    @DeleteMapping("/api/vaccines/{vaccineId}")
    public void deleteVaccine(@PathVariable Long vaccineId){
        service.deleteVaccine(vaccineId);
    }
}
