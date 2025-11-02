package BOJ;

import java.io.*;
import java.util.*;

public class silver1_10844_쉬운계단수 {
    static int N, res;
    static int[][] dp;
    public static void main(String[] args) throws IOException {
        // 인접한 모든 자리의 차이가 1 -> 계단 수
        // N인 계단 수가 총 몇개 있는지

        // 입력 : N,
        // 출력 : 정답을 1000000000로 나눈 나머지

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        res = 0;
        dp = new int[N+1][10];

        for(int i=1; i<10; i++) {
            res = ( res + solve(N-1, i) ) % 1000000000;
        }

        System.out.println(res);
    }

    public static int solve(int d, int n) {
        if(d==0) return 1;

        int cnt = 0;

        if(dp[d][n] != 0) return dp[d][n];

        if(n>0) {
            cnt = (cnt + solve(d-1, n-1))%1000000000;
        }
        if(n<9) {
            cnt = (cnt + solve(d-1, n+1))%1000000000;
        }

        dp[d][n] = cnt;

        return cnt;
    }
}
