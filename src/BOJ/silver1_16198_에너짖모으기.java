package BOJ;

import java.io.*;
import java.util.*;

public class silver1_16198_에너짖모으기 {
    static int N, res;
    static List<Integer> list ;
    public static void main(String[] args) throws IOException {
        // 에너지 구슬
        // 에너지 구슬 하나 고름 -> x ( 첫번째 마지막 고를 수 없음 )
        // x번째 에너지 구슬 제거
        // 이전 에너지 이후 에너지 곱한 에너지를 모을 수 있음
        // N을 감소, 1부터 N까지 다시 번호 매김

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        list = new ArrayList<>();
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++) {
            list.add(Integer.parseInt(st.nextToken()));
        }

        res = -1;
        dfs(0);
        System.out.println(res);
    }

    public static void dfs(int d) {
        if(list.size() == 2) {
            res = Math.max(res, d);
            return;
        }

        for(int i=1; i<list.size()-1; i++) {
            int temp = list.get(i);
            int a = list.get(i-1)*list.get(i+1);
            list.remove(i);
            dfs(d+a);
            list.add(i, temp);
        }
    }
}
