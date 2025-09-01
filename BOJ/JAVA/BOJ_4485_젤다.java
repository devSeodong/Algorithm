import java.io.*;
import java.util.*;

public class Main {
	static int N;
	static int[][] arr, RC = {{-1, 1, 0, 0}, {0, 0, -1, 1}};

	public static void main(String[] args) throws Exception {
		// 검정색 루피 -> 오히려 소지한 루피가 감소
		// N*N크기의 동굴 제일 왼쪽 위 0,0
		// N-1, N-1까지 이동해야 함.
		// 도둑 루피 만큼 소지금을 잃게 됨.

		// 잃는 금액을 최소로 하여 이동, 한번에 상하좌우 인접한 곳으로 이동

		// 입력 : N (0이면 종료), 도둑루피 크기 정보
		// 출력 : Problem t : 답

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		int t = 1;
		while (true) {
			N = Integer.parseInt(br.readLine());
			if (N == 0) break;

			arr = new int[N][N];
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					arr[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			sb.append("Problem ").append(t++).append(": ").append(solve(0, 0)).append("\n");
		}
		System.out.println(sb);
	}

	public static int solve(int i, int j) {
		// {x좌표, y좌표, 가중치}
		// 가중치를 기준으로 정렬
		PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[2] - o2[2]);
		int[][] v = new int[N][N];

		for (int k = 0; k < N; k++) {
			Arrays.fill(v[k], Integer.MAX_VALUE);
		}
		pq.add(new int[]{i, j, arr[i][j]});
		v[i][j] = arr[i][j];

		while (!pq.isEmpty()) {
			int[] cur = pq.poll();
			int r = cur[0], c = cur[1];
			for (int d = 0; d < 4; d++) {
				int nr = r + RC[0][d];
				int nc = c + RC[1][d];
				if(nr >= 0 && nr < N && nc >= 0 && nc < N) {
					if(v[nr][nc] > arr[nr][nc] + v[r][c]) {
						v[nr][nc] = arr[nr][nc] + v[r][c];
						pq.add(new int[]{nr, nc, v[nr][nc]});
					}
				}
			}
		}

		return v[N-1][N-1];
	}
}
