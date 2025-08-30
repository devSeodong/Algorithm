import java.io.*;
import java.util.*;

public class Main {
	static class Compare implements Comparator<int[]> {
		@Override
		public int compare(int[] o1, int[] o2) {
			return o2[0] - o1[0];
		}
	}

	public static void main(String[] args) throws IOException {
		// 도로 위의 좌표 -> 아파트 단지 / 학교 위치 / 학생들의 수
		// 정원을 초과할 수 없고, 등교시킬 때까지 반복

		// 입력 : N(아파트 수), K(통학버스 정원), S(학교위치), 위치 정보, 학생 수
		// 출력 : 통학버스의 최소 이동 거리

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		int S = Integer.parseInt(st.nextToken());

		List<int[]> left = new ArrayList<>();
		List<int[]> right = new ArrayList<>();
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			if(a<S) {
				left.add(new int[]{S-a, b});
			} else {
				right.add(new int[]{a-S, b});
			}
		}

		left.sort(new Compare());
		right.sort(new Compare());

		System.out.println(solve(left, K)+solve(right, K));
	}

	public static long solve(List<int[]> arr, int K) {
		long res = 0;
		long cnt = 0; // 남은 학생 수
		for(int[] a: arr) {
			cnt += a[1];
			while(cnt > 0) {
				res += a[0]*2;
				cnt -= K;
			}
		}

		return res;
	}
}
