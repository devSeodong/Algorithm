package BOJ;

import java.io.*;
import java.util.*;

public class gold5_17070_파이프옮기기1 {
	public static void main(String[] args) throws IOException {
		// N*N 
		// 파이프 밀면서 회전 ( 45도만  )
		
		// 입력 : N, 집의 상태 ( 0, 1 )
		// 출력 : 파이프의 한쪽 끝을 N, N으로 이동시키는 방법의 수
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		int[][] arr = new int[N][N];
		int[][][] dp = new int[N][N][3];
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		dp[0][1][0] = 1;
		
		for(int i=0; i<N; i++) {
			for(int j=2; j<N; j++) {
				if(j-1 >= 0 && arr[i][j] == 0) {
					dp[i][j][0] = dp[i][j-1][0] + dp[i][j-1][2];
				}
				
				if(i-1 >= 0 && arr[i][j] == 0) { 
					dp[i][j][1] = dp[i-1][j][1] + dp[i-1][j][2];
				}
				
				if(j-1 >= 0 && i-1 >= 0 && arr[i][j] == 0 && arr[i-1][j] == 0 && arr[i][j-1] == 0) {
					dp[i][j][2] = dp[i-1][j-1][0] + dp[i-1][j-1][1] + dp[i-1][j-1][2];
				}
			}
		}
		
		System.out.println(dp[N-1][N-1][0]+dp[N-1][N-1][1]+dp[N-1][N-1][2]);
	}
}
