package BOJ;

import java.io.*;
import java.util.*;

public class gold3_1941_소문난칠공주 {
    static int res;
    static int[] S = new int[7];
    static int[][] arr = new int[5][5], RC = {{-1, 1, 0, 0}, {0, 0, -1, 1}};
    static boolean[][] V;
    public static void main(String[] args) throws IOException{
        // 7명의 여학생들로 구성
        // 가로나 세로로 반드시 인접
        // 이다솜파가 적어도 4이상

        // 입력 : S ( 이다솜 ), Y ( 임도연 ) 5*5 행렬
        // 출력 : 칠공주 결성 경우의 수

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for(int i=0; i<5; i++) {
            String str = br.readLine();
            for(int j=0; j<5; j++) {
                arr[i][j] = str.charAt(j) == 'S'?1:0;
            }
        }

        dfs(0, 0);
        System.out.println(res);
    }

    public static void dfs(int d, int s) {
        if(d==7) {
            int sCnt = 0;
            V = new boolean[5][5];

            for(int i=0; i<7; i++) {
                int r = S[i]/5, c = S[i]%5;
                if(arr[r][c] == 1) sCnt++;
                V[r][c] = true;
            }

            if(sCnt >= 4 && bfs()) res++;
            return;
        }

        for(int i=s; i<25; i++) {
            S[d] = i;
            dfs(d+1, i+1);
        }
    }

    public static boolean bfs() {
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{S[0]/5, S[0]%5});
        V[S[0]/5][S[0]%5] = false;
        int cnt = 0;

        while(!q.isEmpty()) {
            int[] cur = q.poll();
            cnt++;

            for(int d=0; d<4; d++) {
                int nr = cur[0] + RC[0][d];
                int nc = cur[1] + RC[1][d];

                if(nr < 0 || nr >= 5 || nc < 0 || nc >= 5 || !V[nr][nc]) continue;

                q.add(new int[]{nr, nc});
                V[nr][nc] = false;
            }
        }

        return cnt == 7;
    }
}
