import org.junit.jupiter.api.Test;
import org.testng.Assert;
import su.ANV.entities.PlayGroundEntity;
import su.ANV.exeptions.BadNumberOfPlayersException;
import su.ANV.exeptions.BadPlaygroundSideException;
import su.ANV.exeptions.IncorrectSignException;
import su.ANV.exeptions.NoCellException;

import java.util.Random;

public class whoWinTest {

    @Test
    public void nobody3() {
        PlayGroundEntity playGroundEntity = new PlayGroundEntity();
        char[] tmp = new char[] {   0, 0, 0,
                                    0, 0, 0,
                                    0, 0, 0};
        playGroundEntity.setContent(tmp);
        Assert.assertEquals(playGroundEntity.whoWin(), 0);
    }

    @Test
    public void nobody4() {
        PlayGroundEntity playGroundEntity = new PlayGroundEntity();
        char[] tmp = new char[]{    0, 0, 0, 0,
                                    0, 0, 0, 0,
                                    0, 0, 0, 0,
                                    0, 0, 0, 0};
        playGroundEntity.setContent(tmp);
        Assert.assertEquals(playGroundEntity.whoWin(), 0);
    }



    @Test
    public void row03() {
        PlayGroundEntity playGroundEntity = new PlayGroundEntity();
        char[] tmp = new char[] {   '0', '0', '0',
                                    0, 0, 0,
                                    0, 0, 0};
        playGroundEntity.setContent(tmp);
        Assert.assertEquals(playGroundEntity.whoWin(), '0');
    }

    @Test
    public void row13() {
        PlayGroundEntity playGroundEntity = new PlayGroundEntity();
        char[] tmp = new char[] {   0, 0, 0,
                                    '0', '0', '0',
                                    0, 0, 0};
        playGroundEntity.setContent(tmp);
        Assert.assertEquals(playGroundEntity.whoWin(), '0');
    }

    @Test
    public void row04() {
        PlayGroundEntity playGroundEntity = new PlayGroundEntity();
        char[] tmp = new char[] {   '0', '0', '0', '0',
                                    0, 0, 0, 0,
                                    0, 0, 0, 0,
                                    0, 0, 0, 0};
        playGroundEntity.setContent(tmp);
        Assert.assertEquals(playGroundEntity.whoWin(), '0');
    }

    @Test
    public void row14() {
        PlayGroundEntity playGroundEntity = new PlayGroundEntity();
        char[] tmp = new char[]  {  0, 0, 0, 0,
                                    0, 0, 0, 0,
                                    '0', '0', '0', '0',
                                    0, 0, 0, 0};
        playGroundEntity.setContent(tmp);
        Assert.assertEquals(playGroundEntity.whoWin(), '0');
    }

    @Test
    public void column03() {
        PlayGroundEntity playGroundEntity = new PlayGroundEntity();
        char[] tmp = new char[] {   '0', 0, 0,
                                    '0', 0, 0,
                                    '0', 0, 0};
        playGroundEntity.setContent(tmp);
        Assert.assertEquals(playGroundEntity.whoWin(), '0');
    }

    @Test
    public void column13() {
        PlayGroundEntity playGroundEntity = new PlayGroundEntity();
        char[] tmp = new char[] {   0, '0', 0,
                                    0, '0', 0,
                                    0, '0', 0};
        playGroundEntity.setContent(tmp);
        Assert.assertEquals(playGroundEntity.whoWin(), '0');
    }

    @Test
    public void column04() {
        PlayGroundEntity playGroundEntity = new PlayGroundEntity();
        char[] tmp = new char[] {   '0', 0, 0, 0,
                                    '0', 0, 0, 0,
                                    '0', 0, 0, 0,
                                    '0', 0, 0, 0};
        playGroundEntity.setContent(tmp);
        Assert.assertEquals(playGroundEntity.whoWin(), '0');
    }

    @Test
    public void column14() {
        PlayGroundEntity playGroundEntity = new PlayGroundEntity();
        char[] tmp = new char[]  {  0, '0', 0, 0,
                                    0, '0', 0, 0,
                                    0, '0', 0, 0,
                                    0, '0', 0, 0};
        playGroundEntity.setContent(tmp);
        Assert.assertEquals(playGroundEntity.whoWin(), '0');
    }

