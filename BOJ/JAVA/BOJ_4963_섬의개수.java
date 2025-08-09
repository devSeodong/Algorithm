import java.io.*;
import java.util.*;

public class Main {

	static int w, h;
	static boolean[][] v;
	static int[][] arr ;
	static int[] dr = {-1, 1, 0, 0, -1, -1, 1, 1};
	static int[] dc = {0, 0, -1, 1, -1, 1, -1, 1};

	public static void main(String[] args) throws Exception {
		// 섬 하나 -> 가로, 세로 또는 대각선으로 연결되어 있는 정사각형

		// 입력 : w와 h, 지도(1은 땅, 0은 바다), 마지막 줄에느느 0이 두개
		// 출력 : 섬의 개수 출력

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		while (true) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			w = Integer.parseInt(st.nextToken());
			h = Integer.parseInt(st.nextToken());
			// 0,0 이기 전까지 테스트케이스 반복
			if (w == 0 && h == 0) {
				break;
			}

			v = new boolean[h][w];
			arr = new int[h][w];
			for (int i = 0; i < h; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < w; j++) {
					arr[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			int cnt = 0;
			for (int i = 0; i < h; i++) {
				for (int j = 0; j < w; j++) {
					if (arr[i][j] == 1 && !v[i][j]) {
						sol(i, j); // 너비탐색
						cnt++;
					}
				}
			}

			sb.append(cnt).append('\n');
		}
		System.out.println(sb);
	}

	public static void sol(int i, int j) {
		// 해당 요소의 델타를 탐색 -> 만약 1이 있다면 그 1의 델타를 탐색,
		Queue<int[]> q = new LinkedList<>();

		v[i][j] = true;
		q.add(new int[]{i, j});

		while(!q.isEmpty()) {
			int[] tmp = q.poll();
			int r = tmp[0], c = tmp[1];
			for (int d = 0; d < 8; d++) {
				int nr = r + dr[d];
				int nc = c + dc[d];
				if (nr < 0 || nr >= h || nc < 0 || nc >= w) {
					continue;
				}

				if (arr[nr][nc] == 1 && !v[nr][nc]) {
					v[nr][nc] = true;
					q.add(new int[]{nr, nc});
				}
			}
		}
	}
}
