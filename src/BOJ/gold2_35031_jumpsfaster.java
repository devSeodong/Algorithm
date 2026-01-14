package BOJ;

import java.io.*;
import java.util.*;

public class gold2_35031_jumpsfaster {
    public static void main(String[] args) throws IOException {
        // 입력을 빠르게 받기 위한 BufferedReader
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // N, L, K 입력
        StringTokenizer st = new StringTokenizer(br.readLine());

        long N = Long.parseLong(st.nextToken()); // N은 최대 1e12 -> long
        int L = Integer.parseInt(st.nextToken()); // 영상 길이 (장면 수)
        int K = Integer.parseInt(st.nextToken()); // 점프 장면 수

        // 점프 위치 입력 (1 ~ L)
        st = new StringTokenizer(br.readLine());
        int[] arr = new int[K]; // 점프 위치 저장
        for (int i = 0; i < K; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        // isJump[x] = x번째 장면이 점프면 1
        int[] isJump = new int[L + 1]; // 1..L 사용
        for (int i = 0; i < K; i++) {
            isJump[arr[i]] = 1; // 점프 위치 마킹
        }

        // pref[i] = 1..i까지 점프 개수(prefix sum)
        int[] pref = new int[L + 1];
        for (int i = 1; i <= L; i++) {
            pref[i] = pref[i - 1] + isJump[i];
        }

        // 총 재생해야 하는 장면 수 = N * L (최대 1e18 -> long)
        long totalNeed = N * (long) L;

        // 지금까지 재생한 장면 수
        long totalSeen = 0;

        // 현재 배속 = 이번 프레임에 재생할 장면 수
        long speed = 1;

        // 정답(프레임 수)
        long frames = 0;

        // cycle 내 현재 위치 pos (0..L-1)
        // pref 관점으로 "pos까지 소비한 상태"라고 보면 됨.
        int pos = 0;

        // totalSeen이 totalNeed 이상이 될 때까지 프레임 시뮬레이션
        while (totalSeen < totalNeed) {
            long a = speed; // 이번 프레임에서 재생할 장면 수

            // 이번 프레임에서 점프 개수 j를 O(1)에 계산
            // a개의 장면이 여러 번 L을 넘을 수 있으므로:
            // full = 완전 사이클 수, rem = 남는 장면 수
            long full = a / L;
            int rem = (int) (a % L);

            long j = full * (long) K; // 완전 사이클 full번이면 점프 K개씩

            // rem 구간의 점프 개수 추가
            if (rem != 0) {
                int end = pos + rem; // pos 다음부터 rem개면 (pos, pos+rem]

                if (end <= L) {
                    // 랩 없이 끝나는 경우: (pos, end] => pref[end] - pref[pos]
                    j += (pref[end] - pref[pos]);
                } else {
                    // 랩어라운드:
                    // (pos, L] + (0, end-L]
                    j += (pref[L] - pref[pos]); // pos+1..L
                    j += (pref[end - L] - pref[0]); // 1..end-L
                }
            }

            // 이번 프레임 재생 반영
            totalSeen += a;

            // cycle 위치 업데이트: pos = (pos + a) % L
            // a가 매우 클 수 있으니 a%L만 사용
            int move = (int) (a % L);
            pos += move;
            if (pos >= L) pos -= L;

            // 점프를 j번 봤으니 다음 배속은 speed + j
            speed += j;

            // 프레임 1 증가
            frames++;
        }

        // 최소 프레임 수 출력
        System.out.println(frames);
    }
}
