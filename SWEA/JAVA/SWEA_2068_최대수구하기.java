import java.io.*;
import java.util.*;

public class Solution {
	public static void main(String[] args) throws IOException {
		// 10개의 수를 입력받아, 그 중에서 가장 큰 수를 출력하는 프로그램
		// 입력 : 테케 T, 각 테스트 케이스 10개의 수 
		// 출력 : #t 정답 
		
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
			System.out.println("#"+t+" "+arr[9]);
		}
		
	}
}