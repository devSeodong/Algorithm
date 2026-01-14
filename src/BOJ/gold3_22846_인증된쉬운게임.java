package BOJ;

import java.io.*;
import java.util.*;

public class gold3_22846_인증된쉬운게임 {
    public static void main(String[] args) throws IOException{
        // 칼리와 링고
        // 모니터에 써있는 수의 약수를 하나 선택해 모니터에 있는 값에 더함
        // 제한 K를 초과한 사람이 패배

        // 입력 : 자연수 K
        // 출력 : 칼리가 이기면 Kali, 링고가 이기면 Ringo

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int K = Integer.parseInt(br.readLine());
        boolean[] dp = new boolean[K+1]; // 현재 숫자가 i면 현재 차례인 사람이 이길 수 있는지

        // i에서 i+d로 이동하므로 큰값부터
        for(int i=K; i>=1; i--){
            // 약수 탐색은 sqrt(i)까지만
            for(int j=1; j*j<=i; j++){
                if(i%j == 0) {
                    // 약수 j 사용
                    // i+j<=K 이면서
                    // 그 상태가 패배 상태(dp[i+j]가 false)라면
                    // 현재 i는 승리 상태
                    if(i+j<=K && !dp[i+j]) {
                        dp[i] = true;
                        break;
                    }

                    // 짝 약수 i/j 사용
                    if(i+i/j <=K && !dp[i+i/j]) {
                        dp[i] = true;
                        break;
                    }
                }
            }
        }

        System.out.println(dp[1]?"Kali" : "Ringo");
    }
}
