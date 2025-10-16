package BOJ;

import java.io.*;
import java.util.*;

public class gold5_14719_빗물 {
	public static void main(String[] args) throws IOException {
		// 2차원 블록
		// 비가 오면 고이는 빗물의 총량 
		
		// 입력 : H, W, 블록 높이 정보 
		// 출력 : 고이는 빗물의 총량
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int H = Integer.parseInt(st.nextToken());
		int W = Integer.parseInt(st.nextToken());
		
		int[] arr = new int[W];
		
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<W; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		int res = 0;
		for(int i=1; i<W-1; i++) {
			int l = 0, r = 0;
			
			for(int j=0; j<i; j++) {
				l = Math.max(arr[j], l);
			}
			
			for(int k=i+1; k<W; k++) {
				r = Math.max(arr[k], r);
			}
			
			if(arr[i] < l && arr[i] < r) {
				res += Math.min(l, r) - arr[i];
			}
		}
		
		System.out.println(res);
	}
}
