public class NQueens {
    static final int N = 8;
    static int[][] board = new int[N][N];

    // Print the board
    static void printBoard() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                System.out.print(board[i][j] == 1 ? "Q " : ". ");
            }
            System.out.println();
        }
        System.out.println();
    }

    // Check if it's safe to place a queen at board[row][col]
    static boolean isSafe(int row, int col) {
        // Check this column on upper rows
        for (int i = 0; i < row; i++) {
            if (board[i][col] == 1)
                return false;
        }

        // Check upper left diagonal
        for (int i = row, j = col; i >= 0 && j >= 0; i--, j--) {
            if (board[i][j] == 1)
                return false;
        }

        // Check upper right diagonal
        for (int i = row, j = col; i >= 0 && j < N; i--, j++) {
            if (board[i][j] == 1)
                return false;
        }

        return true;
    }

    // Solve the N-Queens problem
    static boolean solveNQueens(int row) {
        if (row == N) {
            printBoard();
            return true; // Return true to print only one solution
        }

        for (int col = 0; col < N; col++) {
            if (isSafe(row, col)) {
                board[row][col] = 1;
                if (solveNQueens(row + 1))
                    return true; // Comment this line if you want all solutions
                board[row][col] = 0; // Backtrack
            }
        }
        return false;
    }

    public static void main(String[] args) {
        if (!solveNQueens(0))
            System.out.println("No solution exists for " + N + " queens.");
    }
}
