package BOJ;

import java.io.*;
import java.util.*;

public class gold1_13460_구슬탈출2 {
    static int N, M;
    static char[][] board;
    static int[] R, B;
    static int[][] RC = {{-1, 1, 0, 0}, {0, 0, -1, 1}};
    public static void main(String[] args) throws IOException {
        // N*M
        // 빨간구슬을 구멍을 통해서 빼내기
        // 파란구슬이 구멍에 들어가면안됨
        // 왼쪽, 오른쪽 위쪽, 아래쪾 방향 가능
        // 최소 몇번만에 빨간 구슬 뺄 수 있음 ?

        // 입력 : N, M, 보드 모양 정보 ( . 빈칸, # 공이 이동할 수 없는 장애물, 0 구멍위치, R 빨공, B 파공 )
        // 출력 : 최소 몇 번만에 빨간 구슬 뺄 수 있옴? 10번 이상은 -1 출력

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        board = new char[N][M];

        for(int i=0; i<N; i++) {
            String str = br.readLine();
            for(int j=0; j<M; j++) {
                char c = str.charAt(j);
                board[i][j] = str.charAt(j);
                if(c == 'R') {
                    R = new int[]{i, j};
                    board[i][j] = '.';
                } else if(c == 'B') {
                    B = new int[]{i, j};
                    board[i][j] = '.';
                }
            }
        }

        // 만약 B 주변이 다 막혀 있을 경우 빠져나오기 bfs 한번 수행으로 빠져나오기


    }

    public static int solve(int[] r, int[] b) {
        Deque<int[]> rDq = new ArrayDeque<>();
        Deque<int[]> bDq = new ArrayDeque<>();

        rDq.add(new int[]{r[0], r[1], 0});
        bDq.add(new int[]{b[0], b[1]});

        boolean isGoal = false;
        int cnt = 0;
        while(!rDq.isEmpty()) {
            int[] rCur = rDq.poll();
            int[] bCur = bDq.poll();

            if(rCur[2] > 10) return -1;

            for(int d=0; d<4; d++) {
                int rNr = rCur[0], rNc = rCur[1];
                int bNr = bCur[0], bNc = bCur[1];

                // r while 문
                while(true) {
                    rNr += RC[0][d];
                    rNr += RC[1][d];
                    if(rNr < 0 || rNr >= N || rNc < 0 || rNc >=M || board[rNr][rNc] == '#') break;
                    if(board[rNr][rNc] == 'O') {
                        // 골인 했으면,,
                        isGoal = true;
                        cnt = rCur[2]+1;
                        break;
                    }
                    rDq.add(new int[]{rNr, rNc, rCur[2]+1});
                }

                // b while 문
                // 만약.. A가 들어가긴 했는데 B도
                while(true) {
                    bNr += RC[0][d];
                    bNc += RC[1][d];
                    if(bNr < 0 || bNr >= N || bNc < 0 || bNr >= M || board[bNr][bNc] == '#') break;
                    if(board[bNr][bNc] == 'O') break;

                    bDq.add(new int[]{bNr, bNc});
                }
            }
        }

        return cnt;
    }
}
