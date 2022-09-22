package org.proxx;

import java.util.Random;
import java.util.Scanner;

public class GameField {
    private final GameCell[][] gameCells;
    private final int height;
    private final int weight;
    private final int mineCount;

    private final Random random = new Random();

    public GameField(final int height, final int weight, final int mineCount) {
        this.height = height;
        this.weight = weight;
        this.mineCount = mineCount;
        this.gameCells = new GameCell[height][weight];
        initGameField();
    }

    private void initGameField() {
        for (int i = 0; i < weight; i++) {
            for (int j = 0; j < height; j++) {
                gameCells[i][j] = new GameCell();
            }
        }

        for (int i = 0; i < mineCount; i++) {
            int x = generateRandomCoordinateInRange(height);
            int y = generateRandomCoordinateInRange(weight);

            if (!isEmptyCell(x, y)) {
                putMineInCell(x, y);
            }
        }

        for (int i = 0; i < weight; i++) {
            for (int j = 0; j < height; j++) {
                gameCells[i][j].setNeighborHoleTotal(calculateNeighborHoles(i, j));
            }
        }
    }

    private int generateRandomCoordinateInRange(int range) {
        return random.ints(0, range).findFirst().getAsInt();
    }

    private void putMineInCell(int x, int y) {
        gameCells[x][y].setHole(true);
    }

    public boolean isEmptyCell(int x, int y) {
        return gameCells[x][y].hasHole();
    }

    private boolean isEmptyCellAndNotOutOfBorder(int x, int y) {
        if (isBorder(x, y)) {
            return isEmptyCell(x, y);
        } else {
            return false;
        }
    }

    private int calculateNeighborHoles(int x, int y) {
        int total = 0;

        //*  *    *
        //* (x,y) *
        //*  *    *
        if (isEmptyCell(x, y)) {
            return -1;
        }
        //*  (*)    *
        //*  (x,y)  *
        //*   *     *
        if (isEmptyCellAndNotOutOfBorder(x, y+1)) {
            total++;
        }

        //*  *      *
        //*  (x,y)  *
        //*  (*)    *
        if (isEmptyCellAndNotOutOfBorder(x, y-1)) {
            total++;
        }

        //*  *     (*)
        //*  (x,y)  *
        //*  *      *
        if (isEmptyCellAndNotOutOfBorder(x+1, y+1)) {
            total++;
        }

        //*    *     *
        //*   (x,y)  *
        //(*)  *     *
        if (isEmptyCellAndNotOutOfBorder(x-1, y-1)) {
            total++;
        }

        //(*)  *     *
        //*   (x,y)  *
        //*    *     *
        if (isEmptyCellAndNotOutOfBorder(x-1, y+1)) {
            total++;
        }

        //*    *     *
        //(*) (x,y)  *
        //*    *     *
        if (isEmptyCellAndNotOutOfBorder(x-1, y)) {
            total++;
        }

        //*    *     *
        //*   (x,y) (*)
        //*    *     *
        if (isEmptyCellAndNotOutOfBorder(x+1, y)) {
            total++;
        }

        //*    *     *
        //*   (x,y)  *
        //*    *    (*)
        if (isEmptyCellAndNotOutOfBorder(x+1, y-1)) {
            total++;
        }

        return total;
    }

    private boolean isBorder(int x, int y) {
        return (x >= 0 && x <= weight-1) && (y >= 0 && y <= height-1);
    }

    public void printGameFieldInDebugMode() {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < weight; j++) {
                if (gameCells[i][j].hasHole()) {
                    System.out.print("\tH");
                } else {
                    System.out.print("\t"+gameCells[i][j].getNeighborHoleTotal());
                }
            }
            System.out.println();
        }
    }

    public void printGameField() {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < weight; j++) {
                if(!gameCells[i][j].isOpened()) {
                    System.out.print("\t*");
                } else {
                    System.out.print("\t"+gameCells[i][j].getNeighborHoleTotal());
                }
            }
            System.out.println();
        }
    }

    public void openCell(int x, int y) {
        if (isBorder(x, y)) {
            gameCells[x][y].setOpened(true);
        }
    }

    public void openEmptyCells(int x, int y) {
        if (isBorder(x, y) && !gameCells[x][y].isOpened()) {
            if (gameCells[x][y].getNeighborHoleTotal() == 0) {
                openCell(x, y);
                openEmptyCells(x-1, y);
            }

            if (gameCells[x][y].getNeighborHoleTotal() == 0) {
                openCell(x, y);
                openEmptyCells(x+1, y);
            }

            if (gameCells[x][y].getNeighborHoleTotal() == 0) {
                openCell(x, y);
                openEmptyCells(x-1, y-1);
            }

            if (gameCells[x][y].getNeighborHoleTotal() == 0) {
                openCell(x, y);
                openEmptyCells(x-1, y+1);
            }

            if (gameCells[x][y].getNeighborHoleTotal() == 0) {
                openCell(x, y);
                openEmptyCells(x, y+1);
            }

            if (gameCells[x][y].getNeighborHoleTotal() == 0) {
                openCell(x, y);
                openEmptyCells(x, y-1);
            }

            if (gameCells[x][y].getNeighborHoleTotal() == 0) {
                openCell(x, y);
                openEmptyCells(x+1, y+1);
            }

            if (gameCells[x][y].getNeighborHoleTotal() != 0 && !gameCells[x][y].isOpened()) {
                openCell(x, y);
            }
        }
    }


    public static void launch() {

    }
}
