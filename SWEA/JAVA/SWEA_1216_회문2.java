import java.io.IOException;
import java.util.Scanner;

public class Solution {
	public static StringBuilder[] arr;
	public static int N = 100;

	public static void main(String[] args) throws IOException {
		// 100*100 글자판 가장 긴 회문의 길이 (직선만 가능)
		// 각 칸의 글자 -> char 'A' 'B' 'C'

		// 입력 : 10개의 테케 , 테케번호, 테케
		// 출력 : #테케 회문길이

		Scanner sc = new Scanner(System.in);

		for (int i = 0; i < 10; i++) {
			int T = sc.nextInt();
			sc.nextLine();

			arr = new StringBuilder[N];

			for (int n = 0; n < N; n++) {
				arr[n] = new StringBuilder(sc.nextLine());
			}

			int max = 0;
			for (int c = 0; c < N; c++) {
				max = Math.max(max, rcMax(arr[c]));
				
                StringBuilder sb = new StringBuilder();
                for (int r = 0; r < N; r++) {
                    sb.append(arr[r].charAt(c));
                }
                max = Math.max(max, rcMax(sb));
            }

			System.out.println("#" + T + " " + max);
		}
		
		sc.close();
	}

	public static int rcMax(StringBuilder str) {
		for (int l = N; l >= 1; l--) {
            for (int s = 0; s <= N - l; s++) {
                int e = s + l - 1;
                if (isOk(str, s, e)) {
                    return l; 
                }
            }
        }
        return 0;
	}

	public static boolean isOk(StringBuilder str, int s, int e) {
		while (s < e) {
            if (str.charAt(s++) != str.charAt(e--)) {
                return false;
            }
        }
		return true;
	}
}