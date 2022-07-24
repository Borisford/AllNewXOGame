package su.ANV.subEntities;

import su.ANV.entities.PlayGroundEntity;
import su.ANV.exeptions.IncorrectSignException;
import su.ANV.exeptions.NoCellException;
import su.ANV.exeptions.NoPlayerInGameException;
import su.ANV.exeptions.NotEmptyCellException;

public interface PlayGroundLogic {

    public static final char[] signs = {'x', '*', '@', '#', '$', '&'};


    public static int getSide(char[] content) {
        return (int) Math.sqrt(content.length);
    }

    public static char whoWin(char[] content) {
        int side = getSide(content);
        char tmp;
        tmp = rows(side, content);
        if (tmp != 0) {
            return tmp;
        }
        tmp = columns(side, content);
        if (tmp != 0) {
            return tmp;
        }
        tmp = diagonal1(side, content);
        if (tmp != 0) {
            return tmp;
        }
        tmp = diagonal2(side, content);
        if (tmp != 0) {
            return tmp;
        }
        if (fieldIsFull(content)) {
            return 1;
        }
        return 0;
    }

    private static char rows(int side, char[] content) {
        char tmp;
        for (int i = 0; i < side; i++) {
            tmp = row(i, side, content);
            if (tmp != 0) {
                return tmp;
            }
        }
        return 0;
    }

    private static char row(int rowNum, int side, char[] content) {
        char target = content[side * rowNum];
        for (int i = 1; i < side; i++) {
            if (content[side * rowNum + i] != target) {
                return 0;
            }
        }
        return target;
    }

    private static char columns(int side, char[] content) {
        char tmp;
        for (int i = 0; i < side; i++) {
            tmp = column(i, side, content);
            if (tmp != 0) {
                return tmp;
            }
        }
        return 0;
    }

    private static char column(int columnNum, int side, char[] content) {
        char target = content[columnNum];
        for (int i = 1; i < side; i++) {
            if (content[columnNum + side * i] != target) {
                return 0;
            }
        }
        return target;
    }

    private static char diagonal1(int side, char[] content) {
        char target = content[0];
        for (int i = 1; i < side; i++) {
            if (content[i + side * i] != target) {
                return 0;
            }
        }
        return target;
    }

    private static char diagonal2(int side, char[] content) {
        char target = content[side - 1];
        for (int i = 1; i < side; i++) {
            if (content[side * (i + 1) - i - 1] != target) {
                return 0;
            }
        }
        return target;
    }

    private static boolean fieldIsFull(char[] content) {
        for (char tmp : content) {
            if (tmp == 0) {
                return false;
            }
        }
        return true;
    }

    public static void setSign(Long id, int cell, PlayGroundEntity playGroundEntity) throws NoCellException, NotEmptyCellException, NoPlayerInGameException {
        char[] content = playGroundEntity.getContent();
        if (cell >= content.length || cell < 0) {
            throw new NoCellException("В игре нет такой ячейки");
        }
        if (content[cell] != 0) {
            throw new NotEmptyCellException("Ячейка занята");
        }
        char sign;
        if (id > 0) {
            sign = getPlayersSignByID(id, playGroundEntity);
        } else {
            sign = getPlayersSignByStepNo(id, playGroundEntity);
        }
        content[cell] = sign;
        playGroundEntity.nextStep();
    }

    public static char getPlayersSignByStepNo(Long id, PlayGroundEntity playGroundEntity) {
        int tmp = (playGroundEntity.getStepNo() - 1) % playGroundEntity.getPlayerIDs().length;
        if (playGroundEntity.getPlayerIDs()[tmp].equals(id)) {
            return signs[tmp];
        } else {
            return '-';
        }
    }

    public static char getPlayersSignByID(Long id, PlayGroundEntity playGroundEntity) throws NoPlayerInGameException {
        for (int i = 0; i < playGroundEntity.getPlayerIDs().length; i++) {
            if (playGroundEntity.getPlayerIDs()[i].equals(id)) {
                return signs[i];
            }
        }
        throw new NoPlayerInGameException("В игре нет игрока с таким ID");
    }

