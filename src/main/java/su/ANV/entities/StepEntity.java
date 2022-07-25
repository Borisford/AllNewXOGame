package su.ANV.entities;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
public class StepEntity {
    @Id
    @GenericGenerator(name = "strepGenerator", strategy = "random")
    @GeneratedValue
    private Long id;

    private Long playGroundId;
    private Long playGroundKey;
    private Long playerId;
    private Long playerKey;
    private int cell;
    private int stepNo;
    private char sign;

    public StepEntity() {
    }

    public StepEntity(Long playGroundId, Long playGroundKey, Long playerId, Long playerKey, int cell, int stepNo, char sign) {
        this.playGroundId = playGroundId;
        this.playGroundKey = playGroundKey;
        this.playerId = playerId;
        this.playerKey = playerKey;
        this.cell = cell;
        this.stepNo = stepNo;
        this.sign = sign;
    }
}
