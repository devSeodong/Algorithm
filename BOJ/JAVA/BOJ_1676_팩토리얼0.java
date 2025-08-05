import java.io.*;
import java.math.BigInteger;
import java.nio.Buffer;

public class Main {
    public static void main(String[] args) throws IOException {
        // N!에서 뒤에서부터 처음 0이 아닌 숫자가 나올 때까지의 0의 개수
        // 입력 : N
        // 출력 : 구한 0의 개수

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(br.readLine());
        int cnt = 0;

        while (N >= 5) {
            cnt += N/5;
            N/=5;
        }

		bw.write(cnt+"\n");
		bw.flush();
		bw.close();
		br.close();
    }

//	public static void main(String[] args) throws IOException {
//		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
//
//		int N = Integer.parseInt(br.readLine());
//		BigInteger sum = new BigInteger("1");
//		for(int i = 1; i<=N; i++) {
//			sum = sum.multiply(BigInteger.valueOf(i));
//		}
//
//		String s = String.valueOf(sum);
//		int res = 0;
//		for(int i = s.length()-1; i>=0; i--) {
//			if (s.charAt(i) == '0') {
//				res++;
//			} else {
//				break;
//			}
//		}
//
//		bw.write(res+"\n");
//		bw.flush();
//		bw.close();
//		br.close();
//	}
}
