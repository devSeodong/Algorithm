import java.io.*;
import java.util.*;

public class Main {

	static boolean[] v;
	static ArrayList<Integer>[] g;
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int V = Integer.parseInt(st.nextToken());

		g = new ArrayList[N+1];
		for (int i = 1; i <= N; i++) {
			g[i] = new ArrayList<>();
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			g[a].add(b);
			g[b].add(a);
		}

		for (int i = 1; i <= N; i++) {
			Collections.sort(g[i]);
			System.out.println(g[i]);
		}

		v = new boolean[N+1];
		dfs(V);
		sb.append("\n");
		v = new boolean[N+1];
		bfs(V);

		System.out.println(sb);
	}

	static void dfs(int n) {
		v[n] = true;
		sb.append(n).append(" ");
		for (int x : g[n]) {
			if (!v[x]) {
				dfs(x);
			}
		}
	}

	static void bfs(int s) {
		Queue<Integer> q = new LinkedList<>();
		q.add(s);
		v[s] = true;

		while (!q.isEmpty()) {
			System.out.println(q.size());
			int n = q.poll();
			sb.append(n).append(" ");

			for (int x : g[n]) {
				if (!v[x]) {
					v[x] = true;
					q.add(x);
				}
			}
		}
	}
}
