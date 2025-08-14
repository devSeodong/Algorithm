import java.io.*;
import java.util.*;

public class Main {
	static int N;
	static char[][] arr;
	static boolean[][] v;
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };

	public static void main(String[] args) throws IOException {
		// 적록색약 , N*N, R/G/B

		// 입력 : N, 그림
		// 출력 : 적록색약이 아닌 사람 / 적록색약인 사람 구역의 수

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		arr = new char[N][N];
		
		for (int i = 0; i < N; i++) {
			arr[i] = br.readLine().toCharArray();
		}
		
		int cnt1 = solve(false);
		int cnt2 = solve(true);
		

		System.out.println(cnt1+" "+cnt2);
	}
	
	public static int solve(boolean blind) {
		v = new boolean[N][N];
		int cnt = 0;
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(!v[i][j]) {
					bfs(i, j, blind);
					cnt++;
				}
			}
		}
		return cnt;
	}

	public static void bfs(int i, int j, boolean blind) {
		ArrayDeque<int[]> ad = new ArrayDeque<>();
		ad.add(new int[] {i, j});
		v[i][j] = true;
		
		while(!ad.isEmpty()) {
			int[] cur = ad.poll();
			int r = cur[0], c = cur[1];
			for(int d=0; d<4; d++) {
				int nr = r + dr[d];
				int nc = c + dc[d];
				if(nr <0|| nr>=N || nc <0 || nc>=N || v[nr][nc]) continue;
				
				if(isOk(arr[r][c], arr[nr][nc], blind)) {
					v[nr][nc] = true;
					ad.add(new int[] {nr, nc});
				}
			}
		}
	}
	
	public static boolean isOk(char a, char b, boolean blind) {
		if(a==b) return true;
		return blind?(a=='R'&& b=='G')||(a=='G' && b=='R'):false;
	}
}
