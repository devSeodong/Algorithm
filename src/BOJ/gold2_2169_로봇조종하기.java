package BOJ;

import java.io.*;
import java.util.*;

public class gold2_2169_로봇조종하기 {
    static int N, M;                 // 행, 열 크기
    static int[][] arr;              // 격자 가치
    static int[][][] dp;             // dp[r][c][k] : k=0(좌->우 스윕 값), 1(우->좌 스윕 값), 2(해당 행 최종값)

    public static void main(String[] args) throws IOException {
        // 화성에 무선 조종 로봇
        // N*M 격자, 이동은 왼/오/하, 같은 칸 재방문 금지
        // (0,0)에서 (N-1,M-1)까지 가치 합 최대

        // 입력 : N M, 이후 N줄에 걸쳐 M개 정수
        // 출력 : 최대 가치 합

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());   // 행
        M = Integer.parseInt(st.nextToken());   // 열

        arr = new int[N][M];                    // 격자 초기화
        dp = new int[N][M][3];                  // 0:좌->우, 1:우->좌, 2:해당 행 결합

        for (int i = 0; i < N; i++) {           // 격자 입력
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        final int NEG_INF = -1_000_000_000;     // 불가능 상태 대체값(충분히 작은 음수)

        dp[0][0][0] = arr[0][0];                // 시작칸 좌->우 누적
        dp[0][0][1] = NEG_INF;                  // 시작칸에서 우->좌는 의미상 불가
        dp[0][0][2] = dp[0][0][0];              // ★ 첫 행 결합값 초기화 (중요!)

        for (int i = 1; i < M; i++) {           // 첫 행은 좌/우만 가능하므로 단순 누적
            dp[0][i][0] = dp[0][i - 1][0] + arr[0][i]; // 좌->우 누적
            dp[0][i][1] = NEG_INF;                      // 첫 행에서 우->좌 시작은 금지
            dp[0][i][2] = dp[0][i][0];                  // 결합값은 좌->우 값
        }

        for (int i = 1; i < N; i++) {           // 각 행에 대해
            // 좌->우 스윕 시작점(왼쪽 끝)은 위에서만 내려올 수 있음
            dp[i][0][0] = dp[i - 1][0][2] + arr[i][0];

            // 좌->우 스윕: 왼쪽에서 오거나(up), 위에서 내려오거나 중 큰 값 선택
            for (int j = 1; j < M; j++) {
                dp[i][j][0] = Math.max(dp[i - 1][j][2], dp[i][j - 1][0]) + arr[i][j];
            }

            // 우->좌 스윕 시작점(오른쪽 끝)도 위에서만 내려올 수 있음
            dp[i][M - 1][1] = dp[i - 1][M - 1][2] + arr[i][M - 1];

            // 우->좌 스윕: 오른쪽에서 오거나, 위에서 내려오거나 중 큰 값 선택
            for (int j = M - 2; j >= 0; j--) {
                dp[i][j][1] = Math.max(dp[i - 1][j][2], dp[i][j + 1][1]) + arr[i][j];
            }

            // 해당 행의 최종값(재방문 방지용): 좌->우와 우->좌 중 큰 값으로 결합
            for (int j = 0; j < M; j++) {
                dp[i][j][2] = Math.max(dp[i][j][0], dp[i][j][1]);
            }
        }

        System.out.println(dp[N - 1][M - 1][2]); // 목적지의 결합값 출력
    }
}
