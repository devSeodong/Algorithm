package BOJ;

import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		// N 명의 원생 -> 키순서, K개의 조 
		// 키 큰 원생 - 키 작은 원생 
		// K개의 조에 대해 비용의 합 최소
		
		// 입력 : N, K, 원생 정보
		// 출력 : 티셔츠 만드는 최소 비용
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		List<Integer> sumList = new ArrayList<>();
		
		// 비교할 이전 값 저장 ( 홀수든 짝수든 같은 결과 값 )
		int prev = Integer.parseInt(st.nextToken());
		for(int n=1; n<N; n++) {
			int num = Integer.parseInt(st.nextToken());
			sumList.add(num-prev);
			prev = num;
		}
		Collections.sort(sumList);
		
		int sum = 0;
		for(int i=0; i<N-K; i++) {
			sum += sumList.get(i);
		}
		
		System.out.println(sum);
	}
}
