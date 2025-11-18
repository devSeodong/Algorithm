package BOJ;

import java.io.*;
import java.util.*;

public class silver2_15888_이수근 {
    // 이수근 후보 값
    static int[] arr = {2, 4, 8, 16, 32, 64, 128, 256};
    public static void main(String[] args) throws IOException {
        // 두 근 각각 ( N, M )
        // 2^K 이수근
        // 이수근이 아니면서 모두 정수로 표현되면 정수근
        // 이 외의 모든 경우 둘다 틀렸근

        // 입력 : A, B, C
        // 출력 : 출력

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        double a = Double.parseDouble(st.nextToken());
        double b = Double.parseDouble(st.nextToken());
        double c = Double.parseDouble(st.nextToken());

        // b가 a로 나누어 떨어지지 않으면 바로 실패 처리
        if (((int)b) % ((int)a) != 0) {
            System.out.println("둘다틀렸근");
            return;
        }

        // c도 a로 나누어 떨어지지 않으면 실패
        if (((int)c) % ((int)a) != 0) {
            System.out.println("둘다틀렸근");
            return;
        }

        // 모든 계수를 a로 나누어 x^2+bx+c 형태로 변환
        b /= a; c /= a; a /= a;

        // b^2-4ac <= 0 이면 서로 다른 두 실근이 아니므로 조건 불만족
        if (b * b - 4 * a * c <= 0) {
            System.out.println("둘다틀렸근");
            return;
        }

        // 이수근 체크
        // 두 근이 모두 2^k 꼴
        // 이차방정식 x^2+bx+c=0 의 두 근을 r1, r2라 하면
        // r1+r2=-b, r1*r2=c
        for (int i=0; i<8; i++) { // 첫 번째 근 후보
            for (int j=0; j<8; j++) { // 두 번째 근 후보
                if (i == j) continue;
                // 두 수의 합이 -b이고, 곱이 c이면 실제 근과 일치하는지 검사
                if (arr[i]+arr[j] == -b && arr[i]* arr[j] == c) {
                    System.out.println("이수근");
                    return;
                }
            }
        }

        // 정수근 체크
        // 이수근은 아니지만 두 근이 모두 정수이면 정수근
        // 범위를 -200~200 사이의 정수로 완탐
        for (int i=-200; i<=200; i++) {
            for (int j=-200; j<=200; j++) {
                if (i+j == -b && i*j == c) {
                    System.out.println("정수근");
                    return;
                }
            }
        }
        // 둘 다 아니면
        System.out.println("둘다틀렸근");
    }
}
