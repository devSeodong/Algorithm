package BOJ;

import java.util.*;
import java.io.*;

public class gold5_20055_컨베이어벨트위의로봇 {
	static int N, K;          // N: 윗벨트 길이(총 칸 수의 절반), K: 내구도 0인 칸의 목표 개수
	static int[] A;           // 길이 2N의 내구도 배열
	static boolean[] arr;     // 길이 N의 로봇 존재 배열(윗벨트 구간만 관리: 0 ~ N-1)
	public static void main(String[] args) throws IOException{
		// 규칙 요약
		// 1) 벨트와 로봇이 함께 한 칸 회전
		// 2) 앞 칸으로 이동 가능하면 (다음 칸에 로봇 X, 내구도 >=1) 로봇 이동
		// 3) 올리는 위치(0)에 내구도 > 0 이면 로봇 올림
		// 4) 내구도 0 칸이 K개 이상이면 종료, 단계 수 출력

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken()); // 윗벨트 길이
		K = Integer.parseInt(st.nextToken()); // 종료 조건: 내구도 0 칸 개수

		A = new int[N*2];     // 0 ~ 2N-1
		arr = new boolean[N]; // 윗벨트 구간(0 ~ N-1)에서만 로봇을 관리

		st = new StringTokenizer(br.readLine());
		for(int i=0; i<A.length; i++){
			A[i] = Integer.parseInt(st.nextToken()); // 각 칸의 초기 내구도
		}

		System.out.println(solve()); // 시뮬레이션 실행
	}

	public static int solve() {
		int cnt = 0;           // 단계(라운드) 수
		int len = A.length;    // = 2N
		while (isOk()) {       // 내구도 0 칸이 K 미만인 동안 반복

			// (1) 벨트 한 칸 회전: 내구도 배열을 오른쪽으로 한 칸 밀기
			int tmp = A[len - 1];            // 맨 끝 값을 임시 보관
			for (int i = len - 1; i > 0; i--) {
				A[i] = A[i - 1];             // 오른쪽으로 쉬프트
			}
			A[0] = tmp;                      // 맨 앞에 이전 맨 끝 값 삽입

			// 로봇도 함께 회전: 윗벨트 영역(0~N-1)만 관리
			for (int i = arr.length - 1; i > 0; i--) {
				arr[i] = arr[i - 1];         // 로봇 위치를 오른쪽으로 쉬프트
			}
			arr[0] = false;                  // 새로 올라오는 위치에는 회전으로 로봇이 생기지 않음
			arr[N - 1] = false;              // 회전 직후, 내리는 위치(N-1)에서 로봇은 즉시 내려야 함

			// (2) 로봇 이동: 뒤에서부터 확인하여 한 칸씩 전진 가능하면 이동
			for (int i = arr.length - 1; i > 0; i--) { // i: 이동 ‘도착’ 위치
				if (arr[i - 1] && !arr[i] && A[i] >= 1) {
					A[i]--;                   // 도착 칸 내구도 감소
					arr[i] = true;           // 로봇 이동 완료
					arr[i - 1] = false;      // 출발 칸 비우기
				}
			}
			// ★누락 가능 포인트: 이동 후에도 내리는 위치(N-1)에 로봇이 있으면 즉시 내려야 규칙 일치
			// 현재 코드는 회전 직후만 내리고, 이동 후에는 내리지 않아 다음 단계까지 남을 수 있음
			// -> 개선: arr[N - 1] = false; 를 여기에도 한 번 더 수행해야 정밀 규칙 준수

			// (3) 올리는 위치(0)에 로봇 올리기
			if (A[0] > 0) {   // 내구도가 남아 있으면
				arr[0] = true; // 로봇 올림
				A[0]--;        // 내구도 감소
			}

			cnt++;            // 단계 1 증가
		}

		return cnt;           // 종료 시 단계 수 반환
	}

	private static boolean isOk() {
		int cnt = 0; // 내구도 0 칸 수
		for (int i = 0; i < A.length; i++) {
			if (A[i] == 0) {
				cnt++;
			}
			if (cnt >= K) return false; // K 이상이면 종료
		}
		return true; // 아직 계속
	}
}
