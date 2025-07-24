import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        // N개의 수 오름차순 정렬
        // 입력 : 수의 갯수 N, N개의 줄에 수

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];
        for( int i = 0; i < N; i++ ) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(arr);
        for( int i : arr ) {
            sb.append(i).append("\n");
        }
        System.out.println(sb);
    }
}
