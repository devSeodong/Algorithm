import java.io.*;
import java.util.*;

public class Main {
    public static void main(String args[]) throws IOException {
        // 입력 : 카드의 개수 N / M, 카드에 쓰여있는 수
        // 출력 : M을 넘지 않으면서 M에 최대한 가까운 카드 3장의 합

        // 카드 세 장의 합이 M을 넘지 않는 한도 내에서, 카드 세 장의 합을 최대한 크게 만드는 게임

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[] nums = new int[N];
        StringTokenizer st2 = new StringTokenizer(br.readLine());
        for (int n = 0; n < N; n++) {
            nums[n] = Integer.parseInt(st2.nextToken());
        }

        int max = 0;

        for (int i = 0; i < N - 2; i++) {
            for (int j = i + 1; j < N - 1; j++) {
                for (int k = j + 1; k < N; k++) {
                    int sum = nums[i] + nums[j] + nums[k];
                    if (sum <= M && sum > max) {
                        max = sum;
                    }
                }
            }
        }

        System.out.println(max);
    }
}