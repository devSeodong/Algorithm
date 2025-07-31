import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
	public static class xy{
		int x;
		int y;

		xy(int x, int y) {
			this.x = x;
			this.y = y;
		}

		public String toString() {
			return x+" "+y;
		}
	}

	public static void main(String[] args) throws IOException {
		// 2차원 평면 위의 점 N개
		// y좌표가 증가하는 순으로, y좌표가 같으면 x좌표가 증가하는 순서로

		// 입력 : 점의 개수 N, N개의 i번 점의 위치
		// 출력 : N개의 줄에 점을 정렬한 결과 출력

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st;

		xy[] xyArr = new xy[N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			xyArr[i] = new xy(x, y);
		}

		Arrays.sort(xyArr, new Comparator<xy>() {
			@Override
			public int compare(xy o1, xy o2) {
				if(o1.y == o2.y) {
					return o1.x -o2.x;
				}
				return o1.y-o2.y;
			}
		});

		StringBuilder sb = new StringBuilder();
		for(Main.xy xy : xyArr) {
			sb.append(xy).append("\n");
		}

		System.out.println(sb);
	}
}
