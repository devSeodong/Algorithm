//OX퀴즈의 결과가 주어졌을 때, 점수를 구하는 프로그램을 작성하시오.

import java.io.*;

public class Main {
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for(int t = 0; t<T; t++) {
            String r = br.readLine();

            int cnt = 0;
            int result = 0;

            for (int i = 0; i < r.length(); i++) {
                int ascii = r.charAt(i);
                if(ascii == 79) {
                    cnt++;
                } else if (ascii == 88) {
                    cnt = 0;
                }
                result += cnt;
            }

            System.out.println(result);
        }
    }
}