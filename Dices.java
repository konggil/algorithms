public class Dices{
	public static void main(String[] args) {
		slices(5, 8, 7);
	}

	public static void slices(int m, int n, int k) {
		int[][] slices = new int[m + 1][k + 1];
		for (int a = 2; a <= m; a++) {
			slices[a][1] = 0;
		}
		for (int b = 1; b <= k; b++) {
			if (b <= n) {
				slices[1][b] = 1;
			} else {
				slices[1][b] = 0;
			}
		}
		for (int a = 2; a <= m; a++) {
			for (int b = 2; b <= k; b++) {
				slices[a][b] = 0;
				for (int c = 1; c <= n; c++) {
					if (c < b) {
						slices[a][b] += slices[a - 1][b - c];
					}
				}
			}
		}
		for (int a = 1; a <= m; a++) {
			for (int b = 1; b <= k; b++) {
				System.out.print(slices[a][b] + " ");
			}
			System.out.println();
		}
	}

}