package su.ANV.units.AI;

import su.ANV.exeptions.NoVariantsException;
import su.ANV.exeptions.NotAIIDException;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class AI{

    public static Long giveAI(int level) {
        return -1L;
    }

    public static int ai(char[] content, Long id) throws NotAIIDException, NoVariantsException {
        if (id >= 0L) {
            throw new NotAIIDException("Этот игрок должнод быть человек");
        } else if (id == -1) {
            return randomStep(content);
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
}
