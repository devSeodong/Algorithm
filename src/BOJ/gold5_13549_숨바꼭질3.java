package BOJ;

import java.io.*;
import java.util.*;

public class gold5_13549_숨바꼭질3 {
    static int N, K;
    static boolean[] V;
    public static void main(String[] args) throws IOException{
        // 수빈 N, 동생 K
        // 걷거나 순간이동할 수 있음

        // 입력 : N K
        // 출력 : 수빈이가 동생을 찾는 가장 빠른 시간

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        V = new boolean[100001];

        bfs(N);
    }

    public static void bfs(int s) {
        Deque<int[]> dq = new ArrayDeque<>();
        dq.add(new int[]{s, 0});
        V[s] = true;

        while (!dq.isEmpty()) {
            int[] cur = dq.poll();
            if(cur[0] == K) {
                System.out.println(cur[1]);
                return;
            }

            for(int i=0; i<3; i++) {
                int n, t;
                if(i==0) {
                    n = cur[0]*2;
                    t = cur[1];
                } else if(i==1) {
                    n = cur[0]-1;
                    t = cur[1]+1;
                } else {
                    n = cur[0]+1;
                    t = cur[1]+1;
                }

                if(0<=n && n <= 100000 && !V[n]) {
                    dq.add(new int[]{n, t});
                    V[n] = true;
                }
            }
        }
    }
}
