package githubmagovia.ockovanie.evidencia.service;


import githubmagovia.ockovanie.evidencia.controllers.dto.VaccinationDto;
import githubmagovia.ockovanie.evidencia.domain.repositories.VaccinationRepository;
import githubmagovia.ockovanie.evidencia.entity.PersonEntity;
import githubmagovia.ockovanie.evidencia.entity.VaccinationEntity;
import org.springframework.stereotype.Service;

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
        PersonEntity person = personService.getPersonById(request.getId());
        VaccineEntity vaccine = VaccineService.getVaccineById(request.getVaccineById());
        if (person != null && vaccine != null){
            vaccination.setPerson(person);
            vaccination.setVaccine(vaccine);
            return  vaccinationRepository.save(vaccination);
        }
        return null;
    }
    // get vaccination by id
    public VaccinationEntity getVaccinationById(Long vaccinationId){
        Optional<VaccinationEntity> vaccination = vaccinationRepository.findById(vaccinationId);
        return vaccination.orElse(null);
    }
    //delete vaccination service
    public void deleteVaccination(Long vaccinationId){
        vaccinationRepository.deleteById(vaccinationId);
    }
}
