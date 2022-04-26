package su.ANV.entities;

import org.hibernate.annotations.GenericGenerator;
import su.ANV.exeptions.NoVariantsException;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class PlayerEntity {
    @Id
    @GenericGenerator(name = "generator", strategy = "random")
    @GeneratedValue
    private Long id;
    private Long playerKey;
    private String name;

    public PlayerEntity() {
    }

    public PlayerEntity(Long playerKey, String name) {
        this.playerKey = playerKey;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPlayerKey() {
        return playerKey;
    }

    public void setPlayerKey(Long playerKey) {
        this.playerKey = playerKey;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "PlayerEntity{" +
                "id=" + id +
                ", playerKey=" + playerKey +
                ", name='" + name + '\'' +
                '}';
    }
}
