package samsung01;

import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		// 쇠막대기는 자신보다 긴 쇠막대기 위에만 / 끝점은 겹치지 않도록
		// 각 쇠막대기를 자르는 레이저는 적어도 하나 존재
		// () 는 레이저를 표현
		// 쇠막대기 -> "(" ")"

		// 입력 : 쇠막대기와 레이저의 배치를 나타내는 괄호 표현
		// 출력 : 잘려진 조각의 총 개수

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String input = br.readLine();

		Stack<Character> stack = new Stack<>();
		int res = 0;

		for (int i = 0; i < input.length(); i++) {
			char ch = input.charAt(i);
			if (ch == '(') { // '('일 경우
				stack.push(ch);
			} else if (ch == ')') { // ')'일 경우
				stack.pop();
				if(input.charAt(i-1) == '(') { // 해당 요소가 막대기괄호가 아닌 레이저인 경우
					res += stack.size(); // 그 전에 쌓인 막대기 갯수 만큼 ++ 
				}else {
					res += 1; // 막대기의 끝 괄호인 경우 +1 해주어 마무리
				}
			}
		}
		
		System.out.println(res);
	}
}