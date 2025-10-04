package BOJ;

import java.io.*;
import java.util.*;

public class gold4_9019_DSLR {
    public static void main(String[] args) throws IOException {
        // D : n을 두배로 ( 9999보다 큰 경우에는 10000 모듈러 )
        // S : n-1 ( 0이면 9999 )
        // L : n 자리수 왼쪽 회전
        // R : n 자리수 오른쪽 회전

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());
        for(int t=0; t<T; t++) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());

            System.out.println(bfs(A, B));
        }
    }

    public static String bfs(int a, int b) {
        String[] str = new String[10000];

        Deque<Integer> dq = new ArrayDeque<>();
        dq.add(a);
        str[a] = "";

        int dslr, d, s, l, r;
        while (!dq.isEmpty()) {
            dslr = dq.remove();
            d = (dslr*2)%10000;
            if(str[d] == null) {
                str[d] = str[dslr] + "D";
                dq.add(d);
            }

            s = dslr==0?9999:dslr-1;
            if(str[s] == null) {
                str[s] = str[dslr] + "S";
                dq.add(s);
            }

            l = dslr%1000*10+dslr/1000;
            if(str[l] == null) {
                str[l] = str[dslr] + "L";
                dq.add(l);
            }

            r = dslr%10*1000+dslr/10;
            if(str[r] == null) {
                str[r] = str[dslr] + "R";
                dq.add(r);
            }
        }

        return str[b];
    }
}
