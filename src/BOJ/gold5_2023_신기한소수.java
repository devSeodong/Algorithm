package BOJ;

import java.io.*;
import java.util.*;

public class gold5_2023_신기한소수 {
	static int N;                          // 전역 N
	static StringBuilder sb = new StringBuilder(); // 출력 버퍼

	public static void main(String[] args) throws IOException {
		// N자리수가 모두 소수인 숫자
		// N자리의 숫자 중에서 어떤 수들이 신기한 소수인가

		// 입력 : N
		// 출력 : 신기한 소수 오름차순

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());     // 지역변수 대신 전역 N 사용

		// 1자리 소수에서 출발
		int[] seeds = {2, 3, 5, 7};
		for (int s : seeds) {
			dfs(s, 1);                            // 현재 수 s, 길이 1
		}

		System.out.print(sb);                     // 결과 일괄 출력
	}

	// 깊이 우선 탐색으로 신기한 소수 생성
	static void dfs(int cur, int len) {
		if (len == N) {                           // 길이 N 달성 → 해답
			sb.append(cur).append('\n');
			return;
		}

		// 다음 자리 후보(끝자리에 가능한 수)
		int[] tail = {1, 3, 7, 9};
		for (int d : tail) {
			int next = cur * 10 + d;
			if (isPrime(next)) {                  // 접두사 소수만 확장
				dfs(next, len + 1);
			}
		}
	}

	// O(√x) 소수 판별(6k±1 최적화)
	static boolean isPrime(int x) {
		if (x < 2) return false;
		if (x % 2 == 0) return x == 2;
		if (x % 3 == 0) return x == 3;
		for (int i = 5; i * i <= x; i += 6) {
			if (x % i == 0 || x % (i + 2) == 0) return false;
		}
		return true;
	}
}
