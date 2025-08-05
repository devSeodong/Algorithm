import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		// 나이와 이름
		// 나이가 증가하는 순 -> 나이가 같으면 먼저 가입한 사람이 앞에 오는 순서

		// 입력 : 회원 수 N, 각 회원의 나이와 이름
		// 출력 : 나이 이름

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());
		String[][] arr = new String[N][2];

		for(int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			arr[i][0] = st.nextToken();
			arr[i][1] = st.nextToken();
		}

		Arrays.sort(arr, new Comparator<String[]>() {
			@Override
			public int compare(String[] s1, String[] s2) {
				return Integer.parseInt(s1[0]) - Integer.parseInt(s2[0]);
			}
		});

		StringBuilder sb = new StringBuilder();

		for(int i = 0; i < N; i++) {
			sb.append(arr[i][0]).append(' ').append(arr[i][1]).append('\n');
		}

		System.out.println(sb);
	}
}
