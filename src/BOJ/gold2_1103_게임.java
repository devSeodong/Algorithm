package BOJ;

import java.io.*;
import java.util.*;

public class gold2_1103_게임 {
    static int N, M;
    static char[][] arr;
    static int[][] dp;
    static boolean[][] V;
    static boolean flag = false; // 무한플래그
    static int[][] RC = {{-1, 1, 0, 0},{0, 0, -1, 1}};
    public static void main(String[] args) throws IOException {
        // 1~9 숫자, 구멍이 있는 직사각형 보드
        // 가장 왼쪽 위에 동전
        // 1. 동전이 있는 곳에 쓰여있는 숫자 X
        // 2. 사방향 중에 한가지
        // 3. 고른 방향으로 X만큼 움직임 ( 중간에 있는 구멍 무시 )

        // 입력 : N, M, 보드 상태( H는 구멍 )
        // 출력 : 구멍 빠지거나 바깥으로 나가면 게임 종료. 게임 오래하기 위해서 최대 몇 번 동전을 움직일 수 있는지 / 무한번 움직이면 -1
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = new char[N][M];
        dp = new int[N][M];
        V = new boolean[N][M];

        for(int i=0; i<N; i++) {
            String str = br.readLine();
            for(int j=0; j<M; j++) {
                arr[i][j] = str.charAt(j);
            }
        }

        int res = dfs(0, 0);
        if(flag) System.out.println(-1);
        else System.out.println(res);
    }

    public static int dfs(int i, int j) {
        // 범위 밖이거나 구멍이면 못감
        if(i < 0 || i>=N || j <0 || j>=M || arr[i][j] == 'H') return 0;
        if(dp[i][j] != 0) return dp[i][j]; // 이미 계산한건 dp 반환

        V[i][j] = true; // 현재 경로에서 방문 시작

        int num = arr[i][j] - '0';
        int best = 0;
        for(int d = 0; d<4; d++) {
            int nr = i + RC[0][d] * num;
            int nc = j + RC[1][d] * num;

            if(nr >= 0 && nr < N && nc >= 0 && nc < M && arr[nr][nc] != 'H' && V[nr][nc]) {
                flag = true;
                return 0;
            }

            best = Math.max(best, dfs(nr, nc)); // 다음 칸에서 최대 이동 횟수
            if(flag) return 0; // 무한이면 리턴
        }

        V[i][j] = false;
        dp[i][j] = best+1;
        return dp[i][j];
    }
}
