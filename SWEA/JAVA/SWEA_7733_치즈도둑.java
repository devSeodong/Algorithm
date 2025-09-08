import java.io.*;
import java.util.*;

public class Solution {
	static int N, D=100;
	static int[][] arr;
	static boolean[][] v;
	static int[][] RC = {{-1, 1, 0, 0}, {0, 0, -1, 1}};
	public static void main(String[] args) throws IOException{
		// N*N 치즈 
		// 100일동안 치즈를 갉아먹음
		// X 번째 날에는 맛있는 정도가 X인 칸을 먹어버림
		// 100일 중에서 치즈덩어리가 가장 많을 때의 덩어리 개수 
		
		// 입력 : 테케 T, 한변의 길이 N, 치즈 정보 
		// 출력 : #t 100일 중에서 가장 덩어리가 많을 때의 덩어리 개수
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for(int t=1; t<=T; t++) {
			N = Integer.parseInt(br.readLine());
			arr = new int[N][N];
			for(int i=0; i<N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for(int j=0; j<N; j++) {
					arr[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			int max = Integer.MIN_VALUE;
			for(int d=0; d<=D; d++) { // 날짜 누적
				max = Math.max(max, solve(d));
			}
			
			System.out.println("#"+t+" "+max);
		}
	}
	
	public static int solve(int day) {
		int cnt = 0;
		v = new boolean[N][N];
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(arr[i][j] > day && !v[i][j]) {
					bfs(i, j, day);
					cnt++;
				}
			}
		}
		return cnt;
	}
	
	public static void bfs(int i, int j, int day){
		Deque<int[]> dq = new ArrayDeque<>();
		dq.add(new int[] {i, j});
		v[i][j] = true;
		
		while(!dq.isEmpty()) {
			int[] cur = dq.poll();
			for(int d=0; d<4; d++) {
				int nr = cur[0] + RC[0][d];
				int nc = cur[1] + RC[1][d];
				if(nr<0 || nr>=N || nc<0 || nc>=N) continue;
				if(arr[nr][nc] > day && !v[nr][nc]) {
					dq.add(new int[] {nr, nc});
					v[nr][nc] = true;
				}
			}
		}
	}
}