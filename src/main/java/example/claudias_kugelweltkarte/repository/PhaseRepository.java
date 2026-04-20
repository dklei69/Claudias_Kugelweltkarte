package example.claudias_kugelweltkarte.repository;

import example.claudias_kugelweltkarte.model.Phase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhaseRepository extends JpaRepository<Phase, Long> {
    Phase findByPhaseNumber(int phaseNumber);
}
