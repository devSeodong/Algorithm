import java.io.*;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		// 총 N개의 강의
		// i번, j번 강의 녹화 -> i와 j사이의 모든 강의도 녹화
		// M개의 블루레이에 모든 영상 녹화 -> 크기를 최소로 ( 단 모두 같은 크기 )

		// 입력 : 강의 수 N, 블루레이 수 M, 강의 길이 정보
		// 출력 : 블루레이 크기 중 최소

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		int sumV = 0;
		int maxV = 0;
		int[] arr = new int[N];
		st = new StringTokenizer(br.readLine());

		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
			sumV += arr[i]; // 강의 정보 합
			maxV = Math.max(maxV, arr[i]); // 가장 긴 강의의 길이 보다 작은 블루레이 존재 불가
		}

		while (maxV < sumV) { // 제일 긴 강의가 합보다 작을 때까지만
			int mid = (sumV+maxV)/2;

			int cnt = 1, temp = mid; // 남은 공간
			for (int i = 0; i < N; i++) {
				// 현재 강의가 남은 공간보다 크면 새로운 블루레이에
				if(arr[i] > temp) {
					temp = mid; // 용량 초기화
					cnt++; // 블루레이 개수를 증가
				}
				temp -= arr[i]; // 강의 길이 차감
			}

			// M보다 많으면 크기가 너무 작은거,, 오른쪽으로
			if(cnt > M) maxV = mid+1;
			else sumV = mid;
		}

		System.out.println(maxV);
	}
}
