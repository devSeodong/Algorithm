import java.io.*;
import java.util.*;

public class Main
{
	public static void main(String args[]) throws Exception
	{
		// N개의 정수  A[1], A[2], …, A[N] 이 안에 X라는 정수가 존재하는지 
		// 입력 : 자연수 N, 정수 A[1~N], 자연수 M, M개의 수
		// 출력 : 정수A[1~N]안에 M개의 수가 존재하는지, 존재하면 1 / 존재하지 않으면 0
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		Set<Integer> A = new HashSet<>();
		for(int i=0; i<N; i++) {
			A.add(Integer.parseInt(st.nextToken()));
		}
		
		int M = Integer.parseInt(br.readLine());
		StringTokenizer st2 = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<M; i++) {
			int m = Integer.parseInt(st2.nextToken());
			sb.append(A.contains(m)?"1":"0").append("\n");
		}
		
		bw.write(sb+"\n");
		bw.flush();
		bw.close();
		br.close();
	}
}