package Games;
import javax.swing.*;
import java.util.Scanner;
import java.util.Random;
public class seaFight {
    public static Scanner scanner = new Scanner(System.in);
    public static Random random = new Random();

    static final int SIZE = 5;

    public static void main(String[] args) {
        //Do board: colon(letter) and row(number)
        int targetCol = random.nextInt(SIZE);
        int targetRow = random.nextInt(SIZE);

        int targetCol1 = 0;
        int targetRow1 = 0;
        while ((targetCol == targetCol1 || targetRow == targetRow1)) {
            targetCol1 = random.nextInt(SIZE);
            targetRow1 = random.nextInt(SIZE);
        }

        char[][] board = new char[SIZE][SIZE];
        for (int r = 0; r < SIZE; r++) {
            for (int i = 0; i < SIZE; i++) {
                board[r][i] = '.';
            }
        }

        int attempts = 0;
        boolean firstGuessed = false;
        System.out.println("Game: sea fight");
        System.out.println("Computer chose one square on the board 5x5");
        System.out.println("Enter coordinate, for example: B3 " +
                "(letter A-E, number 1-5)");
        System.out.println("If you want to leave the program enter: exit");

        while (true) {
            printBoard(board);
            System.out.println("Your turn");
            String input = scanner.nextLine().toUpperCase();

            if (input.equalsIgnoreCase("EXIT") || input.isEmpty()) {
                board[targetRow][targetCol] = '0';
                board[targetRow1][targetCol1] = '0';
                printBoard(board);
                System.out.println("First square is " + (char) ('A' + targetCol) + (targetRow + 1));
                System.out.println("Second square is " + (char) ('A' + targetCol1) + (targetRow1 + 1));
                break;
            }
            if (input.length() != 2 || input.charAt(0) < 'A' || input.charAt(0) >= 'A' + SIZE
                    || input.charAt(1) < '1' || input.charAt(1) > '0' + SIZE) {
                System.out.println("Incorrect, try again");
                continue;
            }

            int col = input.charAt(0) - 'A';
            int row = input.charAt(1) - '1';


            if (board[row][col] == 'X' || board[row][col] == '0') {
                System.out.println("You have already tried this one");
                continue;
            }
            attempts++;

            if (!firstGuessed && col == targetCol && row == targetRow) {
                board[row][col] = '0';
                markNeighbors(board, row, col, SIZE);
                printBoard(board);
                System.out.println("you guessed the first one!");
                firstGuessed = true;

                System.out.println("Do you want to continue? (y/n)");
                String yn = scanner.nextLine();
                if (yn.equalsIgnoreCase("n")) {
                    System.out.println("Game over. Attempts: " + attempts);
                    System.out.println("Attempts: " + attempts);
                    System.out.println("The second point was " + +(char) ('A' + targetCol1) + (targetRow1 + 1));
                    System.exit(0);
                }
            } else if (firstGuessed && col == targetCol1 && row == targetRow1) {
                board[row][col] = 'O';
                printBoard(board);
                System.out.println("You win!");
                System.out.println("Attempts: " + attempts);
                System.exit(0);
            } else {
                board[row][col] = 'X';
                System.out.println("Miss");
            }
        }
        scanner.close();
    }

        static void printBoard (char[][] board) {

            System.out.print("  ");
            for (int i = 0; i < SIZE; i++) {
                System.out.print((char) ('A' + i) + " ");
            }
            System.out.println();

            for (int j = 0; j < SIZE; j++) {
                System.out.print((j + 1) + " ");
                for (int i = 0; i < SIZE; i++) {
                    System.out.print(board[j][i] + " ");
                }
                System.out.println();
            }
            System.out.println();
        }

        static void markNeighbors ( char[][] board, int r, int c, int size){
            int[] dr = {-1, 1, 0, 0};
            int[] dc = {0, 0, -1, 1};
    }
}
