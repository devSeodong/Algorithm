package BOJ;

import java.io.*;
import java.util.*;

public class silver1_1629_곱셈 {
    static long C;
    public static void main(String[] args) throws IOException {
        // 자연수 A를 B번 곱한 수
        // C로 나눈 나머지를 구하는 프로그램

        // 입력 : A, B, C
        // 출력 : A*B %C

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        long A = Long.parseLong(st.nextToken());
        long B = Long.parseLong(st.nextToken());
        C = Long.parseLong(st.nextToken());

        System.out.println(solve(A, B));
    }

    public static long solve(long A, long B) {
        if(B==1) return A%C;
        long temp = solve(A, B/2);
        if(B%2==1) return(temp*temp%C)*A%C;
        return temp*temp%C;
    }
}
