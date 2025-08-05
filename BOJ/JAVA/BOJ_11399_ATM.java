import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
//		-1대밖에 없는 ATM -> N명의 사람 줄 (1~N번)
//		-i번 사람이 인출하는데 걸리는 시간 P
//
//		-입력 : 첫째 줄 사람의 수 N, 돈을 인출하는데 걸리는 시간 P
//		-출력 : 각 사람이 돈을 인출하는데 필요한 시간의 합의 최솟값
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int each = 0;
		int result = 0;
		List<Integer> list = new ArrayList<>();
		for(int n=0; n<N; n++) {
			int t = Integer.parseInt(st.nextToken());
			list.add(t);
		}
		
		Collections.sort(list);
		for(int i : list) {
			each += i;
			result += each;
		}
		
		System.out.println(result);
	}
}
