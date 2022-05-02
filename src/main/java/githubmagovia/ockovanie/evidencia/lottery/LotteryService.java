package githubmagovia.ockovanie.evidencia.lottery;


import githubmagovia.ockovanie.evidencia.lottery.dto.LotteryDto;
import githubmagovia.ockovanie.evidencia.lottery.models.LotteryEntity;
import githubmagovia.ockovanie.evidencia.person.PersonService;
import githubmagovia.ockovanie.evidencia.person.models.PersonEntity;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@EnableScheduling
@Service
public class LotteryService {
    private final LotteryRepository repository;
    private final PersonService personService;

    public LotteryService(LotteryRepository repository, PersonService service) {
        this.personService = service;
        this.repository = repository;
    }

    // every 30 minutes
    @Scheduled(fixedRate = 1800000)
    public void addWinner(){
        LotteryEntity lottery = new LotteryEntity();
        List<PersonEntity> candidates = personService.getAllFullyVaccinated();
        if(!candidates.isEmpty()){
        lottery.setPerson(candidates.get(ThreadLocalRandom.current().nextInt(0, candidates.size())));
        lottery.setAmount(10000);
        lottery.setDate(LocalDate.now());
        this.repository.save(lottery);
        }
    }
    public List<LotteryDto> getPeople(){
        List<LotteryEntity> entities = repository.findAll();
        List<LotteryDto> result = new ArrayList<>();
        for (LotteryEntity entity : entities) {
            result.add(mapToDto(entity));
        }
        return result;
    }

    private LotteryDto mapToDto(LotteryEntity entity){
        LotteryDto dto = new LotteryDto();
        dto.setId(entity.getId());
        dto.setFirstName(entity.getPerson().getFirstName());
        dto.setLastName(entity.getPerson().getLastName());
        dto.setDate(entity.getDate());
        dto.setAmount(entity.getAmount());
        return dto;
    }
}
