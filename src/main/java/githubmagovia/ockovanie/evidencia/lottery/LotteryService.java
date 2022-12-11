package githubmagovia.ockovanie.evidencia.lottery;


import githubmagovia.ockovanie.evidencia.exceptions.ServerException;
import githubmagovia.ockovanie.evidencia.lottery.dto.LotteryDto;
import githubmagovia.ockovanie.evidencia.lottery.models.LotteryEntity;
import githubmagovia.ockovanie.evidencia.person.PersonService;
import githubmagovia.ockovanie.evidencia.person.models.PersonEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@EnableScheduling
@Service
@Slf4j
public class LotteryService {
    private final LotteryRepository repository;
    private final PersonService personService;

    private final RabbitTemplate template;

    public LotteryService(LotteryRepository repository, PersonService personService, RabbitTemplate template) {
        this.repository = repository;
        this.personService = personService;
        this.template = template;
    }

    // every 30 min
    @Scheduled(fixedRate = 1800000)
    public void addWinner(){
        LotteryEntity lottery = new LotteryEntity();
        List<PersonEntity> candidates = personService.getAllFullyVaccinated();
        if(!candidates.isEmpty()){
            lottery.setPerson(candidates.get(ThreadLocalRandom.current().nextInt(0, candidates.size())));
            lottery.setAmount(10000);
            lottery.setDate(LocalDate.now());
            this.repository.save(lottery);
//            template.convertAndSend("emailQueue",
//                    String.format("V dnesnej loterii vyhrava: %s %s%nSuma vyhry je: %s",
//                            lottery.getPerson().getFirstName(),
//                            lottery.getPerson().getLastName(),
//                            lottery.getAmount()));
        } else {
            log.error("No valid candidates for lottery");
//            template.convertAndSend("emailQueue",
//                    String.format("V dnesnej loterii nikto nevyhral%nSuma vyhry bola: %s",
//                            10000));
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
