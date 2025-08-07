import java.util.*;

class Solution {
	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, 1, -1 };
	
	public static void main(String[] args) {
		String[] storage = {"AZWQY", "CAABX", "BBDDA", "ACACA"};
		String[] requests = {"A", "BB", "A"};

		System.out.println(solution(storage, requests));
	}

	public static int solution(String[] storage, String[] requests) {
		int answer = 0;

		int n = storage.length;
		int m = storage[0].length();

		for (String request : requests) {
			boolean[][] box = new boolean[n][m];
			if (request.length() == 1) {
				for (int i = 0; i < n; i++) {
					for (int j = 0; j < m; j++) {
						if (storage[i].charAt(j) == request.charAt(0) && check(i, j, n, m, storage)) {
							box[i][j] = true;
						}
					}
				}
			} else {
				char front = request.charAt(0);
				char back = request.charAt(1);
				for (int i = 0; i < n; i++) {
					for (int j = 0; j < m; j++) {
						if (storage[i].charAt(j) == front || storage[i].charAt(j) == back) {
							box[i][j] = true;
						}
					}
				}
			}

			for (int i = 0; i < n; i++) {
				for (int j = 0; j < m; j++) {
					if (box[i][j]) {
						storage[i] = storage[i].substring(0, j) + "o" + storage[i].substring(j + 1);
					}
				}
			}
		}
		
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (storage[i].charAt(j) != 'o') {
					answer++;
				}
			}
		}

		return answer;
	}

	public static boolean check(int x, int y, int n, int m, String[] storage) {
		boolean[][] visited = new boolean[n][m];
		Queue<int[]> queue = new LinkedList<>();
		visited[x][y] = true;
		queue.add(new int[] { x, y });

		while (!queue.isEmpty()) {
			int[] tmp = queue.poll();
			int nx = tmp[0];
			int ny = tmp[1];

			for (int i = 0; i < 4; i++) {
				int nextX = nx + dx[i];
				int nextY = ny + dy[i];

				if (nextX < 0 || nextX > n - 1 || nextY < 0 || nextY > m - 1)
					return true;

				if (!visited[nextX][nextY] && storage[nextX].charAt(nextY) == 'o') {
					visited[nextX][nextY] = true;
					queue.add(new int[] { nextX, nextY });
				}
			}
		}
		return false;
	}
}