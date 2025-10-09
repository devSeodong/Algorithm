package BOJ;

import java.io.*;
import java.util.*;

public class silver2_16953_AtoB {
    public static void main(String[] args) throws IOException {
        // A와 B를 바꿈
        // 2를 곱합, 1을 수의 가장 오른쪽에 추가함

        // 입력 : A, B
        // 출력 : A를 B로 바꾸는데 필요한 연산의 최솟값에 1을 더한 값

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int A = Integer.parseInt(st.nextToken());
        int B = Integer.parseInt(st.nextToken());

        int cnt = 0;
        while (B > A) {
            if(B % 10 == 1) {
                B = (B-1) / 10;
                cnt++;
            } else if(B % 2 == 0) {
                B /= 2;
                cnt++;
            } else {
                System.out.println(-1);
                return;
            }
        }

        if(B==A) {
            System.out.println(cnt+1);
        } else {
            System.out.println(-1);
        }
    }
}
