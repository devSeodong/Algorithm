import java.io.*;
import java.util.*;

class Solution
{
	public static void main(String args[]) throws IOException
	{
		 // 좌우로 밀집되어있는 빌딩, 양쪽 모두 거리 2 이상의 공간이 확보될 때 조망권
		// 맨 왼/오른쪽 두칸에는 거물이 지어지지 않는다

		// 입력 : 테케 10개 , 것물의 개수 N (4<= N <= 1000), N개의 건물의 높이
		// 출력 : #t 정답

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		for (int t = 0; t < 10; t++) {
			int N = Integer.parseInt(br.readLine());
			int[] buildings = new int[N];
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				buildings[j] = Integer.parseInt(st.nextToken());
			}

			int result = 0;
			for (int i = 2; i < N - 2; i++) {
				int left = Math.max(buildings[i - 1], buildings[i - 2]);
				int right = Math.max(buildings[i + 1], buildings[i + 2]);
				int totalmax = Math.max(left, right);

				if (buildings[i] > totalmax) {
					result += buildings[i] - totalmax;
				}
			}

			System.out.println("#" + (t + 1) + " " + result);
		}
	}
}