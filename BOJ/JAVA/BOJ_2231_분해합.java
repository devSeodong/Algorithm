import java.io.*;

public class Main {
    public static void main(String args[]) throws IOException {
        // 입력 : 자연수 N
        // 출력 : N의 가장 작은 생성자, 생성자가 없는 경우 0
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int result = 0;
        for (int i = 1; i < N; i++) {
            int cnt = 0;
            int num = i;
            while (num > 0) {
                cnt += num%10;
                num /= 10;
            }
            if (i+cnt == N) {
                result = i;
                break;
            }
        }

        System.out.println(result);
    }
}