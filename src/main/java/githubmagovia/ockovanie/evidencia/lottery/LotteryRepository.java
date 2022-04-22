package githubmagovia.ockovanie.evidencia.lottery;

import githubmagovia.ockovanie.evidencia.lottery.models.LotteryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LotteryRepository extends JpaRepository<LotteryEntity, Long> {
}
