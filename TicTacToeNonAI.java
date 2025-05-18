import java.util.Scanner;

public class TicTacToeNonAI {
    static final int SIZE = 3;
    static char[][] board = { {' ', ' ', ' '}, {' ', ' ', ' '}, {' ', ' ', ' '} };

    public static void printBoard() {
        System.out.println();
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                System.out.print(" " + board[i][j] + " ");
                if (j < SIZE - 1) System.out.print("|");
            }
            System.out.println();
            if (i < SIZE - 1) System.out.println("---|---|---");
        }
        System.out.println();
    }

    public static boolean isGameOver() {
        // Check rows and columns for a win
        for (int i = 0; i < SIZE; i++) {
            if ((board[i][0] == board[i][1] && board[i][1] == board[i][2] && board[i][0] != ' ') ||
                (board[0][i] == board[1][i] && board[1][i] == board[2][i] && board[0][i] != ' ')) {
                return true;
            }
        }

        // Check diagonals for a win
        if ((board[0][0] == board[1][1] && board[1][1] == board[2][2] && board[0][0] != ' ') ||
            (board[0][2] == board[1][1] && board[1][1] == board[2][0] && board[0][2] != ' ')) {
            return true;
        }

        return false;
    }

    public static boolean isDraw() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (board[i][j] == ' ') return false;
            }
        }
        return true;
    }

    public static void playerMove(char symbol) {
        Scanner scanner = new Scanner(System.in);
        int x, y;
        while (true) {
            System.out.print("Enter row and column (1-3) for " + symbol + ": ");
            x = scanner.nextInt();
            y = scanner.nextInt();
            if (x >= 1 && x <= 3 && y >= 1 && y <= 3 && board[x - 1][y - 1] == ' ') {
                board[x - 1][y - 1] = symbol;
                break;
            } else {
                System.out.println("Invalid move! Try again.");
            }
        }
    }

    public static void playGame() {
        int turn = 0;
        while (!isGameOver() && !isDraw()) {
            printBoard();
            if (turn % 2 == 0) {
                System.out.println("Player X's turn:");
                playerMove('X');
            } else {
                System.out.println("Player O's turn:");
                playerMove('O');
            }
            turn++;
        }

        printBoard();
        if (isGameOver()) {
            if (turn % 2 == 1)
                System.out.println("Player X wins!");
            else
                System.out.println("Player O wins!");
        } else {
            System.out.println("It's a draw!");
        }
    }

    public static void main(String[] args) {
        playGame();
    }
}
