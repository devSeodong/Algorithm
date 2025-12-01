package BOJ;

import java.io.*;
import java.util.*;

public class gold4_16472_고냥이 {
	public static void main(String[] args) throws IOException {
		// 문자열 중에서 최대 N개 종류의 알파벳을 가진 연속된 문자열 인식
		// 인식할 수 있는 최대 문자열의 길이?
		
		// 입력 : N, 문자열 ( 소문자 )
		// 출력 : 번역기가 인식할 수 있는 문자열의 최대길이 출력 
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		String str = br.readLine();
		
		int[] arr = new int[26]; // 각 알파벳 카운팅 배열
		int max = Integer.MIN_VALUE;
		
		arr[str.charAt(0)-'a']++; // 첫 문자 포함
		
		// cnt : 현재 윈도우 내에 서로 다른 문자 종류 수
		int s = 0, e = 0, cnt = 1;
		while(true) {
			e++; // end 이동
			if(e==str.length()) break; 
			
			int n = str.charAt(e)-'a'; // 새로 포함되는 문자
			arr[n]++; // 개수 증가
			if(arr[n] == 1) cnt++; // 처음 등장한 문자면 종류 수 증가
			
			// 종류 수가 N넘어가면 start 포인터 이동
			while(cnt > N) {
				int temp = str.charAt(s)-'a'; // 제거 문자
				arr[temp]--; // 개수 감소
				
				if(arr[temp] == 0) cnt--; // 완전 없어지면 종류 감소 
				s++; // start 포인터 이동
			}
			
			// 현재 윈도우 길이 = 최댓값
			max = Math.max(max, e-s+1);
		}
		
		System.out.println(max);
	}
}
