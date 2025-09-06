import java.io.*;
import java.util.*;

public class Main {
	// 사진틀에 올라가는 학생 한 명을 나타내는 클래스
	// Comparable을 구현해서 "추천 수(cnt) 오름차순 → 오래된(t) 순"으로 정렬 기준을 고정
	static class F implements Comparable<F>{
		int num; // 학생 번호
		int cnt; // 추천 받은 횟수
		int t;   // 게시된 시각(작을수록 더 오래됨)

		F(int num, int t) {
			this.num = num;
			this.cnt = 1; // 새로 올라올 때 추천 수는 1로 시작
			this.t = t;
		}

		@Override
		public int compareTo(F o) {
			// 1순위: 추천 수가 더 적은 학생이 "작다"
			if (this.cnt != o.cnt) {
				return Integer.compare(this.cnt, o.cnt);
			}
			// 2순위: 추천 수가 같으면 더 오래된(작은 t) 학생이 "작다"
			return Integer.compare(this.t, o.t);
		}
	}

	public static void main(String[] args) throws IOException {
		// 규칙 정리
		// 1) 사진틀은 N칸. 처음엔 비어 있음
		// 2) 추천이 들어오면
		//    - 이미 걸린 학생이면 cnt만 +1
		//    - 없고 자리가 남으면 바로 올림
		//    - 없고 자리가 없으면: 추천 수 가장 적고, 그 중 가장 오래된 학생을 내리고 새 학생을 올림
		// 3) 최종적으로 사진틀에 남아 있는 학생 번호를 오름차순 출력

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine()); // 사진틀 개수
		int M = Integer.parseInt(br.readLine()); // 총 추천 횟수
		StringTokenizer st = new StringTokenizer(br.readLine()); // 추천 받은 학생 번호들

		// 현재 사진틀 상태
		List<F> frame = new ArrayList<>();
		// 학생 번호 -> 해당 객체(F) 빠르게 찾기 위한 맵
		Map<Integer, F> map = new HashMap<>();

		for (int i = 0; i < M; i++) {
			// 추천을 한 번씩 처리 (i는 "게시된 시각"으로도 사용)
			int num = Integer.parseInt(st.nextToken());

			// 이미 사진에 걸려 있는 학생이면 추천 수만 증가
			F f = map.get(num);
			if (f != null) {
				f.cnt++;
				continue;
			}

			// 사진에 없고, 자리가 남아 있으면 새로 올림
			if (frame.size() < N) {
				F f2 = new F(num, i); // 현재 추천 시각 i를 게시 시각으로 기록
				frame.add(f2);
				map.put(num, f2);
				continue;
			}

			// 사진이 꽉 찼으면 내릴 학생을 고름
			// Comparable 기준(추천 수 ↑, 오래된 순)으로 가장 작은 요소를 뽑아 제거
			F r = Collections.min(frame);
			frame.remove(r);
			map.remove(r.num);

			// 그 자리에 새 학생을 올림 (추천 수 1, 게시 시각 i)
			F f2 = new F(num, i);
			frame.add(f2);
			map.put(num, f2);
		}

		// 사진틀에 남은 학생 번호를 오름차순으로 출력
		List<Integer> res = new ArrayList<>();
		for (F f : frame) {
			res.add(f.num);
		}
		Collections.sort(res);

		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < res.size(); i++) {
			if (i > 0) sb.append(' ');
			sb.append(res.get(i));
		}
		System.out.println(sb);
	}
}
