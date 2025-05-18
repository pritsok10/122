import java.util.LinkedList;
import java.util.Queue;

public class BFSAdjacencyMatrix {
    static final int MAX_VERTICES = 5;

    // Adjacency matrix representation
    static int[][] adjMatrix = {
        {0, 1, 1, 0, 0},  // Connections from vertex 0
        {1, 0, 0, 1, 0},  // Connections from vertex 1
        {1, 0, 0, 0, 1},  // Connections from vertex 2
        {0, 1, 0, 0, 0},  // Connections from vertex 3
        {0, 0, 1, 0, 0}   // Connections from vertex 4
    };

    static boolean[] visited = new boolean[MAX_VERTICES];

    static void bfs(int start) {
        Queue<Integer> q = new LinkedList<>();
        visited[start] = true;
        q.add(start);

        while (!q.isEmpty()) {
            int current = q.poll();
            System.out.print(current + " ");

            for (int i = 0; i < MAX_VERTICES; i++) {
                if (adjMatrix[current][i] == 1 && !visited[i]) {
                    visited[i] = true;
                    q.add(i);
                }
            }
        }
    }

    public static void main(String[] args) {
        System.out.print("BFS traversal starting from vertex 0: ");
        bfs(0);
        System.out.println();
    }
}
