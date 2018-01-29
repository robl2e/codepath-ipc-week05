/**
 * Definition for binary tree
 * class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
/**
 * Definition for singly-linked list.
 * class ListNode {
 *     public int val;
 *     public ListNode next;
 *     ListNode(int x) { val = x; next = null; }
 * }
 */
public class SolutionConvertSortedListToBST {
	public TreeNode sortedListToBST(ListNode a) {
	    if (a == null) return null;

	    TreeNode root = sortedListToBSTUtil(a, null);
	    return root;
	}

	public TreeNode sortedListToBSTUtil(ListNode start, ListNode end) {
        if (start == null) return null;

	    TreeNode treeNode = null;
	    ListNode mid = findMid(start, end);
	    if (mid != null) {
	        treeNode = new TreeNode(mid.val);
	       // System.out.println("mid=" + mid.val);
	    }

	    if (treeNode != null) {
    	    treeNode.left = sortedListToBSTUtil(start, mid);
	        treeNode.right = sortedListToBSTUtil(mid.next, end);
	    }
	    return treeNode;
	}

	// Helper method to find mid
	public ListNode findMid(ListNode start, ListNode end) {
        ListNode slow = start;
        ListNode fast = start;
        if (start != null && start != end) {
            while (fast != null && fast != end && fast.next != null && fast.next != end) {
                fast = fast.next.next;
                slow = slow.next;
            }
            // System.out.println("mid = " + slow.val);
	           return slow;
        }
	    return null;
    }
}
