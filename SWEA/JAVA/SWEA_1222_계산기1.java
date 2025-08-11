import java.io.*;
import java.util.*;

public class Main {
	public static int N;
	public static String str, f;
	public static HashMap<Character, Integer> map = new HashMap<Character, Integer>();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		for (int t = 1; t <= 10; t++) {
			N = Integer.parseInt(br.readLine());
			str = br.readLine();
			
			sol();
			System.out.println("#"+t+" "+calc());
		}
	}
	
	public static void sol() {
		Stack<Character> st = new Stack<Character>();
		StringBuilder sb = new StringBuilder();
		map.put('+', 0);
		
		for (int i = 0; i < N; ++i) {
			char c = str.charAt(i);
			if (c >= '0' && c <= '9') {
				sb.append(c);
				continue;
			}
			
			while (!st.isEmpty() && map.get(st.peek()) >= map.get(c)) {
				sb.append(st.pop());
			}
			st.add(c);
		}
		
		while (!st.isEmpty()) {
			sb.append(st.pop());
		}
		
		f = sb.toString();
	}
	
	public static int calc() {
		Stack<Integer> stk = new Stack<Integer>();
		for (int i = 0; i < f.length(); ++i) {
			char c = f.charAt(i);
			if (c >= '0' && c <= '9') {
				stk.add(c - '0');
				continue;
			}
			if (c == '+') {
				stk.add(stk.pop() + stk.pop());
			}
		}
		
		return stk.pop();
	}
}