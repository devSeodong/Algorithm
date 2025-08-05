import java.io.*;
import java.util.*;

public class Main {
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for (int t = 0; t < T; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int sum = 0;
            int min = Integer.MAX_VALUE;

            for (int i = 0; i < 7; i++) {
                int num = Integer.parseInt(st.nextToken());
                if (num % 2 == 0) {
                    sum += num;
                    if (num < min) {
                        min = num;
                    }
                }
            }

            System.out.println(sum + " " + min);
        }
    }
}
