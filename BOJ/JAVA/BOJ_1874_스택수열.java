import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		// 1부터 n가지의 수를 스택에 넣었다가 뽑아 늘어놓음 -> 하나의 수열
		// 스택에 push하는 순서는 반드시 오름차순

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int n = Integer.parseInt(br.readLine());

		Stack<Integer> stack = new Stack<>();
		int start = 0;
		while(n-- > 0) {
			int v = Integer.parseInt(br.readLine());
			if(v>start) {
				for (int i=start+1; i<=v; i++) {
					stack.push(i);
					sb.append("+").append("\n");
				}
				start = v;
			} else if(stack.peek() != v) {
				System.out.println("NO");
				return;
			}

			stack.pop();
			sb.append("-").append("\n");
		}

		System.out.println(sb);
	}
}
