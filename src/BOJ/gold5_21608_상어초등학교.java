package BOJ;

import java.io.*;
import java.util.*;

public class gold5_21608_상어초등학교 {
    static int N;                             // 교실 한 변의 길이 (NxN)
    static int[][] num, like, empty;         // num: 자리 배치, like: 각 칸의 '좋아하는 친구 수', empty: 인접한 빈칸 수
    static int[][] RC = {{-1, 1, 0, 0},      // 상, 하, 좌, 우 방향 델타 (행 변화량)
            {0, 0, -1, 1}};     // 상, 하, 좌, 우 방향 델타 (열 변화량)
    static List<Integer>[] list;             // 각 학생이 좋아하는 학생 번호 4명을 저장하는 리스트 배열
    static List<int[]> arr1, arr2;           // 1차 후보 자리 목록, 2차 후보 자리 목록
    static int[] score = {0, 1, 10, 100, 1000}; // 인접한 좋아하는 학생 수에 따른 만족도 점수

    public static void main(String[] args) throws IOException{
        // 매번 모든 빈 칸을 보면서 다음 우선순위로 가장 좋은 자리를 찾는다.
        // 우선순위:
        // 1. 인접한 칸에 좋아하는 학생 수가 가장 많은 칸
        // 2. 1번에서 동률이면 인접한 칸 중 비어있는 칸 수가 가장 많은 칸
        // 3. 2번에서도 동률이면 행이 작은 칸, 그 다음 열이 작은 칸

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());      // 교실 크기 입력

        num = new int[N][N];                      // 실제 자리 배치 배열 (0은 비어있음)
        list = new ArrayList[N * N + 1];         // 학생 번호가 1 ~ N^2 이므로 그만큼 리스트 생성
        for (int i = 1; i < N * N + 1; i++) {    // 각 학생 번호별로 리스트 초기화
            list[i] = new ArrayList<>();
        }

        // 학생을 입력 순서대로 한 명씩 배치
        for (int i = 0; i < N * N; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()); // 현재 배치할 학생 번호
            for (int j = 0; j < 4; j++) {             // 좋아하는 학생 4명 정보 입력
                int b = Integer.parseInt(st.nextToken());
                list[a].add(b);                       // a번 학생이 좋아하는 학생 목록에 추가
            }
            solve(a);                                 // a번 학생을 규칙에 맞게 자리 배치
        }

        int ans = 0;  // 최종 만족도 합
        int cnt;      // 각 자리에서 인접한 좋아하는 학생 수

        // 모든 자리를 돌면서 만족도 계산
        for (int i = 0; i < N; i++) {            // 행 순회
            for (int j = 0; j < N; j++) {        // 열 순회
                cnt = 0;                         // 현재 칸(i, j)의 인접 좋아하는 학생 수
                for (int d = 0; d < 4; d++) {    // 4방향 탐색
                    int nr = i + RC[0][d];       // 인접 칸 행
                    int nc = j + RC[1][d];       // 인접 칸 열

                    // 범위 안이고, 그 자리에 앉은 학생이 현재 학생이 좋아하는 학생이면 카운트 증가
                    if (nr >= 0 && nr < N && nc >= 0 && nc < N &&
                            list[num[i][j]].contains(num[nr][nc])) {
                        cnt++;
                    }
                }
                ans += score[cnt];               // 인접 좋아하는 학생 수에 따른 점수 더하기
            }
        }

        System.out.println(ans);                 // 최종 만족도 출력
    }

    // s번 학생을 규칙에 따라 자리에 배치하는 함수
    public static void solve(int s) {
        like = new int[N][N];           // 각 칸마다 인접한 '좋아하는 학생' 수
        empty = new int[N][N];          // 각 칸마다 인접한 '빈 칸' 수
        arr1 = new ArrayList<>();       // 1차 후보(좋아하는 학생 수 최대인 칸들)
        arr2 = new ArrayList<>();       // 2차 후보(그 중 빈칸 수 최대인 칸들)

        // 1. 아직 비어있는 모든 칸에 대해, 인접한 '좋아하는 학생 수'와 '빈 칸 수' 계산
        for (int i = 0; i < N; i++) {           // 모든 행
            for (int j = 0; j < N; j++) {       // 모든 열
                if (num[i][j] == 0) {           // 해당 칸이 비어있는 경우만 고려
                    for (int d = 0; d < 4; d++) {       // 4방향 탐색
                        int nr = i + RC[0][d];          // 인접 칸 행
                        int nc = j + RC[1][d];          // 인접 칸 열
                        // 범위 안인 인접 칸만 확인
                        if (nr >= 0 && nr < N && nc >= 0 && nc < N) {
                            if (list[s].contains(num[nr][nc])) {
                                // 인접 칸에 s가 좋아하는 학생이 앉아있으면 like 증가
                                like[i][j]++;
                            } else if (num[nr][nc] == 0) {
                                // 인접 칸이 비어있으면 empty 증가
                                empty[i][j]++;
                            }
                        }
                    }
                }
            }
        }

        // 2. 인접한 좋아하는 학생 수가 최대인 칸들만 arr1에 수집
        int max = 0;                               // 현재까지의 최대 '좋아하는 학생 수'
        for (int i = 0; i < N; i++) {              // 모든 행
            for (int j = 0; j < N; j++) {          // 모든 열
                if (num[i][j] == 0) {              // 비어있는 칸만 후보
                    if (max < like[i][j]) {        // 더 큰 값을 찾으면
                        max = like[i][j];          // 최대값 갱신
                        arr1.clear();              // 기존 후보 삭제
                        arr1.add(new int[]{i, j}); // 새로운 후보 등록
                    } else if (max == like[i][j]) {
                        // 동일 최대값이면 arr1에 추가 (동률 처리)
                        arr1.add(new int[]{i, j});
                    }
                }
            }
        }

        // 3-1. 후보가 한 칸이면 바로 그 자리에 s를 앉힌다
        if (arr1.size() == 1) {
            num[arr1.get(0)[0]][arr1.get(0)[1]] = s;
        } else {
            // 3-2. 후보가 여러 칸이면, 그 중에서 인접한 빈 칸 수가 최대인 칸들만 arr2에 모음
            max = 0;                           // 이번에는 '빈칸 수' 기준으로 최대값 갱신
            for (int[] temp : arr1) {          // 1차 후보들을 순회
                if (max < empty[temp[0]][temp[1]]) {
                    max = empty[temp[0]][temp[1]];       // 더 큰 빈칸 수 발견
                    arr2.clear();                        // 기존 2차 후보 제거
                    arr2.add(new int[]{temp[0], temp[1]});// 새로운 2차 후보 등록
                } else if (max == empty[temp[0]][temp[1]]) {
                    // 빈칸 수가 같으면 같이 후보에 넣기
                    arr2.add(new int[]{temp[0], temp[1]});
                }
            }
            // 4. arr2에는 '좋아하는 학생 수 최대' + '빈칸 수 최대' 까지 만족하는 칸들만 있음
            //    남은 조건은 행이 작은 순, 열이 작은 순이므로
            //    arr2가 이미 행, 열 오름차순 순회 순서대로 채워졌기 때문에 첫 번째 원소가 조건을 만족
            num[arr2.get(0)[0]][arr2.get(0)[1]] = s; // 최종 자리 결정
        }
    }
}
