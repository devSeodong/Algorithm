package BOJ;

import java.io.*;
import java.util.*;

public class gold4_2580_스도쿠 {
    static boolean flag;
    static int[][] arr = new int[9][9];
    public static void main(String[] args) throws Exception {
        // 스도쿠는 매우 간단한 숫자 퍼즐이다.
        // 9*9 각 행과 열, 1부터 9까지의 숫자가 중복 없이

        // 입력 : 9개의 줄에 9개의 숫자로 보드가 입력됨. 채워지지 않는 칸 0
        // 출력 : 9개의 줄에 9개의 숫자로 출력, 81자리의 수가 제일 작은 경우 출력

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        for(int i=0; i<9; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<9; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dfs(0);
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<9; i++) {
            for(int j=0; j<9; j++) {
                sb.append(arr[i][j]).append(" ");
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }

    public static void dfs(int d) {
        if(d == 81) {
            flag = true;
            return;
        }

        int r = d/9, c = d%9;
        if(arr[r][c]!=0) {
            dfs(d+1);
        } else {
            for(int i=1; i<=9; i++) {
                if(!solve(r, c, i)) continue;
                arr[r][c] = i;
                dfs(d+1);

                if(flag) return;
                arr[r][c] = 0;
            }
        }
    }

    public static boolean solve(int r, int c, int n) {
        for(int i=0; i<9; i++) {
            if(arr[i][c] == n || arr[r][i] == n) return false;
        }

        int nr = r/3*3;
        int nc = c/3*3;
        for(int i=nr; i<nr+3; i++) {
            for(int j=nc; j<nc+3; j++) {
                if(arr[i][j] == n) return false;
            }
        }

        return true;
    }
}
