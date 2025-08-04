import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {
	public static void main(String[] args) throws IOException {
		// 높은 곳의 상자를 낮은 곳에 옮기는 방식으로 최고점과 최저점의 간격을 줄이는
		// 가장 높은 곳과 가장 낮은 곳의 차이가 최대 1이내
		// 상자를 옮기는 작업 횟우 제한 -> 제한된 횟수만큼 작업 후 최고점과 최저점의 차이

		// 입력 : 10개의 테케, 덤프의 횟수, 각 상자의 높이값
		// 출력 : #t 정답

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int R = 100;
		for (int t = 1; t <= 10; t++) {
			int N = Integer.parseInt(br.readLine());
			st = new StringTokenizer(br.readLine());
			
			int[] arr = new int[R];
			for(int i=0; i<R; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
			}
			
			while(N>0) {
				Arrays.sort(arr);
				arr[R-1]--;
				arr[0]++;
				N--;
			}
			
			Arrays.sort(arr);
			System.out.println("#"+t+" "+(arr[R-1]-arr[0]));
		}
	}
}