import java.util.Random;

public class RandomName {
    public static String get() {
        Random random = new Random();
        String res = "";
        res += (char) ('A' + random.nextInt(26));
        int num = random.nextInt(5) + 3;
        for (int i = 0; i < num; i++) {
            res += (char) ('a' + random.nextInt(26));
        }

        return res;
    }
}
