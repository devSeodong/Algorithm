package BOJ; // (백준 제출 시엔 보통 제거) 로컬 패키지 선언

import java.io.*;   // 입력/출력 관련 클래스 사용
import java.util.*; // StringTokenizer, List, ArrayList 등 사용

public class gold4_30516_커플파괴자민욱 { // 클래스 선언
    public static void main(String[] args) throws IOException { // 메인 시작, IOException 던짐
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); // 빠른 입력

        int N = Integer.parseInt(br.readLine().trim()); // 사람 수 N 입력
        int[] arr = new int[N+1]; // 1-indexed로 사람 정보 저장 (0번 인덱스는 미사용)

        StringTokenizer st = new StringTokenizer(br.readLine()); // 두 번째 줄 토큰화
        for (int i=1; i<=N; i++) { // 1..N까지 입력
            arr[i] = Integer.parseInt(st.nextToken()); // i번째 사람 정보 저장
        }

        // 예외: N=2인데 둘이 같은 커플(0이 아니고 값 동일)이면 어떤 방식으로도 이웃을 피할 수 없음
        if (N==2 && arr[1]!=0 && arr[1]==arr[2]) {
            System.out.println(-1); // 불가능 출력
            return; // 종료
        }

        List<Integer> cuts = new ArrayList<>(); // '반드시 잘라야 하는' 절단 위치 목록 저장
        for (int i=1; i<=N-1; i++) { // 인접한 쌍 (i, i+1) 검사
            // 인접한 두 사람이 같은 커플 번호(0이 아님)이면 같은 묶음에 두면 내부에서 이웃이 확정이므로 반드시 절단
            if (arr[i]!=0 && arr[i]==arr[i+1]) {
                cuts.add(i); // i와 i+1 사이를 자른다 (구간 [start..i] 종료)
            }
        }

        List<Integer> len = new ArrayList<>(); // 각 묶음(연속 구간)의 길이 저장
        int start = 1; // 현재 묶음 시작 인덱스
        for (int cut : cuts) { // 절단 지점마다
            len.add(cut-start+1); // [start..cut] 길이를 묶음 길이로 추가
            start = cut+1; // 다음 묶음 시작은 cut+1
        }
        len.add(N-start+1); // 마지막 묶음 [start..N] 길이 추가

        int M = len.size(); // 현재 묶음 개수

        // 특수 케이스: 묶음이 2개일 때는 순서를 바꿔도 양 끝-시작이 커플이 되어 막히는 경우가 존재
        if (M == 2) {
            int len1 = len.get(0); // 1번 묶음 길이
            int firstOfFirst = arr[1]; // 1번 묶음의 첫 사람 값 (전체 첫 사람)
            int lastOfSecond = arr[N]; // 2번 묶음의 마지막 사람 값 (전체 마지막 사람)

            // 첫 사람과 마지막 사람이 같은 커플(0 아님)이라면
            // 1-2 배치 시 (cut 경계)에서 커플이웃이 생기고, 2-1 배치 시 (끝-처음)에서 커플이웃이 생길 수 있음
            if (firstOfFirst != 0 && firstOfFirst == lastOfSecond) {
                // 해결: 한 묶음을 더 쪼개서 3묶음으로 만들어 배치 자유도를 늘림
                if (len1 >= 2) { // 첫 묶음이 2 이상이면 첫 묶음을 1명 + 나머지로 분할
                    int len2 = len.get(1); // 두 번째 묶음 길이
                    len.clear(); // 기존 길이 리스트 초기화
                    len.add(1); // 첫 사람만 별도 묶음
                    len.add(len1-1); // 나머지 (기존 첫 묶음의 뒤쪽)
                    len.add(len2); // 기존 두 번째 묶음 그대로
                } else { // 첫 묶음 길이가 1이면 두 번째 묶음을 분할
                    int len2 = len.get(1); // 두 번째 묶음 길이
                    len.clear(); // 초기화
                    len.add(1); // 첫 묶음 그대로
                    len.add(1); // 두 번째 묶음에서 앞 1명을 떼어 새 묶음
                    len.add(len2-1); // 두 번째 묶음의 나머지
                }
                M = len.size(); // 묶음 수 갱신 (3이 됨)
            }
        }

        StringBuilder sb = new StringBuilder(); // 출력 누적
        sb.append(M).append('\n'); // 1줄: 최소 M 출력

        // 2줄: 각 묶음의 사람 수(길이) 출력
        for (int i=0; i<M; i++) {
            if (i > 0) sb.append(' '); // 공백 구분
            sb.append(len.get(i)); // i번째 묶음 길이
        }
        sb.append('\n');

        // 3줄: 묶음 순서 출력 (역순: M, M-1, ..., 1)
        for (int i=M; i>=1; i--) {
            if (i < M) sb.append(' '); // 공백 구분
            sb.append(i); // 묶음 번호 출력
        }
        sb.append('\n');

        System.out.print(sb); // 최종 출력
    }
}
