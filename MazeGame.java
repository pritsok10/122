import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class MazeGame {
    static final int rows = 5;
    static final int cols = 5;
    
    static char[][] maze = {
        {'P', '#', ' ', ' ', ' '},
        {' ', '#', ' ', '#', ' '},
        {' ', ' ', ' ', '#', ' '},
        {'#', '#', ' ', ' ', ' '},
        {' ', ' ', ' ', '#', 'G'}
    };

    static int[] dx = {0, 1, 0, -1}; // Right, Down, Left, Up
    static int[] dy = {1, 0, -1, 0};

    static class Point {
        int x, y;
        Point(int x, int y) { this.x = x; this.y = y; }
    }

    static boolean isValidMove(int x, int y) {
        return x >= 0 && x < rows && y >= 0 && y < cols && maze[x][y] != '#';
    }

    static void bfs(Point goal, int[][] dist) {
        Queue<Point> q = new LinkedList<>();
        for (int[] row : dist)
            Arrays.fill(row, Integer.MAX_VALUE);

        dist[goal.x][goal.y] = 0;
        q.add(goal);

        while (!q.isEmpty()) {
            Point current = q.poll();

            for (int i = 0; i < 4; i++) {
                int newX = current.x + dx[i];
                int newY = current.y + dy[i];

                if (isValidMove(newX, newY) && dist[newX][newY] == Integer.MAX_VALUE) {
                    dist[newX][newY] = dist[current.x][current.y] + 1;
                    q.add(new Point(newX, newY));
                }
            }
        }
    }

    static void displayMaze(int[][] dist, int playerX, int playerY) {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (i == playerX && j == playerY)
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

        int remainingDist = dist[playerX][playerY];
        if (remainingDist == Integer.MAX_VALUE)
            System.out.println("No path to goal.");
        else
            System.out.println("Remaining distance to goal: " + remainingDist);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Point start = new Point(0, 0);  // Starting position
        Point goal = new Point(4, 4);   // Goal position

        int[][] dist = new int[rows][cols];
        bfs(goal, dist);

        int playerX = start.x, playerY = start.y;
        char move;

        while (true) {
            displayMaze(dist, playerX, playerY);
            System.out.print("Move (W/A/S/D): ");
            move = scanner.next().charAt(0);

            int newX = playerX, newY = playerY;
            switch (Character.toUpperCase(move)) {
                case 'W': newX--; break;
                case 'S': newX++; break;
                case 'A': newY--; break;
                case 'D': newY++; break;
                default: continue;
            }

            if (isValidMove(newX, newY)) {
                playerX = newX;
                playerY = newY;
                if (maze[playerX][playerY] == 'G') {
                    displayMaze(dist, playerX, playerY);
                    System.out.println("Congratulations! You've reached the goal!");
                    break;
                }
            }
        }

        scanner.close();
    }
}
