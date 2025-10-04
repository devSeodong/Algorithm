package BOJ;

import java.io.*;
import java.util.*;

public class silver3_15650_N과M2 {
    static int N, M;
    static int[] arr;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args)  throws IOException {
        // N과 M
        // 1부터 N까지 자연수 중에서 중복 없이 M개
        // 고른 수열은 오름차순

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = new int[M];

        dfs(0, 1);
        System.out.println(sb);
    }

    static void dfs(int d, int s) {
        if (d == M) {
            for (int x : arr) sb.append(x).append(' ');
            sb.append('\n');
            return;
        }
        for (int i = s; i <= N; i++) {
            arr[d] = i;
            dfs(d+1, i+1);
        }
    }
}
