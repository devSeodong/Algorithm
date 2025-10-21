package BOJ;

import java.io.*;
import java.util.*;

public class gold3_15683_감시 {
    static class Node {
        int x, y, num;

        Node(int x, int y, int num) {
            this.x = x;
            this.y = y;
            this.num = num;
        }
    }

    static int N, M;
    static int[][] arr;
    static List<Node> list;
    static int cnt;
    static int res = Integer.MAX_VALUE;
    static int[] dir;
    static int[][] RC = {{0, 1, 0, -1}, {-1, 0, 1, 0}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = new int[N][M];
        list = new ArrayList<>(); // cctv 목록
        cnt = 0; // 빈칸 세기 ( 감시가 가능한 칸 수 )

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
                if (arr[i][j] == 0) {
                    cnt++;
                } else if (arr[i][j] != 6) {
                    // list에 시씨티비 전부 넣기
                    list.add(new Node(i, j, arr[i][j]));
                }
            }
        }

        dir = new int[list.size()]; // 리스트에 담겨진 씨씨티비 방향
        dfs(0);
        System.out.println(res);
    }

    static void dfs(int d) {
        if (d == list.size()) { // 씨씨티비 방향 다 정해졌으면 진짜 탐색 시작
            find(); return;
        }

        int type = list.get(d).num;
        if (type==2) {
            // 2번 : 수평 / 수직
            for (int r = 0; r < 2; r++) {
                // 0 -> 우-좌, 1 -> 상-하
                dir[d] = r; // 씨씨티비 방향
                dfs(d+1); // 다음 씨씨티비로 넘어감
            }
        } else if (type==5) {
            // 5번 : 네방향 동일
            dir[d] = 0;
            dfs(d+1); // 다음 씨씨티비
        } else {
            // 1, 3, 4번 : 네방향
            for (int r=0; r<4; r++) {
                dir[d] = r;
                dfs(d+1);
            }
        }
    }

    static void find() {
        boolean[][] V = new boolean[N][M];
        int cov = 0;

        for (int i = 0; i < list.size(); i++) {
            Node n = list.get(i); // 각 씨씨티비 끄내기
            int[] dirs;
            switch(n.num) {
                case 1:
                    cctv(n.x, n.y, dir[i]%4, V);
                    break;
                case 2:
                    dirs = dir[i]==0 ? new int[]{0, 2} : new int[]{1, 3};
                    for (int d : dirs) {
                        cctv(n.x, n.y, d, V);
                    }
                    break;
                case 3:
                    dirs = new int[]{dir[i]%4, (dir[i]+1)%4};
                    for (int d : dirs) {
                        cctv(n.x, n.y, d, V);
                    }
                    break;
                case 4:
                    dirs = new int[]{dir[i]%4, (dir[i]+1)%4, (dir[i]+2)%4};
                    for (int d : dirs) {
                        cctv(n.x, n.y, d, V);
                    }
                    break;
                case 5:
                    for (int d = 0; d < 4; d++) {
                        cctv(n.x, n.y, d, V);
                    }
                    break;
            }
        }

        // 감시된 빈칸 수
        for (int i=0; i<N; i++) {
            for (int j=0; j<M; j++) {
                if (arr[i][j]==0&&V[i][j]) cov++;
            }
        }

        // 남아있는 사각지대 작은값으로 갱신
        res = Math.min(res, cnt-cov);
    }

    static void cctv(int x, int y, int d, boolean[][] V) {
        int nx = x + RC[0][d];
        int ny = y + RC[1][d];

        while (0 <= nx && nx < N && 0 <= ny && ny < M) {
            if (arr[nx][ny] == 6) break; // 벽 만나면 끝
            if (arr[nx][ny] == 0) { // 빈칸이면 방문 표시
                V[nx][ny] = true;
            }
            nx += RC[0][d];
            ny += RC[1][d];
        }
    }
}
