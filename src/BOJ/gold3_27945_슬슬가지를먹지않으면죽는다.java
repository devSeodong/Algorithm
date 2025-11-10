package BOJ;

import java.io.*;
import java.util.*;

public class gold3_27945_슬슬가지를먹지않으면죽는다 {
	static int N, M;
	static List<Acad> list;
	static int[] parent, size;

	static class Acad {
		int s;
		int e;
		int t;

		Acad(int s, int e, int t) {
			this.s = s;
			this.e = e;
			this.t = t;
		}
	}

	public static void main(String[] args) throws IOException {
		// 1번부터 N번까지 번호 요리
		// 요리학원 사이에는 총 M개의 양방향 길
		// i번째 길에는 ti일에만 문을 여는 노점
		// 매일 노점 들르지 않으면 쓰러짐 ㅠㅠ N-1개의 길만 기억!

		// 입력 : N, M, 연결학원정보 ( v, v, ti )
		// 출력 : 모든 요리 학원에 다닐 수 있도록 길을 고르고 키위새가 쓰러지는 날의 최댓값

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		list = new ArrayList<>();

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int v1 = Integer.parseInt(st.nextToken());
			int v2 = Integer.parseInt(st.nextToken());
			int t = Integer.parseInt(st.nextToken());
			list.add(new Acad(v1, v2, t));
		}

		Collections.sort(list, new Comparator<Acad>() {
			@Override
			public int compare(Acad o1, Acad o2) {
				return Integer.compare(o1.t, o2.t);
			}
		});

		parent = new int[N + 1];
		size = new int[N + 1];
		for (int i = 1; i <= N; i++) {
			parent[i] = i;
			size[i] = 1;
		}
		
		System.out.println(solve());
	}

	public static int find(int x) {
		return parent[x] == x ? x : (parent[x] = find(parent[x]));
	}

	public static boolean union(int a, int b) {
		a = find(a);
		b = find(b);
		if (a == b) return false;
		if (size[a] < size[b]) {
			int t = a;
			a = b;
			b = t;
		}
		parent[b] = a;
		size[a] += size[b];
		return true;
	}

	public static int solve() {
		int day = 0, picked = 0;
		for (Acad e : list) {
			if (picked == N - 1) break;
			if (union(e.s, e.e)) {
				if (e.t > day + 1) break;
				day++;
				picked++;
			}
		}
		return day + 1;
	}
}
