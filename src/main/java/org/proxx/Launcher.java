package org.proxx;

import java.util.Scanner;

public class Launcher {
    private Launcher() {}
    public static void run() {
        GameField gameField = new GameField(10, 10, 20);
        String command; //open, quit
        int x;
        int y;
        Scanner in = new Scanner(System.in);
        System.out.print("Enter command open(o) or quit(q): ");
        command = in.nextLine().trim();

        gameField.printGameFieldInDebugMode();

        while (!(command.equals("quit") || command.equals("q"))) {

            if (command.equals("open") || command.equals("o")) {
                System.out.print("\nOpen cell x, y: ");
                String[] coordinates = in.nextLine().split(" ");
                x = Integer.parseInt(coordinates[0]);
                y = Integer.parseInt(coordinates[1]);
                if (gameField.isEmptyCell(x, y)) {
                    command = "q";
                    gameField.printGameFieldInDebugMode();
                } else {
                    gameField.openEmptyCells(x, y);
                    gameField.printGameField();
                    System.out.print("Enter command open(o) or quit(q): ");
                    command = in.nextLine();
                }
            }
        }

        System.out.println("Game over");
    }
}
