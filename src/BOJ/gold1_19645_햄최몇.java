package BOJ;

import java.io.*;
import java.util.*;

public class gold1_19645_햄최몇 {
    static int N;
    static int[] a;
    static boolean[][] dp; // dp[j][k] : (선배1의 합=j, 선배2의 합=k)가 "가능"한지 여부
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); // 빠른 입력
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        a = new int[N+1];
        int sum = 0;                             // 전체 효용 합(= 모든 햄버거 효용의 합)

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            a[i] = Integer.parseInt(st.nextToken());
            sum += a[i];
        }

        // dp의 두 축은 두 선배의 합(j, k)
        dp = new boolean[sum + 1][sum + 1];// 가능한 합의 최대는 sum

        dp[0][0] = true;// 아무도 안 먹은 상태

        // 각 햄버거 a[i]를 하나씩 배분하며 DP 전이
        for (int i = 1; i <= N; i++) {
            int v = a[i]; // 현재 배분할 햄버거의 효용
            // 같은 라운드에서 중복 사용을 막기 위해 역순으로 갱신
            for (int j = sum; j >= 0; j--) {// 선배1의 합
                for (int k = sum; k >= 0; k--) { // 선배2의 합
                    // 이번 v를 선배1에게 준 경우: 이전 상태는 (j - v, k)
                    if (j - v >= 0 && dp[j - v][k]) {
                        dp[j][k] = true;
                    }
                    // 이번 v를 선배2에게 준 경우: 이전 상태는 (j, k - v)
                    if (k - v >= 0 && dp[j][k - v]) {
                        dp[j][k] = true;
                    }
                }
            }
        }

        System.out.println(solve(sum, dp));
    }

    private static int solve(int sum, boolean[][] dp) {
        int ans = 0;
        // 두 선배 합을 스캔
        for (int i = 0; i <= sum; i++) { // i = 선배1의 합
            for (int j = 0; j <= i; j++) {// j2 = 선배2의 합, 정렬 위해 j2 ≤ i만 확인
                if (dp[i][j]) {
                    int hy = sum - i - j;
                    if (j >= hy) {
                        ans = Math.max(ans, hy);
                    }
                }
            }
        }
        return ans;
    }
}
