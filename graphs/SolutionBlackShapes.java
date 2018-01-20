import java.util.concurrent.atomic.AtomicInteger;

public class SolutionBlackShapes {
    private HashMap<Integer, Vertex> vMap = new HashMap<Integer, Vertex>();
    private int[][] graph;
    private AtomicInteger atomicInteger = new AtomicInteger(1);

    // Generate Unique Id
    public int generateId() {
        return atomicInteger.incrementAndGet();
    }

    // Represent a Vertex
    public class Vertex {
        int id, row, col;

        HashSet<Integer> adjacent = new HashSet<Integer>();
        public Vertex(int id, int row, int col) {
            this.id = id;
            this.row = row;
            this.col = col;
        }

        public void addAdjacent(int id) {
            adjacent.add(id);
        }

        public String toString() {
            return " id=" + id + " (" + row + "," + col + ")";
        }
    }


    public int black(ArrayList<String> A) {
        if (A == null || A.isEmpty()) return 0;

        int ans = 0;
        int numRows = A.size();
        int numCols = A.get(0).length();
        graph = new int[numRows][numCols];

        // Generate graph of X's
        for (int r = 0; r < A.size(); r++) {
            String row = A.get(r);
            for (int c = 0; c < row.length(); c++) {
                char letter = row.charAt(c);
                if (letter == 'X') {
                    int id = generateId();
                    Vertex v = new Vertex(id, r, c);
                    vMap.put(id, v);
                    graph[r][c] = id;
                }
            }
        }

        addConnections(numRows, numCols);
        HashSet<Integer> visited = new HashSet<Integer>();

        // Visit all X's
        for (Vertex v : vMap.values()) {
            if (!visited.contains(v.id)) {
                visited.add(v.id);

                //visit vertex's connections
                bfs(v.id, visited);
                ans++;
            }
        }

        return ans;
    }

    /*
     * BFS of graph given vertex id
     */
    public void bfs(int id, HashSet<Integer> visited) {
        // Create a queue for BFS
        LinkedList<Integer> queue = new LinkedList<Integer>();

        // Mark the current node as visited and enqueue it
        visited.add(id);
        queue.add(id);

        while (!queue.isEmpty()) {
            int vId = queue.poll();
            Vertex v = vMap.get(vId);

            // Get all adjacent vertices of the dequeued vertex
            // If a adjacent has not been visited, then mark it
            // visited and enqueue it
            for (Integer adj : v.adjacent) {
                if (!visited.contains(adj)) {
                    visited.add(adj);
                    queue.add(adj);
                }
            }
        }
    }

    /**
     * Add connection between vertices
     */
    public void addConnections(int numRows, int numCols) {
        // Add connections
        for (Vertex v : vMap.values()) {
            int r = v.row;
            int c = v.col;

            // left of vertex
            if (c-1 >= 0) {
                int leftId = graph[r][c-1];
                if (vMap.containsKey(leftId)) {
                    v.addAdjacent(leftId);
                }
            }

            // right of vertex
            if (c+1 < numCols) {
                int rightId = graph[r][c+1];
                if (vMap.containsKey(rightId)) {
                    v.addAdjacent(rightId);
                }
            }

            // above of vertex
            if (r-1 >= 0) {
                int topId = graph[r-1][c];
                if (vMap.containsKey(topId)) {
                    v.addAdjacent(topId);
                }
            }

            // below of vertex
            if (r+1 < numRows) {
                int bottomId = graph[r+1][c];
                if (vMap.containsKey(bottomId)) {
                    v.addAdjacent(bottomId);
                }
            }
        }
    }
}
