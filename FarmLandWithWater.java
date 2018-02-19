public class FarmLandWithWater {

	static int maxHour = 0;

	public static void main(String[] args) {
		char[][] land = new char[][] { { 'E', 'E', 'E' }, { 'E', '#', 'W' }, { 'E', '#', 'E' } };
		boolean[][] visited;
		int maxDay = 0;
		for (int i = 0; i < 3; i++)
			for (int j = 0; j < 3; j++) {
				if (land[i][j] == 'E') {
					visited = new boolean[3][3];
					maxDay = Math.max(bfs(i, j, land, visited, 3, 3), maxDay);
				}
			}
		System.out.println(maxDay);
	}

	static int bfs(int x, int y, char[][] land, boolean[][] visited, int n, int m) {
		int newX, newY;
		Queue<Point> queue = new LinkedList<Point>();
		queue.offer(new Point(x, y, 0));
		while (!queue.isEmpty()) {
			Point currPoint = queue.poll();
			if (land[currPoint.x][currPoint.y] == 'W') {
				return currPoint.day;
			}
			newX = currPoint.x + 1;
			newY = currPoint.y;
			if (newX < n && land[newX][newY] != '#') {
				queue.offer(new Point(newX, newY, currPoint.day + 1));
			}
			newX = currPoint.x - 1;
			if (newX >= 0 && land[newX][newY] != '#') {
				queue.offer(new Point(newX, newY, currPoint.day + 1));
			}
			newX = currPoint.x;
			newY = currPoint.y + 1;
			if (newY < m && land[newX][newY] != '#') {
				queue.offer(new Point(newX, newY, currPoint.day + 1));
			}
			newY = currPoint.y - 1;
			if (newY >= 0 && land[newX][newY] != '#') {
				queue.offer(new Point(newX, newY, currPoint.day + 1));
			}
		}
		return Integer.MAX_VALUE;
	}

	static class Point {
		int x, y;
		int day;

		Point(int x, int y, int day) {
			this.x = x;
			this.y = y;
			this.day = day;
		}
	}

}
