package BOJ;

import java.io.*;
import java.util.*;

public class gold4_11404_플로이드 {
	static int[][] graph;
	static int n, m;
	public static void main(String[] args) throws IOException {
		// n개의 도시, 한 도시에서 출발해서 다른 도시에 도착하는 M개의 버스 
		// 모든 도시의 쌍에 대해서 필요한 비용의 최솟값 
		
		// 입력 : n, m, 버스 정보 
		// 출력 : n개의 줄 
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		n = Integer.parseInt(br.readLine()); 
		m = Integer.parseInt(br.readLine());
		
		graph = new int[n+1][n+1];
		for(int i=0; i<=n; i++) {
			for(int j=0; j<=n; j++) {
				if(i==j) continue;
				graph[i][j] = 1000000000;
			}
		}
		
		for(int i=0; i<m; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			
			graph[a][b] = Math.min(graph[a][b], w);
		}
		
		// 경유지
		for(int i=1; i<=n; i++) {
			// 출발지
			for(int j=1; j<=n; j++) {
				// 도착지
				for(int k=1; k<=n; k++) {
					graph[j][k] = Math.min(graph[j][k], graph[j][i]+graph[i][k]);
				}
			}
		}
		
		StringBuilder sb = new StringBuilder();
		for(int i=1; i<=n; i++) {
			for(int j=1; j<=n; j++) {
				if(graph[i][j] == 1000000000) sb.append(0).append(" ");
				else sb.append(graph[i][j]).append(" ");
			}
			sb.append("\n");
		}
		
		System.out.println(sb);
	} 
}
