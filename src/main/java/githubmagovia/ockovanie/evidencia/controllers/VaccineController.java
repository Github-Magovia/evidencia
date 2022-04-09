package githubmagovia.ockovanie.evidencia.controllers;

import githubmagovia.ockovanie.evidencia.controllers.dto.VaccineDto;
import githubmagovia.ockovanie.evidencia.entity.VaccineEntity;
import githubmagovia.ockovanie.evidencia.service.VaccineService;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class VaccineController {

    private VaccineService service;

    public VaccineController(VaccineService service){
        this.service = service;
    }

    @PostMapping("/api/vaccines")
    public VaccineEntity createVaccine(@RequestBody VaccineDto vaccine){
        return service.createVaccine(vaccine);
    }
    // get vsetci 
    @GetMapping("/api/vaccines")
    public List<VaccineDto> getVaccines(){
        List<VaccineEntity> people = service.getVaccines();
        List<VaccineDto> result = new ArrayList<>();
        for (VaccineEntity p : people){
            result.add(mapToDto(p));
        }
        return result;
    }
    // get podla id
    @GetMapping("/api/vaccines/{vaccineId}")
    public VaccineDto getVaccineById(@PathVariable Long vaccineId){
        return mapToDto(service.getVaccineById(vaccineId));
    }
    //update person
    @PutMapping("/api/vaccines/{vaccineId}")
    public VaccineDto updateVaccine(@PathVariable Long vaccineId, @RequestBody VaccineDto vaccine){
        return mapToDto(service.updateVaccine(vaccineId,vaccine));
    }
    //delete person
    @DeleteMapping("/api/vaccines/{vaccineId}")
    public void deleteVaccine(@PathVariable Long vaccineId){
        service.deleteVaccine(vaccineId);
    }

    //mapToDto
    private VaccineDto mapToDto(VaccineEntity entity){
        VaccineDto VaccineDto = new VaccineDto();
        VaccineDto.setId(entity.getId());
        VaccineDto.setName(entity.getName());
        VaccineDto.setType(entity.getType());
        VaccineDto.setAmountOfVaccines(entity.getAmountOfVaccines());
        VaccineDto.setAmountToCompleteVaccination(entity.getAmountToCompleteVaccination());
        return VaccineDto;
    }

}
