import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException{
		// 이친수 
		// 1. 0으로 시작하지 않음
		// 2. 1이 두번연속으로 나타나지 않음
		
		// 입력 : N
		// 출력 : N자리 이친수의 개수
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		long[] dp = new long[N+1];
		dp[0] = 0; dp[1] = 1;
		for(int i=2; i<N+1; i++) {
			dp[i] = dp[i-1] + dp[i-2];
		}
		
		System.out.println(dp[N]);
	}
}
