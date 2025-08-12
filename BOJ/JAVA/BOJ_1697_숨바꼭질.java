import java.io.*;
import java.util.*;

public class Main {
    // 수빈이는 현재 점 N에 있고, 동생은 점 K에 있다.
    // 수빈 -> 걷거나 순간이동을 할 수 있음
    // 위치가 X일 때, 1초후에 X-1 or X+1
    // 순간이동하는 경우 -> 1초 후에 2*X의 위치

    // 입력 : N과 K
    // 출력 : 수빈이가 동생을 찾는 가장 빠른 시간

    static boolean[] v ;
    static int[] w ;
    static int max = 100000;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        v = new boolean[max+1];
        w = new int[max+1];

        if (N >= K) {
            System.out.println(N-K);
            return;
        }

        System.out.println(solve(N, K));
    }

    public static Integer solve(int N, int K) {
        Queue<Integer> q = new LinkedList<>();
        q.add(N);
        v[N] = true;
        w[N] = 0;
        while (!q.isEmpty()) {
            int n = q.poll();
            if(n == K) return w[n];

            if(n-1 >= 0 && !v[n-1]) {
                v[n-1] = true;
                w[n-1] = w[n]+1;
                q.add(n-1);
            }

            if(n+1 <= max && !v[n+1]) {
                v[n+1] = true;
                w[n+1] = w[n]+1;
                q.add(n+1);
            }

            if(2*n <= max && !v[2*n]) {
                v[2*n] = true;
                w[2*n] = w[n]+1;
                q.add(2*n);
            }
        }
        return null;
    }
}