    @Test
    public void giag13() {
        PlayGroundEntity playGroundEntity = new PlayGroundEntity();
        char[] tmp = new char[] {   '0', 0, 0,
                                    0, '0', 0,
                                    0, 0, '0'};
        playGroundEntity.setContent(tmp);
        Assert.assertEquals(playGroundEntity.whoWin(), '0');
    }

    @Test
    public void diag14() {
        PlayGroundEntity playGroundEntity = new PlayGroundEntity();
        char[] tmp = new char[]{'0', 0, 0, 0,
                                0, '0', 0, 0,
                                0, 0, '0', 0,
                                0, 0, 0, '0'};
        playGroundEntity.setContent(tmp);
        Assert.assertEquals(playGroundEntity.whoWin(), '0');
    }

    @Test
    public void giag23() {
        PlayGroundEntity playGroundEntity = new PlayGroundEntity();
        char[] tmp = new char[] {   0, 0, '0',
                                    0, '0', 0,
                                    '0', 0, 0};
        playGroundEntity.setContent(tmp);
        Assert.assertEquals(playGroundEntity.whoWin(), '0');
    }

    @Test
    public void diag24() {
        PlayGroundEntity playGroundEntity = new PlayGroundEntity();
        char[] tmp = new char[]{0, 0, 0, '0',
                                0, 0, '0', 0,
                                0, '0', 0, 0,
                                '0', 0, 0, 0};
        playGroundEntity.setContent(tmp);
        Assert.assertEquals(playGroundEntity.whoWin(), '0');
    }

    @Test
    public void draw3() {
        try {
            PlayGroundEntity playGroundEntity = new PlayGroundEntity(new Random().nextLong());
            char[] tmp = new char[] {   'x', 'o', 'x',
                                        'x', 'o', 'o',
                                        'o', 'x', 'x'};
            playGroundEntity.setContent(tmp);
            Assert.assertEquals(playGroundEntity.whoWin(), 1);
        } catch (BadNumberOfPlayersException | BadPlaygroundSideException  e) {
            e.printStackTrace();
        }
    }

    @Test
    public void draw4() {
        PlayGroundEntity playGroundEntity = null;
        try {
            playGroundEntity = new PlayGroundEntity(new Random().nextLong());
            char[] tmp = new char[] {   'o', 'x', 'o', 'x',
                                        'o', 'x', 'o', 'x',
                                        'x', 'o', 'x', 'o',
                                        'x', 'o', 'x', 'o'};
            playGroundEntity.setContent(tmp);
            Assert.assertEquals(playGroundEntity.whoWin(), 1);
        } catch (BadNumberOfPlayersException | BadPlaygroundSideException  e) {
            e.printStackTrace();
        }
    }

    @Test
    public void x3() {
        try {
            PlayGroundEntity playGroundEntity = new PlayGroundEntity(new Random().nextLong());
            char[] tmp = new char[] {   'x', 'o', 'x',
                                        'x', 'x', 'o',
                                        'x', 'o', 'o'};
            playGroundEntity.setContent(tmp);
            Assert.assertEquals(playGroundEntity.whoWin(), 'x');
        } catch (BadNumberOfPlayersException | BadPlaygroundSideException  e) {
            e.printStackTrace();
        }
    }

    @Test
    public void o4() {
        PlayGroundEntity playGroundEntity = null;
        try {
            playGroundEntity = new PlayGroundEntity(new Random().nextLong());
            char[] tmp = new char[] {   'o', 'x', 'o', 'x',
                                        'x', 'o', 'x', 'o',
                                        'x', 'o', 'o', 'x',
                                        'o', 'x', 'o', 'o'};
            playGroundEntity.setContent(tmp);
            Assert.assertEquals(playGroundEntity.whoWin(), 'o');
        } catch (BadNumberOfPlayersException | BadPlaygroundSideException  e) {
            e.printStackTrace();
        }
    }
}
