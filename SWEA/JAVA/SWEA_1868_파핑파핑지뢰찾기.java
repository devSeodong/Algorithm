package SWEA;

import java.io.*;
import java.util.*;

public class Solution {
	static int N;
	static char[][] arr;
	static boolean[][] v;
	static int[][] adj;
	static int[][] RC = { { -1, 1, 0, 0, -1, -1, 1, 1 }, { 0, 0, -1, 1, -1, 1, -1, 1 } };

	public static void main(String[] args) throws IOException {
		// 각 칸에 지뢰가 있을 수도 있고 없을 수도 있음
		// 지뢰가 있는 칸 클릭 시 게임 끝
		// 8칸에 대해 몇 개의 지뢰가 있는지가 숫자로 클릭한 칸에 표시됨
		// 0이라면 지뢰 없다는 것 확정 ( 0이면 연속적으로 숫자 표시 )

		// 입력 : 테케 T, N, N인 문자열 (지뢰판 정보 )-> *지뢰 있음, .지뢰 없음
		// 출력 : #t 최소 몇 번의 클릭?

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			N = Integer.parseInt(br.readLine());
			arr = new char[N][N];
			
			for (int i = 0; i < N; i++) {
				arr[i] = br.readLine().toCharArray();
			}

			v = new boolean[N][N];
			adj = new int[N][N];
			for(int i=0; i<N; i++) {
				Arrays.fill(adj[i], -1);
			}
			
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					if(arr[i][j] == '.') {
						adj[i][j] = solve(i, j);
					} 
				}
			}
			
			int cnt = 0;
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					if(arr[i][j] == '.' && !v[i][j] && adj[i][j] == 0) {
						bfs(i, j);
						cnt++;
					} 
				}
			}
			
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					if(arr[i][j] == '.' && !v[i][j]) {
						cnt++;
					} 
				}
			}
			
			System.out.println("#"+ t + " "+ cnt);
		}
	}

	public static int solve(int r, int c) {
		int cnt = 0;
		for (int d = 0; d < 8; d++) {
			int nr = r + RC[0][d];
			int nc = c + RC[1][d];
			if (nr < 0 || nr >= N || nc < 0 || nc >= N)
				continue;

			if (arr[nr][nc] == '*') {
				cnt++;
			}
		}

		return cnt;
	}

	public static void bfs(int i, int j) {
		Deque<int[]> dq = new ArrayDeque<>();
		dq.add(new int[] { i, j });
		v[i][j] = true;

		while (!dq.isEmpty()) {
			int[] cur = dq.poll();
			int r = cur[0], c = cur[1];

			if (adj[r][c] == 0) {
				for (int d = 0; d < 8; d++) {
					int nr = r + RC[0][d];
					int nc = c + RC[1][d];
					if (nr < 0 || nr >= N || nc < 0 || nc >= N)
						continue;
					if (arr[nr][nc] == '*' || v[nr][nc])
						continue;

					v[nr][nc] = true;
					if (adj[nr][nc] == 0)
						dq.add(new int[] { nr, nc });
				}
			}
		}

	}
}