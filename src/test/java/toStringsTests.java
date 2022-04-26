import org.junit.jupiter.api.Test;
import org.testng.Assert;
import su.ANV.entities.PlayGroundEntity;
import su.ANV.exeptions.BadNumberOfPlayersException;
import su.ANV.exeptions.BadPlaygroundSideException;
import su.ANV.exeptions.IncorrectSignException;
import su.ANV.exeptions.NoCellException;

import java.util.Random;

public class toStringsTests {
    @Test
    public void nobody3() {
        try {
            PlayGroundEntity playGroundEntity = new PlayGroundEntity(new Random().nextLong());
            char[] tmp = new char[] {   0, 0, 0,
                    0, 0, 0,
                    0, 0, 0};
            playGroundEntity.setContent(tmp);
            String[] target = {"[-|-|-]","[-|-|-]","[-|-|-]"};
            String[] result = playGroundEntity.getStrings();
            for (int i = 0; i < result.length; i++) {
                Assert.assertEquals(result[i], target[i]);
            }
        } catch (BadNumberOfPlayersException | BadPlaygroundSideException | NoCellException | IncorrectSignException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void row13() {
        try {
            PlayGroundEntity playGroundEntity = new PlayGroundEntity(new Random().nextLong());
            char[] tmp = new char[] {   0, 0, 0,
                    'x', 'x', 'x',
                    0, 0, 0};
            playGroundEntity.setContent(tmp);
            String[] target = {"[-|-|-]","[x|x|x]","[-|-|-]"};
            String[] result = playGroundEntity.getStrings();
            for (int i = 0; i < result.length; i++) {
                Assert.assertEquals(result[i], target[i]);
            }
        } catch (BadNumberOfPlayersException | BadPlaygroundSideException | NoCellException | IncorrectSignException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void column04() {
        PlayGroundEntity playGroundEntity = null;
        try {
            playGroundEntity = new PlayGroundEntity(new Random().nextLong());
            char[] tmp = new char[] {   'x', 0, 0, 0,
                    0, 'x', 0, 0,
                    0, 0, 'x', 0,
                    0, 0, 0, 'x'};
            playGroundEntity.setContent(tmp);
            String[] target = {"[x|-|-|-]","[-|x|-|-]","[-|-|x|-]","[-|-|-|x]"};
            String[] result = playGroundEntity.getStrings();
            for (int i = 0; i < result.length; i++) {
                Assert.assertEquals(result[i], target[i]);
            }
        } catch (BadNumberOfPlayersException | BadPlaygroundSideException | NoCellException | IncorrectSignException e) {
            e.printStackTrace();
        }
    }

}
