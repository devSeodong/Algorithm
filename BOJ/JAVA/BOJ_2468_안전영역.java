import java.io.*;
import java.util.*;

public class Main {

    static int N;
    static boolean[][] v ;
    static int[][] arr;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    public static void main(String[] args) throws IOException {
        // N*N 2차원 배열
        // 일정한 높이 이하의 모든 지점 물에 잠김
        // 안전한 영역 -> 위, 아래, 오른쪽 혹은 왼쪽으로 인접해 있음 / 크기가 최대

        // 입력 : N (2이상 100이하), 바닥 정보
        // 출력 : 안전한 영역의 최대 개수

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());

        arr = new int[N][N];

        int maxNum = Integer.MIN_VALUE;
        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
                if(arr[i][j] > maxNum) maxNum = arr[i][j];
            }
        }


        int res = 0;
        for(int i = 0; i<=maxNum; i++) {
            int cnt = solve(i);
            if(cnt > res) res = cnt;
        }

        System.out.println(res);
    }

    public static int solve(int n) {
        int cnt = 0;
        v = new boolean[N][N];
        for(int i=0; i<N; i++) {
            for(int j=0; j<N; j++) {
                if(arr[i][j] > n && !v[i][j]) {
                    bfs(i, j, n);
                    cnt++;
                }
            }
        }
        return cnt;
    }

    public static void bfs(int i, int j, int n) {
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{i, j});
        v[i][j] = true;

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int r = cur[0], c = cur[1];
            for(int d=0; d<4; d++) {
                int nr = r + dr[d];
                int nc = c + dc[d];

                if(nr<0||nr>=N||nc<0||nc>=N) continue;

                if(arr[nr][nc] > n && !v[nr][nc]) {
                    v[nr][nc] = true;
                    q.add(new int[] {nr, nc});
                }
            }
        }
    }
}
