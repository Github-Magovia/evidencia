package githubmagovia.ockovanie.evidencia.service;


import githubmagovia.ockovanie.evidencia.controllers.dto.PersonDto;
import githubmagovia.ockovanie.evidencia.domain.models.VaccinationStatus;
import githubmagovia.ockovanie.evidencia.domain.repositories.PersonRepository;
import githubmagovia.ockovanie.evidencia.entity.PersonEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonService {
    private final PersonRepository repository;

    public PersonService (PersonRepository repository) {
        this.repository = repository;
    }

    //ADD PERSON
    public PersonEntity createPerson(PersonDto person){
        PersonEntity personEntity = new PersonEntity();
        personEntity.setFirstName(person.getFirstName());
        personEntity.setLastName(person.getLastName());
        personEntity.setSex(person.getSex());
        personEntity.setDateOfBirth(person.getDateOfBirth());
        personEntity.setStatus(VaccinationStatus.NONE);
        return this.repository.save(personEntity);
    }

    //všetci
    public List<PersonEntity> getPeople(){
        return repository.findAll();
    }
    // podla id
    public PersonEntity getPersonById(Long personId){
        Optional<PersonEntity> person = repository.findById(personId);
        return person.orElse(null);
    }
    //update (bude doplnene este)
    public PersonEntity updatePerson(Long personId, PersonDto person){
        Optional<PersonEntity> p = repository.findById(personId);
        if(p.isPresent()){
            p.get().setFirstName(person.getFirstName());
            p.get().setLastName(person.getLastName());
            p.get().setSex(person.getSex());
            p.get().setDateOfBirth(person.getDateOfBirth());
            return repository.save(p.get());
        }
        return null;
    }

    //delete
    public void deletePerson(Long personId){
        repository.deleteById(personId);
    }
}
