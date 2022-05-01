package githubmagovia.ockovanie.evidencia.term;

import githubmagovia.ockovanie.evidencia.term.dto.TermDto;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
    public class TermController {
        private final TermService service;

        public TermController(TermService service){
            this.service = service;
        }

        @PostMapping("/api/terms")
        public TermDto createTerm(@RequestBody TermDto term){
            return service.createTerm(term);
        }

        @GetMapping("/api/terms")
        public List<TermDto> getTerms(){
            return service.getTerms();
        }

        @GetMapping("/api/terms/{termId}")
        public TermDto getTermById(@PathVariable Long termId){
            return service.getTermById(termId);
        }

        @PutMapping("/api/terms/{termId}")
        public TermDto updateTerm(@PathVariable Long termId, @RequestBody TermDto term){
            return service.updateTerm(termId,term);
        }

        @DeleteMapping("/api/terms/{termId}")
        public void deleteTerm(@PathVariable Long termId){
            service.deleteTerm(termId);
        }
}
