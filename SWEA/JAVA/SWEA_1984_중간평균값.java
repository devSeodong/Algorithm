import java.io.*;
import java.util.*;

public class Solution {
	public static void main(String[] args) throws IOException {
		// 10개의 수를 받아, 최대 수와 최소 수를 제외한 나머지의 평균값을 출력하는 프로그램을 작성
		// ! 소수점 첫째 자리에서 반올림한 정수
		
		// 입력 : 테케 T, 각 테케 10개의 수 
		// 출력 : #t 정답 ( 소수점 첫째 자리에서 반올림한 정수 )
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		for(int t =1; t<=T; t++) {
			st = new StringTokenizer(br.readLine());
			int[] arr = new int[10];
			for(int i=0; i<10; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
			}
			
			Arrays.sort(arr);
			int sum = 0;
			for(int i=1;i<=8; i++) {
				sum += arr[i];
			}
			
			double result = (double)sum/8;
			System.out.printf("#%d %.0f\n", t,  result);
		}
	}
}