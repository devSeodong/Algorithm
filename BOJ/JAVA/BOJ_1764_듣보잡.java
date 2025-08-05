import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		// 듣도 못한 사람의 명단, 듣도 보도 못한 사람의 명단
		// 듣도 보도 못한 사람의 명단

		// 입력 : N, M, N명단, M명단
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		Map<String, Integer> map = new HashMap<> ();
		for(int i=0; i<N; i++) {
			map.put(br.readLine(), 1);
		}

		List<String> list = new ArrayList<>();
		for(int i=0; i<M; i++) {
			String str = br.readLine();
			map.put(str, map.getOrDefault(str, 0) +1);
			if(map.get(str) == 2) {
				list.add(str);
			}
		}
		Collections.sort(list);

		StringBuilder sb = new StringBuilder();
		sb.append(list.size()).append("\n");
		for(String s : list) {
			sb.append(s).append("\n");
		}

		System.out.print(sb);
	}
}
