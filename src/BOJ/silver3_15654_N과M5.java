package BOJ;

import java.io.*;
import java.util.*;

public class silver3_15654_N과M5 {
    static int N, M;
    static int[] nList, arr;
    static boolean[] V;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        // N개의 자연수와 자연수 M
        // N개의 자연수 중에서 M개를 고른 수열
        // 길이가 M인 수열을 모두 구하는 프로그램

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        nList = new int[N];
        V = new boolean[N];

        arr = new int[M];
        for(int i=0; i<N; i++) {
            nList[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(nList);
        dfs(0, 0);
        System.out.println(sb);
    }

    public static void dfs(int s, int d) {
        if(d == M) {
            for(int a : arr) {
                sb.append(a).append(" ");
            }
            sb.append("\n");
            return;
        }

        for(int i=0; i<N; i++) {
            if(V[i]) continue;
            V[i] = true;
            arr[d] = nList[i];
            dfs(i+1, d+1);
            V[i] = false;
        }
    }
}
