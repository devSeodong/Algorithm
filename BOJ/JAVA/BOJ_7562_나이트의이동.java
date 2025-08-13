import java.io.*;
import java.util.*;

public class Main {
	static int l, r2, c2;
	static int[][] cnt;
	static boolean[][] v;
	static int[] dr = {-2, -1, 1, 2, 2, 1, -1, -2};
	static int[] dc = {-1, -2, -2, -1, 1, 2, 2, 1};
	public static void main(String[] args) throws IOException{
		// 체스판 위의 나이트
		// 나이트가 한번에 이동할 수 있는 칸 -> 몇 번 움직이면 ?

		// 입력 : 테케 개수, 체스판 한 변의 길이 l, 현재 있는 칸, 이동하려고 하는 칸
		// 출력 : 최소 몇번만에 이동할 수 있는지

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		for(int t=0; t<T; t++) {
			l = Integer.parseInt(br.readLine());
			v = new boolean[l][l];
			cnt = new int[l][l];

			st = new StringTokenizer(br.readLine());
			int r1 = Integer.parseInt(st.nextToken());
			int c1 = Integer.parseInt(st.nextToken());

			st = new StringTokenizer(br.readLine());
			r2 = Integer.parseInt(st.nextToken());
			c2 = Integer.parseInt(st.nextToken());

			System.out.println(bfs(r1, c1));
		}
	}

	public static int bfs(int i, int j) {
		ArrayDeque<int[]> dq = new ArrayDeque<>();
		dq.add(new int[]{i,j});
		v[i][j] = true;
		cnt[i][j] = 0;

		while(!dq.isEmpty()) {
			int[] tmp = dq.poll();
			int r = tmp[0], c = tmp[1];
			if(r == r2 && c == c2) return cnt[r][c];
			for(int d=0; d<8; d++) {
				int nr = r + dr[d];
				int nc = c + dc[d];
				if(nr < 0 || nr >= l || nc < 0 || nc >= l) continue;

				if(!v[nr][nc]) {
					v[nr][nc] = true;
					cnt[nr][nc] = cnt[r][c] + 1;
					dq.add(new int[]{nr, nc});
				}
			}
		}
		return -1;
	}
}
