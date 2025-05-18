import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class AONode {
    int cost;
    boolean solved;
    List<List<Integer>> children;
    int bestChildGroup;

    AONode(int c) {
        cost = c;
        solved = false;
        bestChildGroup = -1;
        children = new ArrayList<>();
    }
}

public class AOStarAlgorithm {
    static List<AONode> nodes = new ArrayList<>();

    public static int aostar(int node) {
        if (nodes.get(node).solved) {
            return nodes.get(node).cost;
        }

        if (nodes.get(node).children.isEmpty()) {
            nodes.get(node).solved = true;
            return nodes.get(node).cost;
        }

        int minCost = Integer.MAX_VALUE;
        int bestGroup = -1;

        for (int i = 0; i < nodes.get(node).children.size(); i++) {
            int sum = 0;
            for (int child : nodes.get(node).children.get(i)) {
                int childCost = aostar(child);
                sum += childCost;
            }

            if (sum < minCost) {
                minCost = sum;
                bestGroup = i;
            }
        }

        nodes.get(node).cost += minCost;
        nodes.get(node).bestChildGroup = bestGroup;
        nodes.get(node).solved = true;

        return nodes.get(node).cost;
    }

    public static void printSolution(int node) {
        System.out.print(node + " ");
        if (nodes.get(node).bestChildGroup != -1) {
            for (int child : nodes.get(node).children.get(nodes.get(node).bestChildGroup)) {
                printSolution(child);
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of nodes: ");
        int n = sc.nextInt();
        
        for (int i = 0; i < n; i++) {
            System.out.print("Enter cost of node " + i + ": ");
            int cost = sc.nextInt();
            AONode node = new AONode(cost);

            System.out.print("Enter number of child groups for node " + i + ": ");
            int g = sc.nextInt();

            for (int j = 0; j < g; j++) {
                System.out.print("Enter number of children in group " + (j + 1) + ": ");
                int count = sc.nextInt();
                List<Integer> group = new ArrayList<>();

                System.out.print("Enter children indices: ");
                for (int k = 0; k < count; k++) {
                    group.add(sc.nextInt());
                }
                node.children.add(group);
            }
            nodes.add(node);
        }

        System.out.print("Enter start node: ");
        int start = sc.nextInt();

        int minCost = aostar(start);
        System.out.println("Minimum cost: " + minCost);
        System.out.print("Best path: ");
        printSolution(start);
        System.out.println();
        
        sc.close();
    }
}
