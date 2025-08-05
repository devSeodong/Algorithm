import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		// M*N 크기의 보드
		// 검은색, 흰색
		// 8*8크기의 체스판으로 만들려고한다.

		// 맨 왼쪽 위칸이 흰색인 경우 or 검은색인 경우
		// 8*8크기로 잘라서 몇개의 정사각형만 다시 칠할 수 있도록

		// 입력 : N, M, 보드의 각 행 상태
		// 출력 : 다시 칠해야하는 정사각형의 갯수

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		boolean[][] chess = new boolean[N][M];
		for (int i=0; i<N; i++) {
			String[] temp = br.readLine().split("");
			for (int j=0; j<M; j++){
				chess[i][j] = temp[j].equals("W");
			}
		}

		// 10 13 -> 0-7, 1-8, 2-9 / 0-7, 1-8, 2-9, 3-10, 4-11
		// 0 < 10-7 ~ +8
		// 0 < 13-7 ~ +8
		int res = 8*8;
		for(int i=0; i<N-7; i++) {
			for (int j=0; j<M-7; j++) {
				int cnt = 0;
				boolean startC = chess[i][j];
				for (int k=i; k<i+8; k++) {
					for (int l=j; l<j+8; l++) {
						if (chess[k][l] != startC) {
							cnt++;
						}
						startC = !startC;
					}
					startC = !startC;
				}
				cnt = Math.min(cnt, 8*8-cnt);
				res = Math.min(res, cnt);
			}
		}

		System.out.println(res);
	}
}
