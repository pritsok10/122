import java.util.Scanner;

public class TicTacToe {
    
    private static final int SIZE = 3;
    private static char[][] board = new char[SIZE][SIZE];
    private static char human = 'X', ai = 'O';
    
    public static void main(String[] args) {

        // Initialize the board
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                board[i][j] = ' ';
            }
        }
        
        playGame();
    }

    // Print the board
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

    // Check if the game is over
    public static boolean isGameOver() {
        for (int i = 0; i < SIZE; i++) {
            if ((board[i][0] == board[i][1] && board[i][1] == board[i][2] && board[i][0] != ' ') ||
                (board[0][i] == board[1][i] && board[1][i] == board[2][i] && board[0][i] != ' ')) {
                return true;
            }
        }

        if ((board[0][0] == board[1][1] && board[1][1] == board[2][2] && board[0][0] != ' ') ||
            (board[0][2] == board[1][1] && board[1][1] == board[2][0] && board[0][2] != ' ')) {
            return true;
        }

        return false;
    }

    // Check if the game is a draw
    public static boolean isDraw() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (board[i][j] == ' ') return false;
            }
        }
        return true;
    }

    // Evaluate the board to check for wins or losses
    public static int evaluate() {
        for (int i = 0; i < SIZE; i++) {
            if (board[i][0] == board[i][1] && board[i][1] == board[i][2]) {
                if (board[i][0] == ai) return +10;
                if (board[i][0] == human) return -10;
            }
            if (board[0][i] == board[1][i] && board[1][i] == board[2][i]) {
                if (board[0][i] == ai) return +10;
                if (board[0][i] == human) return -10;
            }
        }

        if (board[0][0] == board[1][1] && board[1][1] == board[2][2]) {
            if (board[0][0] == ai) return +10;
            if (board[0][0] == human) return -10;
        }
        if (board[0][2] == board[1][1] && board[1][1] == board[2][0]) {
            if (board[0][2] == ai) return +10;
            if (board[0][2] == human) return -10;
        }
        return 0;
    }

    // Minimax algorithm for AI's move
    public static int minimax(boolean isAI, int depth) {
        int score = evaluate();

        if (score == 10) return score - depth;
        if (score == -10) return score + depth;

        if (isDraw()) return 0;

        if (isAI) {
            int best = -1000;
            for (int i = 0; i < SIZE; i++) {
                for (int j = 0; j < SIZE; j++) {
                    if (board[i][j] == ' ') {
                        board[i][j] = ai;
                        best = Math.max(best, minimax(false, depth + 1));
                        board[i][j] = ' ';
                    }
                }
            }
            return best;
        } else {
            int best = 1000;
            for (int i = 0; i < SIZE; i++) {
                for (int j = 0; j < SIZE; j++) {
                    if (board[i][j] == ' ') {
                        board[i][j] = human;
                        best = Math.min(best, minimax(true, depth + 1));
                        board[i][j] = ' ';
                    }
                }
            }
            return best;
        }
    }

    // Find the best move for AI
    public static int[] findBestMove() {
        int bestVal = -1000;
        int[] bestMove = {-1, -1};

        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (board[i][j] == ' ') {
                    board[i][j] = ai;
                    int moveVal = minimax(false, 0);
                    board[i][j] = ' ';
                    if (moveVal > bestVal) {
                        bestMove[0] = i;
                        bestMove[1] = j;
                        bestVal = moveVal;
                    }
                }
            }
        }
        return bestMove;
    }

    // Handle the human's move
    public static void humanMove() {
        Scanner sc = new Scanner(System.in);
        int x, y;
        while (true) {
            System.out.print("Enter your move (row and column): ");
            x = sc.nextInt();
            y = sc.nextInt();
            if (x >= 1 && x <= 3 && y >= 1 && y <= 3 && board[x - 1][y - 1] == ' ') {
                board[x - 1][y - 1] = human;
                break;
            } else {
                System.out.println("Invalid move! Try again.");
            }
        }
    }

    // Main game loop
    public static void playGame() {
        int turn = 0;
        while (!isGameOver() && !isDraw()) {
            printBoard();
            if (turn % 2 == 0) {
                System.out.println("Player X's turn:");
                humanMove();
            } else {
                System.out.println("AI (O) is thinking...");
                int[] bestMove = findBestMove();
                board[bestMove[0]][bestMove[1]] = ai;
            }
            turn++;
        }

        printBoard();
        if (evaluate() == 10)
            System.out.println("AI wins!");
        else if (evaluate() == -10)
            System.out.println("Player X wins!");
        else
            System.out.println("It's a draw!");
    }
}
