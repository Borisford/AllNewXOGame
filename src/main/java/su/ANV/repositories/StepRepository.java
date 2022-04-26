package su.ANV.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import su.ANV.entities.StepEntity;

@Repository
public interface StepRepository extends JpaRepository<StepEntity, Long> {
}
