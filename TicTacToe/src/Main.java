import javax.swing.text.View;
import java.awt.print.Printable;
import java.util.Properties;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Use the following mapping table to specify a cell using numbers from 1 to 9:");
        PrintableMaket();
        char[][] gameTable = {
                {' ', ' ', ' '},
                {' ', ' ', ' '},
                {' ', ' ', ' '}
        };
        if (new Random().nextBoolean()) {
            makeComputerMove(gameTable);
            printGameTable(gameTable);
        }
        while (true) {
            MakeUserMove(gameTable);
            printGameTable(gameTable);
            if (isUserWin(gameTable)) {
                System.out.println("you Win");
                break;
            }
            if (isDraw(gameTable)) {
                System.out.println("Sorry,DRAW");
                break;
            }
            makeComputerMove(gameTable);
            printGameTable(gameTable);

            if (isComputerWin(gameTable)) {
                System.out.println("you lose");
                break;
            }
            if (isDraw(gameTable)) {
                System.out.println("Sorry,DRAW");
                break;
            }
        }
        System.out.println("GAME OVER");
    }

    private static void PrintableMaket() {
        char[][] mappingTable = {
                {'7', '8', '9'},
                {'4', '5', '6'},
                {'1', '2', '3'}

        };
        printGameTable(mappingTable);
    }

    private static void printGameTable(char[][] gameTable) {
        for (int i = 0; i < 3; i++) {
            System.out.println("-------------");
            for (int j = 0; j < 3; j++) {
                System.out.print("| " + gameTable[i][j] + " ");
            }
            System.out.println("|");
        }
        System.out.println("-------------");
    }

    private static void MakeUserMove(char[][] gameTable) {
        while (true) {
            System.out.println("Please type number between 1 and 9:");
            String string = new Scanner(System.in).nextLine();
            if (string.length() == 1) {
                char digit = string.charAt(0);
                if (digit >= '1' && digit <= '9') {
                    if (MakeUserMovetoCell(gameTable, digit)) {
                        return;

                    }

                }
            }
        }
    }

    private static boolean MakeUserMovetoCell(char[][] gameTable, char digit) {
        char[][] mappingTable = {
                {'7', '8', '9'},
                {'4', '5', '6'},
                {'1', '2', '3'}

        };
        boolean flag = true;
        for (int i = 0; i < mappingTable.length; i++) {
            for (int j = 0; j < mappingTable[i].length; j++) {
                if (mappingTable[i][j] == digit) {
                    if (gameTable[i][j] == ' ') {
                        gameTable[i][j] = 'X';
                        return true;
                    } else {
                        System.out.println("Cant make a move,because the call is not free! Try again");
                        return false;
                    }
                }
            }
        }
        return false;
    }


    private static void makeComputerMove(char[][] gameTable) {
        Random random = new Random();
        while (true) {

            int number = new Random().nextInt(10);
            int row = random.nextInt(3);
            int col = random.nextInt(3);
            if (gameTable[row][col] == ' ') {
                gameTable[row][col] = '0';
                if (gameTable[0][0] == gameTable[0][1])

                    return;
            }
        }
    }

    private static boolean isUserWin(char[][] gameTable) {

        for (int i = 0; i < 3; i++) {
            if (gameTable[i][0] == gameTable[i][1] && gameTable[i][1] == gameTable[i][2] && gameTable[i][0] == 'X') {
                return true;

            }
        }
        for (int i = 0; i < 3; i++) {
            if (gameTable[0][i] == gameTable[1][i] && gameTable[2][i] == gameTable[1][i] && gameTable[0][i] == 'X') {
                return true;

            }
        }
        if (gameTable[0][0] == gameTable[1][1] && gameTable[0][0] == gameTable[2][2] && gameTable[0][0] == 'X') {
            return true;
        }
        if (gameTable[0][2] == gameTable[1][1] && gameTable[2][0] == gameTable[0][2] && gameTable[0][2] == 'X') {
            return true;
        }

        return false;
    }

    private static boolean isComputerWin(char[][] gameTable) {
        for (int i = 0; i < 3; i++) {
            if (gameTable[i][0] == gameTable[i][1] && gameTable[i][1] == gameTable[i][2] && gameTable[i][0] == '0') {
                return true;

            }
        }
        for (int i = 0; i < 3; i++) {
            if (gameTable[0][i] == gameTable[1][i] && gameTable[2][i] == gameTable[1][i] && gameTable[0][i] == '0') {
                return true;

            }
        }
        if (gameTable[0][0] == gameTable[1][1] && gameTable[0][0] == gameTable[2][2] && gameTable[0][0] == '0') {
            return true;
        }
        if (gameTable[0][2] == gameTable[1][1] && gameTable[2][0] == gameTable[0][2] && gameTable[0][2] == '0') {
            return true;
        }

        return false;
    }

    private static boolean isDraw(char[][] gameTable) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (gameTable[i][j] == ' ') {
                    return false;
                }

            }


        }
        return true;
    }
}