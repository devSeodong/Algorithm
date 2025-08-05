import java.io.*;
import java.util.*;

class Main {
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        Set<Integer> v = new HashSet<Integer>();
        int s = n;

        while (true) {
            if (s == 1) {
                System.out.println("HAPPY");
                break;
            }
            if (v.contains(s)) {
                System.out.println("UNHAPPY");
                break;
            }
            v.add(s);
            s = num(s);
        }
    }

    public static int num(int k) {
        int s = 0;
        while (k > 0) {
            s += (k%10)*(k%10);
            k /= 10;
        }
        return s;
    }
}