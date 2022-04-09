package githubmagovia.ockovanie.evidencia.domain.repositories;

import githubmagovia.ockovanie.evidencia.entity.VaccineEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VaccineRepository extends JpaRepository<VaccineEntity, Long> {

}
