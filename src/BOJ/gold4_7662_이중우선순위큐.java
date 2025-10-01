package BOJ;

import java.io.*;
import java.util.*;

public class gold4_7662_이중우선순위큐 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        for(int t = 0; t< T; t++) {
            int K = Integer.parseInt(br.readLine());

            TreeMap<Integer, Integer> map = new TreeMap<>();
            for(int i = 0; i< K; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                String N = st.nextToken();

                if (N.equals("I")) {
                    int num = Integer.parseInt(st.nextToken());
                    map.put(num, map.getOrDefault(num, 0)+1);
                } else {
                    if(map.isEmpty()) continue;
                    int type = Integer.parseInt(st.nextToken());
                    int num = (type == 1) ? map.lastKey() : map.firstKey();

                    if(map.put(num, map.get(num)-1) == 1) {
                        map.remove(num);
                    }
                }
            }

            if (map.isEmpty()) {
                sb.append("EMPTY\n");
            } else {
                sb.append(map.lastKey()).append(" ").append(map.firstKey()).append("\n");
            }

        }
        System.out.println(sb);
    }
}