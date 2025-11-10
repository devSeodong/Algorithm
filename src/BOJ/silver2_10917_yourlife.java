package BOJ;

import java.io.*;
import java.util.*;

public class silver2_10917_yourlife {
    public static void main(String[] args) throws Exception {
        // N개의 상황(정점), M개의 변화(유향 간선)
        // x에서 y로만 갈 수 있고 (x < y), 목표는 1번에서 N번까지 최소 변화 횟수(간선 수)로 도달

        // 입력 : N, M,  x y (x<y)
        // 출력 : 1 -> N 최소 변화 수(없으면 -1)
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        List<List<Integer>> list = new ArrayList<>();
        for (int i = 0; i <= N; i++) {
        	list.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            list.get(x).add(y);
        }

        int[] V = new int[N + 1];
        Arrays.fill(V, -1);

        ArrayDeque<Integer> q = new ArrayDeque<>();
        q.add(1);
        V[1] = 0;

        while (!q.isEmpty()) {
            int cur = q.poll();
            if (cur == N) break;
            for (int v : list.get(cur)) {
                if (V[v]==-1) {
                    V[v] = V[cur]+1;
                    q.add(v);
                }
            }
        }

        System.out.println(V[N]);
    }
}
