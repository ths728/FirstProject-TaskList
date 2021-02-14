package model;

import java.util.Stack;

public class PlaceChess {
    private int[][] placeChess;
    private int x1;
    private int y1;

    class Chess {
        int x1;
        int y1;

        public Chess(int x, int y) {
            this.x1 = x;
            this.y1 = y;
        }
    }

    Stack<Chess> stack;

    // Requires: x,y values
    // Effects: Determine the given coordinate is empty or chess on it. True represents empty,
    //          false represents chess on it.
    public boolean checkEmpty(int x, int y) {
        if (placeChess[x][y] == 0) {
            return true;
        } else {
            return false;
        }
    }
}
