import java.io.*;

public class Main {
    public static void main(String args[]) throws IOException {
        // 입력 : 자연수 N
        // 출력 : 최소 개수의 방을 지나갈 때 몇 개의 방을 지나는지

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        long N = Long.parseLong(br.readLine());

        int level = 1;
        long maxRoom = 1;

        while (N > maxRoom) {
            maxRoom += 6L * level;
            level++;
        }

        System.out.println(level);
    }
}