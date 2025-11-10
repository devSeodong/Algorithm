package BOJ;

import java.io.*;
import java.util.*;

public class gold4_20497_Bessies {
	static int N;
	static char[][] arr;
	static boolean[][] V;
	static int[][] RC = {{-1, 1, 0, 0}, {0, 0, -1, 1}};
	static List<int[]> list;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		arr = new char[N][N];
		list = new ArrayList<>();
		
		for(int i=0; i<N; i++) {
			String str = br.readLine();
			for(int j=0; j<N; j++) {
				char c = str.charAt(j);
				if(c == '.') {
					list.add(new int[] {i, j});
				}
				arr[i][j] = c;
			}
		}
		
		int cnt1 = solve(), res = 0;
		for(int[] l : list) {
			arr[l[0]][l[1]] = '@';
			if(cnt1 < solve()) res++;
			arr[l[0]][l[1]] = '.';
		}
		
		System.out.println(res);
	}
	
	public static int solve() {
		int cnt = 0;
		V = new boolean[N][N];
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(!V[i][j] && arr[i][j]=='.') {
					bfs(i, j);
					cnt++;
				}
			}
		}
		return cnt;
	}
	
	public static void bfs(int r, int c) {
		Deque<int[]> dq = new ArrayDeque<>();
		dq.add(new int[] {r, c});
		V[r][c] = true;
		
		while(!dq.isEmpty()) {
			int[] cur = dq.poll();
			for(int d=0; d<4; d++) {
				int nr = cur[0] + RC[0][d];
				int nc = cur[1] + RC[1][d];
				
				if(nr < 0 || nr >= N || nc < 0 || nc >= N || V[nr][nc] || arr[nr][nc] == 'S' || arr[nr][nc] == '@') continue;
				
				dq.add(new int[] {nr, nc});
				V[nr][nc] = true;
			}
		} 
	}
}
