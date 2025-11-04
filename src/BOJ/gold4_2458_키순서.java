package BOJ;

import java.io.*;
import java.util.*;

public class gold4_2458_키순서 {
    static int N, M, INF = Integer.MAX_VALUE;
    static int[][] dist;
    public static void main(String[] args) throws IOException {
        // 1번부터 N번까지 번호
        // 두 학생끼리 키를 비교한 결과 일부 주어짐
        // 키가 작은 학생부터 연결된 그래프
        // 자신의 키가 몇 번째인지 알 수 있는 학생들이 모두 몇 명?

        // 입력 : N, M, 두 학생의 키 비교 결과
        // 출력 : 결과 출력

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        dist = new int[N+1][N+1];
        for(int[] d : dist) Arrays.fill(d, INF);

        for(int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            dist[a][b] = 1;
        }

        for(int k=1; k<=N; k++) {
            for(int i=1; i<=N; i++) {
                for(int j=1; j<=N; j++) {
                    if(i==j) continue;
                    if(dist[i][k] != INF
                    && dist[k][j] != INF
                    && dist[i][j] > dist[i][k] + dist[k][j]) {
                        dist[i][j] = dist[i][k] + dist[k][j];
                    }
                }
            }
        }

        int res = 0;
        for(int i=1; i<=N; i++) {
            int cnt = 0;
            for(int j=1; j<=N; j++) {
                if(dist[i][j] != INF || dist[j][i] != INF) cnt++;
            }
            if(cnt == N-1) res++;
        }

        System.out.println(res);
    }
}
