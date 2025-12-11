package BOJ;

import java.io.*;
import java.util.*;

public class gold3_23295_스터디시간정하기1 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int T = Integer.parseInt(st.nextToken());

        int[] diff = new int[100005];   // 차분 배열
        int[] people = new int[100005]; // 각 시각별 참석 가능 인원 수

        int max = 0; // 입력 끝시간의 최댓값
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int cnt = Integer.parseInt(st.nextToken());
            for (int j = 0; j < cnt; j++) {
                st = new StringTokenizer(br.readLine());
                int s = Integer.parseInt(st.nextToken());
                int e = Integer.parseInt(st.nextToken());

                diff[s]++;
                diff[e]--;
                max = Math.max(max, e);
            }
        }

        // 차분 배열 -> 실제 각 시간별 인원 수
        people[0] = diff[0];
        for (int t = 1; t <= max; t++) {
            people[t] = people[t - 1] + diff[t];
        }

        // 슬라이딩 윈도우로 길이 T 구간 최대 만족도 찾기
        long current = 0;
        for (int t = 0; t < T; t++) {
            current += people[t];
        }

        long best = current;
        int bestStart = 0;
        int bestEnd = T;

        for (int end = T; end <= max; end++) {
            int prevStartIndex = end - T;
            int newIndex = end;

            current += people[newIndex] - people[prevStartIndex];

            if (current > best) {
                best = current;
                bestStart = prevStartIndex + 1;
                bestEnd = newIndex + 1;
            }
        }

        System.out.println(bestStart + " " + bestEnd);
    }
}
