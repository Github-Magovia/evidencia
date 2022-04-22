package githubmagovia.ockovanie.evidencia.person;

import githubmagovia.ockovanie.evidencia.person.dto.PersonDto;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PersonController {
    private final PersonService service;

    public PersonController(PersonService service){
        this.service = service;
    }

    @PostMapping("/api/people")
    public PersonDto createPerson(@RequestBody PersonDto person){
        return service.createPerson(person);
    }

    @GetMapping("/api/people")
    public List<PersonDto> getPeople(){
        return service.getPeople();
    }

    @GetMapping("/api/people/{personId}")
    public PersonDto getPersonById(@PathVariable Long personId){
        return service.getPersonById(personId);
    }

    @PutMapping("/api/people/{personId}")
    public PersonDto updatePerson(@PathVariable Long personId, @RequestBody PersonDto person){
        return service.updatePerson(personId,person);
    }

    @DeleteMapping("/api/people/{personId}")
    public void deletePerson(@PathVariable Long personId){
        service.deletePerson(personId);
    }
}
