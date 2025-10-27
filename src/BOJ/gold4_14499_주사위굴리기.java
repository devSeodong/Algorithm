package BOJ;

import java.io.*;
import java.util.*;

public class gold4_14499_주사위굴리기 {
	static int N, M, C;
	static int[] diceInfo, up = new int[]{1, 1};
	static int[][] map;
	static int[][] dice = {{-1, 0, -1},{0, 0, 0},{-1, 0, -1},{-1, 0, -1}};
	static int[][] RC = {{0, 0, -1, 1},{1, -1, 0, 0}};
	public static void main(String[] args) throws IOException {
		// N*M인 지도 
		// 모든 면이 0으로 초기화된 주사위
		// 주사위를 굴렸을 때, 
		// 이동한 칸에 쓰여있는 수가 0이면  주사위의 바닥면에 쓰여 있는 수가 칸에 복사
		// 0이 아니면 칸에 쓰여 있는 수가 주사위의 바닥면으로 복사 -> 칸에 쓰여 있는 수 0
		
		// 입력 : N, M, 주사위 좌표, 명령의 개수, 지도 정보, 명령 정보
		// 출력 : 주사위의 윗 면에 쓰여 있는 수 출력 
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		int x = Integer.parseInt(st.nextToken());
		int y = Integer.parseInt(st.nextToken());
		diceInfo = new int[] {x, y};
		
		C = Integer.parseInt(st.nextToken()); // 명령 수
		map = new int[N][M];
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		// 1 -> 동쪽, 2 -> 서쪽, 3 -> 북쪽, 4 -> 남쪽
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<C; i++) {
			int dis = Integer.parseInt(st.nextToken());
			if (solve(dis)) {
				System.out.println(dice[1][1]);
			}
		}
	}
	
	public static boolean solve(int n) {
		int nr = diceInfo[0] + RC[0][n-1];
		int nc = diceInfo[1] + RC[1][n-1];
		
		if(nr<0 || nr>=N || nc<0 || nc>=M) return false;
		
		swap(n);
		
		if(map[nr][nc] == 0) {
			map[nr][nc] = dice[3][1];
		} else {
			dice[3][1] = map[nr][nc];
			map[nr][nc] = 0;
		}
		
		diceInfo[0] = nr; diceInfo[1] = nc;
		return true;
	}
	
	public static void swap(int n) {
		int temp = 0;
		switch(n) {
			// 오른쪽
			case 1 :
				temp = dice[1][2];
				dice[1][2] = dice[1][1];
				dice[1][1] = dice[1][0];
				dice[1][0] = dice[3][1];
				dice[3][1] = temp;
				break;
			// 왼쪽
			case 2 :
				temp = dice[1][2];
				dice[1][2] = dice[3][1];
				dice[3][1] = dice[1][0];
				dice[1][0] = dice[1][1];
				dice[1][1] = temp;
				break;
			// 위쪽
			case 3 :
				temp = dice[0][1];
				dice[0][1] = dice[1][1];
				dice[1][1] = dice[2][1];
				dice[2][1] = dice[3][1];
				dice[3][1] = temp;
				break;
			// 아래쪽
			case 4 : 
				temp = dice[0][1];
				dice[0][1] = dice[3][1];
				dice[3][1] = dice[2][1];
				dice[2][1] = dice[1][1];
				dice[1][1] = temp;
				break;
		}
	}
}
