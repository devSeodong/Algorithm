import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;
 
public class Solution {
    public static void main(String[] args) throws IOException {
        // '()', '[]', '{}', '<>'로 이루어진 문자열 짝이 모두 맞는지 판별
 
        // 입력 : 테케 길이, 테케 문자열
        // 출력 : #T, 공백 유효성 여부 1 / 0
 
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Map<Character, Character> RL = new HashMap<Character, Character>();
        RL.put(')', '(');
        RL.put(']', '[');
        RL.put('}', '{');
        RL.put('>', '<');
         
        for (int t = 1; t <= 10; t++) {
            br.readLine();
            String T = br.readLine();
 
            Deque<Character> dq = new ArrayDeque<>();
            boolean isOk = true;
 
            for (char c : T.toCharArray()) {
                if (RL.containsValue(c)) {
                    dq.push(c);
                } else if (RL.containsKey(c)) {
                    if (dq.isEmpty() || dq.peek() != RL.get(c)) {
                        isOk = false;
                        break;
                    }
                    dq.pop();
                }
            }
 
            int res = isOk && dq.isEmpty() ? 1 : 0;
            System.out.println("#" + t + " " + res);
        }
    }
}