import java.io.*;
import java.util.StringTokenizer;

public class Main {
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] arr = new int[N];
        for (int n = 0; n < N; n++) {
            arr[n] = Integer.parseInt(st.nextToken());
        }

        int count = 0;
        for (int i = 0; i < N; i++) {
            if (isPrime(arr[i])) {
                count++;
            }
        }
        System.out.println(count);
    }

    public static boolean isPrime(int z) {
        if(z<2) return false;
        for (int i = 2; i <= Math.sqrt(z); i++) {
            if (z % i == 0) return false;
        }
        return true;
    }
}