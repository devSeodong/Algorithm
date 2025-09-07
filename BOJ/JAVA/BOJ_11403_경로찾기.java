import java.io.*;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		// 가중치 없는 방향 그래프 G
		// i에서 j로 가는 길이가 양수인 경로가 있는지 없는지

		// 입력 : 정점의 개수 N, 그래프의 인접 행렬
		// 출력 : N개의 줄에 정답을 인접행렬 형식으로 출력

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(br.readLine());

		int[][] arr = new int[N][N];
		for(int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		for(int i = 0; i < N; i++) {
			boolean[] v = new boolean[N];
			Deque<Integer> dq = new ArrayDeque<>();
			dq.add(i);

			while(!dq.isEmpty()) {
				int cur = dq.poll();
				for(int j = 0; j < N; j++) {
					if (arr[cur][j]==1 && !v[j]) {
						v[j] = true;
						dq.add(j);
					}
				}
			}

			for(int j = 0; j < N; j++) {
				sb.append(v[j]?1:0).append(" ");
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}
}
