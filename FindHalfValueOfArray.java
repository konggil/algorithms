/*
dynamic programming recursion equation:

j >= a[i]:
c[i][j] = {a[i]+c[i-1][j-a[i]] or c[i-1][j]| which one is closer to value j}
j < a[i]
c[i][j] = c[i-1][j]

*/


class FindHalfValueOfArray {
	static void findHalfValueOfArray(int[] a) {
		int sum = 0, target;
		for (int x : a) {
			sum += x;
		}
		target = sum / 2;
		int[][] c = new int[a.length][target + 1];
		boolean[][] answer = new boolean[a.length][target + 1];
		for (int i = 0; i < a.length; i++) {
			c[i][0] = 0;
			answer[i][0] = false;
		}
		for (int j = 1; j <= target; j++) {
			c[0][j] = a[0];
			answer[0][j] = true;
		}
		for (int i = 1; i < a.length; i++) {
			for (int j = 1; j <= target; j++) {
				if (j - a[i] >= 0) {
					int delta1 = Math.abs(a[i] + c[i - 1][j - a[i]] - j);
					int delta2 = Math.abs(c[i - 1][j] - j);
					if (delta1 < delta2) {
						c[i][j] = a[i] + c[i - 1][j - a[i]];
						answer[i][j] = true;
					} else {
						c[i][j] = c[i - 1][j];
						answer[i][j] = false;
					}
				} else {
					c[i][j] = c[i - 1][j];
					answer[i][j] = false;
				}
			}
		}
		System.out.println("sum=" + sum + " target=" + target);
		printAnswer(a, c, answer, a.length - 1, target);
	}

	static void printAnswer(int[] a, int[][] c, boolean[][] answer, int i, int j) {
		if (i < 0 || j < 0)
			return;
		if (answer[i][j] == true) {
			printAnswer(a, c, answer, i - 1, j - a[i]);
			System.out.print(" " + a[i]);
		} else {
			printAnswer(a, c, answer, i - 1, j);
		}
	}

}
