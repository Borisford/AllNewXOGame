package su.ANV.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import su.ANV.exeptions.*;
import su.ANV.subEntities.PlayGroundLogic;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Arrays;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class PlayGroundEntity implements PlayGroundLogic {

    @Id
    @GenericGenerator(name = "generator", strategy = "random")
    @GeneratedValue
    private Long id;

    private Long playGroundKey;
    private char[] content;
    private int stepNo;
    private Long[] playerIDs;

    public PlayGroundEntity(Long playGroundKey) throws BadNumberOfPlayersException, BadPlaygroundSideException {
        //new PlayGroundEntity(playGroundKey, 3, 2);
        this.playGroundKey = playGroundKey;
        this.content = new char[9];
        for (int i = 0; i < 9; i++) {
            this.content[i] = 0;
        }
        this.stepNo = 1;
        this.playerIDs = new Long[2];
        for (int i = 0; i < 2; i++) {
            this.playerIDs[i] = 0L;
        }
    }

    public PlayGroundEntity(Long playGroundKey, int side, int maxPlayers) throws BadPlaygroundSideException, BadNumberOfPlayersException {
        incomingValid(side, maxPlayers);
        this.playGroundKey = playGroundKey;
        int tmp = side * side;
        this.content = new char[tmp];
        for (int i = 0; i < tmp; i++) {
            this.content[i] = 0;
        }
        this.stepNo = 1;
        this.playerIDs = new Long[maxPlayers];
        for (int i = 0; i < maxPlayers; i++) {
            this.playerIDs[i] = 0L;
        }
    }

    private void incomingValid(int side, int maxPlayers) throws BadPlaygroundSideException, BadNumberOfPlayersException {
        if (side < 3 || side > 50) {
            throw new BadPlaygroundSideException("Сторна игрового поля " +side+ " не находится в диапозоне от 3 до 50 включительно.");
        }
        if (maxPlayers < 2 || maxPlayers > signs.length || maxPlayers > side) {
            throw new BadNumberOfPlayersException("Колличество игроков" +maxPlayers+ " не находится в диапозоне от 2 до 7 включительно, или больше стороны поля " +side);
        }
    }
    public void nextStep() {
        this.stepNo = this.stepNo + 1;
    }
}
