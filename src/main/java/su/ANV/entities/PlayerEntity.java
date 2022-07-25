package su.ANV.entities;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import su.ANV.exeptions.NoVariantsException;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
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
}
