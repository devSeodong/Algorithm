import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		// 동전 총 N종류, 동전사용하여 합 K를 만드는데에 동전갯수 최솟값

		// 입력 : 첫째 줄 N과 K
		// 출력 : K원을 만드는데 필요한 동전 개수

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken()); // 동전 종류
		int K = Integer.parseInt(st.nextToken()); // 만들어야 할 돈
		
		int[] nArr = new int[N];
		for (int n = 0; n < N; n++) {
			nArr[n] = Integer.parseInt(br.readLine());
		}
		
		int A = K;
		int i = N-1;
		int cnt = 0;
		while(A>0) {
			if(nArr[i] <= A) {
				A -= nArr[i];
				cnt++;
			} else {
				i--;
			}
		}
		
		System.out.println(cnt);
	}
}