import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(br.readLine());

		Queue<Integer> q = new LinkedList<>();
		int l = 0;
		for(int i =0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			String a = st.nextToken();
			switch (a) {
				case "push":
					l = Integer.parseInt(st.nextToken());
					q.offer(l);
					break;
				case "pop":
					if (q.isEmpty()) sb.append(-1).append("\n");
					else sb.append(q.poll()).append("\n");
					break;
				case "size":
					sb.append(q.size()).append("\n");
					break;
				case "empty":
					if (q.isEmpty()) sb.append(1).append("\n");
					else sb.append(0).append("\n");
					break;
				case "front":
					if(q.isEmpty()) sb.append(-1).append("\n");
					else sb.append(q.peek()).append("\n");
					break;
				case "back":
					if(q.isEmpty()) sb.append(-1).append("\n");
					else sb.append(l).append("\n");
					break;
			}
		}

		System.out.println(sb);
	}
}
