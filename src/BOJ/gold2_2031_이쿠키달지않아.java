package BOJ;

import java.io.*;
import java.util.*;

public class gold2_2031_이쿠키달지않아 {
	static int N, K;
	static long T, D;
	static long[] arr;
	static int[] cnt, temp;
	static int[][] dp;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		T = Long.parseLong(st.nextToken());
		N = Integer.parseInt(st.nextToken()); // 음식개수
		D = Long.parseLong(st.nextToken()); // 다이어트 유지 시간
		K = Integer.parseInt(st.nextToken()); // 차 잔수

		arr = new long[N+1];
		st = new StringTokenizer(br.readLine());
		for(int i=1; i<=N; i++) {
			arr[i] = Long.parseLong(st.nextToken());
		}

		Arrays.sort(arr);

		temp = new int[N+1];
		int L = 1; //왼쪽 끝
		for (int i=1; i<=N; i++) {
			long t = arr[i]-D+1; //범위가 길이 D인
			while (L<=i && arr[L]<t) L++;//윈도우에 들어오도록 L++
			temp[i] = L;
		}

		// 윈도우에 포함되는 개수
		cnt = new int[N+1];
		for (int i=1; i<=N; i++) {
			cnt[i] = i-temp[i]+1;
		}

		dp = new int[N+1][K+1];
		for (int i=1; i<=N; i++) {

			for (int k=0; k<=K; k++) {
				dp[i][k] = Math.max(dp[i][k], dp[i-1][k]);
			}

			for (int k=1; k<=K; k++) {// 겹치지 않게 하려면 temp[i]-1까지에서 k-1
				dp[i][k] = Math.max(dp[i][k], dp[temp[i]-1][k-1] + cnt[i]);
			}
		}

		System.out.println(dp[N][K]);
	}
}
