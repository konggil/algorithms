/*
    https://discuss.leetcode.com/topic/32268/find-max-distance-between-any-two-nodes-of-a-binary-tree-with-at-most-a-single-bent
    one bent means: LLLLLRRRRRR or RRRRRRLLLLLL
    more than one bent like: LLLRRRLLL
*/

	
public class FindMaxDistanceByOneBent {

	static int max = Integer.MIN_VALUE;

	public static void main(String[] args) {
		TreeNode t1, t2, t3, t4, t5;
		t1 = new TreeNode();
		t2 = new TreeNode();
		t3 = new TreeNode();
		t4 = new TreeNode();
		t5 = new TreeNode();
		t1.left = t2;
		t1.right = t3;
		t2.left = t4;
		t3.right = t5;
		findDistance(t1, true);
		System.out.println(max);
	}

	static int findDistance(TreeNode treeNode, boolean leftRight) {
		if (treeNode == null) {
			return 0;
		}
		int leftDistance = findDistance(treeNode.left, false);
		int rightDistance = findDistance(treeNode.right, true);
		max = Math.max(max, leftDistance + rightDistance);
		return leftRight ? rightDistance + 1 : leftDistance + 1;

	}

	static class TreeNode {
		TreeNode left;
		TreeNode right;
	}
}
