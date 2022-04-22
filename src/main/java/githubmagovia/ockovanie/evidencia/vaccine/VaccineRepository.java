package githubmagovia.ockovanie.evidencia.vaccine;

import githubmagovia.ockovanie.evidencia.vaccine.models.VaccineEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VaccineRepository extends JpaRepository<VaccineEntity, Long> {

}
