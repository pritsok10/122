import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Queue;
import java.util.Set;

class MissionariesAndCannibalsBFS {
    static class State {
        int M_left, C_left, boat, M_right, C_right;
        State parent; // To track the path

        public State(int M_left, int C_left, int boat, int M_right, int C_right, State parent) {
            this.M_left = M_left;
            this.C_left = C_left;
            this.boat = boat;
            this.M_right = M_right;
            this.C_right = C_right;
            this.parent = parent;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj instanceof State) {
                State other = (State) obj;
                return (this.M_left == other.M_left && this.C_left == other.C_left &&
                        this.boat == other.boat && this.M_right == other.M_right &&
                        this.C_right == other.C_right);
            }
            return false;
        }

        @Override
        public int hashCode() {
            return Objects.hash(M_left, C_left, boat, M_right, C_right);
        }

        boolean isValid() {
            if ((M_left < C_left && M_left > 0) || (M_right < C_right && M_right > 0)) {
                return false;
            }
            return M_left >= 0 && C_left >= 0 && M_right >= 0 && C_right >= 0;
        }

        boolean isGoal() {
            return M_left == 0 && C_left == 0 && boat == 0 && M_right == 3 && C_right == 3;
        }
    }

    private static final int[][] MOVES = {
            {1, 0}, 
            {2, 0}, 
            {0, 1}, 
            {0, 2}, 
            {1, 1}  
    };

    public static void solve() {
        Queue<State> queue = new LinkedList<>();
        Set<State> visited = new HashSet<>();

        State initialState = new State(3, 3, 1, 0, 0, null);
        queue.add(initialState);
        visited.add(initialState);

        while (!queue.isEmpty()) {
            State current = queue.poll();

            // Print the state being checked
            System.out.println("Checking state: (" + current.M_left + ", " + current.C_left + ", " + current.boat +
                    ", " + current.M_right + ", " + current.C_right + ")");

            if (current.isGoal()) {
                printSolutionPath(current);
                return;
            }

            for (int[] move : MOVES) {
                State newState = getNextState(current, move);
                if (newState != null) {
                    if (!newState.isValid()) {
                        // Print invalid state
                        System.out.println("Invalid state: (" + newState.M_left + ", " + newState.C_left + ", " + newState.boat +
                                ", " + newState.M_right + ", " + newState.C_right + ")");
                    } else if (!visited.contains(newState)) {
                        queue.add(newState);
                        visited.add(newState);
                        // Print visited state
                        System.out.println("Visited state: (" + newState.M_left + ", " + newState.C_left + ", " + newState.boat +
                                ", " + newState.M_right + ", " + newState.C_right + ")");
                    }
                }
            }
        }

        System.out.println("No solution found!");
    }

    private static State getNextState(State current, int[] move) {
        int M = move[0], C = move[1];

        if (current.boat == 1) {
            return new State(
                    current.M_left - M, current.C_left - C, 0,
                    current.M_right + M, current.C_right + C, current
            );
        } else {
            return new State(
                    current.M_left + M, current.C_left + C, 1,
                    current.M_right - M, current.C_right - C, current
            );
        }
    }

    private static void printSolutionPath(State goalState) {
        List<State> path = new ArrayList<>();
        State current = goalState;
        while (current != null) {
            path.add(current);
            current = current.parent;
        }
        Collections.reverse(path);

        System.out.println("Solution Path:");
        for (State state : path) {
            System.out.println("(" + state.M_left + ", " + state.C_left + ", " + state.boat +
                    ", " + state.M_right + ", " + state.C_right + ")");
        }
    }

    public static void main(String[] args) {
        solve();
    }
}

