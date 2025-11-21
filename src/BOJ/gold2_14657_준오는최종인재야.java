package BOJ;

import java.io.*;
import java.util.*;

public class gold2_14657_준오는최종인재야 {
	static int N, T;
	static class Node {
		int a, b;
		
		Node(int a, int b) {
			this.a = a;
			this.b = b;
		}
	}
	public static void main(String[] args) throws IOException {
		// N개의 문제들과 임의의 두 문제를 연결하는 링크 N-1 ( 양방향 )
		// N개의 문제들 중 링크를 통해 도달할 수 없는 문제는 없다. 
		// 임의의 문제 A에서 B까지 도달하는 경로는 유일하다
		// 이전으로 돌아갈 수 없고, 처음 고른 문제는 꽁짜
		// 매일 투자할 수 있는 최대한의 시간 동안 문제를 풀고 남은 문제는 그 다음날에 이어서
		
		// 입력 : N, T, A, B, C 정보
		// 출력 : 최대한 많은 문제를 푸는데 걸리는 최소 날짜 수 출력 
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());
		
		List<List<Node>> list = new ArrayList<>();
		for(int i=0; i<=N; i++) {
			list.add(new ArrayList<>());
		}
		
		for(int i=0; i<N-1; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			list.get(a).add(new Node(b, c));
			list.get(b).add(new Node(a, c));
		}
		
		Deque<Integer> dq = new ArrayDeque<>();
		boolean[] V = new boolean[N+1];
		
		dq.add(1);
		V[1] = true;
		
		Set<Integer> set = new HashSet<>();
		while(!dq.isEmpty()) {
			int size = dq.size();
			for(int i=0; i<size; i++) {
				int cur = dq.poll();
				set.add(cur);
				
				for(Node n : list.get(cur)) {
					int next = n.a;
					if(V[next]) continue;
					dq.add(next);
					V[next] = true;
				}
			}
			
			if(!dq.isEmpty()) {
				set.clear();
			}
		}
		
		int max = 0;
		int min = Integer.MAX_VALUE;
		for(int s : set) {
			int curDist = 0;
			Deque<Node> dist = new ArrayDeque<>();
			V = new boolean[N+1];
			
			dist.add(new Node(s, 0));
			V[s] = true;
			
			while(!dist.isEmpty()) {
				curDist++;
				int size = dist.size();
				for(int i=0; i<size; i++) {
					Node cur = dist.poll();
					
					for(Node n : list.get(cur.a)) {
						if(V[n.a]) continue;
						if(curDist > max) {
							max = curDist;
							min = cur.b + n.b;
						} else if (curDist == max && min > cur.b+n.b){
							min = cur.b + n.b;
						}
						
						dist.add(new Node(n.a, cur.b+n.b));
						V[n.a] = true;
					}
				}
			}
		}
		
		System.out.println((min+T-1)/T);
	}
}
