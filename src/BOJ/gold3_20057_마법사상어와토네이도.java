package BOJ;

import java.io.*;
import java.util.*;

public class gold3_20057_마법사상어와토네이도 {
	static int N;
	static int[][] A, RC = {{0, 1, 0, -1},{-1, 0, 1, 0}};
	public static void main(String[] args) throws IOException {
		// 토네이도 N*N 격자로 나누어진 모래밭에서 연습
		// A[r][c] = 모래의 양 
		// 한칸 이동할때마다 모래 흩날림
		
		// 입력 : 격자 크기 N, 모래 정보 
		// 출력 : 격자의 밖으로 나간 모래의 양
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		A = new int[N][N];
		
	}
	
	public static void solve() {
		int line = 0, cnt = 1, d = 0;
		int r = N/2, c = N/2;
		
		while(N > line) {
			int nr = r + RC[0][d];
			int nc = c + RC[1][d];
			
			
		}
	}
}
 