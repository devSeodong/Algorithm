import java.io.*;
import java.util.*;

class Solution {

	public static void main(String[] args) throws IOException {
		// 8자리의 암호를 생성할 수 있음
		// 1~5까지 감소 후 뒤로 이동 -> 1 사이클

		// 입력 : 테케 10개, 테케 번호, 8개의 데이터
		// 출력 : #t 정답

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb;
		StringTokenizer st;
		int N = 8;
		for (int t = 1; t <= 10; t++) {
			int T = Integer.parseInt(br.readLine());
			st = new StringTokenizer(br.readLine());

			Queue<Integer> q = new LinkedList<>();
			for (int i = 0; i < N; i++) {
				q.offer(Integer.parseInt(st.nextToken()));
			}

			int idx = 1;
			while (true) {
				if (idx > 5)
					idx = 1;
				int a = q.poll() - (idx++);
				if (a <= 0) {
					q.offer(0);
					break;
				}
				q.offer(a);
			}
			
			sb = new StringBuilder().append("#").append(T).append(" ");
			for(int i=0; i<N; i++) {
				sb.append(q.poll()).append(" ");
			}
			
			System.out.println(sb);
		}
	}
}