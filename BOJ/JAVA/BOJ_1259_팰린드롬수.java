import java.io.*;
import java.util.*;

public class Main {
    public static void main(String args[]) throws IOException {
        // 입력 : 각 줄마다 1<= <=99999 , 입력 마지막 줄 0
        // 출력 : 팰린드롬수 -> yes / no

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String N = br.readLine();

        while(!N.equals("0")) {
            StringBuilder R = new StringBuilder();

            String[] t = N.split("");
            for(int i=t.length-1; i>=0; i--) {
                R.append(t[i]);
            }

            if (N.contentEquals(R)) {
                System.out.println("yes");
            } else {
                System.out.println("no");
            }
            N = br.readLine();
        }
    }
}
