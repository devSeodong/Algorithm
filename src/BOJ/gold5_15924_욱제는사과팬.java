package BOJ;

import java.io.*;
import java.util.*;

public class gold5_15924_욱제는사과팬 {
    static int N, M, MOD = 1000000009;
    static char[][] arr;
    static int[][] dp;
    public static void main(String[] args) throws IOException {
        // N*M 크기의 지도
        // E -> ( i, j+1 )
        // S -> ( i+1, j )
        // B -> ( i, j+1 ) or ( i+1, j )
        // 최종 목적지 N, M
        // 구사과가 선물을 가져가는 경로의 수

        // 입력 : N, M, 지도 정보
        // 출력 : 구사과가 선물을 가져가는 경로의 수 모듈러
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = new char[N][M];
        dp = new int[N][M];
        for(int i=0; i<N; i++) {
            String str = br.readLine();
            for(int j=0; j<M; j++) {
                arr[i][j] = str.charAt(j);
            }
        }

        for(int i=0; i<N; i++) {
            for(int j=0; j<M; j++) {
                dp[i][j] = (dp[i][j]+1) % MOD;

                if (arr[i][j] == 'X') continue;

                // 오른쪽
                if(arr[i][j] == 'E' || arr[i][j] == 'B') {
                    if(j+1 < M) {
                        dp[i][j+1] = (dp[i][j+1] + dp[i][j]) % MOD;
                    }
                }

                // 아래로
                if(arr[i][j] == 'S' || arr[i][j] == 'B') {
                    if(i+1 < N) {
                        dp[i+1][j] = (dp[i+1][j] + dp[i][j]) % MOD;
                    }
                }
            }
        }

        System.out.println(dp[N-1][M-1]%MOD);
    }
}
