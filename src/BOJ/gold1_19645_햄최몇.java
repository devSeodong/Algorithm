package BOJ;

import java.io.*;
import java.util.*;

public class gold1_19645_햄최몇 {
    static int N, sum;
    static int[] a;
    static boolean[][] dp; // dp[j][k] : 선배1의 합=j, 선배2의 합=k가 가능한지 여부
    public static void main(String[] args) throws Exception {
    	// 세 모질이들
    	// N개의 햄버거 
    	// 모질이들이 얻을 수 있는 효용 = 먹은 햄버거들의 효용의 합
    	// 막내는 선배들보다 높으 ㄴ효용을 누리면 안됨
    	
    	// 입력 : N, 햄버거 효용
    	// 출력 : 막내가 얻을 수 있느 ㄴ효용의 합의 최댓값
    	
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        a = new int[N+1];
        sum = 0;

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            a[i] = Integer.parseInt(st.nextToken());
            sum += a[i];
        }

        dp = new boolean[sum+1][sum+1];// 가능한 합의 최대
        dp[0][0] = true;// 아무도 안 먹은 상태

        // 각 햄버거를 하나씩 배분하며 DP
        for (int i=1; i<=N; i++) {
            int v = a[i]; // 현재 배분할 햄버거
            //중복 사용을 막기 위해 역순으로 
            for (int j=sum; j>=0; j--) {// 선배1의 합
                for (int k=sum; k>=0; k--) { // 선배2의 합
                    // 선배1에게 준 경우
                    if (j-v >= 0 && dp[j-v][k]) {
                        dp[j][k] = true;
                    }
                    // 선배2에게 준 경우
                    if (k-v >= 0 && dp[j][k-v]) {
                        dp[j][k] = true;
                    }
                }
            }
        }

        System.out.println(solve());
    }

    private static int solve() {
        int ans = 0;
        // 두 선배 합을 스캔
        for (int i=0; i<=sum; i++) { // 선배1의 합
            for (int j = 0; j <= i; j++) {// 선배2의 합
                if (dp[i][j]) {
                    int hy = sum-i-j;
                    if (j >= hy) {
                        ans = Math.max(ans, hy);
                    }
                }
            }
        }
        return ans;
    }
}
