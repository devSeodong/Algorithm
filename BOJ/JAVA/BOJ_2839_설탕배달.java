import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		// 설탕 N킬로그램 배달
		// 봉지는 3킬로, 5킬로

		// 최대한 적은 봉지
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

//        1.
//        int N = Integer.parseInt(br.readLine());
//
//        if (N == 4 || N == 7) {
//            System.out.println(-1);
//        }
//        else if (N % 5 == 0) {
//            System.out.println(N / 5);
//        }
//        else if (N % 5 == 1 || N % 5 == 3) {
//            System.out.println((N / 5) + 1);
//        }
//        else if (N % 5 == 2 || N % 5 == 4) {
//            System.out.println((N / 5) + 2);
//        }

//        2.
//        int N = Integer.parseInt(br.readLine());
//        int[] dp = new int[N + 1];
//
//        if (N >= 3)
//            dp[3] = 1;
//        if (N >= 5)
//            dp[5] = 1;
//
//        for (int i = 6; i <= N; i++) {
//            if (i % 5 == 0) {
//                dp[i] = dp[i - 5] + 1;
//            } else if (i % 3 == 0) {
//                dp[i] = dp[i - 3] + 1;
//            } else {
//                if (dp[i - 3] != 0 && dp[i - 5] != 0) {
//                    dp[i] = Math.min(dp[i - 3], dp[i - 5]) + 1;
//                }
//            }
//        }
//
//        if (dp[N] == 0) {
//            System.out.println("-1");
//            return;
//        }
//        System.out.println(dp[N]);

		int N = Integer.parseInt(br.readLine());
		for(int i=0; i<=N/3; i++) {
			for(int j = 0; j<=N/5; j++) {
				if( i*3 + j* 5 == N) {
					System.out.println(i+j);
					return;
				}
			}
		}
		System.out.println("-1");
	}
}
