package BOJ;

import java.io.*;
import java.util.*;

public class gold5_1501_영어읽기 {
    public static void main(String[] args) throws IOException{
        // 각각의 단어들은 제일 첫 문자와 제일 끝 문자를 제외하고는 순서가 뒤섞여 있다
        // 영어 문장이 주어졌을 때, 그 문장을 해석하는 방법의 경우의 수

        // 입력 : N, 단어들, M, 문장
        // 출력 : M개의 줄에 문장을 해석하는 경우의 수 출력

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        HashMap<String, Integer> map = new HashMap<>();
        String str;
        for(int i=0; i<N; i++){
            String a = br.readLine();
            if(a.length() > 2) {
                str = solve(a);
            } else {
                str = a;
            }
            map.put(str, map.getOrDefault(str, 0) + 1);
        }

        StringBuilder sb = new StringBuilder();
        int M = Integer.parseInt(br.readLine());
        for(int i=0; i<M; i++) {
            String[] strs = br.readLine().split(" ");
            int res = 1;
            for(int j=0; j<strs.length; j++) {
                String temp = strs[j];
                if(temp.length() > 2) {
                    str = solve(temp);
                } else {
                    str = temp;
                }

                int num = map.get(str) == null ? 0 : map.get(str);
                res *= num;
            }
            sb.append(res).append("\n");
        }

        System.out.println(sb);
    }

    public static String solve(String str) {
        if (str.length() <= 2) {
            return str;
        }
        char first = str.charAt(0);
        char last = str.charAt(str.length() - 1);
        char[] mid = str.substring(1, str.length() - 1).toCharArray();
        Arrays.sort(mid);

        StringBuilder sb = new StringBuilder().append(first).append(mid).append(last);
        return sb.toString();
    }
}
