package su.ANV.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import su.ANV.entities.PlayerEntity;


@Repository
public interface PlayerRepository extends JpaRepository<PlayerEntity, Long> {
    PlayerEntity findByName(String name);
    PlayerEntity findByPlayerKey(Long playerKey);
    boolean existsByName(String name);
    boolean existsByPlayerKey(Long playerKey);
}
