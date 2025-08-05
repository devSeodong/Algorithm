import java.io.*;
import java.util.*;

public class Main {
	public static void main(String args[]) throws Exception {
		// N장의 카드 1~N , 1번카드 제일 위에 N번카드 제일 아래
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		Queue<Integer> q = new LinkedList<>();
		for (int i = 1; i <= N; i++) {
			q.add(i);
		}
		
		int num = 0;
		while(q.size()>1) {
			q.remove();
			num = q.poll();
			q.add(num);
		}
		
		System.out.println(q.peek());
	}
}