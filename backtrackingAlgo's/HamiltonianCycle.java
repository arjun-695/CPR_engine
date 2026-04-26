public class HamiltonianCycle {
    private int V; // Number of vertices
    private int[] path;

    /**
     * Check if the vertex v can be added at index 'pos' in the Hamiltonian Cycle
     */
    private boolean isSafe(int v, int[][] graph, int[] path, int pos) {
        // Check if this vertex is adjacent to the previously added vertex
        if (graph[path[pos - 1]][v] == 0) {
            return false;
        }

        // Check if the vertex has already been included in the path
        for (int i = 0; i < pos; i++) {
            if (path[i] == v) {
                return false;
            }
        }

        return true;
    }

    /**
     * A recursive utility function to solve the Hamiltonian Cycle problem
     */
    private boolean hamCycleUtil(int[][] graph, int[] path, int pos) {
        // Base case: If all vertices are included in the cycle
        if (pos == V) {
            // And if there is an edge from the last vertex to the first vertex
            return graph[path[pos - 1]][path[0]] == 1;
        }

        // Try different vertices as the next candidate in the Hamiltonian Cycle
        for (int v = 1; v < V; v++) {
            if (isSafe(v, graph, path, pos)) {
                path[pos] = v;

                if (hamCycleUtil(graph, path, pos + 1)) {
                    return true;
                }

                // If adding vertex v doesn't lead to a solution, backtrack
                path[pos] = -1;
            }
        }

        return false;
    }

    public int hamCycle(int[][] graph) {
        V = graph.length;
        path = new int[V];
        for (int i = 0; i < V; i++) {
            path[i] = -1;
        }

        // Let's start at vertex 0
        path[0] = 0;
        if (!hamCycleUtil(graph, path, 1)) {
            System.out.println("\nSolution does not exist");
            return 0;
        }

        displaySolution();
        return 1;
    }

    private void displaySolution() {
        System.out.println("Solution Exists: Following is one Hamiltonian Cycle:");
        for (int i = 0; i < V; i++) {
            System.out.print(" " + path[i] + " ");
        }
        // Print the first vertex again to complete the cycle
        System.out.println(" " + path[0] + " ");
    }

    public static void main(String[] args) {
        HamiltonianCycle ham = new HamiltonianCycle();
        
        /* Example Graph:
           (0)---(1)---(2)
            |   / |   /
            |  /  |  /
           (3)---(4)    */
        int[][] graph1 = {
            {0, 1, 0, 1, 0},
            {1, 0, 1, 1, 1},
            {0, 1, 0, 0, 1},
            {1, 1, 0, 0, 1},
            {0, 1, 1, 1, 0},
        };

        ham.hamCycle(graph1);
    }
}