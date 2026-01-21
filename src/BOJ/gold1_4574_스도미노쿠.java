package BOJ;

import java.io.*;
import java.util.*;

public class gold1_4574_스도미노쿠 {
    static int N;
    static boolean flag;
    static boolean[][] V;
    static int[] dr = { 0, 1 };
    static int[] dc = { 1, 0 };
    public static void main(String[] args) throws IOException {
        // 스도쿠 규칙
        // 그리드에 1부터 9까지 숫자
        // 나머지 칸은 타일(도미노)로 채워야 함
        // 타일 : 1~9까지 가능한 쌍 포함 (각 쌍은 한 번만 사용)

        // 입력 : 여러 테스트케이스
        // N(이미 놓인 도미노 수), N개의 도미노 정보, 1~9 단독 숫자 위치 9개
        // 출력 : 스도쿠를 푼 결과

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int num = 1;

        while (true) {
            N = Integer.parseInt(br.readLine());
            if (N == 0) break;

            int[][] map = new int[9][9];
            V = new boolean[10][10];
            flag = false;

            int num1, num2;
            String str1, str2;
            for (int i = 0; i < N; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine(), " ");
                num1 = Integer.parseInt(st.nextToken());
                str1 = st.nextToken();
                num2 = Integer.parseInt(st.nextToken());
                str2 = st.nextToken();

                // (num1, num2) 도미노는 사용됨 표시 (순서 없는 쌍이라 대칭 처리)
                V[num1][num2] = true;
                V[num2][num1] = true;

                // 위치 문자열을 (r,c)로 변환해서 보드에 배치
                // 행: 'A'~'I' => 0~8 (char - 65)
                // 열: '1'~'9' => 0~8 (char - '1')
                map[str1.charAt(0)-65][str1.charAt(1)-'1'] = num1;
                map[str2.charAt(0)-65][str2.charAt(1)-'1'] = num2;
            }

            // 1~9 단독 숫자(도미노가 아닌, 각각 한 칸씩 고정된 숫자) 위치 9개 입력
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            for (int i = 1; i <= 9; i++) { // 숫자 i가 들어갈 위치를 하나씩 받음
                str1 = st.nextToken(); // 위치 문자열
                map[str1.charAt(0)-65][str1.charAt(1)-'1'] = i; // 해당 칸에 i 배치
            }

            System.out.printf("Puzzle %d\n", num++);
            solve(map, 0);
        }
    }

    private static void solve(int[][] map, int idx) {
        // 이미 답을 찾은 상태면 더 탐색할 필요 없음
        if (flag) return;

        // 81칸 전부 채웠으면 정답 완성
        if (idx == 81) {
            flag = true; // 정답 찾았다고 표시

            // 보드 출력 (문제 요구: 숫자를 공백 없이 9줄)
            for (int i = 0; i < map.length; i++) {
                for (int j = 0; j < map[i].length; j++) {
                    System.out.print(map[i][j]);
                }
                System.out.println();
            }
            return;
        }

        // idx를 (r,c)로 변환
        int r = idx / 9, c = idx % 9;

        // 이미 숫자가 채워진 칸이면 다음 칸으로
        if (map[r][c] != 0) {
            solve(map, idx + 1);
            return;
        }

        // 빈 칸이면 도미노를 놓아야 함
        // 현재 칸(r,c)을 도미노의 한 칸으로 두고, 오른쪽/아래 방향으로만 확장 시도
        for (int k = 0; k < 2; k++) {
            int nr = r + dr[k]; // 도미노의 두 번째 칸 행
            int nc = c + dc[k]; // 도미노의 두 번째 칸 열

            // 보드 밖이거나, 두 번째 칸이 이미 채워져 있으면 도미노 못 놓음
            if (nr < 0 || nc < 0 || nr >= 9 || nc >= 9 || map[nr][nc] != 0) continue;

            // (r,c)에 i, (nr,nc)에 j를 넣는 모든 경우 시도
            for (int i = 1; i < 10; i++) {
                for (int j = 1; j < 10; j++) {

                    // i==j면 도미노 쌍 규칙 위반(문제에서 서로 다른 숫자)
                    // V[i][j]가 true면 그 도미노 쌍은 이미 사용됨
                    // check는 스도쿠 제약(행/열/3x3) 위반 여부 검사
                    // 주의: 두 칸 각각에 대해 check 수행
                    if (i == j || V[i][j] || !check(j, map, nr, nc) || !check(i, map, r, c)) continue;

                    // 배치: (r,c)=i
                    map[r][c] = i;

                    // 도미노 (i,j) 사용 처리 (대칭)
                    V[i][j] = true;
                    V[j][i] = true;

                    // 배치: (nr,nc)=j
                    map[nr][nc] = j;

                    // 다음 칸으로 진행
                    solve(map, idx + 1);

                    // 백트래킹 원복
                    map[nr][nc] = 0;   // 두 번째 칸 비우기
                    V[i][j] = false;   // 도미노 사용 해제
                    V[j][i] = false;   // 도미노 사용 해제(대칭)
                    map[r][c] = 0;     // 첫 번째 칸 비우기
                }
            }
        }
    }

    private static boolean check(int idx, int[][] map, int r, int c) {
        // (r,c)에 idx를 넣을 수 있는지 스도쿠 규칙 검사
        // 1) 3x3 박스
        int row = r / 3 * 3; // 박스 시작 행
        int col = c / 3 * 3; // 박스 시작 열

        // 해당 3x3 박스에 idx가 이미 있으면 불가
        for (int i = row; i < row + 3; i++) {
            for (int j = col; j < col + 3; j++) {
                if (map[i][j] == idx) return false;
            }
        }

        // 2) 같은 행 검사
        for (int i = 0; i < map.length; i++) {
            if (map[r][i] == idx) return false;
        }

        // 3) 같은 열 검사
        for (int i = 0; i < map.length; i++) {
            if (map[i][c] == idx) return false;
        }

        // 모든 제약 통과 => 가능
        return true;
    }
}
