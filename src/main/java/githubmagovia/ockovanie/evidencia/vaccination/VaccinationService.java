package githubmagovia.ockovanie.evidencia.vaccination;


import githubmagovia.ockovanie.evidencia.vaccination.dto.VaccinationDto;
import githubmagovia.ockovanie.evidencia.vaccination.models.VaccinationStatus;
import githubmagovia.ockovanie.evidencia.person.models.PersonEntity;
import githubmagovia.ockovanie.evidencia.vaccination.models.VaccinationEntity;
import githubmagovia.ockovanie.evidencia.vaccine.models.VaccineEntity;
import githubmagovia.ockovanie.evidencia.person.PersonService;
import githubmagovia.ockovanie.evidencia.vaccine.VaccineService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
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

    public List<VaccinationDto> getVaccinations(){
        List<VaccinationEntity> vaccinations = vaccinationRepository.findAll();
        List<VaccinationDto> result = new ArrayList<>();
        for (VaccinationEntity entity : vaccinations) {
            result.add(mapToDto(entity));
        }
        return result;
    }

    public VaccinationDto createVaccination(VaccinationDto request){
        VaccinationEntity vaccination = new VaccinationEntity();
        PersonEntity person = personService.getEntityById(request.getIdPerson());
        VaccineEntity vaccine = vaccineService.getEntityById(request.getIdVaccine());
        // TODO throw exception when vaccines amount == 0
        if (person != null && vaccine != null) {
            int numberOfVaccinations = vaccinationRepository.findAllByPersonEquals(person).size() + 1;
            int amountToComplete = vaccine.getAmountToCompleteVaccination();
            vaccination.setPerson(person);
            vaccination.setVaccine(vaccine);
            vaccination.setShotNumber(numberOfVaccinations);
            vaccination.setDateOfVaccination(request.getDateOfVaccination());
            processDurationOfVaccine(person,
                    request.getDateOfVaccination(),
                    vaccine.getDaysToFullVaccination(),
                    vaccine.getDurationOfVaccine(),
                    numberOfVaccinations,
                    amountToComplete);
            vaccine.decrementAmountOfVaccines();
            person.setStatus(processNewStatus(numberOfVaccinations, amountToComplete));
            return mapToDto(vaccinationRepository.save(vaccination));
        }
        return null;
    }

    public VaccinationDto getVaccinationById(long vaccinationId){
        Optional<VaccinationEntity> vaccination = vaccinationRepository.findById(vaccinationId);
        return vaccination.map(this::mapToDto).orElse(null);
    }

   /* // todo update vaccination
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

    public void deleteVaccination(long vaccinationId){
        vaccinationRepository.deleteById(vaccinationId);
    }

    private VaccinationStatus processNewStatus(int numberOfVaccinations, int requiredAmount) {
        if (numberOfVaccinations <= 0) { return VaccinationStatus.NONE; }
        if (numberOfVaccinations < requiredAmount) { return VaccinationStatus.PARTIAL; }
        if (numberOfVaccinations == requiredAmount) { return VaccinationStatus.FULL; }
        return VaccinationStatus.BOOSTER;
    }

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

    private VaccinationDto mapToDto(VaccinationEntity entity){
        VaccinationDto vaccinationDto = new VaccinationDto();
        VaccineEntity vaccine = entity.getVaccine();
        PersonEntity person = entity.getPerson();
        vaccinationDto.setId(entity.getId());
        vaccinationDto.setFirstName(person.getFirstName());
        vaccinationDto.setIdVaccine(vaccine.getId());
        vaccinationDto.setIdPerson(person.getId());
        vaccinationDto.setLastName(person.getLastName());
        vaccinationDto.setType(vaccine.getType());
        vaccinationDto.setDateOfVaccination(entity.getDateOfVaccination());
        return vaccinationDto;
    }
}
