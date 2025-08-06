import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		// 랜덤으로 만들어지는 비밀번호
		// 메모장에 사이트 주소와 비밀번호
		// 메모장에서 비밀번호를 찾는 프로그램

		// 입력 : 저장된 사이트 주소의 수 N, 비밀번호 찾으려는 사이트 주소의 수 M, 저장된 사이트, 비밀번호 찾으려는 사이트
		// 출력 : 비밀번호를 찾으려는 사이트 주소의 비밀번호 출력

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		HashMap<String, String> map = new HashMap<>();
		for (int n = 0; n < N; n++) {
			st = new StringTokenizer(br.readLine());
			map.put(st.nextToken(), st.nextToken());
		}

		for (int m = 0; m < M; m++) {
			bw.write(map.get(br.readLine()));
			bw.write("\n");
		}
		
		bw.flush();
		bw.close();
	}
}