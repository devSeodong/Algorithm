package BOJ;

import java.io.*;
import java.util.*;

public class gold3_23030_후다다닥을이겨츄르를받자 {
    static int N, M, T, K;
    static int[][][] arr;
    static List<Integer>[] list;
    static int[] num;
    static class Node implements Comparable<Node> {
        int cost, line, station;

        Node(int cost, int line, int station) {
            this.cost = cost;
            this.line = line;
            this.station = station;
        }

        @Override
        public int compareTo(Node o) {
            return Integer.compare(this.cost, o.cost);
        }
    }
    public static void main(String[] args) throws IOException {
        // 환승시간, 출발지, 도착지를 입력하면 최단 경로로 이동했을 때 걸리는 소요 시간을 알려주는 어플
        // 1~N까지의 번호로 노선 구분
        // 하나의 노선에는 최소 1개, 최대 50개 역
        // 지하철은 양방향 통행 가능
        // M개의 환승역 3개 이상의 노선이 겹치지는 않음
        // 환승하는데 걸리는 시간 T

        // 입력 : N, 속한 역의 개수, M, 환승역의 정보, 요청 개수 K, T, 출발 도착 정보
        // 출력 : 출발지에서 도착지로 가는 최단 시간

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());

        arr = new int[N+1][][];
        list = new ArrayList[N+1];
        num = new int[N+1];

        for(int i=1; i<=N; i++){
            int cnt = Integer.parseInt(st.nextToken());
            num[i] = cnt;
            arr[i] = new int[cnt+1][2];
            list[i] = new ArrayList<>();
        }

        M = Integer.parseInt(br.readLine());
        for(int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            int p1 = Integer.parseInt(st.nextToken());
            int p2 = Integer.parseInt(st.nextToken());
            int q1 = Integer.parseInt(st.nextToken());
            int q2 = Integer.parseInt(st.nextToken());

            list[p1].add(p2);
            list[q1].add(q2);

            arr[p1][p2][0] = q1;
            arr[p1][p2][1] = q2;

            arr[q1][q2][0] = p1;
            arr[q1][q2][1] = p2;
        }

        K = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<K; i++) {
            st = new StringTokenizer(br.readLine());
            int t = Integer.parseInt(st.nextToken());
            int u1 = Integer.parseInt(st.nextToken());
            int u2 = Integer.parseInt(st.nextToken());
            int v1 = Integer.parseInt(st.nextToken());
            int v2 = Integer.parseInt(st.nextToken());

            int ans = solve(t, u1, u2, v1, v2);
            sb.append(ans).append('\n');
        }

        System.out.println(sb);
    }

    public static int solve(int t, int sl, int ss, int el, int es) {
        int[][] dist = new int[N+1][];
        for(int i=1; i<=N; i++){
            dist[i] = new int[num[i]+1];
            Arrays.fill(dist[i], 1000000000);
        }

        PriorityQueue<Node> pq = new PriorityQueue<>();
        dist[sl][ss] = 0;
        pq.add(new Node(0, sl, ss));

        while(!pq.isEmpty()){
            Node cur = pq.poll();
            if(cur.cost > dist[cur.line][cur.station]) continue;
            if(cur.line == el && cur.station == es) return cur.cost;

            for(int l : list[cur.line]) {
                int cost = Math.abs(cur.station - l);
                int line = arr[cur.line][l][0];
                int station = arr[cur.line][l][1];

                int nextCost = cur.cost + cost + t;
                if(line == 0) continue;
                if(dist[line][station] > nextCost) {
                    dist[line][station] = nextCost;
                    pq.add(new Node(nextCost, line, station));
                }
            }

            if(cur.line == el) {
                int cost = Math.abs(cur.station - es);
                int nextCost = cur.cost + cost;
                if(dist[el][es] > nextCost) {
                    dist[el][es] = nextCost;
                    pq.add(new Node(nextCost, el, es));
                }
            }
        }

        return dist[el][es];
    }
}
