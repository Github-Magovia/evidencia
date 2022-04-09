package githubmagovia.ockovanie.evidencia.controllers;


import githubmagovia.ockovanie.evidencia.controllers.dto.VaccinationDto;
import githubmagovia.ockovanie.evidencia.entity.PersonEntity;
import githubmagovia.ockovanie.evidencia.entity.VaccinationEntity;
import githubmagovia.ockovanie.evidencia.service.VaccinationService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class VaccinationController {
    private final VaccinationService vaccinationService;

    public VaccinationController(VaccinationService vaccinationService) {
        this.vaccinationService = vaccinationService;
    }

    //get all vaccinations
    @GetMapping("/api/vaccinations")
    public List<VaccinationDto> getVaccinations(){
        List<VaccinationEntity> entities = vaccinationService.getVaccinations();
        List<VaccinationDto> result = new ArrayList<>();
        for (VaccinationEntity entity: entities){
            result.add(mapToDto(entity));
        }
        return result;
    }
    // post new vaccination
    @PostMapping("/api/vaccinations")
    public VaccinationEntity createVaccination(@RequestBody VaccinationDto vaccination){
        return vaccinationService.createVaccination(vaccination);
    }
    //get vaccination by id
    @GetMapping("/api/vaccinations/{vaccinationId}")
    public VaccinationDto getVaccinationById(@PathVariable Long vaccinationId){
        return  mapToDto(vaccinationService.getVaccinationById(vaccinationId));
    }
    //delete vaccination
    @DeleteMapping("api/vaccinations/{vaccinationId}")
    public void deleteVaccination(@PathVariable Long vaccinationId){
        vaccinationService.deleteVaccination(vaccinationId);
    }

    //maps VaccinationEntity into VaccinationDto
    // TODO
    private VaccinationDto mapToDto(VaccinationEntity entity){
        VaccinationDto vaccinationDto = new VaccinationDto();
        VaccineEntity vaccine = entity.getVaccine();
        PersonEntity person = entity.getPerson();
        vaccinationDto.setId(entity.getId());
        vaccinationDto.setFirstName(person.getFirstName());
        vaccinationDto.setLastName(person.getLastName());
        vaccinationDto.setName(vaccine.getName());
        vaccinationDto.setType(vaccine.getType());
        return vaccinationDto;

    }
}
