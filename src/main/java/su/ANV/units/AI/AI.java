package su.ANV.units.AI;

import su.ANV.entities.PlayGroundEntity;
import su.ANV.exeptions.NoVariantsException;
import su.ANV.exeptions.NotAIIDException;
import su.ANV.subEntities.PlayGroundLogic;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class AI{

    public static String[] aiNames =  {"randomStep", "linesStep"};

    public static String getAIName (Long ID) {
        int nameNo = Math.toIntExact(ID) * ( - 1) - 1;
        if (nameNo < aiNames.length) {
            return aiNames[nameNo];
        }
        return null;
    }

    public static Long giveAI (int level) {
        return (long) ((new Random().nextInt(2) + 1) * (-1));
    }

    public static int ai (char[] content, Long id, char sign) throws NotAIIDException, NoVariantsException {
        if (id >= 0L) {
            throw new NotAIIDException("Этот игрок должнод быть человек");
        } else if (id == -1) {
            return randomStep(content);
        } else if (id == -2) {
            return linesStep(content, sign);
        } else {
            return randomStep(content);
        }
    }

    public static int randomStep(char[] content) throws NoVariantsException {
        List<Integer> variants = new LinkedList<>();
        for (int i = 0; i < content.length; i++) {
            if (content[i] == 0) {
                variants.add(i);
            }
        }
        if (variants.size() == 0) {
            throw new NoVariantsException("Некуда ходить");
        }
        return variants.get(new Random().nextInt(variants.size()));
    }

    public static int linesStep(char[] content, char sign){
        int[] chance = new int[content.length];
        for (int i = 0; i < chance.length; i++) {
            if (content[i] == 0 ) {
                chance[i] = 1;
            } else {
                chance[i] = 0;
            }
        }
        int side = PlayGroundLogic.getSide(content);
        rowsAndColumns(content, chance, side, sign);
        return chanceMax(chance);
    }

    private static int chanceMax(int[] chance) {
        int maxChance = -1;
        int maxChanceNum = 0;
        for (int i = 0; i < chance.length; i++) {
            if (chance[i] > maxChance) {
                maxChance = chance[i];
                maxChanceNum = i;
            }
        }
        return maxChanceNum;
    }

    private static void rowsAndColumns(char[] content, int[] chance, int side, char target) {
        for (int i = 1; i < side; i++) {
            if (row(content, i, side, target)) {
                doRow(content, chance, i, side);
            }
            if (column(content, i, side, target)) {
                doColumn(content, chance, i, side);
            }
        }
        diagonal1(content, chance, side, target);
        diagonal2(content, chance, side, target);
    }

    private static boolean row(char[] content, int rowNum, int side, char target) {
        for (int i = 1; i < side; i++) {
            if (content[side * rowNum + i] != target && content[side * rowNum + i] != 0) {
                return false;
            }
        }
        return true;
    }

    private static void doRow(char[] content, int[] chance, int rowNum, int side) {
        for (int i = 1; i < side; i++) {
            if (content[side * rowNum + i] == 0) {
                chance[side * rowNum + i] += 1;
            }
        }
    }

    private static boolean column(char[] content, int columnNum, int side, char target) {
        for (int i = 1; i < side; i++) {
            if (content[columnNum + side * i] != target && content[columnNum + side * i] != 0) {
                return false;
            }
        }
        return true;
    }

    private static void doColumn(char[] content, int[] chance, int columnNum, int side) {
        for (int i = 1; i < side; i++) {
            if (content[columnNum + side * i] == 0) {
                chance[columnNum + side * i] += 1;
            }
        }
    }

    private static void diagonal1(char[] content, int[] chance, int side, char target) {
        for (int i = 1; i < side; i++) {
            if (content[i + side * i] != target && content[i + side * i] != 0) {
                return;
            }
        }
        for (int i = 1; i < side; i++) {
            if (content[i + side * i] == 0) {
                chance[i + side * i] += 1;
            }
        }
    }

    private static void diagonal2(char[] content, int[] chance, int side, char target) {
        for (int i = 1; i < side; i++) {
            if (content[side * (i + 1) - i - 1] != target && content[side * (i + 1) - i - 1] != 0) {
                return;
            }
        }
        for (int i = 1; i < side; i++) {
            if (content[side * (i + 1) - i - 1] == 0) {
                chance[side * (i + 1) - i - 1] += 1;
            }
        }
    }
}
