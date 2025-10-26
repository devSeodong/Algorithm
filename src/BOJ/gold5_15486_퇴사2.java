package BOJ;

import java.io.*;
import java.util.*;

public class gold5_15486_퇴사2 {
    public static void main(String[] args) throws IOException {
        // N+1일째 되는 날 퇴사
        // N일 동안 최대한 많은 상담
        // 상담 완료하는데 걸리는 시간 T, 받을 수 있는 금액 P

        // 입력 : N, T, P
        // 출력 : 백준이가 얻을 수 있는 최대 이익
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine());

        int[][] arr = new int[N+2][2];
        for(int i=1; i<=N; i++) {
            st = new StringTokenizer(br.readLine());
            arr[i][0] = Integer.parseInt(st.nextToken());
            arr[i][1] = Integer.parseInt(st.nextToken());
        }

        int[] dp = new int[N+2];
        int max = -1;
        for(int i=1; i<=N+1; i++) {
            max = Math.max(max, dp[i]);
            int n = i+arr[i][0];
            if(n < N+2) {
                // 다른 상담을 선택했을 때랑 현재 상담을 선택했을 때의 이익 비교
                dp[n] = Math.max(dp[n], max + arr[i][1]);
            }
        }

        System.out.println(dp[N+1]);
    }
}
