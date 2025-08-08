import java.io.*;
import java.util.*;

public class Main {
	static int cnt;
	static boolean[] v;
	static List<Integer>[] arr;
	public static void main(String[] args) throws IOException {
		// 입력 : 컴퓨터의 수, 연결되어 있는 컴퓨터 쌍의 수, 컴퓨터의 번호 쌍
		// 출력 : 1번 컴퓨터를 통해 바이러스에 걸리게 되는 컴퓨터의 수

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int C = Integer.parseInt(br.readLine());
		int N = Integer.parseInt(br.readLine());

		arr = new ArrayList[C+1];
		for( int i=1; i<=C; i++ ) {
			arr[i] = new ArrayList<>();
		}

		for( int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			arr[a].add(b);
			arr[b].add(a);
		}

		v = new boolean[C+1];
		Queue<Integer> q = new LinkedList<>();
		v[1] = true;
		q.add(1);

		cnt = 0;
		while(!q.isEmpty()) {
			int c = q.poll();
			for(int x : arr[c]) {
				if(!v[x]) {
					v[x] = true;
					q.add(x);
					cnt++;
				}
			}
		}

		System.out.println(cnt);
	}
}
