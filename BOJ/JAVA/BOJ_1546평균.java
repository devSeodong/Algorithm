import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		// 자기 점수 중에 최댓값 -> M
		// 점수/M*100
		
		// 입력 : 시험 본 과목의 개수 N, 현재 성적
		// 출력 : 새로운 평균
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		double[] score = new double[N];
		double max = Integer.MIN_VALUE;
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int n=0;n<N;n++) {
			score[n] = Integer.parseInt(st.nextToken());
			if(max < score[n]) max = score[n];
		}
		
		double sum = 0;
		for(int i=0;i<score.length;i++) {
			sum += (score[i]/max) *100;
		}
		
		System.out.printf("%.2f", sum/N);
	}

}
