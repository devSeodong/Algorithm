package BOJ;

import java.io.*;
import java.util.*;

public class silver3_11478_서로다른문자열의개수 {
	public static void main(String[] args) throws IOException {
	// 문자열 S, 서로 다른 부분 문자열의 개수
	// S에서 연속된 일부분, 길이가 1보다 크거나 같음

	// 입력 : S ( 소문자, 길이 1000 이하 )
	// 출력 : S의 서로 다른 부분 문자열의 개수

	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	String str = br.readLine();

	Set<String> set = new HashSet<>();
	int n = str.length();

	for (int i=0; i<n; i++) {
		for (int j=i+1; j<=n; j++) {
			set.add(str.substring(i, j));
		}
	}

	System.out.println(set.size());
	}
}


// 트라이를 사용한다면 더 빠르게 풀 수 있겠네요!!
// - 지나가는 시영쌤


//--------------------------------스포일러 방지-----------------------------------------------


//Set<String> set = new HashSet<>();
//
//int cnt = 0;
//
//for(int i = 1; i <= str.length(); i++) {
//	for(int j = 0; j < str.length() - i + 1; j++) {
//		sb = new StringBuilder();
//		for(int s = 0; s < i; s++) {
//			sb.append(str.charAt(s+j));
//		}
//		if(set.contains(sb.toString())) continue;
//		set.add(sb.toString());
//		cnt++;
//	}
//}
//
//System.out.println(cnt);

// --------------------------------- 이게 맞나 -------------------------------------


