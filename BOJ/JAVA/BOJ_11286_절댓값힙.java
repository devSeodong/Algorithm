import java.io.*;
import java.util.*;

public class Solution {
	public static void main(String[] args) throws IOException {
		// 절댓값 힙
		// 배열에 정수 x를 넣는다
		// 배열에서 절댓값이 가장 작은 값을 출력하고, 그 값을 배열에서 제거한다. 절댓값이 가장 작은 값이 여러개일 때에는, 가장 작은 수를
		// 출력하고 배열에서 제거

		// 입력 : 연산의 개수 N, 정수 x
		// 출력 : 입력에서 0이 주어진 회수만큼 답을 출력 , 비어있다면 0을 출력

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(br.readLine());

		PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> {
			int abs = Integer.compare(Math.abs(a), Math.abs(b));
			if (abs == 0)
				return Integer.compare(a, b);
			return abs;
		});

		for (int n = 0; n < N; n++) {
			int x = Integer.parseInt(br.readLine());
			if(x == 0) {
				if(pq.isEmpty()) {
					sb.append(0).append("\n");
				} else {
					sb.append(pq.poll()).append("\n");
				}
			} else {
				pq.add(x);
			}
		}

		System.out.println(sb);
	}
}