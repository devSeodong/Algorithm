package BOJ;

import java.io.*;
import java.util.*;

public class silver2_15666_N과M12 {
    static int N, M;
    static int[] arr, num;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        // N개의 자연수와 자연수 M
        // N개의 자연수 중에서 M개를 고른 수열
        // 같은 수를 여러번
        // 비내림차순

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = new int[M];
        num = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            num[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(num);
        dfs(0, 0);
        System.out.println(sb);
    }

    public static void dfs(int s, int d) {
        if(d==M) {
            for(int a:arr) {
                sb.append(a).append(" ");
            }
            sb.append("\n");
            return;
        }

        int temp = -1;
        for(int i=s;i<N; i++) {
            int now = num[i];
            if(temp != now) {
                temp = now;
                arr[d] = num[i];
                dfs(i, d+1);
            }
        }
    }
}
