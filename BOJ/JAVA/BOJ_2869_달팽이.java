import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		// 높이가 V미터인 나무 막대
		// 낮에 A미터 오르고, 밤에 B미터 미끄러짐 / 정상에 올라간 후에는 미끄러지지 않음
		// 모두 올라갈라면 며칠?
		
		// 입력 : A, B, V
		// 출력 : 며칠?
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		long A = Long.parseLong(st.nextToken());
		long B = Long.parseLong(st.nextToken());
		long V = Long.parseLong(st.nextToken());
		
		long day = (V - B - 1) / (A - B) + 1;
		System.out.println(day);
	}
}
