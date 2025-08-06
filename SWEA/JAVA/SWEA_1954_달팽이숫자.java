import java.io.*;

public class Solution {
	public static void main(String[] args) throws IOException {
		// 1부터 N*N까지의 숫자가 시계방향으로 이루어져 있음
		// 정수 N을 입력 받아 N크기의 달팽이를 출력

		// 입력 : 테케 T, N
		// 출력 : #t \n 정답

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb;
		int T = Integer.parseInt(br.readLine());

		for (int t = 1; t <= T; t++) {
			sb = new StringBuilder();
			int N = Integer.parseInt(br.readLine());
			int[][] arr = new int[N][N];
			int[] dr = { 0, 1, 0, -1 };
			int[] dc = { 1, 0, -1, 0 };

			int r = 0;
			int c = 0;
			int d = 0;

			arr[r][c] = 1;
			for (int i = 2; i <= N * N; i++) {
				int nr = r + dr[d];
				int nc = c + dc[d];
				if (nr < 0 || nr > N - 1 || nc < 0 || nc > N - 1 || arr[nr][nc] != 0) {
					d = (d + 1) % 4;
					nr = r + dr[d];
					nc = c + dc[d];
				}
				r = nr;
				c = nc;
				arr[r][c] = i;
			}

			sb.append("#"+t+"\n");
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					sb.append(arr[i][j]+" ");
				}
				if(i!=N-1)sb.append("\n");
			}
			System.out.println(sb);
		}
	}
}