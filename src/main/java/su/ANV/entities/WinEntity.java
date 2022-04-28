package su.ANV.entities;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
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

    public Long getWinnerId() {
        return winnerId;
    }

    public void setWinnerId(Long winnerId) {
        this.winnerId = winnerId;
    }

    @Override
    public String toString() {
        return "WinEntity{" +
                "id=" + id +
                ", playGroundId=" + playGroundId +
                ", winnerId=" + winnerId +
                '}';
    }
}
