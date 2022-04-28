package su.ANV.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import su.ANV.entities.PlayGroundEntity;
import su.ANV.entities.WinEntity;

@Repository
public interface WinRepository extends JpaRepository<WinEntity, Long> {
    WinEntity findByPlayGroundId(Long playGroundId);
    boolean existsByPlayGroundId(Long playGroundId);
}
