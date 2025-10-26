package BOJ;

import java.io.*;
import java.util.*;

public class gold5_30460_스위치 {
    public static void main(String[] args) throws IOException {
        // i초에 Ai 점수를 얻는 게임
        // N초 동안 진행하는 게임
        // 추가 점수 -> T초에 스위치를 눌러, T+1, T+2초에 얻는 점수를 두배로
        // T초에 스위치를 누르면 T+3초부터 다시 스위치를 누를 수 있음

        // 입력 : 점수를 얻는 횟수 N, 점수 정보
        // 출력 : 얻을 수 있는 점수의 최댓값
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int[] dp = new int[N+3];
        int[] arr = new int[N+3];
        Arrays.fill(dp, Integer.MIN_VALUE);

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        dp[0] = 0;
        for (int i=1; i<=N+2; i++) {
            if (i >= 3) {
                int calc = (arr[i-2] + arr[i-1] + arr[i]) * 2;
                dp[i] = Math.max(dp[i], dp[i-3] + calc);
            }
            // 그냥 이번 초 점수를 더하는 경우
            dp[i] = Math.max(dp[i], dp[i-1] + arr[i]);
        }

        System.out.println(dp[N+2]);
    }
}
