package BOJ;

import java.io.*;
import java.util.*;

public class gold3_9466_텀프로젝트 {

    static int N, tmp; // tmp : 현재 dfs 시작점에서 찾은 사이클에 포함된 노드 수
    static int[] arr; // i가 선택한 학생
    static List<Integer> list; // dfs 경로 저장
    static boolean[] V;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        for (int t = 0; t < T; t++) {
            N = Integer.parseInt(br.readLine());
            arr = new int[N+1];
            V = new boolean[N+1];

            st = new StringTokenizer(br.readLine());
            for (int i=1; i<=N; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
            }

            int ans = 0;
            for (int i = 1; i <= N; i++) { // 모든 학생을 시작점으로
                if (!V[i]) {
                    list = new ArrayList<>(); // 현재 dfs 경로 리스트 초기화
                    dfs(i, 0); // i부터 dfs, 현재 깊이 cnt = 0
                    ans += tmp; // 이번 탐색에서 찾은 사이클 크기 누적
                }
            }
            System.out.println(N-ans); // 전체 - 팀 = 팀에 못든 학생 수
        }
    }

    static void dfs(int d, int cnt) {
        if (V[d]) { // 이미 방문한 노드 다시 만났으면
            if (!list.contains(d)) { // 현재 경로 안에 없으면
                tmp = 0; // 새로운 사이클이 아니니까 0, 종료
                return;
            }
            tmp = cnt - list.indexOf(d); // 현재 경로에서 d가 처음 나온 위치부터 사이클
            return; // 종료
        }

        V[d] = true;
        list.add(d);
        dfs(arr[d], cnt +1);
    }
}