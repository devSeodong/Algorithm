package BOJ;

import java.io.*;
import java.util.*;

public class silver2_30619_내집마련 {
    public static void main(String[] args) throws IOException {
        // 집 N개, 한사람당 한 채의 집
        // x번 집에 y번 사람이 살면 xy만큼의 세금 감면

        // 입력 : N, A수열, 쿼리 개수 M, L R
        // 출력 : 쿼리마다 정답 한개 씩

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder out = new StringBuilder();

        int N = Integer.parseInt(br.readLine());
        int[] A = new int[N + 1];       // A[집] = 사람 (1-index)
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }

        // house[사람] = 집
        int[] house = new int[N + 1];
        for (int home = 1; home <= N; home++) {
            int person = A[home];
            house[person] = home;
        }

        int M = Integer.parseInt(br.readLine());

        for (int q = 0; q < M; q++) {
            st = new StringTokenizer(br.readLine());
            int L = Integer.parseInt(st.nextToken());
            int R = Integer.parseInt(st.nextToken());

            // 1. house 배열 복사
            int[] tempHouse = house.clone();

            // 2. 사람 L~R에게 배정된 집들을 모아 정렬
            int len = R - L + 1;
            int[] seg = new int[len];
            for (int i = 0; i < len; i++) {
                seg[i] = tempHouse[L + i];   // house[L..R]
            }
            Arrays.sort(seg);                // 집 번호 오름차순

            // 3. 다시 사람 L..R에게 오름차순으로 집 재배정
            for (int i = 0; i < len; i++) {
                tempHouse[L + i] = seg[i];
            }

            // 4. tempHouse[사람] -> result[집]으로 뒤집어서 최종 배정 상태 만들기
            int[] result = new int[N + 1];   // result[집] = 사람
            for (int person = 1; person <= N; person++) {
                int home = tempHouse[person];
                result[home] = person;
            }

            // 5. 출력
            for (int home = 1; home <= N; home++) {
                out.append(result[home]).append(" ");
            }
            out.append("\n");
        }

        System.out.print(out);
    }
}
