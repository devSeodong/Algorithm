import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		// 1초, N*N의 표에 채워진 수, 모든 수는 자신의 한 칸 위에 있는 수보다 큼.
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st ;

//        List<Integer> list = new ArrayList<>();
//        for(int n = 0; n<N; n++) {
//            st = new StringTokenizer(br.readLine());
//            for(int i=0;i<N; i++) list.add(Integer.parseInt(st.nextToken()));
//        }
//
//        list.sort(new Comparator<Integer>() {
//            @Override
//            public int compare(Integer o1, Integer o2) {
//                return o2 - o1;
//            }
//        });
//
//        System.out.print(list.get(N-1));

		// 시간초과
//        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
//        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
//        for(int n=0; n<N; n++) {
//            st = new StringTokenizer(br.readLine());
//            for(int i=0; i<N; i++){
//                pq.add(Integer.parseInt(st.nextToken()));
//            }
//        }
//
//        for(int j=0;j<N-1;j++) {
//            pq.remove(pq.poll());
//        }
//
//        bw.write(pq.poll()+"\n");
//        bw.flush();

		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		PriorityQueue<Integer> q = new PriorityQueue<>();
		for(int n=0; n<N; n++) {
			st = new StringTokenizer(br.readLine());
			for(int i=0; i<N; i++){
				int num = Integer.parseInt(st.nextToken());
				if (q.size() != N) {
					q.add(num);
				} else {
					if (q.peek() < num) {
						q.poll();
						q.add(num);
					}
				}
			}
		}

		bw.write(q.poll() +" ");
		bw.flush();
		bw.close();
	}
}
