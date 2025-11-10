package BOJ;

import java.io.*;
import java.util.*;

public class gold2_2031_이쿠키달지않아 {	
	public static void main(String[] args) throws IOException {
		// 미래 T분 동안 먹을 음식 N종류
		// D분 동안 다이어트 효과 유지
		// K잔의 차를 적당한 타이밍에 마심
		// 다이어트 효과가 유지되는 동안 음식을 먹는 횟수를 최대
		
		// 입력 : T, N, D, K, N개의 자연수 
		// 출력 : 차를 마셨을 때 효과가 적용되는 횟수의 최댓값
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int T = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(st.nextToken());
		int D = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		int[] arr = new int[N];
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(arr);
		
		
	} 
}
