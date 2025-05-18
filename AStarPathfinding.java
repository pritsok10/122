import java.util.Comparator;
import java.util.PriorityQueue;

class Node {
    int x, y, g, h;
    Node parent;

    Node(int x, int y, int g, int h, Node parent) {
        this.x = x;
        this.y = y;
        this.g = g;
        this.h = h;
        this.parent = parent;
    }

    int f() {
        return g + h;
    }
}

class Compare implements Comparator<Node> {
    public int compare(Node a, Node b) {
        return Integer.compare(a.f(), b.f());
    }
}

public class AStarPathfinding {
    static final int MAX = 5;
    static int[][] grid = {
        {0, 1, 0, 0, 0},
        {0, 1, 0, 1, 0},
        {0, 0, 0, 1, 0},
        {1, 1, 0, 0, 0},
        {0, 0, 0, 1, 0}
    };

    static boolean[][] visited = new boolean[MAX][MAX];

    static int heuristic(int x, int y, int goalX, int goalY) {
        return Math.abs(goalX - x) + Math.abs(goalY - y); // Manhattan distance
    }

    static void printPath(Node node) {
        if (node == null) return;
        printPath(node.parent);
        System.out.print("(" + node.x + "," + node.y + ") ");
    }

    static void AStar(int startX, int startY, int goalX, int goalY) {
        PriorityQueue<Node> openList = new PriorityQueue<>(new Compare());
        openList.add(new Node(startX, startY, 0, heuristic(startX, startY, goalX, goalY), null));

        int[] dx = {1, 0, -1, 0};
        int[] dy = {0, 1, 0, -1};

        while (!openList.isEmpty()) {
            Node current = openList.poll();

            if (current.x == goalX && current.y == goalY) {
                System.out.print("Path: ");
                printPath(current);
                System.out.println();
                return;
            }

            visited[current.x][current.y] = true;

            for (int i = 0; i < 4; i++) {
                int nx = current.x + dx[i];
                int ny = current.y + dy[i];

                if (nx >= 0 && ny >= 0 && nx < MAX && ny < MAX && !visited[nx][ny] && grid[nx][ny] == 0) {
                    visited[nx][ny] = true;
                    openList.add(new Node(nx, ny, current.g + 1, heuristic(nx, ny, goalX, goalY), current));
                }
            }
        }

        System.out.println("No path found.");
    }

    public static void main(String[] args) {
        AStar(0, 0, 4, 4);
    }
}
