public class SolutionPossibleFinishCoursesWithPrereq {
    public static final int POSS = 1;
    public static final int NOT_P = 0;

    public int solve(int N, ArrayList<Integer> B, ArrayList<Integer> C) {
        boolean ans = bfs(N, B, C);
        return ans ? POSS : NOT_P;
    }

    public boolean bfs(int n, ArrayList<Integer> B, ArrayList<Integer> C) {
        if (n <= 0) return false;

        // N+1 since course numbers start at index 1
        //inDegree index == course number, val == # prequisite needed
    	int[] inDegree = new int[n+1];
    	for (int i = 0; i < B.size(); i++) {
    	    int course = C.get(i);
    		inDegree[course]++; //
    	}

        Queue<Integer> queue = new LinkedList<Integer>();
        // Add courses with no prequisites to the queue
    	for (int i = 1; i < inDegree.length; i++) { // Courses start at index 1
    		if (inDegree[i] == 0) { //
    			queue.offer(i);
    		}
    	}

    	while (!queue.isEmpty()) {
    		int c = queue.poll(); // finished course

    		for (int i = 0; i < B.size(); i++) {
    		    int pre = B.get(i);

    		    // finished prequsite course decrease amount
    			if (c == pre) {
    			    int course = C.get(i);
    				inDegree[course]--;
    				// No prequsite add to queue
    				if (inDegree[course] == 0) {
    					queue.offer(course);
    				}
    			}
    		}
    	}

    	// Iterate to see if all courses finished
    	for (int i = 1; i < inDegree.length; i++) {
    		if (inDegree[i] != 0) {
    			return false;
    		}
    	}
    	return true;
    }
}
