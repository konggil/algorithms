/*
 * https://discuss.leetcode.com/topic/99262/binary-tree-largest-sum
 */


public class FindMaxDiameterSum {

	static Set<String> set = new HashSet<String>();

	public static void main(String[] args) {
		String instruction = "RRLRLR";
		StringBuilder path = new StringBuilder();
		findPath(instruction, 1, 2, 6, 0, path);
		System.out.println(set.size());
		System.out.println(set);

	}

	static void findPath(String instruction, int start, int end, int right, int currIndex, StringBuilder path) {
		if (start == end) {
			set.add(path.toString());
		}
		if (currIndex == instruction.length() || start < 0 || end > right) {
			return;
		}
		char c = instruction.charAt(currIndex);
		path.append(c);
		if (c == 'L') {
			findPath(instruction, start - 1, end, right, currIndex + 1, path);
		} else {
			findPath(instruction, start + 1, end, right, currIndex + 1, path);
		}
		path.deleteCharAt(path.length() - 1);
		findPath(instruction, start, end, right, currIndex + 1, path);
	}

}
