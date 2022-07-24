package su.ANV.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.GenericGenerator;
import su.ANV.exeptions.*;
import su.ANV.subEntities.PlayGroundLogic;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Arrays;

@Entity
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

    public PlayGroundEntity() {
    }

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

    public PlayGroundEntity(Long id, Long playGroundKey, char[] content, int stepNo, Long[] playerIDs) {
        this.id = id;
        this.playGroundKey = playGroundKey;
        this.content = content;
        this.stepNo = stepNo;
        this.playerIDs = playerIDs;
    }

    private void incomingValid(int side, int maxPlayers) throws BadPlaygroundSideException, BadNumberOfPlayersException {
        if (side < 3 || side > 50) {
            throw new BadPlaygroundSideException("Сторна игрового поля " +side+ " не находится в диапозоне от 3 до 50 включительно.");
        }
        if (maxPlayers < 2 || maxPlayers > signs.length || maxPlayers > side) {
            throw new BadNumberOfPlayersException("Колличество игроков" +maxPlayers+ " не находится в диапозоне от 2 до 7 включительно, или больше стороны поля " +side);
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPlayGroundKey() {
        return playGroundKey;
    }

    public void setPlayGroundKey(Long playGroundKey) {
        this.playGroundKey = playGroundKey;
    }

    public char[] getContent() {
        return content;
    }

    public void setContent(char[] content) {
        this.content = content;
    }

    public int getStepNo() {
        return stepNo;
    }

    public void setStepNo(int stepNo) {
        this.stepNo = stepNo;
    }

    public void nextStep() {
        this.stepNo = this.stepNo + 1;
    }

    public Long[] getPlayerIDs() {
        return playerIDs;
    }

    public void setPlayerIDs(Long[] playerIDs) {
        this.playerIDs = playerIDs;
    }

    @Override
    public String toString() {
        return "PlayGroundEntity{" +
                "id=" + id +
                ", playGroundKey=" + playGroundKey +
                ", content=" + Arrays.toString(content) +
                ", stepNo=" + stepNo +
                ", players=" + Arrays.toString(playerIDs) +
                '}';
    }
}
