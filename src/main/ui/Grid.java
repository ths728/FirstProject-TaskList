package ui;

import model.Win;

public class Grid {

    private int[][] grid;
    private int x1;
    private int y1;

    class Chess {
        int x1;
        int y1;

        public Chess(int x,int y) {
            this.x1 = x;
            this.y1 = y;
        }
    }

    public Grid(int x,int y) {
        grid = new int[x][y];
        this.x1 = x;
        this.y1 = y;
    }

    private boolean checkEmpty(int x,int y) {
        if (grid[x][y] == 0) {
            return true;
        } else {
            return false;
        }
    }

    private int win(int x,int y){
        
    }




    public static void main(String[] args) {
    }
}
