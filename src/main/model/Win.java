package model;

// Check who wins the game with the given coordinate.

public class Win {
    private int[][] exist;

    public Win(int[][] exist) {
        this.exist = exist;
    }

    // check win in 4 directions

    public int win(int x, int y) {
        if (horizontalWin(x, y) >= 5 || verticalWin(x, y) >= 5 || rightSlash(x, y) >= 5 || leftSlash(x, y) >= 5) {
            // Use 1 represents colour "Black win", 2 represents colour "White win"
            // 0 represents null, -1 represents else(impossible).
            if (exist[x][y] == 1) {
                return 1;
            }

            if (exist[x][y] == 2) {
                return 2;
            }

            if (exist[x][y] == 0) {
                return 0;
            }
        }
        return -1;
    }

    // Win in horizontal line
    // Top-left corner is (0,0)

    public int horizontalWin(int x, int y) {
        int countNum = 0;
        //Towards right
        for (int i = x + 1; i < 10; i++) {
            if (exist[x][y] == exist[i][y]) {
                countNum++;
            }

            if (exist[x][y] != exist[i][y]) {
                break;
            }
        }
        // Towards left
        for (int i = x; i >= 0; i--) {
            if (exist[x][y] == exist[i][y]) {
                countNum++;
            }
            if (exist[x][y] != exist[i][y]) {
                break;
            }
        }
        return countNum;
    }

    // Win in vertical line

    public int verticalWin(int x, int y) {
        int countNum = 0;
        //Towards down
        for (int i = y + 1; i < 10; i++) {
            if (exist[x][y] == exist[x][i]) {
                countNum++;
            } else {
                break;
            }
        }
        // Towards up
        for (int i = y; i >= 0; i--) {
            if (exist[x][y] == exist[x][i]) {
                countNum++;
            } else {
                break;
            }
        }
        return countNum;
    }

    // Win in right slash line (positive m)

    public int rightSlash(int x, int y) {
        int countNum = 0;
        // up-right direction
        for (int i = x + 1, n = y - 1; i < 10 && n >= 0; i++, n--) {
            if (exist[x][y] == exist[i][n]) {
                countNum++;
            } else {
                break;
            }
        }

        // down-left direction
        for (int i = x - 1, n = y + 1; i >= 0 && n < 10; i--, n++) {
            if (exist[x][y] == exist[i][n]) {
                countNum++;
            } else {
                break;
            }
        }
        return countNum;
    }

    // Win in left slash line (negative m)

    public int leftSlash(int x, int y) {
        int countNum = 0;
        //Up-left direction
        for (int i = x - 1, n = y - 1; i >= 0 && n >= 0; i--, n--) {
            if (exist[x][y] == exist[i][n]) {
                countNum++;
            } else {
                break;
            }
        }

        for (int i = x + 1, n = y + 1; i < 10 && n < 10; i++, n++) {
            if (exist[x][y] == exist[i][n]) {
                countNum++;
            } else {
                break;
            }
        }
        return countNum;
    }
}
