import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		// 1-N까지 N명의 사람 원
		// 순서대로 K번째 사람을 제거

		// 입력 : N, K
		// 출력 : <3, 6, 2, 7, 5, 1, 4>

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());

		Queue<Integer> q = new LinkedList<>();
		for(int i=1; i<=N; i++) {
			q.add(i);
		}

		StringBuilder sb = new StringBuilder();
		sb.append("<");
		int idx = 0;
		while (q.size() > 1) {
			int p = q.poll();
			if(++idx%K==0) {
				sb.append(p).append(", ");
			} else {
				q.add(p);
			}
		}
		sb.append(q.poll()).append(">");
		System.out.print(sb);
	}
}