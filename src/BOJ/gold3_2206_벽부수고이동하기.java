package BOJ;

import java.io.*;
import java.util.*;

public class gold3_2206_벽부수고이동하기 {
    static int n, m;
    static int[][] map;
    static int[][] RC = {{-1, 1, 0, 0}, {0, 0, -1, 1}};
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        if(n==1&&m==1) {
            System.out.println(1);
            return;
        }

        map = new int[n][m];
        for (int i = 0; i < map.length; i++) {
            String str = br.readLine();
            for (int j = 0; j < map[i].length; j++) {
                int num = str.charAt(j) - '0';
                if (num == 1) {
                    map[i][j] = num;
                }
            }
        }
        System.out.println(bfs());

    }

    private static int bfs() {
        int[][][] check = new int[2][n][m];

        Deque<int[]> dq = new ArrayDeque<>();
        dq.add(new int[] { 0, 0, 0 });
        check[0][0][0] = 1;

        while (true) {
            int[] cur = dq.poll();
            if (cur == null) return -1;

            int w = cur[0], r = cur[1], c = cur[2];
            for (int d = 0; d < 4; d++) {
                int nx = r + RC[0][d];
                int ny = c + RC[1][d];
                if (nx >= n || nx < 0 || ny >= m || ny < 0) continue;

                if (w == 0) {
                    if (map[nx][ny] == 0 && check[0][nx][ny]==0) {
                        dq.add(new int[] { 0, nx, ny });
                        check[0][nx][ny] = check[0][r][c] + 1;
                    } else if(map[nx][ny] == 1 && check[0][nx][ny]==0){
                        if (check[1][nx][ny] == 0) {
                            dq.add(new int[] { 1, nx, ny });
                            check[1][nx][ny] = check[0][r][c] + 1;
                        }
                    }
                } else {
                    if (map[nx][ny] == 0) {
                        if (check[1][nx][ny] == 0) {
                            dq.add(new int[] { 1, nx, ny });
                            check[1][nx][ny] = check[1][r][c] + 1;
                        }
                    }
                }
                if (nx == n - 1 && ny == m - 1) {
                    return Math.max(check[0][nx][ny], check[1][nx][ny]);
                }
            }
        }
    }
}
