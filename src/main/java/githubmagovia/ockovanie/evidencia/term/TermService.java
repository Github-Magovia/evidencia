package githubmagovia.ockovanie.evidencia.term;

import githubmagovia.ockovanie.evidencia.person.PersonService;
import githubmagovia.ockovanie.evidencia.person.models.PersonEntity;
import githubmagovia.ockovanie.evidencia.term.dto.TermDto;
import githubmagovia.ockovanie.evidencia.term.models.TermEntity;
import githubmagovia.ockovanie.evidencia.vaccination.VaccinationService;
import githubmagovia.ockovanie.evidencia.vaccination.dto.VaccinationDto;
import githubmagovia.ockovanie.evidencia.vaccination.models.VaccinationEntity;
import githubmagovia.ockovanie.evidencia.vaccine.VaccineService;
import githubmagovia.ockovanie.evidencia.vaccine.dto.VaccineDto;
import githubmagovia.ockovanie.evidencia.vaccine.models.VaccineEntity;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;

@EnableScheduling
@Service
public class TermService {
    private final TermRepository repository;
    private final PersonService personService;
    private final VaccineService vaccineService;
    private final VaccinationService vaccinationService;

    public TermService(TermRepository repository, PersonService personService, VaccineService vaccineService, VaccinationService vaccinationService) {
        this.repository = repository;
        this.personService = personService;
        this.vaccineService = vaccineService;
        this.vaccinationService = vaccinationService;
    }

    public TermDto createTerm(TermDto term){
        TermEntity termEntity = new TermEntity();
        PersonEntity person = personService.getEntityById(term.getPersonId());
        termEntity.setVaccinationCentre(term.getVaccinationCentre());
        termEntity.setDateOfVaccination(term.getDateOfVaccination());
        termEntity.setPerson(person);
        termEntity.setVaccine(getVaccine(vaccinationService.getVaccinationsByPersonId(person)));

        return mapToDto(this.repository.save(termEntity));
    }


    public List<TermDto> getTerms(){
        List<TermEntity> entities = repository.findAll();
        List<TermDto> result = new ArrayList<>();
        for (TermEntity entity : entities) {
            result.add(mapToDto(entity));
        }
        return result;
    }
    public TermDto getTermById(Long termId){
        Optional<TermEntity> term = repository.findById(termId);
        return term.map(this::mapToDto).orElse(null);
    }

    public TermDto updateTerm(Long termId, TermDto term){
        Optional<TermEntity> t = repository.findById(termId);
        if(t.isPresent()){
            t.get().setVaccinationCentre(term.getVaccinationCentre());
            t.get().setDateOfVaccination(term.getDateOfVaccination());
            return mapToDto(repository.save(t.get()));
        }
        return null;
    }

    public void deleteTerm(Long termId){
        repository.deleteById(termId);
    }

    // 86400000 - every 24 hours
    @Scheduled(fixedRate = 86400000)
    public void addVaccinations(){
        List<TermEntity> selection = repository.getAllByDateOfVaccinationBefore(LocalDateTime.now());
        for (TermEntity term : selection) {
            VaccinationDto dto = new VaccinationDto();
            dto.setIdPerson(term.getPerson().getId());
            dto.setIdVaccine(term.getVaccine().getId());
            dto.setDateOfVaccination(term.getDateOfVaccination().toLocalDate());
            vaccinationService.createVaccination(dto);
            repository.delete(term);
        }
    }


    private TermDto mapToDto(TermEntity entity){
        TermDto termDto = new TermDto();
        PersonEntity person = entity.getPerson();
        termDto.setId(entity.getId());
        termDto.setVaccinationCentre(entity.getVaccinationCentre());
        termDto.setDateOfVaccination(entity.getDateOfVaccination());
        termDto.setPersonId(person.getId());
        termDto.setPersonName(person.getFirstName() + " " + person.getLastName());
        termDto.setVaccineName(entity.getVaccine().getName());
        return termDto;
    }

    private VaccineEntity getVaccine(List<VaccinationEntity> vaccinations) {
        if (vaccinations.size() <= 0) {
            // get random vaccine
            List<VaccineDto> vaccines = vaccineService.getVaccines();
            return vaccineService.getEntityById(vaccines.get(ThreadLocalRandom.current().nextInt(0, vaccines.size())).getId());
        } else {
            // get the one already assigned
            return vaccinations.get(0).getVaccine();
        }
    }
}
