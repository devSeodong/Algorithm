package BOJ;

import java.io.*;
import java.util.*;

public class gold4_1504_특정한최단경로 {
    static class Node implements Comparable<Node> {
        int end;
        long weight;

        Node(int end, long weight) {
            this.end = end;
            this.weight = weight;
        }

        @Override
        public int compareTo(Node o) {
            return Long.compare(this.weight, o.weight);
        }

    }
    static int N, E;
    static List<List<Node>> list;
    static long[] dist;
    static boolean[] V;
    static long INF = Long.MAX_VALUE / 4;
    public static void main(String[] args) throws IOException{
        // 방향성이 없는 그래프
        // 1~N번 정점으로 최단 거리 이동
        // 임의로 주어진 두 정점은 반드시 통과해야함

        // 입력 : 정점의 갯수 N, 간선의 개수 E, a, b, c, 반드시 거쳐야 하는 두 개의 정점 번호
        // 출력 : 두개의 정점을 지나는 최단 경로의 길이

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        list = new ArrayList<>();
        dist = new long[N+1];
        V = new boolean[N+1];
        Arrays.fill(dist, INF);

        for(int i=0; i<=N; i++) {
            list.add(new ArrayList<>());
        }

        for(int i=0; i<E; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            list.get(s).add(new Node(e, w));
            list.get(e).add(new Node(s, w));
        }

        st = new StringTokenizer(br.readLine());
        int v1 = Integer.parseInt(st.nextToken());
        int v2 = Integer.parseInt(st.nextToken());

        long res = 0;
        res += solve(1, v1);
        res += solve(v1, v2);
        res += solve(v2, N);

        long res1 = 0;
        res1 += solve(1, v2);
        res1 += solve(v2, v1);
        res1 += solve(v1, N);

        long ans = Math.min(res, res1);
        System.out.println(ans >= INF ? -1 : ans);
    }

    public static long solve(int s, int e) {
        Arrays.fill(dist, INF);
        Arrays.fill(V, false);

        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(s, 0));
        dist[s] = 0;

        while(!pq.isEmpty()) {
            Node cur = pq.poll();
            int end = cur.end;

            if(!V[end]) {
                V[end] = true;
                for(Node n : list.get(end)) {
                    if(!V[n.end] && dist[n.end] > dist[end]+n.weight) {
                        dist[n.end] = dist[end] + n.weight;
                        pq.add(new Node(n.end, dist[n.end]));
                    }
                }
            }
        }

        return dist[e];
    }
}
