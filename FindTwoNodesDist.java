
public class FindTwoNodesDist {
	public static void main(String[] args) {
		TreeNode node1 = new TreeNode('A', null, null);
		TreeNode node2 = new TreeNode('B', null, null);
		TreeNode node3 = new TreeNode('C', null, null);
		TreeNode node4 = new TreeNode('D', null, null);
		TreeNode node5 = new TreeNode('E', null, null);
		TreeNode node6 = new TreeNode('F', null, null);
		TreeNode node7 = new TreeNode('G', null, null);
		TreeNode node8 = new TreeNode('H', null, null);
		node1.left = node2;
		node1.right = node3;
		node2.left = node4;
		node2.right = node5;
		node3.right = node6;
		node4.left = node7;
		node6.right = node8;
		find(node1, 'H', 'G');
		System.out.println(distance);
	}

	static int distance = 0;

	static int find(TreeNode node, char a, char b) {
		if (node == null) {
			return -1;
		}
		if (node.c == a || node.c == b) {
			return 1;
		}
		int leftDistance = find(node.left, a, b);
		int rightDistance = find(node.right, a, b);
		if (leftDistance != -1 && rightDistance != -1) {
			distance = leftDistance + rightDistance;
			return -1;
		}
		if (leftDistance != -1) {
			return leftDistance + 1;
		}
		if (rightDistance != -1) {
			return rightDistance + 1;
		}
		return -1;
	}

	static class TreeNode {
		char c;
		TreeNode left;
		TreeNode right;

		TreeNode(char c, TreeNode left, TreeNode right) {
			this.c = c;
			this.left = left;
			this.right = right;
		}
	}
}