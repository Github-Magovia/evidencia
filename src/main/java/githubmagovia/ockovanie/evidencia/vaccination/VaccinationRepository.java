package githubmagovia.ockovanie.evidencia.vaccination;

import githubmagovia.ockovanie.evidencia.person.models.PersonEntity;
import githubmagovia.ockovanie.evidencia.vaccination.models.VaccinationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VaccinationRepository extends JpaRepository<VaccinationEntity, Long> {

    List<VaccinationEntity> findAllByPersonEquals(PersonEntity person);

    VaccinationEntity findFirstByPersonEqualsOrderByShotNumberDesc(PersonEntity person);
}
