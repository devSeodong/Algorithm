import java.io.*;
import java.util.*;

public class Main {
	static String[] arr = new String[4];
	static int[] flags;
	static boolean[] v ;
	public static void main(String[] args) throws IOException {
		// 8톱니를 가지고 있는 톱니바퀴 4개
		// 톱니바퀴를 총 K번 회전 -> 시계 / 반시계
		// 서로 맞닿은 톱니의 극이 다르면, 반대 방향으로 회전
		// 톱니바퀴 초기상태, 회전시킨 방법 -> 최종 톱니바퀴의 상태 구하기

		// 입력 : 1~4번째 줄 톱니바퀴 상태(8개의 정수/시계방향 순서), 회전 횟수 K, 회전시킨 방법 (톱니바퀴 번호, 방향 -> 1 시계,
		// -1 반시계)
		// 출력 : 네 톱니바퀴 점수의 합 (N극 0점, S극 1,2,4,8점)

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		for (int i = 0; i < 4; i++) {
			arr[i] = br.readLine();
		}

		int K = Integer.parseInt(br.readLine());
		for (int k = 0; k < K; k++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken()); // 톱니바퀴 번호
			int f = Integer.parseInt(st.nextToken()); // flag
			solve(x - 1, f); // 인덱스, flag
		}

		int sum = 0;
		// 인덱스 0이 1이면 1,2,4,8 ++
		for(int i=0; i<4; i++) {
			String str = arr[i].replace(' ', '0');
			if(str.charAt(0) == '1') sum += (1<<i); // 2^i
		}

		System.out.println(sum);
	}

	public static void solve(int idx, int f) {
		flags = new int[4];
		v = new boolean[4];

		rotate(idx, f);

		for (int i = 0; i < 4; i++) {
			if (flags[i] == 0) continue;
			// 문자열 -> 2진수
			int bin = Integer.parseInt(arr[i].replace(' ', '0'), 2);
			// 순환시프트
			arr[i] = shift(bin, flags[i]).replace(' ', '0');
		}
	}

	static void rotate(int idx, int f) {
		String n = arr[idx].replace(' ', '0');
		v[idx] = true; // 방문배열로 기본조건
		flags[idx] = f;

		// 왼쪽
		if (idx-1 >= 0 && !v[idx-1]) {
			String l = arr[idx-1].replace(' ', '0');
			if (n.charAt(6) != l.charAt(2)) {
				rotate(idx-1, -f);
			}
		}

		// 오른쪽
		if (idx+1 < 4 && !v[idx+1]) {
			String r = arr[idx+1].replace(' ', '0');
			if (n.charAt(2) != r.charAt(6)) {
				rotate(idx+1, -f);
			}
		}
	}

	public static String shift(int x, int f) {
		int num = f == 1 ? (x >>> 1) | ((x & 1) << 7) : ((x << 1) & 0xFF) | (x >>> 7);
		return String.format("%8s", Integer.toBinaryString(num).replace(' ', '0'));
	}
}
