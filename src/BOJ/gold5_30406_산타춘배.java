package BOJ;

import java.util.*;
import java.io.*;

public class gold5_30406_산타춘배 {
    static int N;
    static int[] cnt;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        cnt = new int[4];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++) {
            int num = Integer.parseInt(st.nextToken());
            cnt[num] ++;
        }

        int res = 0;
        res += solve(0, 3);
        res += solve(1, 2);
        res += solve(0, 2);
        res += solve(1, 3);
        res += solve(0, 1);
        res += solve(2, 3);

        System.out.println(res);
    }

    public static int solve(int a, int b) {
        int m = Math.min(cnt[a], cnt[b]);
        cnt[a] -= m;
        cnt[b] -= m;
        return m * (a^b);
    }
}
