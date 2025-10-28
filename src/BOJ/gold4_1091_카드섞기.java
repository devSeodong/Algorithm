package BOJ;

import java.io.*;
import java.util.*;

public class gold4_1091_카드섞기 {
	static int N;
	static int[] a, temp, S, P;

	public static void main(String[] args) throws IOException {
		// 세명의 플레이어 ( 0, 1, 2 ), N개의 카드
		// 카드 순서를 알고있고, 이 정보를 이용해 각 카드가 특정한 플레이어에게 보내지게
		// 방법 -> 길이가 N인 수열 S로 주어짐
		// 카드를 한번 섞고 나면, i번째 위치에 있던 카드 S[i]번째 위치로 이동
		// i번째 위치에 있던 카드 최종적으로 플레이어 P[i]에게 보내야함

		// 입력 : N, 수열 P, 수열 S
		// 출력 : 몇번 섞어야 하는지 출력

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());

		P = new int[N];
		S = new int[N];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			P[i] = Integer.parseInt(st.nextToken());
		}

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			S[i] = Integer.parseInt(st.nextToken());
		}

		a = new int[N];
		temp = new int[N];
		for (int i = 0; i < N; i++) {
			a[i] = i;
		}

		if (isV()) {
			System.out.println(0);
			return;
		}

		int[] arr = a.clone();
		int cnt = 0;
		while (true) {
			cnt++;

			for (int i = 0; i < N; i++) {
				temp[S[i]] = a[i];
			}

			for (int i = 0; i < N; i++) {
				a[i] = temp[i];
			}

			if (isV()) break;

			boolean c = true;
			for (int i = 0; i < N; i++) {
				if (a[i] != i) {
					c = false;
					break;
				}
			}
			
			if (c) {
				cnt = -1;
				break;
			}

		}

		System.out.println(cnt);
	}

	static boolean isV() {
		for (int i = 0; i < a.length; i++) {
			if (P[a[i]] != (i % 3))
				return false;
		}
		return true;
	}
}
