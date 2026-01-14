package BOJ;

import java.io.*;
import java.util.*;

public class gold4_1633_최고의팀만들기 {
    static int[] W, B;
    static int[][][] dp;
    public static void main(String[] args) throws IOException{
        // 흑 15명, 백 15명 = 총 30명
        // 흑 / 백 중 한 가지만으로 참여
        // 팀 전체 능력치 = 흑 + 백 능력치

        // 입력 : 각 플레이어드르이 능력치
        // 출력 : 만들 수 있는 팀 중 가장 큰 능력치

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        W = new int[1001]; // 백 능력치 ( 최대 1000명 )
        B = new int[1001]; // 흑 능력치

        int N = 0;
        String str;
        while((str = br.readLine())!=null){
            N++;
            st = new StringTokenizer(str);
            W[N] = Integer.parseInt(st.nextToken());
            B[N] = Integer.parseInt(st.nextToken());
        }

        // i명까지 고려했을 때 백 w명 흑 b명을 채우는 최대 능력치
        dp = new int[N+1][16][16];

        for (int i=0; i<=N; i++) {
            for (int w=0; w<=15; w++) {
                Arrays.fill(dp[i][w], -1);
            }
        }

        System.out.println(dfs(N, 15, 15));
    }

    public static int dfs(int i, int w, int b) {
        // i : 선수 인덱스, w : 앞으로 뽑을 백 인원, b : 앞으로 뽑을 흑 인원
        if(i==0) return 0; // 더이상 고려할 선수가 없으면 끝
        if(w==0 && b==0) return 0; // 백/흑 인원 다 채웠으면 끝
        if(dp[i][w][b] != -1) return dp[i][w][b]; // 이미 계산해쓰면 끝

        int ans = 0;
        // i번째 선수가 선택되지 않는다면
        ans = Math.max(ans, dfs(i-1, w, b));
        // i번째 선수가 백팀에 들어가면
        if(w>0) ans = Math.max(ans, dfs(i-1, w-1, b)+W[i]);
        // i번째 선수가 흑팀에 들어가면
        if(b>0) ans = Math.max(ans, dfs(i-1, w, b-1)+B[i]);

        dp[i][w][b] = ans;
        return ans;
    }
}
