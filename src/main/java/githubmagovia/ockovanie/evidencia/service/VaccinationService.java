package githubmagovia.ockovanie.evidencia.service;


import githubmagovia.ockovanie.evidencia.controllers.dto.VaccinationDto;
import githubmagovia.ockovanie.evidencia.domain.models.VaccinationStatus;
import githubmagovia.ockovanie.evidencia.domain.repositories.VaccinationRepository;
import githubmagovia.ockovanie.evidencia.entity.PersonEntity;
import githubmagovia.ockovanie.evidencia.entity.VaccinationEntity;
import githubmagovia.ockovanie.evidencia.entity.VaccineEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class VaccinationService {
    private final VaccinationRepository vaccinationRepository;
    private final PersonService personService;
    private final VaccineService vaccineService;

    public VaccinationService(VaccinationRepository vaccinationRepository, PersonService personService, VaccineService vaccineService) {
        this.vaccinationRepository = vaccinationRepository;
        this.personService = personService;
        this.vaccineService = vaccineService;
    }

    // get list of vaccinations
    public List<VaccinationEntity> getVaccinations(){
        return vaccinationRepository.findAll();
    }

    // create - post vaccination
    public VaccinationEntity createVaccination(VaccinationDto request){
        VaccinationEntity vaccination = new VaccinationEntity();
        PersonEntity person = personService.getPersonById(request.getIdPerson());
        VaccineEntity vaccine = vaccineService.getVaccineById(request.getIdVaccine());
        // TODO throw exception when vaccines amount == 0
        if (person != null && vaccine != null) {
            int numberOfVaccinations = vaccinationRepository.findAllByPersonEquals(person).size() + 1;
            int amountToComplete = vaccine.getAmountToCompleteVaccination();
            vaccination.setPerson(person);
            vaccination.setVaccine(vaccine);
            vaccination.setShotNumber(numberOfVaccinations);
            vaccination.setDateOfVaccination(request.getDateOfVaccination());
            processDurationOfVaccine(person,
                    request.getDateOfVaccination().toLocalDate(),
                    vaccine.getDaysToFullVaccination(),
                    vaccine.getDurationOfVaccine(),
                    numberOfVaccinations,
                    amountToComplete);
            vaccine.decrementAmountOfVaccines();
            person.setStatus(processNewStatus(numberOfVaccinations, amountToComplete));
            return vaccinationRepository.save(vaccination);
        }
        return null;
    }

     // get vaccination by id
    public VaccinationEntity getVaccinationById(long vaccinationId){
        Optional<VaccinationEntity> vaccination = vaccinationRepository.findById(vaccinationId);
        return vaccination.orElse(null);
    }

   /* // update vaccination
    public VaccinationEntity updateVaccinationById(long vaccinationId, VaccinationEntity entity){
        VaccinationEntity vaccination = this.getVaccinationById(vaccinationId);
        if (vaccination != null){
            vaccination.setVaccine(entity.getVaccine());
            vaccination.setPerson(entity.getPerson());
            vaccination.setDateOfVaccination(entity.getDateOfVaccination());
            vaccination.setShotNumber(entity.getShotNumber());
            return vaccinationRepository.save(vaccination);
        }
        return null;
    }*/

    // delete vaccination service
    public void deleteVaccination(long vaccinationId){
        vaccinationRepository.deleteById(vaccinationId);
    }

    private VaccinationStatus processNewStatus(int numberOfVaccinations, int requiredAmount) {
        if (numberOfVaccinations <= 0) { return VaccinationStatus.NONE; }
        if (numberOfVaccinations < requiredAmount) { return VaccinationStatus.PARTIAL; }
        if (numberOfVaccinations == requiredAmount) { return VaccinationStatus.FULL; }
        return VaccinationStatus.BOOSTER;
    }

    // sets the date when FULL and BOOSTER vaccinations start and expire
    private void processDurationOfVaccine(PersonEntity person,
                                          LocalDate start,
                                          int startOffset,
                                          int duration,
                                          int numberOfVaccinations,
                                          int requiredAmount) {
        if (numberOfVaccinations >= requiredAmount) {
            person.setVaccineStart(start.plusDays(startOffset));
            person.setVaccineEnd(start.plusDays(startOffset + duration));
        } else {
            person.setVaccineStart(null);
            person.setVaccineEnd(null);
        }
    }
}
