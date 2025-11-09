package BOJ;

import java.io.*;
import java.util.*;

public class gold5_17352_여러분의다리가 {
    static int N;
    static int[] P;
    public static void main(String[] args) throws IOException {
        // N개의 섬
        // N-1개의 다리 ( 왕복 가능 )
        // 다리가 하나 뿌서짐 ㅠㅠ
        // 그 다리를 다시 이어서 모든 섬에 왔다갔다 할 수 있도록

        // 입력 N, 다리 정보
        // 출력 다리로 이을 두 섬의 번호

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        P = new int[N+1];
        for(int i=0; i<=N; i++) {
            P[i] = i;
        }

        for(int i=0; i<N-2; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            union(a, b);
        }

        StringBuilder sb = new StringBuilder();
        for(int i=1; i<=N; i++) {
            if(find(i) == i) {
                sb.append(i).append(" ");
            }
        }

        System.out.println(sb);
    }

    public static void union(int a , int b) {
        int ar = find(a);
        int br = find(b);

        if(ar == br) return;
        P[br] = ar;
    }

    public static int find (int p) {
        if (P[p] == p) return p;
        else return P[p] = find(P[p]);
    }
}
