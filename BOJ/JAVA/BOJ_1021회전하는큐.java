import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		// 입력 : 큐의 크기 N, 뽑아내려고 하는 수의 개수 M / 뽑아내려고 하는 수의 위치
		// 출력 : 문제의 정답 출력

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		LinkedList<Integer> d = new LinkedList<>();

		for(int i=1;i<=N;i++) {
			d.add(i);
		}

		int cnt = 0;
		st = new StringTokenizer(br.readLine());
		for(int m=0;m<M; m++) {
			int a = Integer.parseInt(st.nextToken());
			while(d.getFirst() != a) {
				if(d.indexOf(a) <= d.size()/2) {
					d.addLast(d.getFirst());
					d.removeFirst();
				} else {
					d.addFirst(d.getLast());
					d.removeLast();
				}
				cnt++;
			}

			if (d.getFirst() == a) {
				d.removeFirst();
			}
		}
		System.out.println(cnt);
	}
}
