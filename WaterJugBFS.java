import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Queue;
import java.util.Set;

class WaterJugBFS {
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

    public static void bfsWaterJug() {
        Queue<State> queue = new LinkedList<>();
        Set<State> visited = new HashSet<>();

        queue.add(new State(0, 0));

        while (!queue.isEmpty()) {
            State current = queue.poll();

            if (visited.contains(current)) continue;
            visited.add(current);

            System.out.println("(" + current.four + ", " + current.three + ")");
            if (current.four == 2) {
                System.out.println("Solution found!");
                return;
            }

            List<State> nextMoves = Arrays.asList(
                new State(4, current.three), // Fill 4-gallon jug
                new State(current.four, 3),  // Fill 3-gallon jug
                new State(0, current.three), // Empty 4-gallon jug
                new State(current.four, 0),  // Empty 3-gallon jug
                new State(Math.max(0, current.four - (3 - current.three)), Math.min(3, current.three + current.four)), // Pour 4-gallon into 3-gallon
                new State(Math.min(4, current.four + current.three), Math.max(0, current.three - (4 - current.four)))  // Pour 3-gallon into 4-gallon
            );

            for (State next : nextMoves) {
                if (!visited.contains(next)) queue.add(next);
            }
        }

        System.out.println("No solution found.");
    }

    public static void main(String[] args) {
        bfsWaterJug();
    }
}


