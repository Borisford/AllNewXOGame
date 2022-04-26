package su.ANV.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import su.ANV.entities.PlayGroundEntity;


@Repository
public interface PlayGroundRepository extends JpaRepository<PlayGroundEntity, Long> {
    PlayGroundEntity findByPlayGroundKey(Long playGroundKey);
    boolean existsByPlayGroundKey(Long playGroundKey);
}
