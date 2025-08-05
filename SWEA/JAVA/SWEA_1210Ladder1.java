

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	static int[] dc = { -1, 1 };

	public static void main(String[] args) throws IOException {
		// 사다리 게임을 통하여 누가 아이스크림을 구입할지 결정 => 사다리를 그리기로 함
		// 어느 사다리를 고르면 X표시에 도착하게 되는지 ?

		// 1로 이루어져있는 사다리
		// 요소의 값이 2인 위치에 도달하는 x좌표 ?

		// 입력 받을 때 2의 좌표 저장 [99][a]
		// 델타 상좌우

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		for (int t = 1; t <= 10; t++) {
			int T = Integer.parseInt(br.readLine());

			int N = 100;
			int[][] arr = new int[N][N];
			int startC = 0;
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					int num = Integer.parseInt(st.nextToken());
					if (num == 2) {
						startC = j;
					}
					arr[i][j] = num;
				}
			}

			int r = N - 1, c = startC; // 현재 위치
			int d = -1;
			while (r >= 0) {
				// 만약에 현재 위치가 상인 경우
				if (d == -1) {
					for (int i = 0; i < dc.length; i++) {
						int nc = c + dc[i];
						if (nc < 0 || nc >= N) {
							continue;
						} else if (arr[r][nc] == 1) {
							c = nc;
							d = i;
							break;
						}
					}
					if (d == -1)
						r--;

				// 만약에 현재 위치가 좌인 경우
				} else if (d == 0) {
					if (arr[r - 1][c] == 1) {
						r--;
						d = -1;
					} else {
						c--;
						d = 0;
					}
					
				// 만약에 현재 위치가 우인 경우
				} else {
					if (arr[r - 1][c] == 1) {
						r--;
						d = -1;
					} else {
						c++;
						d = 1;
					}
				}
			}
			
			System.out.println("#"+T+" "+c);
		}
	}
}