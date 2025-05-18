import java.util.Stack;

public class DFSIterative {
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

    static void dfsIterative(int start) {
        Stack<Integer> stack = new Stack<>();
        stack.push(start);

        while (!stack.isEmpty()) {
            int current = stack.pop();

            if (!visited[current]) {
                visited[current] = true;
                System.out.print(current + " ");

                // Push adjacent vertices in reverse order for consistent traversal
                for (int i = MAX_VERTICES - 1; i >= 0; i--) {
                    if (adjMatrix[current][i] == 1 && !visited[i]) {
                        stack.push(i);
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        System.out.print("DFS traversal starting from vertex 0: ");
        dfsIterative(0);
        System.out.println();
    }
}
