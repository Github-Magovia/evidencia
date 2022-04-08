package githubmagovia.ockovanie.evidencia.domain.repositories;

import githubmagovia.ockovanie.evidencia.entity.VaccinationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VaccinationRepository extends JpaRepository<VaccinationEntity, Long> {
}
