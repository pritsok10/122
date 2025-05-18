import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.Stack;

class GraphDFS {
    private Map<Integer, List<Integer>> adjList;

    public GraphDFS() {
        adjList = new HashMap<>();
    }

    public void addEdge(int u, int v) {
        adjList.computeIfAbsent(u, k -> new ArrayList<>()).add(v);
        adjList.computeIfAbsent(v, k -> new ArrayList<>()).add(u); // Remove for directed graph
    }

    public void dfs(int start) {
        Stack<Integer> stack = new Stack<>();
        Set<Integer> visited = new HashSet<>();

        stack.push(start);

        while (!stack.isEmpty()) {
            int node = stack.pop();

            if (!visited.contains(node)) {
                System.out.print(node + " ");
                visited.add(node);

                List<Integer> neighbors = adjList.getOrDefault(node, new ArrayList<>());
                Collections.reverse(neighbors);  // Reverse to maintain correct order
                for (int neighbor : neighbors) {
                    if (!visited.contains(neighbor)) {
                        stack.push(neighbor);
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        GraphDFS g = new GraphDFS();

        System.out.print("Enter number of edges: ");
        int edges = scanner.nextInt();

        System.out.println("Enter edges (format: u v):");
        for (int i = 0; i < edges; i++) {
            int u = scanner.nextInt();
            int v = scanner.nextInt();
            g.addEdge(u, v);
        }

        System.out.print("Enter starting node for DFS: ");
        int startNode = scanner.nextInt();

        System.out.println("DFS Traversal:");
        g.dfs(startNode);
        
        scanner.close();
    }
}



