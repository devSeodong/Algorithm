import java.io.*;
import java.util.StringTokenizer;

public class Main {
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int[] size = new int[6];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < 6; i++) {
            size[i] = Integer.parseInt(st.nextToken());
        }

        StringTokenizer st2 = new StringTokenizer(br.readLine());
        int T = Integer.parseInt(st2.nextToken());
        int P = Integer.parseInt(st2.nextToken());

        int tShirt = 0;
        for (int n : size) {
            if (n % T == 0) {
                tShirt += n / T;
            } else {
                tShirt += n / T + 1;
            }
        }
        System.out.println(tShirt);

        int pen = 0;
        if (N%P != 0) {
            pen = N-P*(N/P);
        }
        System.out.println(N/P + " " + pen);
    }
}