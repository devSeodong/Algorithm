package BOJ;

import java.io.*;
import java.util.*;

public class gold4_14938_서강그라운드 {
    static int N, M, R;
    static int[] item, dist;
    static List<List<Node>> list;
    static boolean[] V;
    static class Node implements Comparable<Node> {
        int n, w;

        Node(int n, int w) {
            this.n = n;
            this.w = w;
        }

        @Override
        public int compareTo(Node o) {
            return Integer.compare(this.w, o.w);
        }
    }
    public static void main(String[] args) throws IOException {
        // 각 지역에 아이템들이 몇 개 있는지 알려주는 프로그램
        // 각 지역은 일정한 길이의 길로 다른 지역과 연결
        // 양방향 통행 가능
        // 수색 범위 m이내의 모든 지역의 아이템을 습득 가능

        // 입력 : 지역의 개수 n, 수색범위 m, 길의 개수 r, 각 구역에 있는 아이템 수, 지역의 번호, 길의 길이 l
        // 출력 : 예은이가 얻을 수 있는 최대 아이템 개수 출력

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // 지역 개수
        M = Integer.parseInt(st.nextToken()); // 수색 범위
        R = Integer.parseInt(st.nextToken()); // 길의 개수

        item = new int[N+1];
        st = new StringTokenizer(br.readLine());
        for(int i=1; i<=N; i++) item[i] = Integer.parseInt(st.nextToken());

        list = new ArrayList<>();
        for(int i=0; i<=N; i++) {
            list.add(new ArrayList<>());
        }

        for(int i=1; i<=R; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            list.get(a).add(new Node(b, c));
            list.get(b).add(new Node(a, c));
        }

        dist = new int[N+1];
        V = new boolean[N+1];

        int res = 0;
        for(int i=1; i<=N; i++) {
            res = Math.max(res, solve(i));
        }

        System.out.println(res);
    }

    public static int solve(int s) {
        Arrays.fill(dist, Integer.MAX_VALUE);
        Arrays.fill(V, false);

        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(s, 0));
        dist[s] = 0;

        while(!pq.isEmpty()) {
            Node cur = pq.poll();
            int pos = cur.n;

            if(!V[pos]) {
                V[pos] = true;

                for(Node n : list.get(pos)) {
                    if(!V[n.n] && dist[n.n] > dist[pos] + n.w) {
                        dist[n.n] = dist[pos] + n.w;
                        pq.add(new Node(n.n, dist[n.n]));
                    }
                }
            }
        }

        int cnt = 0;
        for(int i=1; i<=N; i++) {
            if(dist[i] <= M) {
                cnt += item[i];
            }
        }

        return cnt;
    }
}
