package githubmagovia.ockovanie.evidencia.person;

import githubmagovia.ockovanie.evidencia.person.models.PersonEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends JpaRepository<PersonEntity, Long> {
}
