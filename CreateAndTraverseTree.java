public class CreateAndTraverseTree {

	public static void main(String[] args) {
		int[] input = new int[] { 4, 2, 5, 5, 6, 1, 4 };
		TreeNode root = createTree(input);
		System.out.println(traverseTree(root));
	}

	static TreeNode createTree(int[] scores) {
		if (scores == null || scores.length == 0) {
			return null;
		}
		TreeNode root = new TreeNode(scores[0], 1, null, null);
		for (int i = 1; i < scores.length; i++) {
			TreeNode currNode = root;
			while (true) {
				if (currNode.score == scores[i]) {
					currNode.times++;
					break;
				} else if (currNode.score > scores[i]) {
					if (currNode.leftNode == null) {
						currNode.leftNode = new TreeNode(scores[i], 1, null, null);
						break;
					} else {
						currNode = currNode.leftNode;
					}
				} else {
					if (currNode.rightNode == null) {
						currNode.rightNode = new TreeNode(scores[i], 1, null, null);
						break;
					} else {
						currNode = currNode.rightNode;
					}
				}
			}
		}
		return root;
	}

	static String traverseTree(TreeNode root) {
		if (root == null) {
			return "";
		}
		StringBuilder output = new StringBuilder();
		Queue<TreeNode> queue = new LinkedList<TreeNode>();
		queue.offer(root);
		int nodeCount = 1;

		while (!queue.isEmpty() && nodeCount > 0) {
			TreeNode node = queue.poll();
			if (node.score != -1) {
				output.append("," + node.score + ":" + node.times);
				nodeCount--;
			} else {
				output.append(",");
				queue.offer(new TreeNode(-1, -1, null, null));
				queue.offer(new TreeNode(-1, -1, null, null));
				continue;
			}
			if (node.leftNode != null) {

				queue.offer(node.leftNode);
				nodeCount++;
			} else {
				queue.offer(new TreeNode(-1, -1, null, null));
			}
			if (node.rightNode != null) {
				queue.offer(node.rightNode);
				nodeCount++;
			} else {
				queue.offer(new TreeNode(-1, -1, null, null));
			}
		}

		return output.substring(1);
	}

	static class TreeNode {
		int score;
		int times;
		TreeNode leftNode;
		TreeNode rightNode;

		TreeNode(int score, int times, TreeNode leftNode, TreeNode rightNode) {
			this.score = score;
			this.times = times;
			this.leftNode = leftNode;
			this.rightNode = rightNode;
		}
	}
}