import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int[][] arr;
    static boolean[][] v;
    static int[] br = {-1, 1, 0, 0};
    static int[] bc = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        // 정사각형 모양의 지도
        // 1 -> 집이 있는곳, 0 -> 집이 없는곳
        // 좌우, 아래위로 다른집이 있는 경우는 단지

        // 입력 : N, 지도 정보
        // 출력 : 총 단지수, 각 단지내 집의 수를 오름차순으로 정렬

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        arr = new int[N][N];
        v = new boolean[N][N];

        for(int i=0; i<N; i++) {
            String[] input  = br.readLine().split("");
            for(int j=0; j<N; j++) {
                arr[i][j] = Integer.parseInt(input[j]);
            }
        }

        int cnt = 0;
        List<Integer> l = new ArrayList<>();
        for(int i=0; i<N; i++) {
            for(int j=0; j<N; j++) {
                if(arr[i][j] == 1 && !v[i][j]) {
                    l.add(bfs(i, j));
                    cnt++;
                }
            }
        }

        StringBuilder sb = new StringBuilder().append(cnt).append("\n");
        Collections.sort(l);
        for(int i : l) {
            sb.append(i).append("\n");
        }

        System.out.println(sb);
    }

    public static int bfs(int i, int j) {
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{i, j});
        v[i][j] = true;

        int cnt = 1;
        while (!q.isEmpty()) {
            int[] p = q.poll();
            int r = p[0], c = p[1];
            for(int d=0; d<4; d++) {
                int nr = r + br[d];
                int nc = c + bc[d];

                if(nr<0||nr>=N||nc<0||nc>=N) continue;

                if(arr[nr][nc]==1&&!v[nr][nc]) {
                    v[nr][nc] = true;
                    q.add(new int[]{nr, nc});
                    cnt++;
                }
            }
        }
        return cnt;
    }
}
