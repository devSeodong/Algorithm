import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		// 정수를 저장하는 덱을 구현, 주어지는 명령을 처리하는 프로그램

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(br.readLine());

		Deque<Integer> dq = new ArrayDeque<>();
		for(int i =0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			String a = st.nextToken();
			switch (a) {
				case "push_front":
					dq.addFirst(Integer.parseInt(st.nextToken()));
					break;
				case "push_back":
					dq.addLast(Integer.parseInt(st.nextToken()));
					break;
				case "pop_front":
					if (dq.isEmpty()) sb.append(-1).append("\n");
					else sb.append(dq.pollFirst()).append("\n");
					break;
				case "pop_back":
					if(dq.isEmpty()) sb.append(-1).append("\n");
					else sb.append(dq.pollLast()).append("\n");
					break;
				case "size":
					sb.append(dq.size()).append("\n");
					break;
				case "empty":
					if (dq.isEmpty()) sb.append(1).append("\n");
					else sb.append(0).append("\n");
					break;
				case "front":
					if(dq.isEmpty()) sb.append(-1).append("\n");
					else sb.append(dq.peekFirst()).append("\n");
					break;
				case "back":
					if (dq.isEmpty()) sb.append(-1).append("\n");
					else sb.append(dq.peekLast()).append("\n");
					break;
			}
		}

		System.out.println(sb);
	}
}
