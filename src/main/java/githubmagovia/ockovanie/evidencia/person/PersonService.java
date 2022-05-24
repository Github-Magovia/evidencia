package githubmagovia.ockovanie.evidencia.person;


import githubmagovia.ockovanie.evidencia.exceptions.ServerException;
import githubmagovia.ockovanie.evidencia.person.dto.PersonDto;
import githubmagovia.ockovanie.evidencia.vaccination.models.VaccinationStatus;
import githubmagovia.ockovanie.evidencia.person.models.PersonEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PersonService {
    private final PersonRepository repository;

    public PersonService (PersonRepository repository) {
        this.repository = repository;
    }

    public PersonDto createPerson(PersonDto person){
        PersonEntity personEntity = new PersonEntity();
        personEntity.setFirstName(person.getFirstName());
        personEntity.setLastName(person.getLastName());
        personEntity.setSex(person.getSex());
        personEntity.setDateOfBirth(person.getDateOfBirth());
        personEntity.setStatus(VaccinationStatus.NONE);
        return mapToDto(this.repository.save(personEntity));
    }
    public List<PersonDto> getPeople(){
        List<PersonEntity> entities = repository.findAll();
        List<PersonDto> result = new ArrayList<>();
        for (PersonEntity entity : entities) {
            result.add(mapToDto(entity));
        }
        return result;
    }
    public PersonDto getPersonById(Long personId){
        Optional<PersonEntity> person = repository.findById(personId);
        return person.map(this::mapToDto).orElse(null);
    }

    public PersonDto updatePerson(Long personId, PersonDto person){
        Optional<PersonEntity> p = repository.findById(personId);
        if(p.isPresent()){
            p.get().setFirstName(person.getFirstName());
            p.get().setLastName(person.getLastName());
            p.get().setSex(person.getSex());
            p.get().setDateOfBirth(person.getDateOfBirth());
            return mapToDto(repository.save(p.get()));
        } else {
            throw new ServerException("No person with ID \"" + personId + "\" was found");
        }
    }

    public PersonEntity getEntityById(Long personId) {
        Optional<PersonEntity> person = repository.findById(personId);
        return person.orElse(null);
    }

    public void deletePerson(Long personId){
        repository.deleteById(personId);
    }

    public List<PersonEntity> getAllFullyVaccinated() {
        return repository.getAllByStatusGreaterThan(VaccinationStatus.PARTIAL);
    }

    private PersonDto mapToDto(PersonEntity entity){
        PersonDto personDto = new PersonDto();
        personDto.setId(entity.getId());
        personDto.setFirstName(entity.getFirstName());
        personDto.setLastName(entity.getLastName());
        personDto.setSex(entity.getSex());
        personDto.setDateOfBirth(entity.getDateOfBirth());
        personDto.setStatus(entity.getStatus());
        personDto.setVaccineStart(entity.getVaccineStart());
        personDto.setVaccineEnd(entity.getVaccineEnd());
        return personDto;
    }

    public void updateVaccinationDetails(PersonEntity person) {
        this.repository.save(person);
    }
}
