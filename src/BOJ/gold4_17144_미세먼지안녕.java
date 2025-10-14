package BOJ; // 패키지 선언

import java.io.*;   // 입출력 관련 클래스
import java.util.*; // List, StringTokenizer 등 컬렉션/유틸

public class gold4_17144_미세먼지안녕 {
	static int R, C, T;                // 행, 열, 진행 시간(초)
	static List<Integer> ac;           // 공기청정기 위치(행 인덱스 2개 저장: 위/아래)
	static int[][] arr, RC = {{-1, 1, 0, 0},{0, 0, -1, 1}}; // 방 배열, 4방향 델타(상/하/좌/우)
	public static void main(String[] args) throws IOException {
        /* R*C인 격자판의 집
         - 1번열 ( 인덱스 0 )에 공기정청기, 크기는 두 행
         - 1초동안 미세먼지 확산, 공기청정기 작동
        */

		// 입력 : R, C, T, 미세먼지 정보 ( -1 공기청정기 )
		// 출력 : T초가 지난 후 방에 남아있는 미세먼지의 양

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); // 빠른 입력
		StringTokenizer st = new StringTokenizer(br.readLine()); // 첫 줄 파싱
		R = Integer.parseInt(st.nextToken()); // 행 수
		C = Integer.parseInt(st.nextToken()); // 열 수
		T = Integer.parseInt(st.nextToken()); // 시뮬레이션 시간(초)

		arr = new int[R][C];   // 방(미세먼지/청정기) 정보
		ac = new ArrayList<>(); // 공기청정기 행 인덱스 2개 저장
		for(int i=0; i<R; i++) {              // 각 행에 대해
			st = new StringTokenizer(br.readLine()); // 한 줄 입력
			for(int j=0; j<C; j++) {          // 각 열에 대해
				arr[i][j] = Integer.parseInt(st.nextToken()); // 값 채우기
				if(arr[i][j] == -1) ac.add(i);                // -1이면 청정기, 행 저장
			}
		}

		// t초 동안 시뮬레이션 반복
		for(int t=0; t<T; t++) {
			dust();  // 1) 미세먼지 확산 단계
			AC();    // 2) 공기청정기 작동 단계(반/시계 방향 순환)
		}

		int res = 0;                 // 최종 미세먼지 총합
		for(int i=0; i<R; i++) {
			for(int j=0; j<C; j++) {
				if(arr[i][j] > 0) res += arr[i][j]; // -1(청정기) 제외하고 양수만 합산
			}
		}

		System.out.println(res); // 결과 출력
	}

	/*
    - 미세먼지 확산
        모든 미세먼지 네 방향 ( 공기청정기가 있거나, 칸이 없으면 확산 X )
        확산되는 양 A/5
        남은 미세먼지 양 -> A - A/5 * 확산된 방향의 개수
    */
	public static void dust() {
		int[][] q = new int[R][C]; // 변화량(순증가/감소) 누적용 배열

		for(int i=0; i<R; i++) {
			for(int j=0; j<C; j++) {
				if(arr[i][j] > 0) { // 먼지가 있는 칸만 처리
					int cnt = 0;    // 실제 확산된 방향 수
					for(int d=0; d<4; d++) { // 4방 탐색
						int nr = i + RC[0][d];
						int nc = j + RC[1][d];
						if(nr<0 || nr>=R || nc<0 || nc>=C) continue; // 범위 밖
						if(arr[nr][nc] == -1) continue;               // 청정기 칸 제외

						q[nr][nc] += ( arr[i][j] / 5 ); // 이웃으로 확산되는 양 누적
						cnt++;                          // 확산 성공 방향 증가
					}
					q[i][j] -= (cnt*(arr[i][j]/5)); // 현재 칸에서 나간 만큼 감소
				}
			}
		}

		// 원본(arr)에 변화량(q)을 더해 최종 확산 결과 반영
		for(int i=0; i<R; i++) {
			for(int j=0; j<C; j++) {
				arr[i][j] += q[i][j];
			}
		}
	}

	/*
    - 공기청정기 작동
        위쪽 공기청정기 바람 반시계 방향
        아래쪽 공기청정기 바람 시계방향
        바람 불면 미세먼지 바람방향대로 한칸씩 이동
        공기청정기로 들어간 미세먼지는 모두 정화
    */
	public static void AC() {
		int up = ac.get(0);   // 위쪽 청정기 행
		int down = ac.get(1); // 아래쪽 청정기 행

		// ------ 위쪽 순환(반시계) ------
		// 왼쪽 세로줄 위로 끌어올리기: (up-1,0) ~ (1,0) <- (up-2,0) ~ (0,0)
		for (int i = up - 1; i > 0; i--) {
			arr[i][0] = arr[i - 1][0];
		}

		// 맨 윗줄 왼쪽으로 밀기: (0,0) ~ (0,C-2) <- (0,1) ~ (0,C-1)
		for (int j = 0; j < C - 1; j++) {
			arr[0][j] = arr[0][j + 1];
		}

		// 오른쪽 세로줄 아래로 끌어내리기: (0,C-1) ~ (up-1,C-1) <- (1,C-1) ~ (up,C-1)
		for (int i = 0; i < up; i++) {
			arr[i][C - 1] = arr[i + 1][C - 1];
		}

		// 청정기 줄 오른쪽으로 밀기: (up,C-1) ~ (up,1) <- (up,C-2) ~ (up,0)
		for (int j = C - 1; j > 0; j--) {
			arr[up][j] = arr[up][j - 1];
			if (j == 1) arr[up][j] = 0; // 청정기 바로 오른쪽 칸은 정화되어 0
		}

		// ------ 아래쪽 순환(시계) ------
		// 왼쪽 세로줄 아래로 끌어내리기: (down+1,0) ~ (R-2,0) <- (down+2,0) ~ (R-1,0)
		for (int i = down + 1; i < R - 1; i++) {
			arr[i][0] = arr[i + 1][0];
		}

		// 맨 아랫줄 왼쪽으로 밀기: (R-1,0) ~ (R-1,C-2) <- (R-1,1) ~ (R-1,C-1)
		for (int j = 0; j < C - 1; j++) {
			arr[R - 1][j] = arr[R - 1][j + 1];
		}

		// 오른쪽 세로줄 위로 끌어올리기: (R-1,C-1) ~ (down+1,C-1) <- (R-2,C-1) ~ (down,C-1)
		for (int i = R - 1; i > down; i--) {
			arr[i][C - 1] = arr[i - 1][C - 1];
		}

		// 청정기 줄 오른쪽으로 밀기: (down,C-1) ~ (down,1) <- (down,C-2) ~ (down,0)
		for (int j = C - 1; j > 0; j--) {
			arr[down][j] = arr[down][j - 1];
			if (j == 1) arr[down][j] = 0; // 청정기 바로 오른쪽 칸은 정화되어 0
		}

		// (안전) 청정기 위치는 항상 -1 유지 보증
		arr[up][0] = -1;
		arr[down][0] = -1;
	}
}
