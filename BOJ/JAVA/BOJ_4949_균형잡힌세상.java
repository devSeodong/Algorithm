import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String line;

		while (!(line = br.readLine()).equals(".")) {
			Deque<Character> deque = new ArrayDeque<>();
			boolean isB = true;

			for (char ch : line.toCharArray()) {
				if (ch == '(' || ch == '[') {
					deque.push(ch);
				} else if (ch == ')') {
					if (deque.isEmpty() || deque.peek() != '(') {
						isB = false;
						break;
					}
					deque.pop();
				} else if (ch == ']') {
					if (deque.isEmpty() || deque.peek() != '[') {
						isB = false;
						break;
					}
					deque.pop();
				}
			}

			if (isB && deque.isEmpty()) {
				System.out.println("yes");
			} else {
				System.out.println("no");
			}
		}
	}
}
