package BOJ;

import java.io.*;
import java.util.*;

public class gold4_1976_여행가자 {
    static int[] arr;
    public static void main(String[] args) throws IOException {
        // 도시 N개
        // 여행 경로가 가능 한 것인지
        // 가능한지 여부 ( 여러번 방문 가능 )

        // 입력 : N, M, N개의 연결정보 ( 1 연결 0 연결 X )
        // 출력 : YES NO

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());
        int[][] arr = new int[N+1][N+1];
        int[] node = new int[M];

        for(int i=1; i<=N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=1; j<=N; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
            arr[i][i] = 1;
        }

        st = new StringTokenizer(br.readLine());
        for(int i=0; i<M; i++) {
            node[i] = Integer.parseInt(st.nextToken());
        }

        for(int k=1; k<=N; k++) {
            for(int i=1; i<=N; i++) {
                if(arr[i][k]==0) continue;
                for(int j=1; j<=N; j++) {
                    if(arr[k][j] == 1) arr[i][j] = 1;
                }
            }
        }

        boolean isOk = true;
        for(int i=0; i<M-1; i++){
            int a = node[i];
            int b = node[i+1];
            if(arr[a][b] == 0) {
                isOk = false;
                break;
            }
        }

        System.out.println(isOk ? "YES" : "NO");
    }
}
