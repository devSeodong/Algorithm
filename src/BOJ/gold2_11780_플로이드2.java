package BOJ;

import java.io.*;
import java.util.*;

public class gold2_11780_플로이드2 {
	// 무한대(INF) 값 정의 — 충분히 큰 값 (도시 간 연결이 없음을 의미)
	static final int INF = 1000000000;

	public static void main(String[] args) throws IOException {
		// 입력을 빠르게 받기 위한 BufferedReader 선언
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		// 도시의 개수 n
		int n = Integer.parseInt(br.readLine());
		// 버스의 개수 m
		int m = Integer.parseInt(br.readLine());

		// graph[i][j] : i → j 최소 비용 저장할 배열
		int[][] graph = new int[n + 1][n + 1];
		// next[i][j] : i에서 j로 가는 경로 중 ‘첫 번째로 이동할 도시’를 저장
		int[][] next = new int[n + 1][n + 1];

		// 그래프 초기화
		for (int i = 1; i <= n; i++) {
			Arrays.fill(graph[i], INF);   // 일단 모두 무한대로 채움
			graph[i][i] = 0;              // 자기 자신으로 가는 비용은 0
		}

		// 버스 정보 입력
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken()); // 출발 도시
			int b = Integer.parseInt(st.nextToken()); // 도착 도시
			int w = Integer.parseInt(st.nextToken()); // 비용

			// 같은 경로가 여러 개 있을 수 있으므로 최소 비용만 저장
			if (graph[a][b] > w) {
				graph[a][b] = w;  // 더 작은 비용으로 갱신
				next[a][b] = b;   // 바로 도착하므로 첫 번째 경로는 b
			}
		}

		// 플로이드-워셜 알고리즘 (모든 정점 쌍의 최단 경로 구하기)
		for (int k = 1; k <= n; k++) {             // k: 중간 경유 도시
			for (int i = 1; i <= n; i++) {         // i: 출발 도시
				if (graph[i][k] == INF) continue;  // i→k 경로가 없으면 패스
				for (int j = 1; j <= n; j++) {     // j: 도착 도시
					if (graph[k][j] == INF) continue; // k→j 경로 없으면 패스

					int cand = graph[i][k] + graph[k][j]; // i→k→j 경로의 비용 계산
					if (cand < graph[i][j]) {             // 더 짧은 경로라면 갱신
						graph[i][j] = cand;               // 최소비용 업데이트
						next[i][j] = next[i][k];          // i→j의 첫 이동 도시 갱신
					}
				}
			}
		}

		// 결과 출력을 위한 StringBuilder
		StringBuilder sb = new StringBuilder();

		// [1] 최소비용 행렬 출력
		for (int i = 1; i <= n; i++) {                // 출발 도시
			for (int j = 1; j <= n; j++) {            // 도착 도시
				// 경로가 없는 경우 0 출력
				sb.append(graph[i][j] == INF ? 0 : graph[i][j]).append(' ');
			}
			sb.append('\n');
		}

		// [2] 경로 출력
		for (int i = 1; i <= n; i++) {                // 출발 도시
			for (int j = 1; j <= n; j++) {            // 도착 도시
				// 자기 자신이거나 경로가 없는 경우
				if (i == j || next[i][j] == 0) {
					sb.append(0).append('\n');        // 경로 개수 0 출력
					continue;
				}

				// 경로 복원
				List<Integer> path = new ArrayList<>(); // 경로를 저장할 리스트
				int cur = i;                            // 현재 위치 = 출발 도시
				path.add(cur);                          // 출발 도시 추가

				// next 배열을 이용해 다음 도시를 따라가며 도착 도시까지 반복
				while (cur != j) {
					cur = next[cur][j];                 // 다음 도시로 이동
					path.add(cur);                      // 이동한 도시 저장
				}

				// 경로의 도시 개수와 경로 전체를 출력 형식에 맞게 추가
				sb.append(path.size()).append(' ');
				for (int v : path) sb.append(v).append(' ');
				sb.append('\n');
			}
		}

		// 모든 결과 출력
		System.out.print(sb);
	}
}
