public class SolutionLargestDistanceBetweenNodesTree {
    private HashMap<Integer, Vertex> graph = new HashMap<Integer, Vertex>();
    private HashSet<Integer> visited = new HashSet<Integer>();
    private int[] dst; //level

    // Represent a Vertex
    public class Vertex {
        int id;
        HashSet<Integer> adjacent = new HashSet<Integer>();

        public Vertex(int id) {
            this.id = id;
        }

        public void addAdjacent(int id) {
            adjacent.add(id);
        }

        public String toString() {
            return " id=" + id + " adj=" + adjacent.toString();
        }
    }

    public int solve(ArrayList<Integer> A) {
        if (A == null || A.isEmpty()) return 0;
        if (A.size() == 2) return 1;

        int rootIndex = buildGraph(A);
//        System.out.println("graph = " + graph.toString());

        dst = new int[graph.size()];
        bfs(0);
        int longestIndexFrom = findIndexLargestDistance(dst);

        // Reset values
        visited.clear();
        dst = new int[graph.size()];

        // Find the longest path from longest Index
        bfs(longestIndexFrom);
     //   printDistances();

        int longestPathIndex = findIndexLargestDistance(dst);
        int max = dst[longestPathIndex];
 //       printDistances();

        return max;
    }

    private int findIndexLargestDistance(int[] dst) {
        int maxIndex = 0;
        int maxDis = Integer.MIN_VALUE;
        for (int i = 0; i < dst.length; i++) {
            if (dst[i] > maxDis) {
                maxDis = dst[i];
                maxIndex = i;
            }
        }
        return maxIndex;
    }

    private void bfs(int node) {
        Queue<Integer> q =
            new LinkedList<Integer>();
        q.offer(node);

        while (!q.isEmpty()) {
            Integer gNode = q.poll();
            visited.add(gNode);

            Vertex v = graph.get(gNode);
            if (v.adjacent == null) continue;

            for (Integer n : v.adjacent) {
                if (!visited.contains(n)) {
                    q.offer(n);

                    // distance of adj is
                    // one more than parent
                    dst[n] = dst[gNode] + 1;
                }
            }
        }
    }

    /**
     * Build graph returning index of root node
     */
    private int buildGraph(ArrayList<Integer> A) {
        int indexRoot = Integer.MIN_VALUE;

        // Build Graph
        for (int i = 0; i < A.size(); i++) {
            Vertex v = new Vertex(i);
            int adjNode = A.get(i); //edge

            // Found root node
            if (adjNode == -1) {
                indexRoot = i;
            } else {
                v.addAdjacent(adjNode);

                // Graph is undirected add connections
                // in other vertex
                if (graph.containsKey(adjNode)) {
                    Vertex adj = graph.get(adjNode);
                    adj.addAdjacent(v.id);
                }
            }
            graph.put(i, v);
        }
        return indexRoot;
    }

    /**
     * Debugging print method
     */
    private void printDistances() {
        ArrayList<Integer> distance = new ArrayList<>();
        for (int d : dst) {
            distance.add(d);
        }
        System.out.println("dst = " + distance.toString());
    }
}
