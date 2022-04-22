package githubmagovia.ockovanie.evidencia.lottery;

import githubmagovia.ockovanie.evidencia.lottery.dto.LotteryDto;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class LotteryController {
    private final LotteryService service;

    public LotteryController(LotteryService service){
        this.service = service;
    }

    @GetMapping("/api/lottery")
    public List<LotteryDto> getWinners(){
        return service.getPeople();
    }
}
