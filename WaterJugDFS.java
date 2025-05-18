import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

class WaterJugDFS {
    static class State {
        int four, three;

        public State(int four, int three) {
            this.four = four;
            this.three = three;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj instanceof State) {
                State other = (State) obj;
                return this.four == other.four && this.three == other.three;
            }
            return false;
        }

        @Override
        public int hashCode() {
            return Objects.hash(four, three);
        }
    }

    private static Set<State> visited = new HashSet<>();
    private static List<State> solutionPath = new ArrayList<>();

    public static boolean dfsWaterJug(State current) {
        if (visited.contains(current)) return false;
        visited.add(current);
        solutionPath.add(current);

        if (current.four == 2) {
            return true;
        }

        List<State> nextMoves = Arrays.asList(
            new State(4, current.three), // Fill 4L jug
            new State(current.four, 3),  // Fill 3L jug
            new State(0, current.three), // Empty 4L jug
            new State(current.four, 0),  // Empty 3L jug
            new State(Math.max(0, current.four - (3 - current.three)), Math.min(3, current.three + current.four)), // Pour 4L → 3L
            new State(Math.min(4, current.four + current.three), Math.max(0, current.three - (4 - current.four)))  // Pour 3L → 4L
        );

        Collections.shuffle(nextMoves);

        for (State next : nextMoves) {
            if (dfsWaterJug(next)) return true; 
        }

        solutionPath.remove(solutionPath.size() - 1); // Backtrack if this path doesn't lead to a solution
        return false;
    }

    public static void main(String[] args) {
        State initial = new State(0, 0); 

        if (dfsWaterJug(initial)) {
            System.out.println("Solution found! Path:");
            for (State state : solutionPath) {
                System.out.println("(" + state.four + ", " + state.three + ")");
            }
        } else {
            System.out.println("No solution found.");
        }
    }
}

