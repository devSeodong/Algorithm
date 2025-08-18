import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		// 정수 삼각형
		// 이제까지 선택한 수의 합이 최대가 되는 경로
		// 대각선 왼쪽, 오른쪽만 선택 가능

		// 입력 : n, 삼각형 정보
		// 출력 : 합의 최대가 되는 경로 합

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());

		int nodeTot = n*(n+1)/2; // 노드의 총 개수 1+2+3... 점화식
		int[] arr = new int[nodeTot];

		int idx = 0;
		for (int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j <= i; j++) {
				arr[idx++] = Integer.parseInt(st.nextToken());
			}
		}

		int[] dp = new int[nodeTot];
		dp[0] = arr[0];

		int p = 0; // 직전 행의 시작 인덱스
		int cur = 1; // 지금까지 누적 노드 수
		for (int i = 1; i < n; i++) {
			cur = i*(i+1) / 2; // 방문한 노드 sum
			dp[cur] = dp[p]+arr[cur]; // 맨 왼쪽
			for (int c=1; c<i; c++) { // 중간 값 max
				int t = cur+c;
				dp[t] = arr[t] + Math.max(dp[t-i], dp[t-(i+1)]);
			}
			dp[cur+i] = dp[cur-1]+arr[cur+i]; // 맨 오른쪽
			p = cur;
		}

		int sum = 0;
		int lastR = n*(n-1)/2; // 마지막 행
		for (int t = lastR; t < nodeTot; t++) {
			sum = Math.max(sum, dp[t]);
		}
		System.out.println(sum);
	}
}
