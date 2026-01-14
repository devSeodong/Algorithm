package BOJ;

import java.io.*;

public class gold5_6568_귀도반로썸 {

    static int PC, add;                 // PC: 프로그램 카운터(5비트, 0~31), add: 가산기(8비트, 0~255)
    static int[] Memory = new int[32];  // 32칸 메모리, 각 칸은 1바이트(0~255)

    // 명령어 코드 상수 (가독성용, 실제 switch에서는 직접 숫자 사용)
    static int STA = 0, LDA = 1, BEQ = 2, NOP = 3, DEC = 4, INC = 5, JMP = 6, HLT = 7;

    public static void main(String[] args) throws IOException{

        // 32바이트 메모리
        // 8비트 가산기(add)
        // 5비트 프로그램 카운터(PC)
        // 명령어 1바이트: 상위 3비트 = opcode, 하위 5비트 = operand
        // 명령어 실행 전에 PC를 먼저 1 증가
        // 초기 상태: PC = 0, add = 0

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();   // 모든 테스트케이스 출력 누적
        String str = "";

        // 테스트케이스 개수 미정 → EOF까지 반복
        while(true) {

            PC = 0;      // 테스트케이스 시작 시 PC 초기화
            add = 0;     // 가산기 초기화

            // 메모리 32칸 입력
            for(int i=0; i<32; i++) {
                str = br.readLine();

                // EOF면 지금까지 출력하고 프로그램 종료
                if(str == null) {
                    System.out.println(sb);
                    return;
                }

                // 2진 문자열을 정수로 변환해 메모리에 저장
                Memory[i] = Integer.parseInt(str, 2);
            }

            boolean end = true; // HLT를 만날 때까지 실행

            while(end) {

                int mem = Memory[PC];    // 현재 PC가 가리키는 명령어 fetch
                PC = (PC+1) % 32;        // 명령어 실행 전에 PC 1 증가 (5비트 순환)

                // opcode = 상위 3비트 (mem / 32 와 동일)
                switch(mem / 32) {

                    case 0: // STA
                        // add 값을 메모리[operand]에 저장
                        Memory[mem % 32] = add;
                        break;

                    case 1: // LDA
                        // 메모리[operand] 값을 add로 로드
                        add = Memory[mem % 32];
                        break;

                    case 2: // BEQ
                        // add가 0이면 PC를 operand로 이동
                        if(add == 0) PC = mem % 32;
                        break;

                    case 3: // NOP
                        // 아무 동작 없음
                        break;

                    case 4: // DEC
                        // add = add - 1 (8비트 overflow 처리)
                        add = (add + 255) % 256;
                        break;

                    case 5: // INC
                        // add = add + 1 (8비트 overflow 처리)
                        add = (add + 1) % 256;
                        break;

                    case 6: // JMP
                        // 무조건 operand 주소로 점프
                        PC = mem % 32;
                        break;

                    case 7: // HLT
                        // 프로그램 종료
                        // add 값을 8비트 이진수로 출력
                        for(int i = 7; i >= 0; i--) {
                            sb.append((add >> i) & 1);
                        }
                        sb.append("\n");

                        end = false; // 현재 테스트케이스 종료
                        break;
                }
            }
        }
    }
}
