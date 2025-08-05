import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int a = Integer.parseInt(st.nextToken());
		int b = Integer.parseInt(st.nextToken());

		int c = a;
		int d = b;
		while(d!=0) {
			int temp = d;
			d = c%d;
			c = temp;
		}

		System.out.println(c);
		System.out.println(a*b/c);
	}
}
