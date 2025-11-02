package BOJ;

import java.io.*;
import java.util.*;

public class gold5_18428_감시피하기 {
    static int N, res;
    static char[][] arr;
    static List<int[]> T;
    static int[][] RC = {{-1, 1, 0, 0}, {0, 0, -1, 1}};
    public static void main(String[] args) throws IOException {
        // N*N 크기의 복도 -> 선생님, 학생, 장애물 위치
        // 선생님 : 자신의 위치에서 상, 하, 좌, 우 4가지 방향 감시
        // 장애물 : 감시가 막힘
        // 장애물을 설치할 위치를 골라서 감시를 피하기

        // 입력 : N, 복도 정도 ( S 학생, T 선생, 그 외 X )
        // 출력 : 장애물을 설치하여 감시 피하기 여부 출력

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new char[N][N];
        T = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                char c = st.nextToken().charAt(0);
                arr[i][j] = c;
                if(c == 'T') T.add(new int[]{i, j});
            }
        }

        dfs(0, 0, 0);
        System.out.println(res == 1?"YES":"NO");
    }

    public static void dfs(int d, int r, int c) {
        if(d == 3) {
            if(solve()) res = 1;
            return;
        }

        if(c >= N) {
            c = 0; r++;
        }
        if(r >= N) return;

        if(arr[r][c] == 'X') {
            arr[r][c] = 'O';
            dfs(d+1, r, c+1);
            arr[r][c] = 'X';
        }

        dfs(d, r, c+1);
    }

    public static boolean solve() {
        for(int i=0; i<T.size(); i++) {
            int r = T.get(i)[0];
            int c = T.get(i)[1];
            for(int d=0; d<4; d++) {
                int nr = r, nc = c;
                while(true) {
                    nr += RC[0][d];
                    nc += RC[1][d];
                    if(nr < 0 || nr >= N || nc < 0 || nc >= N) break;

                    if(arr[nr][nc] == 'O') break;
                    if(arr[nr][nc] == 'S') return false;
                }
            }
        }
        return true;
    }
}
