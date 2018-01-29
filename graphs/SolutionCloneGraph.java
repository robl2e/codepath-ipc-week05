/**
 * Definition for undirected graph.
 * class UndirectedGraphNode {
 *     int label;
 *     List<UndirectedGraphNode> neighbors;
 *     UndirectedGraphNode(int x) { label = x; neighbors = new ArrayList<UndirectedGraphNode>(); }
 * };
 */
public class SolutionCloneGraph {
    HashMap<Integer, UndirectedGraphNode> copyG =
        new HashMap<Integer, UndirectedGraphNode>();

    public UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
        if (node == null) return null;

        UndirectedGraphNode copy = new UndirectedGraphNode(node.label);
        copyG.put(copy.label, copy);

        bfs(node);
        return copy;
    }

    public void bfs(UndirectedGraphNode node) {
        if (node == null) return;

        Queue<UndirectedGraphNode> q =
            new LinkedList<UndirectedGraphNode>();
        q.offer(node);

        while (!q.isEmpty()) {
            UndirectedGraphNode gNode = q.poll();

            // Obtain the copy version of gNode if exists
            // otherwise create one and add it
            UndirectedGraphNode copy;
            if (copyG.containsKey(gNode.label)) {
                copy = copyG.get(gNode.label);
            } else {
                copy = new UndirectedGraphNode(gNode.label);
                copyG.put(copy.label, copy);
            }

            if (gNode.neighbors == null) continue;

            for (UndirectedGraphNode n : gNode.neighbors) {
                // Handle nodes visited already
                if (!copyG.containsKey(n.label)) {
                    copyG.put(n.label, new UndirectedGraphNode(n.label));
                    q.offer(n);
                }
                // For each neighor link/connect them to copy
                addNeighbor(n, copy);
            }
        }
    }

    public void addNeighbor(UndirectedGraphNode src, UndirectedGraphNode dest) {
        // Initialize neighbors list
        if (dest.neighbors == null) {
            dest.neighbors = new ArrayList<>();
        }

        // Check whether neighbor node has already been
        // created
        UndirectedGraphNode neighbor;
        if (copyG.containsKey(src.label)) {
            neighbor = copyG.get(src.label);
        } else {
            neighbor = new UndirectedGraphNode(src.label);
            copyG.put(neighbor.label, neighbor);
        }

        // Check whether it already has been added
        if (!dest.neighbors.contains(neighbor)) {
            dest.neighbors.add(neighbor);
        }
    }


}