    public static Long getPlayersIDBySign(char sign, PlayGroundEntity playGroundEntity) {
        for (int i = 0; i < playGroundEntity.getPlayerIDs().length; i++) {
            if (signs[i] == sign) {
                return playGroundEntity.getPlayerIDs()[i];
            }
        }
        return 0L;
    }

    private static char getSign(int cell, PlayGroundEntity playGroundEntity) throws NoCellException{
        if (cell >= playGroundEntity.getContent().length) {
            throw new NoCellException("В игре нет такой ячейки");
        }
        char sign = playGroundEntity.getContent()[cell];
        if (sign == 0) {
            return '-';
        }
        return sign;
    }

    private static String getSignNum(int cell, PlayGroundEntity playGroundEntity) throws NoCellException{
        if (cell >= playGroundEntity.getContent().length) {
            throw new NoCellException("В игре нет такой ячейки");
        }
        char sign = playGroundEntity.getContent()[cell];
        if (sign == 0) {
            return "" + cell;
        }
        return "" + sign;
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

    public static boolean isNextPlayerAI(PlayGroundEntity playGroundEntity) {
        int tmp = playGroundEntity.getStepNo() % playGroundEntity.getPlayerIDs().length;
        return playGroundEntity.getPlayerIDs()[tmp] < 0L;
    }

    public static boolean isThisPlayerAI(PlayGroundEntity playGroundEntity) {
        int tmp = (playGroundEntity.getStepNo() - 1) % playGroundEntity.getPlayerIDs().length;
        return playGroundEntity.getPlayerIDs()[tmp] < 0L;
    }

    public static Long getCurrentPlayersID(PlayGroundEntity playGroundEntity) {
        int tmp = (playGroundEntity.getStepNo() - 1) % playGroundEntity.getPlayerIDs().length;
        return playGroundEntity.getPlayerIDs()[tmp];
    }

    public static int getMaxPlayers(PlayGroundEntity playGroundEntity) {
        return playGroundEntity.getPlayerIDs().length;
    }

    public static boolean addPlayerID(Long playerID, PlayGroundEntity playGroundEntity) {
        for (int i = 0; i < playGroundEntity.getPlayerIDs().length; i++) {
            if (playGroundEntity.getPlayerIDs()[i].equals(0L)) {
                playGroundEntity.getPlayerIDs()[i] = playerID;
                return true;
            }
        }
        return false;
    }

    public static boolean playerIsIn(Long playerID, PlayGroundEntity playGroundEntity) {
        if (playerID < 0) {
            return false;
        }
        for (Long aLong : playGroundEntity.getPlayerIDs()) {
            if (aLong.equals(playerID)) {
                return true;
            }
        }
        return false;
    }

    public static boolean gameIsFull(PlayGroundEntity playGroundEntity) {
        for (Long aLong : playGroundEntity.getPlayerIDs()) {
            if (aLong.equals(0L)) {
                return false;
            }
        }
        return true;
    }



    public static String[] getStrings(char[] content) throws NoCellException {
        int side = getSide(content);
        String[] res;
        res = new String[side];
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

    public static String[] getStrings(PlayGroundEntity playGroundEntity) throws NoCellException, IncorrectSignException {
        char[] content = playGroundEntity.getContent();
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

    public static String[] getStringsNum(PlayGroundEntity playGroundEntity) throws NoCellException, IncorrectSignException {
        char[] content = playGroundEntity.getContent();
        int side = getSide(content);
        String[] res = new String[side];
        for (int i = 0; i < side; i++) {
            res[i] = "";
            res[i] += '[';
            res[i] += getSignNum(i * side, playGroundEntity);
            for (int j = 1; j < side; j++) {
                res[i] += '|';
                res[i] += getSignNum(i * side + j, playGroundEntity);
            }
            res[i] += ']';
        }
        return res;
    }
}
