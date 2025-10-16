package BOJ;

import java.io.*;
import java.util.*;

public class silver1_11660_구간합구하기5 {
	public static void main(String[] args) throws IOException {
		// N*N개의 수가 N*N 크기의 표에 채워져 있음 
		// (x1, y1) 부터 (x2, y2)까지 합
		
		// 입력 : N, M, 정보
		// 출력 : 합 출력 
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		int[][] arr = new int[N+1][N+1];
		int[][] dp = new int[N+1][N+1];
		
		for(int i=1; i<=N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=1; j<=N; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
				dp[i][j] = dp[i][j-1] + arr[i][j];
			}
		}
		
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int x1 = Integer.parseInt(st.nextToken());
			int y1 = Integer.parseInt(st.nextToken());
			int x2 = Integer.parseInt(st.nextToken());
			int y2 = Integer.parseInt(st.nextToken());
			
			int res = 0;
			for(int j=x1; j<=x2; j++) {
				res += dp[j][y2] - dp[j][y1-1];
			}
			
			sb.append(res).append("\n");
		}
		
		System.out.println(sb);
	}
}
