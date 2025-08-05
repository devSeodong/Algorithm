import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Main {
    // list , 인덱스 방법을 써도 됨
    public static void main(String[] args) throws IOException {
        // N개의 수 오름차순 정렬
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        ArrayList<Integer> arr = new ArrayList<>();
        for(int i =0; i<N; i++){
            arr.add(Integer.parseInt(br.readLine()));
        }

        Collections.sort(arr);

        for (int i : arr) {
            bw.write(i+"\n");
        }

        bw.flush();
    }
}
