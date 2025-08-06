import java.io.*;

public class Main {
	public static Integer[] dp;
	public static int[] arr;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());

		arr = new int[N];
		for (int n = 0; n < N; n++) {
			arr[n] = Integer.parseInt(br.readLine());
		}

		if (N == 1) {
			System.out.println(arr[0]);
			return;
		} else if (N == 2) {
			System.out.println(arr[0] + arr[1]);
			return;
		}

		dp = new Integer[N];
		dp[0] = arr[0];
		dp[1] = arr[0] + arr[1];
		dp[2] = Math.max(arr[0] + arr[2], arr[1] + arr[2]);

		System.out.println(step(N - 1));
	}

	public static int step(int P) {
		if (dp[P] == null) {
			dp[P] = Math.max(step(P - 2) + arr[P], step(P - 3) + arr[P - 1] + arr[P]);
		}
		return dp[P];
	}
}
