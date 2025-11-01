package BOJ;                           // BOJ(백준) 패키지

import java.io.*;                      // 입출력 클래스 임포트
import java.util.*;                    // 유틸리티(문자열 토크나이저 등) 임포트

public class gold4_1091_카드섞기 {     // BOJ 1091 카드 섞기 (Gold 4)
	static int N;                      // 카드 수
	static int[] a, temp, S, P;        // a: 현재 카드ID의 위치 배열(=현재 덱 상태), temp: 다음 상태 임시 버퍼, S: 셔플 규칙(목적 위치), P: 각 위치의 목표 플레이어

	public static void main(String[] args) throws IOException {
		// 세명의 플레이어 (0,1,2), N장의 카드
		// S[i]: i번째 위치의 카드가 셔플 후 이동할 위치
		// 최종적으로 카드 j는 플레이어 (j%3)에게 가야 함(= 위치 pos의 목표 P[pos]와 일치해야 성공)

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); // 빠른 입력
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());         // N 입력

		P = new int[N];                               // 위치별 목표 플레이어
		S = new int[N];                               // 셔플 매핑

		st = new StringTokenizer(br.readLine());      // P 배열 입력
		for (int i = 0; i < N; i++) {
			P[i] = Integer.parseInt(st.nextToken());
		}

		st = new StringTokenizer(br.readLine());      // S 배열 입력
		for (int i = 0; i < N; i++) {
			S[i] = Integer.parseInt(st.nextToken());
		}

		a = new int[N];                               // 현재 덱 상태(각 위치에 어떤 카드ID가 있는지) — 초기엔 [0,1,2,...,N-1]
		temp = new int[N];                            // 다음 상태 계산을 위한 임시 버퍼
		for (int i = 0; i < N; i++) {
			a[i] = i;                                 // 초기 상태는 카드ID = 위치 인덱스
		}

		if (isV()) {                                  // 이미 목표 충족이면 0회
			System.out.println(0);
			return;
		}

		int cnt = 0;                                  // 셔플 횟수
		while (true) {
			cnt++;                                    // 이번에 한 번 셔플

			for (int i = 0; i < N; i++) {
				temp[S[i]] = a[i];                    // 규칙: i 위치의 카드는 S[i] 위치로 이동
			}

			for (int i = 0; i < N; i++) {
				a[i] = temp[i];                       // temp를 a로 복사(다음 상태로 갱신)
			}

			if (isV()) break;                         // 목표 충족되면 종료

			boolean c = true;                         // 다시 초기 상태(항등)로 돌아왔는지 체크용
			for (int i = 0; i < N; i++) {
				if (a[i] != i) {                      // 하나라도 다르면 아직 항등 아님
					c = false;
					break;
				}
			}

			if (c) {                                  // 항등으로 돌아왔다 = 사이클을 한 바퀴 돌았는데도 목표 미달 => 불가능
				cnt = -1;                             // -1 출력
				break;
			}
		}

		System.out.println(cnt);                      // 정답 출력
	}

	static boolean isV() {                            // 현재 상태가 목표 조건을 만족하는지 확인
		for (int i = 0; i < a.length; i++) {
			if (P[a[i]] != (i % 3))                   // 위치 i에 있는 카드ID = a[i]가 도착한 플레이어 P[i]가 아니라,
				return false;                         // 카드ID의 소유자(j%3)가 위치의 목표 플레이어와 일치해야 함.
		}
		return true;                                  // 모든 위치가 조건 충족
	}
}
