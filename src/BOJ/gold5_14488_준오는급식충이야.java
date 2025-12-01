package BOJ;

import java.io.*;
import java.util.*;

public class gold5_14488_준오는급식충이야 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        double T = Double.parseDouble(st.nextToken());

        int[] X = new int[N];
        int[] V = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            X[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            V[i] = Integer.parseInt(st.nextToken());
        }

        // 가능한 만남 위치들의 오른쪽 끝 중 최소값
        double min = Double.MAX_VALUE;
        // 가능한 만남 위치들의 왼쪽 끝 중 최대값
        double max = 0;
        for (int i = 0; i < N; i++) {
            // i번째 학생이 T초 안에 도달할 수 있는 가장 오른쪽 위치
            min = Math.min(min, X[i]+V[i]*T);
            // i번째 학생이 T초 안에 도달할 수 있는 가장 왼쪽 위치
            max = Math.max(max, X[i]-V[i]*T);
        }

        System.out.println(min+1e-8 >= max ?1:0);
    }
}
