package BOJ;

import java.io.*;
import java.util.*;

public class gold3_1600_말이되고픈원숭이 {
    static int K, W, H;
    static int[][] arr, RC={{-1, 1, 0, 0},{0, 0, -1, 1}};
    static boolean[][][] V;
    static class Node {
        int r, c, cnt, move;
        public Node(int r, int c, int cnt, int move) {
            this.r = r;
            this.c = c;
            this.cnt = cnt;
            this.move = move;
        }
    }
    public static void main(String[] args) throws IOException {
        // 말은 격자판에서 체스 나이트와 같은 이동방식
        // 원숭이 -> K번만 위와 같이 움직일 수 있고, 인접한 칸으로만 움직일 수 있음
        // 맨 왼쪽 위 -> 맨 오른쪾 아래

        // 입력 : K, W, H, 격자판 정보 ( 0, 1)
        // 출력 : 원숭이의 동작수의 최솟값, 갈 수 없는 경우엔 -1

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        K = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        W = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());

        arr = new int[H][W];
        V = new boolean[H][W][K+1];
        for(int i=0; i<H; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<W; j++){
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        System.out.println(solve());

    }

    static int[][] horse = {{-1, -2, -2, -1, 1, 2, 2, 1}, {-2, -1, 1, 2, 2, 1, -1, -2}};
    public static int solve() {
        Deque<Node> dq = new ArrayDeque<>();
        dq.add(new Node(0, 0, 0, K));
        V[0][0][K] = true;

        while (!dq.isEmpty()) {
            Node cur = dq.poll();
            if (cur.r == H - 1 && cur.c == W - 1) return cur.cnt;

            for (int d=0; d<4; d++) {
                int nr = cur.r + RC[0][d];
                int nc = cur.c + RC[1][d];

                if (nr < 0 || nr >= H || nc < 0 || nc >= W) continue;
                if (arr[nr][nc] == 1) continue;
                if (V[nr][nc][cur.move]) continue;

                V[nr][nc][cur.move] = true;
                dq.add(new Node(nr, nc, cur.cnt + 1, cur.move));
            }

            if (cur.move == 0) continue;
            for (int d=0; d<8; d++) {
                int nr = cur.r + horse[0][d];
                int nc = cur.c + horse[1][d];

                if (nr < 0 || nr >= H || nc < 0 || nc >= W) continue;
                if (arr[nr][nc] == 1) continue;
                if (V[nr][nc][cur.move - 1]) continue;

                V[nr][nc][cur.move - 1] = true;
                dq.add(new Node(nr, nc, cur.cnt + 1, cur.move - 1));
            }
        }

        return -1;
    }
}
