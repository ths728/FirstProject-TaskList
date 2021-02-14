package Test;

import model.Chess;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ChessTest {
    private Chess chess;
    private static final int x1 = 2;
    private static final int y1 = 3;

    @BeforeEach
    public void runBefore() {
        chess = new Chess(x1, y1);
    }

    @Test
    public void testConstructor(){
        assertEquals(2, chess.getX());
        assertEquals(3, chess.getY());
    }


}
