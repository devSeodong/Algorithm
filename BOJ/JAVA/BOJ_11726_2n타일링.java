import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException {
		// 2*n크기의 직가각형을 1*2, 2*1 타일로 채우는 방법의 수

		// 입력 : n
		// 출력 : 2*n크기의 직사각형을 채우는 방법의 수를 10007로 나눈 나머지

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());

		long[] dp = new long[N+1];
		dp[0]=1; dp[1]=1;
		if(N>=2) dp[2] = 2;
		for(int i=3; i<=N; i++) {
			dp[i] = (dp[i-1] + dp[i-2]) %10007;
		}

		System.out.println(dp[N]);
	}
}