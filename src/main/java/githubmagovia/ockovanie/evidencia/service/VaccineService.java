package githubmagovia.ockovanie.evidencia.service;

import githubmagovia.ockovanie.evidencia.controllers.dto.VaccineDto;
import githubmagovia.ockovanie.evidencia.domain.repositories.VaccineRepository;
import githubmagovia.ockovanie.evidencia.entity.VaccineEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VaccineService {
    private final VaccineRepository repository;

    public VaccineService (VaccineRepository repository) {
        this.repository = repository;
    }

    //ADD PERSON
    public VaccineEntity createVaccine(VaccineDto vaccine){
        VaccineEntity vaccineEntity = new VaccineEntity();
        vaccineEntity.setName(vaccine.getName());
        vaccineEntity.setType(vaccine.getType());
        vaccineEntity.setAmountOfVaccines(vaccine.getAmountOfVaccines());
        vaccineEntity.setAmountToCompleteVaccination(vaccine.getAmountToCompleteVaccination());
        vaccineEntity.setDaysToFullVaccination(vaccine.getDaysToFullVaccination());
        vaccineEntity.setDurationOfVaccine(vaccine.getdurationOfVaccine());
        return this.repository.save(vaccineEntity);
    }

    //všetci
    public List<VaccineEntity> getVaccines(){
        return repository.findAll();
    }

    // podla id
    public VaccineEntity getVaccineById(Long vaccineId){
        Optional<VaccineEntity> vaccine = repository.findById(vaccineId);
        return vaccine.orElse(null);
    }

    //update
    public VaccineEntity updateVaccine(Long vaccineId, VaccineDto vaccine){
        Optional<VaccineEntity> v = repository.findById(vaccineId);
        if(v.isPresent()){
            v.get().setName(vaccine.getName());
            v.get().setType(vaccine.getType());
            v.get().setAmountOfVaccines(vaccine.getAmountOfVaccines());
            v.get().setAmountToCompleteVaccination(vaccine.getAmountToCompleteVaccination());
            v.get().setDaysToFullVaccination(vaccine.getDaysToFullVaccination());
            return repository.save(v.get());
        }
        return null;
    }

    //delete
    public void deleteVaccine(Long vaccineId){
        repository.deleteById(vaccineId);
    }
}
