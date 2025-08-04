import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	public static void main(String[] args) throws IOException {
		// 100*100 2차원 배열 각 행의 합, 각 대각선의 합 중 최댓값을 구하는 프로그램
		// 테케 10개 , 동일한 최댓값이 있을 경우 하나만

		// 입력 : 케이스 번호 , 2차원 배열의 각 행 값
		// 출력 : #t 정답

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int N = 100;
		for (int t = 0; t < 10; t++) {
			int T = Integer.parseInt(br.readLine());
			int[][] arr = new int[N][N];
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					arr[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			int r, c, x = 0, y = 0, rc = 0;
			int maxRC = Integer.MIN_VALUE;
			for (int i = 0; i < N; i++) {
				r = 0;
				c = 0;
				x += arr[i][i];
				y += arr[i][N - i - 1];
				for (int j = 0; j < N; j++) {
					r += arr[i][j];
					c += arr[j][i];
				}
				rc = Math.max(r, c);
				if (maxRC < rc) {
					maxRC = rc;
				}
			}
			int maxXY = Math.max(x, y);
			int result = Math.max(maxXY, maxRC);

			System.out.println("#" + T + " " + result);
		}
	}
}