package BOJ;

import java.io.*;
import java.util.*;

public class gold4_1062_가르침 {
	static int N, K;                 // 단어 수, 가르칠 글자 수
	static int[] words;              // 각 단어를 비트마스크로 표현 (알파벳 26비트)
	static int requiredMask;         // 필수 알파벳 a,c,i,n,t 마스크
	static int answer = 0;           // 읽을 수 있는 단어 수의 최댓값

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());    // 단어 개수
		K = Integer.parseInt(st.nextToken());    // 가르칠 수 있는 글자 수

		// 특수 케이스 처리
		if (K < 5) {                              // a, c, i, n, t 5개는 필수
			System.out.println(0);
			return;
		}
		if (K == 26) {                            // 모든 알파벳을 가르칠 수 있음
			System.out.println(N);
			return;
		}

		// 필수 문자 마스크 구성: a, c, i, n, t
		requiredMask = 0;
		requiredMask |= 1 << ('a' - 'a');
		requiredMask |= 1 << ('c' - 'a');
		requiredMask |= 1 << ('i' - 'a');
		requiredMask |= 1 << ('n' - 'a');
		requiredMask |= 1 << ('t' - 'a');

		words = new int[N];

		// 단어 입력 → 'anta'와 'tica'를 제거한 본체를 비트마스크로 변환해 저장
		for (int i = 0; i < N; i++) {
			String s = br.readLine().trim();
			// 문제 성질상 모든 단어는 anta...tica 형태.
			// 꼭 자르지 않아도 되지만, 불필요한 필수 문자들이 포함되어 있어도 결국 마스크에는 같은 효과.
			// 깔끔하게 본체만 쓰고 싶다면:
			if (s.length() > 8) s = s.substring(4, s.length() - 4); // "anta"(4), "tica"(4) 제거

			int mask = 0;
			for (int c = 0; c < s.length(); c++) {
				int bit = s.charAt(c) - 'a';
				mask |= 1 << bit;
			}
			words[i] = mask;
		}

		// 현재 배운 문자 마스크는 필수 문자로 시작
		int learned = requiredMask;
		// 남은 K-5개의 글자를 추가로 선택
		dfs(0, 0, learned);

		System.out.println(answer);
	}

	/**
	 * dfs(idx, picked, learned)
	 * idx     : 알파벳 인덱스(0~25)를 훑으며 결정
	 * picked  : 필수 외에서 지금까지 선택한 개수
	 * learned : 현재까지 배운 문자들의 비트마스크
	 */
	static void dfs(int idx, int picked, int learned) {
		// 이미 K개를 채웠거나(필수 5개 + picked), 모든 알파벳을 다 본 경우
		if (picked == K - 5 || idx == 26) {
			// 현재 배운 문자들(learned)로 읽을 수 있는 단어 수를 세서 answer 갱신
			int cnt = 0;
			for (int w : words) {
				// 단어 w의 모든 비트가 learned에 포함되어 있어야 읽을 수 있음
				if ((w & ~learned) == 0) cnt++;
			}
			answer = Math.max(answer, cnt);
			return;
		}

		// 현재 idx 알파벳이 필수 문자면 스킵 (이미 배운 것으로 처리)
		if ((requiredMask & (1 << idx)) != 0) {
			dfs(idx + 1, picked, learned);
			return;
		}

		// 가지치기(선택 가능한 알파벳 수가 부족한 경우)
		// 남은 칸: (K - 5 - picked), 남은 후보 알파벳 수: rough (26 - idx)
		// 강한 pruning은 아니지만, 필요 시 추가 가능

		// 1) idx 알파벳을 가르친다(선택)
		dfs(idx + 1, picked + 1, learned | (1 << idx));

		// 2) idx 알파벳을 가르치지 않는다(미선택)
		dfs(idx + 1, picked, learned);
	}
}
