package su.ANV.entities;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPlayGroundId() {
        return playGroundId;
    }

    public void setPlayGroundId(Long playGroundId) {
        this.playGroundId = playGroundId;
    }

    public Long getPlayGroundKey() {
        return playGroundKey;
    }

    public void setPlayGroundKey(Long playGroundKey) {
        this.playGroundKey = playGroundKey;
    }

    public Long getPlayerId() {
        return playerId;
    }

    public void setPlayerId(Long playerId) {
        this.playerId = playerId;
    }

    public Long getPlayerKey() {
        return playerKey;
    }

    public void setPlayerKey(Long playerKey) {
        this.playerKey = playerKey;
    }

    public int getCell() {
        return cell;
    }

    public void setCell(int cell) {
        this.cell = cell;
    }

    public int getStepNo() {
        return stepNo;
    }

    public void setStepNo(int stepNo) {
        this.stepNo = stepNo;
    }

    public char getSign() {
        return sign;
    }

    public void setSign(char sign) {
        this.sign = sign;
    }

    @Override
    public String toString() {
        return "StepEntity{" +
                "id=" + id +
                ", playGroundId=" + playGroundId +
                ", playGroundKey=" + playGroundKey +
                ", playerId=" + playerId +
                ", playerKey=" + playerKey +
                ", cell=" + cell +
                ", stepNo=" + stepNo +
                ", sign=" + sign +
                '}';
    }
}
