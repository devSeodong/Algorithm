import java.io.*;
import java.util.*;

public class Main {
	static List<Integer>[] graph ;
	static boolean[] v;
	public static void main(String[] args) throws Exception {
		// 병합 없는 그래프 -> 연결 요소의 개수

		// 입력 : 정점의 개수 N, 간선의 개수 M, M개의 줄에 간선의 양 끝점 u와 v
		// 출력 : 연결 요소의 개수

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		graph = new ArrayList[N+1];
		v = new boolean[N+1];

		for(int n=1; n<=N; n++) {
			graph[n] = new ArrayList<>();
		}

		for(int m=0; m<M; m++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			graph[u].add(v);
			graph[v].add(u);
		}

		int cnt = 0;
		for(int i=1; i<=N; i++) {
			if(!v[i]) {
				cnt++;
				solve(i);
			}
		}

		System.out.println(cnt);
	}

	public static void solve(int n) {
		Queue<Integer> q = new LinkedList<>();
		q.add(n);
		v[n] = true;
		while(!q.isEmpty()) {
			int c = q.poll();
			for(int u : graph[c]) {
				if(!v[u]) {
					q.add(u);
					v[u] = true;
				}
			}
		}
	}
}
