import java.io.*;
import java.util.*;

public class Main {
	public static class xy {
		int x;
		int y;

		public xy(int x, int y) {
			this.x = x;
			this.y = y;
		}

		@Override
		public String toString() {
			return String.format("%d %d\n", x, y);
		}
	}

	public static void main(String[] args) throws IOException {
		// 2차원 평면 위의 점 N개
		// x좌표가 증가하는 순, x가 같으면 y좌표가 증가하는 순

		// 입력 : 점의 개수 N, x와 y좌표
		// 출력 : 점을 정렬한 결과

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		xy[] xys = new xy[N];

		for(int i= 0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			xys[i] = new xy(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		}

		Arrays.sort(xys, new Comparator<xy>() {
			@Override
			public int compare(xy o1, xy o2) {
				if (o1.x == o2.x) {
					return o1.y-o2.y;
				} else {
					return o1.x - o2.x;
				}
			}
		});

		StringBuilder sb = new StringBuilder();
		for (xy xy : xys) {
			sb.append(xy);
		}

		System.out.println(sb);
	}
}
