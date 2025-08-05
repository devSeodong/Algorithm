import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		// M 이상 N 이하의 모든 소수 출력

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();

		int M = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(st.nextToken());

		for (int i = M; i <= N; i++) {
			if (isPrime(i)) {
				sb.append(i).append("\n");
			}
		}

		System.out.println(sb);
	}

	// 소수 판별 함수
	public static boolean isPrime(int n) {
		if (n < 2) return false;
		for (int i = 2; i <= (int)Math.sqrt(n); i++) {
			if (n % i == 0) return false;
		}
		return true;
	}
}
