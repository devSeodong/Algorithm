import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		// 길이 N인 정수 배열 A와 B
		// S의 값을 가장 작세 만들기 위해 A의 수 재배열, B는 재배열 X

		// 입력 : N, A배열, B배열
		// 출력 : S의 최솟값

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] A = new int[N];
		Integer[] B = new Integer[N];

		StringTokenizer st = new StringTokenizer(br.readLine());
		StringTokenizer st2 = new StringTokenizer(br.readLine());

		for(int i=0; i<N; i++) {
			A[i] = Integer.parseInt(st.nextToken());
			B[i] = Integer.parseInt(st2.nextToken());
		}

		Arrays.sort(A);
		Arrays.sort(B, Collections.reverseOrder());

		int cnt = 0;
		for(int i=0; i<N; i++) {
			int sum = A[i]*B[i];
			cnt += sum;
		}

		System.out.println(cnt);
	}
}

