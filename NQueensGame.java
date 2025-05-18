import java.util.Scanner;

public class NQueensGame {
    private int N;
    private int[] board;

    public NQueensGame(int n) {
        this.N = n;
        this.board = new int[n];
        for (int i = 0; i < n; i++) {
            board[i] = -1;
        }
    }

    private boolean isSafe(int row, int col) {
        for (int i = 0; i < row; i++) {
            if (board[i] == col || Math.abs(board[i] - col) == Math.abs(i - row)) {
                return false;
            }
        }
        return true;
    }

    private void printBoard() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (board[i] == j)
                    System.out.print("Q ");
                else
                    System.out.print(". ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public void startGame() {
        Scanner scanner = new Scanner(System.in);
        int row = 0;

        while (row < N) {
            printBoard();
            System.out.print("Place queen in row " + (row + 1) + " (enter column 1-" + N + "): ");
            int col = scanner.nextInt();
            col--; // Adjust for 0-based indexing

            if (col >= 0 && col < N && isSafe(row, col)) {
                board[row] = col;
                row++;
            } else {
                System.out.println("Invalid move. Try again.");
            }
        }
        System.out.println("Congratulations! You have placed all queens successfully.");
        printBoard();
        scanner.close();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the size of the board (N): ");
        int N = scanner.nextInt();

        NQueensGame game = new NQueensGame(N);
        game.startGame();
        scanner.close();
    }
}
