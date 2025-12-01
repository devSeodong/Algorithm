package BOJ;

import java.io.*;
import java.util.*;

public class silver1_2583_영역구하기 {
    static int M, N, K;
    static int[][] arr, RC = {{-1, 1, 0, 0}, {0, 0, -1, 1}};
    static boolean[][] V;
    public static void main(String[] args) throws IOException {
        // M*N 모눈종이
        // 직사각형 좌표
        // 분리된 영역

        // 입력 : M, N, K, 직사각형 좌표
        // 출력 : 영역의 개수 출력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        arr = new int[N][M];
        V = new boolean[N][M];
        for(int i=0; i<K; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            for(int j=b;j<d; j++) {
                for(int k=a;k<c;k++) {
                    arr[j][k] = 1;
                }
            }
        }

        int res = 0;
        List<Integer> list = new ArrayList<>();
        for(int i=0; i<N; i++) {
            for(int j=0; j<M; j++) {
                if(!V[i][j] && arr[i][j] == 0) {
                    int size = bfs(i, j);
                    res++;
                    list.add(size);
                }
            }
        }

        Collections.sort(list);
        StringBuilder sb = new StringBuilder();
        sb.append(res).append("\n");
        for(int s : list) {
            sb.append(s).append("\n");
        }

        System.out.println(sb);
    }

    public static int bfs(int i, int j) {
        Deque<int[]> dq = new ArrayDeque<>();
        V[i][j] = true;
        dq.add(new int[]{i, j});

        int cnt = 1;
        while (!dq.isEmpty()) {
            int[] cur = dq.poll();
            int r = cur[0], c = cur[1];

            for(int d=0; d<4; d++) {
                int nr = r + RC[0][d];
                int nc = c + RC[1][d];
                if(nr < 0 || nr >=N || nc <0 || nc >=M ) continue;
                if(V[nr][nc] || arr[nr][nc] == 1) continue;

                V[nr][nc] = true;
                dq.add(new int[]{nr, nc});
                cnt++;
            }
        }

        return cnt;
    }
}
