package BOJ;

import java.io.*;
import java.util.*;

public class gold5_19598_최소회의실개수 {
    public static void main(String[] args) throws IOException {
        // N개의 회의를 모두 진행할 수 있는 최소 회의실 개수
        // 시작시간, 끝나는 시간, 두개의 회의 동시 진행 불가

        // 입력 : 배열 크기 N, 시작시간, 끝나는 시간
        // 출력 : 최소 회의실 개수 출력

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st;

        int[][] arr = new int[N][2];
        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            arr[i][0] = Integer.parseInt(st.nextToken());
            arr[i][1] = Integer.parseInt(st.nextToken());
        }

        // 시작시간 오름차순, 같으면 종료시간 오름차순
        Arrays.sort(arr, (a, b) ->{
            if(a[0] == b[0]) return Integer.compare(a[1], b[1]);
            return Integer.compare(a[0], b[0]);
        });

        PriorityQueue<Integer> pq = new PriorityQueue<>();
        int res = 0; // 동시에 필요한 최대 회의실 수
        for(int i=0; i<N; i++) {
            int s = arr[i][0];
            int e = arr[i][1];

            // 현재 시작시간 이상으로 끝는 회의들 pq에서 빼기
            while (!pq.isEmpty() && pq.peek() <= s) {
                pq.poll(); // 제일 빨리 끝난 회의 없애기
            }

            pq.add(e);
            // pq 사이즈가 동시에 진행 중인 회의 수
            res = Math.max(res, pq.size());
        }

        System.out.println(res);
    }
}
