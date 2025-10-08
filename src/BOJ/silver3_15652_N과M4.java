package BOJ;

import java.io.*;
import java.util.*;

public class silver3_15652_N과M4 {
    // N과 M이 주어졌을 때 길이 M인 수열
    // 1부터 N까지 자연수 중에서 M개
    // 같은 수 여러번
    // 비 내림차순
    static int N, M;
    static int[] arr;
    static StringBuilder res = new StringBuilder();
    public static void main (String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = new int[M];

        dfs( 0);
        System.out.println(res);
    }

    public static void dfs(int d) {
        if(d==M) {
            StringBuilder sb = new StringBuilder();
            int temp = 0;
            boolean isAsc = true;
            for(int a : arr) {
                if(temp <= a) {
                    sb.append(a).append(" ");
                    temp = a;
                } else {
                    isAsc = false;
                    break;
                }
            }

            if(isAsc) {
                res.append(sb).append("\n");
            }
            return;
        }

        for(int i=1; i<=N; i++) {
            arr[d] = i;
            dfs(d+1);
        }
    }

}
