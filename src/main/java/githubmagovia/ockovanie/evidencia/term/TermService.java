package githubmagovia.ockovanie.evidencia.term;

import githubmagovia.ockovanie.evidencia.term.dto.TermDto;
import githubmagovia.ockovanie.evidencia.term.models.TermEntity;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@EnableScheduling
@Service
public class TermService {
    private final TermRepository repository;

    public TermService (TermRepository repository) {
        this.repository = repository;
    }

    public TermDto createTerm(TermDto term){
        TermEntity TermEntity = new TermEntity();
        TermEntity.setVaccinationCentre(term.getVaccinationCentre());
        TermEntity.setDateOfVaccination(term.setDateOfVaccination());
        return mapToDto(this.repository.save(TermEntity));
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
            t.get().setDateOfVaccination();
            return mapToDto(repository.save(t.get()));
        }
        return null;
    }

    public void deleteTerm(Long termId){
        repository.deleteById(termId);
    }

    @Scheduled(fixedRate = 86400000)
    public void addVaccination(){
        TermEntity term = new TermEntity();
    }


    private TermDto mapToDto(TermEntity entity){
        TermDto TermDto = new TermDto();
        TermDto.setId(entity.getId());
        TermDto.setVaccinationCentre(entity.getVaccinationCentre());
        TermDto.setDateOfVaccination(entity.getDateOfVaccination());
        return TermDto;
    }
}
