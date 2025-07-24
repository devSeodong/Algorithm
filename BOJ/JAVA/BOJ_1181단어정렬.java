import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        // 알파벳 소문자로 이루어진 N개의 단어
        // 길이가 짧은 것부터 , 길이가 같으면 사전 순으로 정렬
        // 중복 단어 제거

        // 입력 : 단어의 개수 N , 알파벳 소문자 N개의 단어
        // 정렬하여 출력

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        String[] arr = new String[N];
        for(int n = 0; n<N; n++) {
            arr[n] = br.readLine();
        }

        Arrays.sort(arr, new Comparator<String>() {
            public int compare(String o1, String o2) {
                if(o1.length()==o2.length()) {
                    return o1.compareTo(o2);
                } else {
                    return o1.length()-o2.length();
                }
            }
        });

        StringBuilder sb = new StringBuilder();
        sb.append(arr[0]).append("\n");

        for(int i=1; i<N; i++) {
            if (!arr[i].equals(arr[i-1])) {
                sb.append(arr[i]).append("\n");
            }
        }

        System.out.println(sb);
    }
}
