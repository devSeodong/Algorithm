package BOJ;

import java.io.*;
import java.util.*;

public class gold3_4179_불 {
    static int R, C;
    static int[] J;
    static char[][] arr;
    static int[][] fT, jT, RC = {{-1, 1, 0, 0},{0, 0, -1, 1}};
    public static void main(String[] args) throws IOException {
        // 지훈이를 구하쟈!
        // 불이 붙은 위치, 불에 타기전에 탈출할 수 있는지 여부, 얼마나 빨리 탈출할 수 있는지
        // 불은 매 분마다 한칸씩, 수평, 수직으로
        // 네 방향으로 확산
        // 벽은 통과 못함

        // 입력 : R, C, 미로 정보
        // 출력 : 미로 탈출 X -> IMPOSSIBLE, 가장 빠른 시간 출력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        arr = new char[R][C];
        fT = new int[R][C];
        jT = new int[R][C];

        for(int i=0; i<R; i++) {
            Arrays.fill(jT[i], -1);
            Arrays.fill(fT[i], -1);
        }

        Deque<int[]> fq = new ArrayDeque<>();
        for(int i=0; i<R; i++) {
            String str = br.readLine();
            for(int j=0; j<C; j++) {
                char c = str.charAt(j);
                arr[i][j] = c;
                if(c == 'J') {
                    J = new int[]{i, j};
                } else if(c == 'F') {
                    fq.add(new int[]{i, j});
                    fT[i][j] = 0;
                }
            }
        }

        while(!fq.isEmpty()) {
            int[] cur = fq.poll();
            int r = cur[0], c = cur[1];
            for(int d=0; d<4; d++) {
                int nr = r+RC[0][d];
                int nc = c+RC[1][d];

                if(nr<0 || nr>=R || nc<0 || nc>=C ||
                    arr[nr][nc] == '#' || fT[nr][nc] != -1) continue;

                fT[nr][nc] = fT[r][c] + 1;
                fq.add(new int[]{nr, nc});
            }
        }

        int res = bfs();
        System.out.println(res==-1?"IMPOSSIBLE":res+1);
    }

    public static int bfs() {
        Deque<int[]> jq = new ArrayDeque<>();
        jq.add(J);
        jT[J[0]][J[1]] = 0;

        while(!jq.isEmpty()) {
            int[] cur = jq.poll();
            int r = cur[0], c = cur[1];
            for(int d=0; d<4; d++) {
                int nr = r+RC[0][d];
                int nc = c+RC[1][d];
                if(nr<0 || nr>=R || nc<0 || nc>=C) {
                    return jT[r][c];
                } else if(jT[nr][nc] != -1 || arr[nr][nc] == '#') continue;

                if(fT[nr][nc] == -1 || fT[nr][nc] > jT[r][c] + 1) {
                    jq.add(new int[]{nr, nc});
                    jT[nr][nc] = jT[r][c] + 1;
                }
            }
        }

        return -1;
    }
}
