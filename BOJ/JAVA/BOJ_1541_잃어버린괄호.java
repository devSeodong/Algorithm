import java.io.*;
import java.util.*;

public class Main {
	static String[] ch = {"-", "\\+"};
	public static void main(String[] args) throws IOException {
		// 양수와 + -, 괄호 가지고 식을 만듦
		// 괄호를 모두 지움
		// 괄호를 적절히 쳐서 이 식의 값을 최소로

		// 입력 : 식
		// 출력 : 정답 출력

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String calc = br.readLine();

		// 가장 큰 값을 빼야 최솟값이 될 것임
		// 덧셈 -> 뺄셈

		// - 기준으로 식 나누기
		String[] strs = calc.split(ch[0]);

		// 계산된 결과들 list
		List<Integer> list = new ArrayList<>();
		// -기준으로 나눈 것들 먼저 덧셈
		for(String s : strs) {
			int sum = 0;
			String[] strs1 = s.split(ch[1]);
			for(String s2 : strs1) {
				sum += Integer.parseInt(s2);
			}
			list.add(sum);
		}

		// 맨 처음 값 먼저 셋팅 ( 빼면안되니까 )
		int res = list.get(0);
		for(int i=1; i<list.size(); i++) {
			res -= list.get(i);
		}
		System.out.println(res);
	}
}

