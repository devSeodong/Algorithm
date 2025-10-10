package BOJ;

import java.io.*;
import java.util.*;

public class silver1_1991_트리순회 {
    static int N;
    static int[] L = new int[26];
    static int[] R = new int[26];  // 각 노드의 오른쪽 자식 인덱스 (없으면 -1)
    static StringBuilder pre = new StringBuilder();   // 전위 순회 결과
    static StringBuilder in  = new StringBuilder();   // 중위 순회 결과
    static StringBuilder post= new StringBuilder();   // 후위 순회 결과

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); // 빠른 입력
        N = Integer.parseInt(br.readLine()); // 첫 줄: 노드 개수

        Arrays.fill(L, -1);
        Arrays.fill(R, -1);

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            char p = st.nextToken().charAt(0);
            char lc = st.nextToken().charAt(0);
            char rc = st.nextToken().charAt(0);
            int pi = p - 'A';
            if (lc != '.') L[pi] = lc - 'A';
            if (rc != '.') R[pi] = rc - 'A';
        }

        int root = 0;

        preorder(root);
        inorder(root);
        postorder(root);

        System.out.println(pre.toString());
        System.out.println(in.toString());
        System.out.println(post.toString());
    }

    // 전위: (루트) (왼쪽) (오른쪽)
    static void preorder(int u) {
        if (u == -1) return;                 // 공백(없음)인 경우 종료
        pre.append((char) (u + 'A'));        // 1) 루트 기록
        preorder(L[u]);                      // 2) 왼쪽 서브트리
        preorder(R[u]);                      // 3) 오른쪽 서브트리
    }

    // 중위: (왼쪽) (루트) (오른쪽)
    static void inorder(int u) {
        if (u == -1) return;
        inorder(L[u]);                       // 1) 왼쪽
        in.append((char) (u + 'A'));         // 2) 루트
        inorder(R[u]);                       // 3) 오른쪽
    }

    // 후위: (왼쪽) (오른쪽) (루트)
    static void postorder(int u) {
        if (u == -1) return;
        postorder(L[u]);                     // 1) 왼쪽
        postorder(R[u]);                     // 2) 오른쪽
        post.append((char) (u + 'A'));       // 3) 루트
    }
}
