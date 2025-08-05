import java.io.*;
import java.util.*;

public class Solution {
	public static void main(String[] args) throws IOException {
		// 최대 힙 
		// 배열에 자연수 X를 넣는다
		// 배열에서 가장 큰 값을 출력하고, 그 값을 배열에서 제거한다.
		
		// x -> x라는 값을 배열에 넣는 연산
		// 0 -> 배열에서 가장 큰 값을 출력하고 제거 
		
		// 입력 : 연산의 개수 N, 연산에 대한 정보를 나타내는 정수 x
		// 0이 주어진 횟수만큼 답을 출력. 배열이 비어있는 경우에는 0을 출력 
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(br.readLine());
		
		PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
		for(int n=0; n<N; n++) {
			int x = Integer.parseInt(br.readLine());
			if(x == 0) {
				if(pq.isEmpty()) {
					sb.append(0).append("\n");
				}else {
					int poll = pq.poll();
					sb.append(poll).append("\n");
				}
			} else {
				pq.add(x);
			}
		}
		
		System.out.println(sb);
		
	}
}