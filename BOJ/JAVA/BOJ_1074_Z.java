import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		// Z 모양으로 탐색
		// 4등분 한 후에 재귀적으로 순서대로 방문
		// N이 주어졌을 때, r행 c열을 몇 번째로 방문하는지

		// 입력 : N, r, c
		// 출력 : r행 c열을 몇 번째로 방문했는지

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int r = Integer.parseInt(st.nextToken());
		int c = Integer.parseInt(st.nextToken());

		int res = 0;
		System.out.println(solve(N, c, r, res));
	}

	public static int solve(int N, int c, int r, int res) {
		if (N == 0) return res;

		int h = (int) Math.pow(2, N-1);
		int q = 0;
		if(c >= h && r >=h) {
			q = 3;
			c -= h;
			r -= h;
		} else if (c < h && r >= h) {
			q = 2;
			r -= h;
		} else if (c >= h) {
			q = 1;
			c -= h;
		}

		int num = (int) Math.pow(4, N-1);
		res += num*q;

		return solve(--N, c, r, res);
	}
}
