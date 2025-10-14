package BOJ;                                     // 백준 풀이 패키지 (선택 사항)

import java.io.*;                                // 입출력 관련 라이브러리
import java.util.*;                              // 유틸(Tokenizer 등)

public class gold3_20057_마법사상어와토네이도 {
    static int N;                                // 격자 크기 N
    // RC[0]=dr, RC[1]=dc, d=0..3 => 좌(0), 하(1), 우(2), 상(3) (CCW 순회)
    static int[][] A, RC = {{0, 1, 0, -1}, {-1, 0, 1, 0}};
    static int res = 0;                          // 격자 밖으로 나간 모래 합(정답)

    public static void main(String[] args) throws IOException {
        // 토네이도 시뮬레이션: 중심에서 나선형으로 이동하며 도착칸 모래를 분산
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());     // N 입력
        A = new int[N][N];                       // 모래 격자

        for (int i = 0; i < N; i++) {            // N줄 입력
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {        // 각 칸 모래량 저장
                A[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        solve();                                  // 시뮬레이션 수행
        System.out.println(res);                  // 정답 출력
    }

    public static void solve() {
        int cnt = 0, cy = 1, d = 0;              // cnt: 현재 구간 이동수, cy: 이번 구간 목표 길이, d: 방향
        boolean flag = false;                    // 두 번 전환마다 cy++ 하기 위한 토글
        int r = N / 2, c = N / 2;                // 시작점: 격자 중앙

        for (int i = 0; i < N * N - 1; i++) {    // 시작칸 제외 총 N*N-1번 이동
            int nr = r + RC[0][d];               // 다음 행
            int nc = c + RC[1][d];               // 다음 열

            if (nr < 0 || nr >= N || nc < 0 || nc >= N) break; // 방어 코드(실제로는 안 나감)
            if (A[nr][nc] != 0) sand(nr, nc, d); // 도착칸 모래가 있으면 분산(없어도 호출 가능)

            if (cy == ++cnt) {                   // 구간 길이만큼 이동 완료 시
                d = (d + 1) % 4;                 // 방향 전환(좌→하→우→상)
                cnt = 0;                         // 구간 이동 카운트 리셋

                if (flag) {                      // 두 번 전환마다
                    cy++;                        // 다음 구간 길이 증가(1,1,2,2,3,3,...)
                    flag = false;                // 토글 해제
                } else {
                    flag = true;                 // 한 번 전환 표시
                }
            }
            r = nr;                              // 현재 위치 갱신
            c = nc;
        }
    }

    // (dx, dy)를 90도 '반시계(CCW)'로 d번 회전: (x,y)->(-y,x)
    public static int[] swap(int r, int c, int d) {
        for (int i = 0; i < d; i++) {
            int nr = -c;                         // x' = -y
            int nc = r;                          // y' = x
            r = nr;                              // 갱신
            c = nc;
        }
        return new int[]{r, c};                  // 회전된 상대좌표 반환
    }

    // 좌(왼쪽 진행) 기준 분산 패턴: {dx, dy, percent}
    // 합계 45% (2+2+10+7+1+10+7+1+5), 남은 55%는 알파 칸으로 이동
    static int[][] per = {
            {-2, 0, 2}, {2, 0, 2},
            {-1, -1, 10}, {-1, 0, 7}, {-1, 1, 1},
            {1, -1, 10}, {1, 0, 7}, {1, 1, 1},
            {0, -2, 5}
    };

    public static void sand(int r, int c, int d) {
        int s = A[r][c];                         // 도착칸 모래량

        int move = 0;                            // 퍼센트로 분산된 총합(알파 계산용)
        for (int i = 0; i < 9; i++) {            // 9개 비율 칸 처리
            int[] cur = swap(per[i][0], per[i][1], d); // 좌 기준 좌표를 d방향으로 회전
            int nr = r + cur[0];                 // 실제 분산 행
            int nc = c + cur[1];                 // 실제 분산 열
            int perV = s * per[i][2] / 100;        // 퍼센트 분배(정수 나눗셈: 버림)

            move += perV;                        // 분산 누적
            if (0 <= nr && nr < N && 0 <= nc && nc < N) { // 범위 내면 해당 칸에 더함
                A[nr][nc] += perV;
            } else {
                res += perV;                     // 범위 밖이면 정답 누적
            }
        }

        int[] cur = swap(0, -1, d);              // 알파 칸: 좌 기준 (0,-1)을 d방향 회전
        int nr = r + cur[0];                     // 알파 행
        int nc = c + cur[1];                     // 알파 열
        int a = s - move;                        // 남은 모래(=55%)

        if (0 <= nr && nr < N && 0 <= nc && nc < N) {     // 알파 범위 체크
            A[nr][nc] += a;
        } else {
            res += a;                            // 밖이면 정답 누적
        }

        A[r][c] = 0;                             // 도착칸은 모두 흩날려 0
    }
}
