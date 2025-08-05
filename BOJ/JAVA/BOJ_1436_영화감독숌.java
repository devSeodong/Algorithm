import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		// 666은 종말을 나타내는 수 
		// 6이 적어도 세개 이상 들어가는 수 
		// N번째로 작은 종말의 수 
		
		// 입력 : 첫째 줄 N 
		// N번째 영화의 제목에 들어가는 수
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		int num = 666;
		int cnt = 1;
		
		while (cnt != N) {
			num++;
			if(String.valueOf(num).contains("666")) {
				cnt++;
			}
		}
		
		System.out.println(num);
	}
}
