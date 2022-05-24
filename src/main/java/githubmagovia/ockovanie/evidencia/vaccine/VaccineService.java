package githubmagovia.ockovanie.evidencia.vaccine;

import githubmagovia.ockovanie.evidencia.vaccine.dto.VaccineDto;
import githubmagovia.ockovanie.evidencia.vaccine.models.VaccineEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class VaccineService {
    private final VaccineRepository repository;

    public VaccineService (VaccineRepository repository) {
        this.repository = repository;
    }

    public VaccineDto createVaccine(VaccineDto vaccine){
        VaccineEntity vaccineEntity = new VaccineEntity();
        vaccineEntity.setName(vaccine.getName());
        vaccineEntity.setType(vaccine.getType());
        vaccineEntity.setAmountOfVaccines(vaccine.getAmountOfVaccines());
        vaccineEntity.setAmountToCompleteVaccination(vaccine.getAmountToCompleteVaccination());
        vaccineEntity.setDaysToFullVaccination(vaccine.getDaysToFullVaccination());
        vaccineEntity.setDurationOfVaccine(vaccine.getdurationOfVaccine());
        return mapToDto(this.repository.save(vaccineEntity));
    }

    public List<VaccineDto> getVaccines(){
        List<VaccineEntity> vaccines = repository.findAll();
        List<VaccineDto> result = new ArrayList<>();
        for (VaccineEntity vaccine : vaccines){
            result.add(mapToDto(vaccine));
        }
        return result;
    }

    public VaccineDto getVaccineById(Long vaccineId){
        Optional<VaccineEntity> vaccine = repository.findById(vaccineId);
        return vaccine.map(this::mapToDto).orElse(null);
    }

    public VaccineDto updateVaccine(Long vaccineId, VaccineDto vaccine){
        Optional<VaccineEntity> v = repository.findById(vaccineId);
        if(v.isPresent()){
            v.get().setName(vaccine.getName());
            v.get().setType(vaccine.getType());
            v.get().setAmountOfVaccines(vaccine.getAmountOfVaccines());
            // TODO implement full update
            //v.get().setAmountToCompleteVaccination(vaccine.getAmountToCompleteVaccination());
            //v.get().setDaysToFullVaccination(vaccine.getDaysToFullVaccination());
            return mapToDto(repository.save(v.get()));
        }
        return null;
    }

    public void deleteVaccine(Long vaccineId){
        repository.deleteById(vaccineId);
    }

    public VaccineEntity getEntityById(Long vaccineId) {
        Optional<VaccineEntity> vaccine = repository.findById(vaccineId);
        return vaccine.orElse(null);
    }

    private VaccineDto mapToDto(VaccineEntity entity){
        VaccineDto vaccineDto = new VaccineDto();
        vaccineDto.setId(entity.getId());
        vaccineDto.setName(entity.getName());
        vaccineDto.setType(entity.getType());
        vaccineDto.setAmountOfVaccines(entity.getAmountOfVaccines());
        vaccineDto.setAmountToCompleteVaccination(entity.getAmountToCompleteVaccination());
        vaccineDto.setDaysToFullVaccination(entity.getDaysToFullVaccination());
        vaccineDto.setdurationOfVaccine(entity.getDurationOfVaccine());
        return vaccineDto;
    }
}
