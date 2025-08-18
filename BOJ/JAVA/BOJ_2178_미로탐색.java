import java.io.*;
import java.util.*;

public class Main {
	static int[][] arr, cnt;
	static boolean[][] v;
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	static int N, M;
	public static void main(String[] args) throws IOException{
		// N*M 미로
		// 1은 이동할 수 있는 칸, 0은 이동할 수 없는 칸
		// (1,1)에서 출발, (N, M)의 위치로 이동
		// 지나야 하는 최소 칸 수 ( 인접한 칸으로만 이동 )

		// 입력 : 두 정수 N, M , 미로 정보 공백 없이
		// 출력 : 지나야 하는 최소 칸 수

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		arr = new int[N][M];
		cnt = new int[N][M];
		v = new boolean[N][M];
		for(int i=0; i<N; i++) {
			String str = br.readLine();
			for(int j=0; j<M; j++) {
				arr[i][j] = str.charAt(j) -'0';
			}
		}

		System.out.println(bfs(0, 0));
	}

	public static int bfs(int i, int j) {
		ArrayDeque<int[]> dq = new ArrayDeque<>();
		dq.add(new int[]{i, j});
		v[i][j] = true;
		cnt[i][j] = 1;

		while(!dq.isEmpty()) {
			int[] tmp = dq.poll();
			int r = tmp[0], c = tmp[1];
			if(r == N-1 && c == M-1) return cnt[r][c];

			for(int d=0; d<4; d++) {
				int nr = r + dr[d];
				int nc = c + dc[d];
				if(nr < 0 || nr >= N || nc < 0 || nc >= M ) continue;

				if(!v[nr][nc] && arr[nr][nc] == 1) {
					v[nr][nc] = true;
					cnt[nr][nc] = cnt[r][c] + 1;
					dq.add(new int[]{nr, nc});
				}
			}
		}
		return -1;
	}
}
