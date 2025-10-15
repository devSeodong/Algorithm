package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class gold5_2447_별찍기10 {
    static char[][] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        arr = new char[N][N];
        solve(0, 0, N, false);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                sb.append(arr[i][j]);
            }
            sb.append('\n');
        }
        System.out.print(sb);
    }

    public static void solve(int r, int c, int N, boolean isB) {
        if(isB) {
            for(int i=r; i<r+N; i++) {
                for(int j=c; j<c+N; j++) {
                    arr[i][j] = ' ';
                }
            }
            return;
        }

        if(N==1) {
            arr[r][c] = '*';
            return;
        }

        int size = N/3;
        int cnt = 0;
        for(int i=r; i<r+N; i+=size) {
            for(int j=c; j<c+N; j+=size) {
                cnt++;
                solve(i, j, size, cnt == 5);
            }
        }
    }
}
