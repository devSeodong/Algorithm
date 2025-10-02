package BOJ;

import java.io.*;
import java.util.*;

public class gold3_2638_치즈 {
    static int N, M;
    static int[][] arr;
    static final int[] dr = {-1, 1, 0, 0};
    static final int[] dc = {0, 0, -1, 1};
    public static void main(String[] args) throws Exception {
    	 // N은 세로격자, M은 가로 격자 
        // 4변중 적어도 2변이상이 실내온도의 공기와 접촉한 것은 한시간만에 녹아 없어짐
        // 치즈 내부 있는 공간은 외부 공기와 접촉하지 않는 것으로 가정
        
        // 입력 : N, M, 격자 정보 ( 치즈 1 ) 
        // 출력 : 치즈가 모두 녹아 없어지는데 걸리는 정확한 시간
    	
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = new int[N][M];
        
        int cheese = 0;
        for (int r = 0; r < N; r++) {
            st = new StringTokenizer(br.readLine());
            for (int c = 0; c < M; c++) {
            	arr[r][c] = Integer.parseInt(st.nextToken());
                if (arr[r][c] == 1) cheese++;
            }
        }

        int res = 0;
        while (cheese > 0) {
            boolean[][] air = new boolean[N][M];
            Deque<int[]> q = new ArrayDeque<>();
            q.add(new int[]{0, 0});
            air[0][0] = true;

            while (!q.isEmpty()) {
                int[] cur = q.poll();
                int r = cur[0], c = cur[1];
                for (int d = 0; d < 4; d++) {
                    int nr = r + dr[d], nc = c + dc[d];
                    if (nr < 0 || nr >= N || nc < 0 || nc >= M || air[nr][nc]) continue;
                    if (arr[nr][nc] == 0) {
                        air[nr][nc] = true;
                        q.add(new int[]{nr, nc});
                    }
                }
            }

            List<int[]> list = new ArrayList<>();
            for (int r = 0; r < N; r++) {
                for (int c = 0; c < M; c++) {
                    if (arr[r][c] != 1) continue;
                    int cnt = 0;
                    for (int d = 0; d < 4; d++) {
                        int nr = r + dr[d], nc = c + dc[d];
                        if (nr < 0 || nr >= N || nc < 0 || nc >= M) continue;
                        if (air[nr][nc]) cnt++;
                    }
                    if (cnt >= 2) list.add(new int[]{r, c});
                }
            }

            for (int[] l : list) {
            	arr[l[0]][l[1]] = 0;
                cheese--;
            }

            res++;
        }

        System.out.println(res);
    }
}
