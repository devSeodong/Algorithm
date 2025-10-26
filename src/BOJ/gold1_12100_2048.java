package BOJ;

import java.io.*;
import java.util.*;

public class gold1_12100_2048 {
    static int n, res;
    static int[][] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        arr = new int[n][n];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dfs(0);
        System.out.println(res);
    }

    public static void dfs(int cnt) {
        if (cnt == 5) {
            for (int i = 0; i < n; i++)
                for (int j = 0; j < n; j++)
                    res = Math.max(res, arr[i][j]);
            return;
        }

        int[][] copy = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                copy[i][j] = arr[i][j];
            }
        }

        for (int i = 0; i < 4; i++) {
            solve(i);
            dfs(cnt + 1);
            for (int r = 0; r < n; r++) {
                for (int c = 0; c < n; c++) {
                    arr[r][c] = copy[r][c];
                }
            }
        }
    }

    public static void solve(int dir) {
        switch (dir) {
            case 0: // 위로 이동
                for (int i = 0; i < n; i++) {
                    int idx = 0;
                    int block = 0;
                    for (int j = 0; j < n; j++) {
                        if (arr[j][i] != 0) {
                            if (block == arr[j][i]) {
                                arr[idx - 1][i] = block * 2;
                                block = 0;
                                arr[j][i] = 0;
                            } else {
                                block = arr[j][i];
                                arr[j][i] = 0;
                                arr[idx][i] = block;
                                idx++;
                            }
                        }
                    }
                }
                break;
            case 1: // 아래로 이동
                for (int i = 0; i < n; i++) {
                    int idx = n - 1;
                    int block = 0;
                    for (int j = n - 1; j >= 0; j--) {
                        if (arr[j][i] != 0) {
                            if (block == arr[j][i]) {
                                arr[idx + 1][i] = block * 2;
                                block = 0;
                                arr[j][i] = 0;
                            } else {
                                block = arr[j][i];
                                arr[j][i] = 0;
                                arr[idx][i] = block;
                                idx--;
                            }
                        }
                    }
                }
                break;
            case 2: // 왼쪽 이동
                for (int i = 0; i < n; i++) {
                    int idx = 0;
                    int block = 0;
                    for (int j = 0; j < n; j++) {
                        if (arr[i][j] != 0) {
                            if (block == arr[i][j]) {
                                arr[i][idx - 1] = block * 2;
                                block = 0;
                                arr[i][j] = 0;
                            } else {
                                block = arr[i][j];
                                arr[i][j] = 0;
                                arr[i][idx] = block;
                                idx++;
                            }
                        }
                    }
                }
                break;
            case 3: // 오른쪽 이동
                for (int i = 0; i < n; i++) {
                    int idx = n - 1;
                    int block = 0;
                    for (int j = n - 1; j >= 0; j--) {
                        if (arr[i][j] != 0) {
                            if (block == arr[i][j]) {
                                arr[i][idx + 1] = block * 2;
                                block = 0;
                                arr[i][j] = 0;
                            } else {
                                block = arr[i][j];
                                arr[i][j] = 0;
                                arr[i][idx] = block;
                                idx--;
                            }
                        }
                    }
                }
                break;
        }
    }
}
