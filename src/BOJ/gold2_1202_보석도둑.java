package BOJ;

import java.io.*;
import java.util.*;

public class gold2_1202_보석도둑 {
    static int N, K;
    static int[] bag;
    static Jwel[] jwel;

    static class Jwel {
        int idx = 0, W, C;
        public Jwel(int W, int C) {
            this.W = W;
            this.C = C;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        jwel = new Jwel[N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int W = Integer.parseInt(st.nextToken());
            int C = Integer.parseInt(st.nextToken());
            jwel[i] = new Jwel(W, C);
        }

        Arrays.sort(jwel, new Comparator<Jwel>() {
            @Override
            public int compare(Jwel o1, Jwel o2) {
                return Integer.compare(o1.W, o2.W);
            }
        });

        bag = new int[K];
        for (int i = 0; i < K; i++) {
            bag[i] = Integer.parseInt(br.readLine());
        }
    	
        Arrays.sort(bag);
        
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        long res = 0L;
        int j = 0;
        
        for (int b : bag) {
            while (j < N && jwel[j].W <= b) {
                pq.add(jwel[j].C);
                j++;
            }
            
            if (!pq.isEmpty()) {
                res += pq.poll();
            }
        }

        System.out.println(res);
    }
}
