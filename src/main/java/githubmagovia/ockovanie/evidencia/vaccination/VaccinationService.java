package githubmagovia.ockovanie.evidencia.vaccination;


import githubmagovia.ockovanie.evidencia.exceptions.ServerException;
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
        if (vaccine == null || vaccine.getAmountOfVaccines() <= 0) {
            throw new ServerException("Vaccine is not available");
        } else if (person == null) {
            throw new ServerException("Person is not available");
        } else if (request.getDateOfVaccination() == null) {
            throw new ServerException("Date of vaccination is not available");
        } else {
            int numberOfVaccinations = getVaccinationsByPersonId(person).size() + 1;
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
            personService.updateVaccinationDetails(person);
            return mapToDto(vaccinationRepository.save(vaccination));
        }
    }

    public VaccinationDto getVaccinationById(long vaccinationId){
        Optional<VaccinationEntity> vaccination = vaccinationRepository.findById(vaccinationId);
        return vaccination.map(this::mapToDto).orElse(null);
    }

    public List<VaccinationEntity> getVaccinationsByPersonId(PersonEntity person) {
        return vaccinationRepository.findAllByPersonEquals(person);
    }

    public List<VaccinationEntity> getVaccinationsByPersonId(Long id) {
        return vaccinationRepository.findAllByPersonId(id);
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
        VaccinationEntity removed = vaccinationRepository.getById(vaccinationId);
        PersonEntity person = removed.getPerson();
        List<VaccinationEntity> vaccinations = vaccinationRepository.findAllByPersonEquals(person);
        int index = vaccinations.indexOf(removed);
        if (vaccinations.size() > 1) {
            VaccinationEntity item;
            for (int i = index + 1; i < vaccinations.size(); i++) {
                item = vaccinations.get(i);
                item.setShotNumber(item.getShotNumber() - 1);
                vaccinationRepository.save(item);
            }
            vaccinationRepository.delete(removed);
            item = vaccinationRepository.findFirstByPersonEqualsOrderByShotNumberDesc(person);
            processDurationOfVaccine(person,
                    item.getDateOfVaccination(),
                    item.getVaccine().getDaysToFullVaccination(),
                    item.getVaccine().getDurationOfVaccine(),
                    item.getShotNumber(),
                    item.getVaccine().getAmountToCompleteVaccination());
            person.setStatus(processNewStatus(item.getShotNumber(),
                    item.getVaccine().getAmountToCompleteVaccination()));
        } else {
            vaccinationRepository.delete(removed);
            processDurationOfVaccine(person, null, 1, 1, 0, 1);
            person.setStatus(processNewStatus(0, 1));
        }
        personService.updateVaccinationDetails(person);
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
        vaccinationDto.setVaccineName(vaccine.getName());
        vaccinationDto.setLastName(person.getLastName());
        vaccinationDto.setType(vaccine.getType());
        vaccinationDto.setDateOfVaccination(entity.getDateOfVaccination());
        return vaccinationDto;
    }
}
