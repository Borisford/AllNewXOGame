package su.ANV.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
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


    public StepEntity(Long playGroundId, Long playGroundKey, Long playerId, Long playerKey, int cell, int stepNo, char sign) {
        this(null, playGroundId, playGroundKey, playerId, playerKey, cell, stepNo, sign);
    }
}
