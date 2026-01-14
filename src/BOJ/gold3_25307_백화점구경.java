package BOJ;

import java.io.*;
import java.util.*;

public class gold3_25307_백화점구경 {
    static int[][] RC = {{-1, 1, 0, 0}, {0, 0, -1, 1}};
    public static void main(String[] args) throws IOException{
        // N*M 백화점
        // 칸 이동 -> 1만큼 체력 소모
        // 기둥 칸 이동 불가, 마네킹과 거리가 K이하인 칸은 사용하지 않음 ( 출발할때는 가능 )
        // 체력 소모를 최소화

        // 입력 : N, M, K, 백화점의 상태 ( 기둥 1, 의자 2, 마네킹 3, 시작 4 )
        // 출력 : 체력의 최솟값 ( 갈 수 없다면 -1 )

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[][] arr = new int[N][M];
        int[][] V = new int[N][M]; // 시작점에서의 최단거리
        int[][] dist = new int[N][M]; // 마네킹까지 최단거리
        for(int i=0; i<N; i++) {
            Arrays.fill(V[i], Integer.MAX_VALUE);
            Arrays.fill(dist[i], Integer.MAX_VALUE);
        }

        Deque<int[]> m = new ArrayDeque<>();
        int[] start = new int[2];
        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<M; j++){
                arr[i][j] = Integer.parseInt(st.nextToken());
                if(arr[i][j] == 3) {
                    m.add(new int[]{i, j, 0});
                    dist[i][j] = 0;
                } else if(arr[i][j] == 4) {
                    start[0] = i;
                    start[1] = j;
                }
            }
        }

        // 각 칸의 마네킹으로부터의 거리 계산 ( K까지 )
        // dist[r][c] = (r, c)가 가장 가까운 마네킹으로부터 떨어진 거리
        while(!m.isEmpty()) {
            int[] cur = m.poll();
            if(cur[2] == K) continue;
            for(int d=0; d<4; d++){
                int nr = cur[0] + RC[0][d];
                int nc = cur[1] + RC[1][d];
                // 이미 더 짧은 거리로 방문 했으면 갱신할 필요 업슴
                if(nr < 0 || nr >= N || nc < 0 || nc >= M || dist[nr][nc] <= cur[2]+1) continue;

                dist[nr][nc] = cur[2]+1;
                m.add(new int[]{nr, nc, cur[2]+1});
            }
        }

        Deque<int[]> dq = new ArrayDeque<>();
        V[start[0]][start[1]] = 0;
        dq.add(new int[]{start[0], start[1], 0});
        int res = Integer.MAX_VALUE;

        while(!dq.isEmpty()) {
            int[] cur = dq.poll();
            if(arr[cur[0]][cur[1]] == 2) {
                res = cur[2];
                break;
            }

            for(int d=0; d<4; d++){
                int nr = cur[0] + RC[0][d];
                int nc = cur[1] + RC[1][d];

                // 기둥이면 안댐, 마네킹 위험구역 안댐, 이미 더 짧게 방문한적 있으면 스킵
                if(nr < 0 || nr >= N || nc < 0 || nc >= M || arr[nr][nc] == 1 || V[nr][nc] <= cur[2]+1 || dist[nr][nc] <= K) continue;

                V[nr][nc] = cur[2]+1;
                dq.add(new int[]{nr, nc, cur[2]+1});
            }
        }

        System.out.println(res == Integer.MAX_VALUE?-1:res);
    }


}
