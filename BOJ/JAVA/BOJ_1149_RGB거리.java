import java.io.*;
import java.util.StringTokenizer;

public class Main {
	public static int N;
	public static Integer[][] dp;
	public static int[][] RGB;

	public static void main(String[] args) throws Exception {
		// 빨강, 초록, 파랑 중 하나의 색으로 집을 칠함 -> 모든 집을 칠하는 비용의 최솟값
		// 1번 집의 색은 2번 집의 색과 같이 않아야 함
		// i번 집의 색은 i-1, i+1번 집의 색과 같지 않아야 함

		// 입력 : 집의 수 N, 빨강, 초록, 파랑으로 칠하는 비용
		// 출력 : 모든 집을 칠하는 비용의 최솟값

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());

		dp = new Integer[N+1][2];
		RGB = new int[N+1][3];

		for(int n=1; n<=N; n++) {
			st = new StringTokenizer(br.readLine());
			RGB[n][0] = Integer.parseInt(st.nextToken());
			RGB[n][1] = Integer.parseInt(st.nextToken());
			RGB[n][2] = Integer.parseInt(st.nextToken());
		}

		// 1부터 N까지
		// 1부터 Math.min으로 RGB중 최소 비용 체크 해서 해당 색과 비용을 넣음
		// N이 2부터 시작이라서 0와 1 예외처리는 해주지 않아도 됨.

		dp[1] = min(1, -1); // 이전 값을 체크할게 없기 때문에 -1 주기
		System.out.println(solve(2));
	}

	public static Integer[] min(int n, int nc) {
		int minC = 0;
		int min = Integer.MAX_VALUE;
		for(int i=0; i<3; i++) {
			if(nc == i) continue;
			int num = RGB[n][i];
			if(min > num) {
				min = num; minC = i;
			}
		}

		return new Integer[]{minC, min};
	}

	public static int solve(int n) {
		// flag -> R-0, G-1, B-2
		// 1인 경우 해당 RGB 컬러 중 가장 저렴한걸로 초기 세팅, N인 경우 그 전의 값만 체크
		// 1부터 체크 , N일 경우 조건 타서 return
		Integer[] res = min(n, dp[n-1][0]);
		dp[n] = new Integer[] {res[0], dp[n-1][1] + res[1]};

		if(n == N) {
			return dp[n][1];
		}

		return solve(n+1);
	}
}
