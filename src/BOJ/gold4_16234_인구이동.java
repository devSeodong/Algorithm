package BOJ;

import java.io.*;
import java.util.*;

public class gold4_16234_인구이동 {
    static int N, L, R;
    static boolean flag = false;
    static boolean[][] V;
    static List<int[]> list = new ArrayList<>();
    static int[][] arr, RC = {{-1, 1, 0, 0}, {0, 0, -1, 1}};
    public static void main(String[] args) throws IOException {
        // N*N 크기의 땅
        // 각 땅에 나라 하나씩, 각 나라에 A[r][c]명 거주
        // 국경선을 공유하는 두 나라 인구차이 L명 이상, R명 이하라면 국경선 하루동안 엶
        // 국경선이 모두 열리면 인구 이동 시작
        // 연합 : ( 연합의 인구수 ) / ( 연합을 이루고 있는 칸의 개수 )
        // 연합 해체, 국경선 닫음

        // 입력 : N, L R, 각 나라의 인구수
        // 출력 : 인구 이동이 며칠 동안 발생하는지 첫째 줄에 출력

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        arr = new int[N][N];
        V = new boolean[N][N];
        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int cnt = 0;
        while(true) {
            flag = false;
            V = new boolean[N][N];
            for(int i=0; i<N; i++) {
                for(int j=0; j<N; j++) {
                    if(!V[i][j]) {
                        solve(i, j);
                    }
                }
            }
            if(!flag) break;
            else cnt++;
        }

        System.out.println(cnt);
    }

    public static void solve(int i, int j) {
        Deque<int[]> dq = new ArrayDeque<>();
        dq.add(new int[]{i, j});
        V[i][j] = true;

        list.clear();
        list.add(new int[]{i, j});

        while(!dq.isEmpty()) {
            int[] cur = dq.poll();
            i = cur[0]; j = cur[1];
            for(int d=0; d<4; d++) {
                int nr = i + RC[0][d];
                int nc = j + RC[1][d];
                if(nr < 0 || nr >= N || nc < 0 || nc >= N || V[nr][nc]) continue;

                int diff = Math.abs(arr[i][j]-arr[nr][nc]);
                if(diff < L || diff > R) continue;

                V[nr][nc] = true;
                list.add(new int[]{nr, nc});
                dq.add(new int[]{nr, nc});
            }
        }

        if(list.size() > 1) flag = true;

        int sum = 0;
        for (int[] cur : list) {
            sum += arr[cur[0]][cur[1]];
        }

        for(int a=0; a<list.size(); a++) {
            int[] cur = list.get(a);
            arr[cur[0]][cur[1]] = sum / list.size();
        }
    }
}
