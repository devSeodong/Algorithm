import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		// 모두 완전한 괄호일 경우 YES, 아니면 NO
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for(int t=0; t<T; t++) {
			String str = br.readLine();
			sb.append(M(str)).append("\n");
		} 
		System.out.println(sb);
	}
	
	public static String M(String str) {
		Stack<Character> stack = new Stack<>();
		
		for(int i=0; i<str.length();i++) {
			char sChar = str.charAt(i);
			if(sChar == '(') {
				stack.push(sChar);
			} else if (sChar == ')'&&!stack.isEmpty()) {
				stack.pop();
			} else if (sChar == ')'&&stack.isEmpty()) {
				return "NO";
			}
		}
		
		if(stack.isEmpty()) {
			return "YES";
		} else {
			return "NO";
		}
	}
}