package su.ANV.entities;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
public class WinEntity {
    @Id
    @GenericGenerator(name = "generator", strategy = "random")
    @GeneratedValue
    private Long id;

    private Long playGroundId;
    private Long winnerId;

    public WinEntity() {
    }

    public WinEntity(Long playGroundId, Long winnerId) {
        this.playGroundId = playGroundId;
        this.winnerId = winnerId;
    }
}
