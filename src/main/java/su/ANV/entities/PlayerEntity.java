package su.ANV.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import su.ANV.exeptions.NoVariantsException;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlayerEntity {
    @Id
    @GenericGenerator(name = "generator", strategy = "random")
    @GeneratedValue
    private Long id;
    private Long playerKey;
    private String name;

    public PlayerEntity(Long playerKey, String name) {
        this(null, playerKey, name);
    }
}
