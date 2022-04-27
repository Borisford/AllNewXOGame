package su.ANV.entities;

import org.hibernate.annotations.GenericGenerator;
import su.ANV.exeptions.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Arrays;

@Entity
//@JsonIgnoreProperties(ignoreUnknown = true)
public class PlayGroundEntity {

    @Id
    @GenericGenerator(name = "generator", strategy = "random")
    @GeneratedValue
    private Long id;

    private Long playGroundKey;
    private char[] content;
    private int stepNo;
    private Long[] playerIDs;
    private static final char[] signs = {'x', '*', '@', '#', '$', '&'};

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

    public char[] getContentClone() {
        return this.content.clone();
    }

    private boolean fieldIsFull() {
        for (char tmp : this.content) {
            if (tmp == 0) {
                return false;
            }
        }
        return true;
    }

    public void setSign(Long id, int cell) throws NoCellException, NotEmptyCellException, NoPlayerInGameException {
        if (cell >= this.content.length) {
            throw new NoCellException("В игре нет такой ячейки");
        }
        if (this.content[cell] != 0) {
            throw new NotEmptyCellException("Ячейка занята");
        }
        char sign;
        if (id > 0) {
            sign = getPlayersSignByID(id);
        } else {
            sign = getPlayersSignByStepNo(id);
        }
        this.content[cell] = sign;
        nextStep();
    }

    public char getPlayersSignByStepNo(Long id) {
        int tmp = (this.stepNo - 1) % this.playerIDs.length;
        if (this.playerIDs[tmp].equals(id)) {
            return signs[tmp];
        } else {
            return '-';
        }
    }

    public Long getPlayersIDBySign(char sign) {
        for (int i = 0; i < this.playerIDs.length; i++) {
            if (signs[i] == sign) {
                return this.playerIDs[i];
            }
        }
        return 0L;
    }

    private char getSign(int cell) throws NoCellException{
        if (cell >= this.content.length) {
            throw new NoCellException("В игре нет такой ячейки");
        }
        char sign = this.content[cell];
        if (sign == 0) {
            return '-';
        }
        return sign;
    }

    private static char getSign(int cell, char[] content) throws NoCellException{
        if (cell >= content.length) {
            throw new NoCellException("В игре нет такой ячейки");
        }
        char sign = content[cell];
        if (sign == 0) {
            return '-';
        }
        return sign;
    }

    public int getSide() {
        return (int) Math.sqrt(this.content.length);
    }

    public static int getSide(char[] content) {
        return (int) Math.sqrt(content.length);
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

    public boolean thisPlayersStep(Long id) {
        int tmp = this.stepNo % this.playerIDs.length;
        return this.playerIDs[tmp].equals(id);
    }

    public boolean isNextPlayerAI() {
        int tmp = this.stepNo % this.playerIDs.length;
        return this.playerIDs[tmp] < 0L;
    }

    public boolean isThisPlayerAI() {
        int tmp = (this.stepNo - 1) % this.playerIDs.length;
        return this.playerIDs[tmp] < 0L;
    }

    public Long getCurrentPlayersID() {
        int tmp = (this.stepNo - 1) % this.playerIDs.length;
        return this.playerIDs[tmp];
    }

    public Long[] getPlayerIDs() {
        return playerIDs;
    }

    public void setPlayerIDs(Long[] playerIDs) {
        this.playerIDs = playerIDs;
    }

    public int getMaxPlayers() {
        return this.playerIDs.length;
    }

    public boolean addPlayerID(Long playerID) {
        for (int i = 0; i < this.playerIDs.length; i++) {
            if (this.playerIDs[i].equals(0L)) {
                this.playerIDs[i] = playerID;
                return true;
            }
        }
        return false;
    }

    public char getPlayersSignByID(Long id) throws NoPlayerInGameException {
        for (int i = 0; i < this.playerIDs.length; i++) {
            if (this.playerIDs[i].equals(id)) {
                return signs[i];
            }
        }
        throw new NoPlayerInGameException("В игре нет игрока с таким ID");
    }

    public boolean playerIsIn(Long playerID) {
        if (playerID < 0) {
            return false;
        }
        for (Long aLong : this.playerIDs) {
            if (aLong.equals(playerID)) {
                return true;
            }
        }
        return false;
    }

    public boolean gameIsFull() {
        for (Long aLong : this.playerIDs) {
            if (aLong.equals(0L)) {
                return false;
            }
        }
        return true;
    }

    public char whoWin() {
        int side = this.getSide();
        char tmp;
        tmp = rows(side);
        if (tmp != 0) {
            return tmp;
        }
        tmp = columns(side);
        if (tmp != 0) {
            return tmp;
        }
        tmp = diag1(side);
        if (tmp != 0) {
            return tmp;
        }
        tmp = diag2(side);
        if (tmp != 0) {
            return tmp;
        }
        if (fieldIsFull()) {
            return 1;
        }
        return 0;
    }

    private char rows(int side) {
        char tmp;
        for (int i = 0; i < side; i++) {
            tmp = row(i, side);
            if (tmp != 0) {
                return tmp;
            }
        }
        return 0;
    }

    private char row(int rowNum, int side) {
        char target = this.content[side * rowNum];
        for (int i = 1; i < side; i++) {
            if (this.content[side * rowNum + i] != target) {
                return 0;
            }
        }
        return target;
    }

    private char columns(int side) {
        char tmp;
        for (int i = 0; i < side; i++) {
            tmp = column(i, side);
            if (tmp != 0) {
                return tmp;
            }
        }
        return 0;
    }

    private char column(int columnNum, int side) {
        char target = this.content[columnNum];
        for (int i = 1; i < side; i++) {
            if (this.content[columnNum + side * i] != target) {
                return 0;
            }
        }
        return target;
    }

    private char diag1(int side) {
        char target = this.content[0];
        for (int i = 1; i < side; i++) {
            if (this.content[i + side * i] != target) {
                return 0;
            }
        }
        return target;
    }

    private char diag2(int side) {
        char target = this.content[side - 1];
        for (int i = 1; i < side; i++) {
            if (this.content[side * (i + 1) - i - 1] != target) {
                return 0;
            }
        }
        return target;
    }

    public static String[] getStrings(char[] content) throws NoCellException {
        int side = getSide(content);
        String[] res = new String[side];
        for (int i = 0; i < side; i++) {
            res[i] = "";
            res[i] += '[';
            res[i] += getSign(i * side, content);
            for (int j = 1; j < side; j++) {
                res[i] += '|';
                res[i] += getSign(i * side + j, content);
            }
            res[i] += ']';
        }
        return res;
    }

    public String[] getStrings() throws NoCellException, IncorrectSignException {
        int side = this.getSide();
        String[] res = new String[side];
        for (int i = 0; i < side; i++) {
            res[i] = "";
            res[i] += '[';
            res[i] += getSign(i * side);
            for (int j = 1; j < side; j++) {
                res[i] += '|';
                res[i] += getSign(i * side + j);
            }
            res[i] += ']';
        }
        return res;
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
