package BOJ;

import java.io.*;
import java.util.*;

public class gold1_27653_최소트리분할 {
    public static void main(String[] args) throws IOException {
        // 정점이 N개인 트리 , 1부터 N까지의 번호, 초기 가중치 0
        // 1이상 N이하의 모든 i에 대해 정점 i의 가중치가 Ai가 되도록
        //   임의의 부분 연결 그래프에 대하여 정점의 가중치를 1씩 증가

        // 입력 : 정점의 개수 N, 목표 가중치, 간선정보
        // 출력 : 최소 연산 횟수

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        long[] arr = new long[N+1];
        boolean[] V = new boolean[N+1];

        st = new StringTokenizer(br.readLine());
        for(int i=1; i<=N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        List<List<Integer>> list = new ArrayList<>();
        for(int i=0; i<=N; i++) {
            list.add(new ArrayList<>());
        }

        for(int i=1; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            list.get(a).add(b);
            list.get(b).add(a);
        }

        Deque<Integer> dq = new ArrayDeque<>();
        dq.add(1);
        V[1] = true;
        long res = arr[1];

        while(!dq.isEmpty()) {
            int cur = dq.poll();
            List<Integer> l = list.get(cur);

            for(int i=0; i<l.size(); i++) {
                int next = l.get(i);
                if(V[next]) continue;

                if(arr[cur] < arr[next]) {
                    res += arr[next]-arr[cur];
                }

                V[next] = true;
                dq.add(next);
            }
        }

        System.out.println(res);
    }
}
