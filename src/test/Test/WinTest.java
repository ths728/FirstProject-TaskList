package Test;

import model.Win;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class WinTest {
    private int[][] exist;

    @BeforeEach


    @Test
    public void testHorizontalWin(int x, int y) {
        assertEquals(0,exist[x][y] == 0);
        assertEquals(1, exist[x][y] == 1);
    }

    @Test
    public void testVerticalWin(){

    }

    @Test
    public void testRightSlashWin(){

    }

    @Test
    public void testLeftSlashWin(){

    }
}
