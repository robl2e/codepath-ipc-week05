/**
 * Definition for binary tree
 * class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) {
 *      val = x;
 *      left=null;
 *      right=null;
 *     }
 * }
 */
public class SolutionLevelOrder {
    public ArrayList<ArrayList<Integer>> levelOrder(TreeNode A) {
        ArrayList<ArrayList<Integer>> ans = new ArrayList<>();

        ArrayList<Integer> levelList = new ArrayList<>();
        Queue<TreeNode> q = new LinkedList<TreeNode>();
        q.offer(A);

        while (!q.isEmpty()) {
            int level = q.size();

            for (int i = 0; i < level; i++) {
                TreeNode node = q.poll();
                levelList.add(node.val);

                if (node.left != null) {
                    q.offer(node.left);
                }

                if (node.right != null) {
                    q.offer(node.right);
                }
            }
            ans.add(new ArrayList(levelList));
            levelList.clear();
        }
        return ans;
    }
}
