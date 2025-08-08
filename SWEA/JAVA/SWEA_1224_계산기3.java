import java.io.*;
import java.util.*;

public class Solution {
	public static int N;
	public static String str, f;
	public static HashMap<Character, Integer> map = new HashMap<Character, Integer>();

	public static void main(String[] args) throws IOException {
		// “3+(4+5)*6+7” -> "345+6*+7+" -> 64

		// 입력 : 10개의 테케, 테케 길이, 식
		// 출력 : #t 정답

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
		map.put('(', 0);
		map.put('+', 1);
		map.put('*', 2);

		for (int i = 0; i < N; i++) {
			char c = str.charAt(i);
			if (c >= '0' && c <= '9') {
				sb.append(c);
			} else if (c == '(') {
				st.push(c);
			} else if (c == ')') {
				while (st.peek() != '(') {
					sb.append(st.pop());
				}
				st.pop();
			} else {
				if (st.isEmpty()) {
					st.push(c);
				} else {
					while (!st.isEmpty() && st.peek() != '(' && map.get(c) <= map.get(st.peek())) {
						sb.append(st.pop());
					}
					st.push(c);
				}
			}
		}

		while (!st.isEmpty()) {
			sb.append(st.pop());
		}

		f = sb.toString();
	}

	public static int calc() {
		Stack<Integer> st = new Stack<>();
		for (int i = 0; i < f.length(); i++) {
			char c = f.charAt(i);
			if (c >= '0' && c <= '9') {
				st.push(c - '0');
			} else {
				int b = st.pop();
				int a = st.pop();
				if (c == '+')
					st.push(a + b);
				else if (c == '*')
					st.push(a * b);
			}
		}
		return st.pop();
	}
}