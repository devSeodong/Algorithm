package BOJ;

import java.io.*;
import java.util.*;

public class gold4_23889_돌굴러가유 {
    public static void main(String[] args) throws IOException {
        // 돌 -> 모두 왼쪽에서 오른쪽으로 계속 굴러감
        // 굴러가기 시작하는 마을의 모래성부터 부숨
        // 벽이 설치된 마을부터 모래성 못 부숨
        // 벽을 어디에 설치해야 가장 많은 모래성을 지킬 수 있을까

        // 입력 : N, M, K, 모래성 개수, 돌이 굴러가기 시작하는 마을의 위치, 돌의 위치 중복 없음
        // 출력 : 가장 많은 모래성을 지키기 위해 벽을 설치해 할 마을의 위치 오름차순 출력 ( 사전순으로 가장 빠른 답 출력 )

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()); // 마을 개수
        int M = Integer.parseInt(st.nextToken()); // 벽의 개수
        int K = Integer.parseInt(st.nextToken()); // 돌의 개수

        int[] cas = new int[N]; // 각 마을의 모래성 개수 저장
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++) {
            cas[i] = Integer.parseInt(st.nextToken());
        }

        int[] stone = new int[K]; // 돌이 시작하는 마을 위치 저장
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<K; i++) {
            stone[i] = Integer.parseInt(st.nextToken());
        }

        // 각 돌의 위치부터 다음 돌 위치까지 파괴될 모래성 수
        List<int[]> list = new ArrayList<>();
        for(int i=0; i<K-1; i++) { // 마지막 돌은 다음 돌이 없으니 K-2까지만 처리
            int sum = 0; // 현재 구간에서 부서질 모래성 합
            int s = stone[i] -1; // 시작 인덱스
            int e = stone[i+1] -1; // 다음 돌 시작 인덱스

            for(int j=s; j<e; j++) { // 모래성 합 계산
                sum += cas[j]; // 모래성 누적
            }

            list.add(new int[]{stone[i], sum}); // 돌 시작 마을 번호와 구간합 저장
        }

        int lastSum = 0; // 마지막 돌 구간의 합
        for(int i=stone[K-1]-1; i<N; i++) { // 마지막 돌 시작 인덱스부터 끝까지
            lastSum += cas[i]; // 누적 합
        }
        list.add(new int[]{stone[K-1], lastSum});

        // 파괴된 모래성 기준 내림차순 -> 사전순
        list.sort((i, j)-> {
            int res = Integer.compare(j[1], i[1]);
            if(res == 0) {
                return Integer.compare(j[0], i[0]);
            }
            return res;
        });

        List<Integer> ans = new ArrayList<>(); // 벽을 설치할 마을 번호 M개를 담을 리스트
        for(int i=0; i<M; i++) {
            ans.add(list.get(i)[0]);
        }

        Collections.sort(ans);
        for(int i : ans) {
            System.out.println(i);
        }
    }
}
