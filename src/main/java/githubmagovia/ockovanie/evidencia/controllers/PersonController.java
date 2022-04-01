package githubmagovia.ockovanie.evidencia.controllers;

import githubmagovia.ockovanie.evidencia.controllers.dto.PersonDto;
import githubmagovia.ockovanie.evidencia.entity.PersonEntity;
import githubmagovia.ockovanie.evidencia.service.PersonService;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class PersonController {
    private PersonService service;

    public PersonController(PersonService service){
        this.service = service;
    }

    @PostMapping("/api/people")
    public PersonEntity createPerson(@RequestBody PersonDto person){
        return service.createPerson(person);
    }
    // get vsetci
    @GetMapping("/api/people")
    public List<PersonDto> getPerson(){
        List<PersonEntity> persons = service.getPerson();
        List<PersonDto> personDtoList = new ArrayList<>();
        for (PersonEntity p : persons){personDtoList.add(mapToDto(p));}
        return personDtoList;
    }
    // get podla id
    @GetMapping("/api/people/{personId}")
    public PersonDto getPersonById(@PathVariable Long personId){
        return mapToDto(service.getPersonById(personId));
    }
    //update person
    @PutMapping("/api/people/{personId}")
    public PersonDto updatePerson(@PathVariable Long personId, @RequestBody PersonDto person){
        return mapToDto(service.updatePerson(personId,person));
    }
    //delete person
    @DeleteMapping("/api/person/{personId}")
    public void deletePerson(@PathVariable Long personId){
        service.deletePerson(personId);
    }

    //mapToDto
    private PersonDto mapToDto(PersonEntity entity){
        PersonDto personDto = new PersonDto();
        personDto.setId(entity.getId());
        personDto.setFirstName(entity.getFirstName());
        personDto.setLastName(entity.getLastName());
        personDto.setSex(entity.getSex());
        personDto.setDateOfBirth(entity.getDateOfBirth());
        return personDto;
    }

}