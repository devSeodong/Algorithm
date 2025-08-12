import java.io.*;
import java.util.*;

public class Main {
    // 한 도화지에 그림의 개수와 그 그림중 넓이가 가장 넓은 것이 넓이
    // 1로 연결된 것이 한 그림
    // 그림에 포함된 1의 개수가 넓이

    // 입력 : 세로 크기 N, 가로 크기 M, 그림정보
    // 출력 : 그림의 개수, 넓은 그림의 넓이
    static int N, M;
    static boolean[][] v ;
    static int[][] arr;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        v = new boolean[N][M];
        arr = new int[N][M];
        for(int n=0; n<N; n++) {
            st = new StringTokenizer(br.readLine());
            for(int m=0; m<M; m++) {
                arr[n][m] = Integer.parseInt(st.nextToken());
            }
        }

        int cnt = 0, w = 0;
        for(int n=0; n<N; n++) {
            for(int m=0; m<M; m++) {
                if(arr[n][m]==1 && !v[n][m]) {
                    int a = solve(n, m);
                    cnt++;
                    if(w<a) w=a;
                }
            }
        }

        System.out.println(cnt+"\n"+w);
    }

    public static int solve(int n, int m) {
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{n, m});
        v[n][m] = true;

        int cnt = 1;
        while(!q.isEmpty()) {
            int[] c = q.poll();
            for(int d=0; d<4; d++) {
                int nr = c[0]+dr[d];
                int nc = c[1]+dc[d];
                if(nr<0||nr>=N||nc<0||nc>=M) continue;
                if(!v[nr][nc] && arr[nr][nc] == 1) {
                    v[nr][nc] = true;
                    q.add(new int[]{nr, nc});
                    cnt++;
                }
            }
        }
        return cnt;
    }
}
