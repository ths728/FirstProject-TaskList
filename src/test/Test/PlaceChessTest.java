package Test;

import model.PlaceChess;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PlaceChessTest {
    public PlaceChess placeChess;
    private static final int x = 2;
    private static final int y = 3;

    @BeforeEach
    public void runBefore() {
        placeChess = new PlaceChess();
    }

    @Test
    public void testCheckEmpty() {
        assertEquals(placeChess.checkEmpty(x,y),true);
        assertEquals(placeChess.checkEmpty(x,y),false);
    }
}