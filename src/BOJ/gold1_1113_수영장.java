package BOJ;

import java.io.*;
import java.util.*;

public class gold1_1113_수영장 {
    static int N, M;
    static int[][] arr, RC = {{-1, 1, 0, 0},{0, 0, -1, 1}};
    static boolean[][] V;
    static class Node implements Comparable<Node> {
        int r, c, h;
        Node(int r, int c, int h) {
            this.r = r;
            this.c = c;
            this.h = h;
        }

        @Override
        public int compareTo(Node o) {
            return Integer.compare(this.h, o.h);
        }
    }
    public static void main(String[] args) throws IOException{
        // N*M 수영장
        // 물은 항상 높이가 더 낮은 곳으로만 흐르고
        // 땅의 높이는 0, 땅은 물을 무한대로 흡수할 수 있다

        // 입력 : N, M, 땅의 높이
        // 출력 : 수용장에 물이 얼마만큼 있을 수 있는지

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = new int[N][M];
        V = new boolean[N][M];
        for(int i=0; i<N; i++){
            String str = br.readLine();
            for(int j=0; j<M; j++){
                arr[i][j] = str.charAt(j)-'0';
            }
        }

        PriorityQueue<Node> pq = new PriorityQueue<>();
        for(int i=0; i<N; i++) {
            pq.add(new Node(i, 0, arr[i][0]));
            pq.add(new Node(i, M-1, arr[i][M-1]));
            V[i][0] = true;
            V[i][M-1] = true;
        }
        for(int i=0; i<M; i++) {
            pq.add(new Node(0, i, arr[0][i]));
            pq.add(new Node(N-1, i, arr[N-1][i]));
            V[0][i] = true;
            V[N-1][i] = true;
        }

        long water = 0;
        while (!pq.isEmpty()) {
            Node cur = pq.poll();
            for (int d = 0; d < 4; d++) {
                int nr = cur.r + RC[0][d];
                int nc = cur.c + RC[1][d];

                if (nr < 0 || nr >= N || nc < 0 || nc >= M || V[nr][nc]) continue;

                V[nr][nc] = true;
                int nh = arr[nr][nc];
                if (nh < cur.h) {
                    water += (cur.h - nh);
                }

                pq.add(new Node(nr, nc, Math.max(cur.h, nh)));
            }
        }

        System.out.println(water);
    }
}
