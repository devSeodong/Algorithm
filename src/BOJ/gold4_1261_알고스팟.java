package BOJ;

import java.io.*;
import java.util.*;

public class gold4_1261_알고스팟 {
	static int N, M, INF = Integer.MAX_VALUE;
	static int[][] arr, dist, RC = {{-1, 1, 0, 0}, {0, 0, -1, 1}};
	static class Node implements Comparable<Node>{
		int r, c, d;
		Node(int r, int c, int d) {
			this.r = r;
			this.c = c;
			this.d = d;
		}
		@Override
		public int compareTo(Node o) {
			return Integer.compare(this.d, o.d);
		}
	}
	public static void main(String[] args) throws IOException {
		// N*M크기 미로
		// 항상 모두 같은 방에 있어야함. 
		// 네방향으로 이동 가능 
		// 무기를 이용해서 벽을 부실 수 ㅣㅇㅆ음 
		// (1, 1)에서 (N, M)으로 가려면 벽을 몇개 부셔야함?
		
		// 입력 : M, N, 미로 상태 
		// 출력 : 몇 개의 병을 부수어야 하는지 
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		
		arr = new int[N][M];
		dist = new int[N][M];
		for(int i=0; i<N; i++) {
			Arrays.fill(dist[i], INF);
		}
		
		for(int i=0; i<N; i++) {
			String str = br.readLine();
			for(int j=0; j<M; j++) {
				arr[i][j] = str.charAt(j)-'0';
			}
		}
		
		System.out.println(solve());
	}
	
	public static int solve() {
		PriorityQueue<Node> pq = new PriorityQueue<>();
		pq.add(new Node(0, 0, 0));
		dist[0][0] = 0;
		
		while(!pq.isEmpty()) {
			Node cur = pq.poll();
			
			if(cur.d != dist[cur.r][cur.c]) continue;
			if(cur.r == N-1 && cur.c == M-1) return cur.d;
			
			for(int d=0; d<4; d++) {
				int nr = cur.r + RC[0][d];
				int nc = cur.c + RC[1][d];
				if(nr < 0 || nr >= N || nc < 0 || nc >=M) continue;
				
				int nd = cur.d + arr[nr][nc];
				if(nd < dist[nr][nc]) {
					dist[nr][nc] = nd;
					pq.add(new Node(nr, nc, nd));
				}
			}
		}
		
		return -1;
	}
}
