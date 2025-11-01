package BOJ;

import java.io.*;
import java.util.*;

public class gold5_15486_퇴사2 {
    public static void main(String[] args) throws IOException {
        // N+1일째 되는 날 퇴사
        // N일 동안 최대한 많은 상담을 수행해야 함
        // 각 상담은 걸리는 시간 T와 받을 수 있는 금액 P가 주어짐
        // 하루에 하나의 상담만 진행할 수 있음

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine()); // 전체 일 수 입력

        // arr[i][0] = i번째 날 상담 기간(T), arr[i][1] = 상담 금액(P)
        int[][] arr = new int[N+2][2]; // 인덱스 초과 방지를 위해 N+2 크기
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            arr[i][0] = Integer.parseInt(st.nextToken()); // 상담 소요 시간
            arr[i][1] = Integer.parseInt(st.nextToken()); // 상담 보상 금액
        }

        // dp[i] : i번째 날까지 얻을 수 있는 최대 이익
        int[] dp = new int[N+2];

        int max = -1; // 현재까지의 최대 이익을 저장할 변수
        for (int i = 1; i <= N + 1; i++) { // N+1일까지 확인해야 함
            max = Math.max(max, dp[i]); // 이전 날까지의 최대 이익 갱신

            int n = i + arr[i][0]; // 현재 상담을 마치는 날짜 계산
            if (n < N + 2) { // 퇴사일(N+1)을 넘지 않는다면
                // i번째 날의 상담을 하는 경우의 이익을 반영
                // 현재까지의 최대 이익 + i번째 상담 보상과 기존 dp[n] 중 큰 값을 저장
                dp[n] = Math.max(dp[n], max + arr[i][1]);
            }
        }

        // 퇴사일(N+1)까지의 최대 이익 출력
        System.out.println(dp[N+1]);
    }
}
