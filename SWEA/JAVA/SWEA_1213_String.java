import java.io.IOException;
import java.util.Scanner;

public class Solution {
	public static void main(String[] args) throws IOException {
		// 특정한 문자열의 개수를 반환

		// 10개의 테케
		// 입력 : 테케 번호, 찾을 문자열, 검색할 문장
		// 출력 : #T 갯수
		Scanner sc = new Scanner(System.in);
		for (int t = 0; t < 10; t++) {
			int T = sc.nextInt();
			sc.nextLine();

			String s = sc.nextLine();
			String l = sc.nextLine();

			int sLen = s.length();
			int lLen = l.length();
			int cnt = 0;
			
			for(int i=0; i<lLen-sLen+1; i++) {
				boolean isOk = true;
				for(int j=0; j<sLen; j++) {
					if(l.charAt(i+j) != s.charAt(j)) {
						isOk = false;
						break;
					}
				}
				if(isOk) cnt++;
			}
			
			System.out.println("#"+T+" "+cnt);
		}
		
		sc.close();
	}
}