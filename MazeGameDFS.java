import java.util.Scanner;
import java.util.Stack;

public class MazeGameDFS {
    static final int rows = 5;
    static final int cols = 5;

    static char[][] maze = {
        {'P', '#', ' ', ' ', ' '},
        {' ', '#', ' ', '#', ' '},
        {' ', ' ', ' ', '#', ' '},
        {'#', '#', ' ', ' ', ' '},
        {' ', ' ', ' ', '#', 'G'}
    };

    static class Point {
        int x, y;
        Point(int x, int y) { this.x = x; this.y = y; }
    }

    static final int[] dx = {0, 1, 0, -1}; // Right, Down, Left, Up
    static final int[] dy = {1, 0, -1, 0};

    static boolean isValidMove(int x, int y) {
        return x >= 0 && x < rows && y >= 0 && y < cols && maze[x][y] != '#';
    }

    static void dfs(Point goal, int[][] dist) {
        Stack<Point> stack = new Stack<>();
        for (int i = 0; i < rows; i++)
            for (int j = 0; j < cols; j++)
                dist[i][j] = -1; // -1 means unvisited

        dist[goal.x][goal.y] = 0;
        stack.push(goal);

        while (!stack.isEmpty()) {
            Point current = stack.pop();

            for (int i = 0; i < 4; i++) {
                int newX = current.x + dx[i];
                int newY = current.y + dy[i];

                if (isValidMove(newX, newY) && dist[newX][newY] == -1) {
                    dist[newX][newY] = dist[current.x][current.y] + 1;
                    stack.push(new Point(newX, newY));
                }
            }
        }
    }

    static void displayMaze(Point player, Point goal, int[][] dist) {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (i == player.x && j == player.y)
                    System.out.print("P ");
                else if (maze[i][j] == '#')
                    System.out.print("# ");
                else if (maze[i][j] == 'G')
                    System.out.print("G ");
                else
                    System.out.print(". ");
            }
            System.out.println();
        }

        int remainingDist = dist[player.x][player.y];
        if (remainingDist == -1)
            System.out.println("No path to goal.");
        else
            System.out.println("Remaining distance to goal: " + remainingDist);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Point start = new Point(0, 0);  // Start position (P)
        Point goal = new Point(4, 4);   // Goal position (G)

        int[][] dist = new int[rows][cols];
        dfs(goal, dist);

        Point player = start;

        while (true) {
            displayMaze(player, goal, dist);

            System.out.print("Move (W/A/S/D): ");
            char move = scanner.next().charAt(0);

            int newX = player.x, newY = player.y;
            switch (Character.toUpperCase(move)) {
                case 'W': newX--; break;
                case 'S': newX++; break;
                case 'A': newY--; break;
                case 'D': newY++; break;
                default: continue; // Ignore invalid input
            }

            if (isValidMove(newX, newY)) {
                player = new Point(newX, newY);
                if (player.x == goal.x && player.y == goal.y) {
                    displayMaze(player, goal, dist);
                    System.out.println("Congratulations! You've reached the goal!");
                    break;
                }
            }
        }

        scanner.close();
    }
}
