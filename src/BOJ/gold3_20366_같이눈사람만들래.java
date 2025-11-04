package BOJ;

import java.io.*;
import java.util.*;

public class gold3_20366_같이눈사람만들래 {
	public static void main(String[] args) throws IOException {
		// N개의 눈덩이, 눈덩이 지름 Hi
		// 하나의 눈사람은 두 개의 눈덩이 구성
		// 눈사람의 키 -> 두 눈덩이 지름의 합
		// N개 중 4개를 골라서 눈사람 두개 만들기 
		
		// 입력 : N, 지름 정보 
		// 출력 : 두 눈사람의 키 차이  최솟값
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		long[] arr = new long[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(arr);
		
		long res = Long.MAX_VALUE;
		
		for(int i=0; i<N; i++) {
			for(int j=i+1; j<N; j++) {
				long sum = arr[i]+arr[j];
				int L = 0, R = N-1;
				
				while(L < R) {
					if(L == i || L == j) {
						L++;
						continue;
					}
					if(R == i || R == j) {
						R--;
						continue;
					}
					
					long sum2 = arr[L] + arr[R];
					res = Math.min(Math.abs(sum2-sum), res);
					
					if (sum < sum2) {
						R--;
					} else if (sum > sum2){
						L++;
					} else {
						System.out.println(0);
						return;
					}
				}
			}
		}
		
		System.out.println(res);
	}
}
