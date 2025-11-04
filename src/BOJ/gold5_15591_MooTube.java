package BOJ;

import java.io.*;
import java.util.*;

public class gold5_15591_MooTube {
	static int N, Q;
	static List<List<Video>> list;
	static class Video {
		int n, r;
		
		Video(int n, int r) {
			this.n = n;
			this.r = r;
		}
	}
	public static void main(String[] args) throws IOException {
		// 1부터 N까지 번호가 붙여진 N개의 동영상
		// N-1개의 쌍 
		// 어떤 동영상에서 다른 동영상으로 가는 경로 존재 
		
		// 입력 : N, Q, 동영상 연결 정보 , 질문 정보 ( k, v => K=k라면 동영상 v를 보고 있느 ㄴ소들에게 몇개의 동영상?)
		// 출력 : 질문에 대한 답변 
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		Q = Integer.parseInt(st.nextToken());
		
		list = new ArrayList<>();
		for(int i=0; i<=N; i++) {
			list.add(new ArrayList<>());
		}
		
		for(int i=0; i<N-1; i++) {
			st = new StringTokenizer(br.readLine());
			int p = Integer.parseInt(st.nextToken());
			int q = Integer.parseInt(st.nextToken());
			int r = Integer.parseInt(st.nextToken());
			list.get(p).add(new Video(q, r));
			list.get(q).add(new Video(p, r));
		}
		
		for(int i=0; i<Q; i++) {
			st = new StringTokenizer(br.readLine());
			int k = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			
			System.out.println(solve(k, v));
		}
	}
	
	public static int solve(int k, int v) {
		boolean[] V = new boolean[N];
		Deque<Integer> dq = new ArrayDeque<>();
		dq.add(v);
		V[v] = true;
		
		int cnt = 0;
		while(!dq.isEmpty()) {
			int cur = dq.poll();
			for(Video vid : list.get(cur)) {
				if(vid.r >= k && !V[vid.n]) {
					dq.add(vid.n);
					V[vid.n] = true;
					cnt++;
				}
			}
		}
		
		return cnt;
	}
}
