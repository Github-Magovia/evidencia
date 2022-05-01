package githubmagovia.ockovanie.evidencia.term;

import githubmagovia.ockovanie.evidencia.term.models.TermEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TermRepository extends JpaRepository<TermEntity, Long> {
}
