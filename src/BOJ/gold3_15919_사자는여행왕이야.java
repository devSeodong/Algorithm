package BOJ;

import java.io.*;
import java.util.*;

public class gold3_15919_사자는여행왕이야 {
	static int N, M;
	static List<int[]> list;
	static int[] dp;
	public static void main(String[] args) throws IOException {
		// 어느 곳을 어떤 기간 동안 갈 수 있는지를 조사한 목록 
		// 선택한 여행지 간 기간이 겹치는 경우는 없어야 함
		
		// 입력 : N, M, 여행 정보 
		// 출력 : 여행중이 아닌 기간의 최댓값이 최소 
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		
		list = new ArrayList<>();
		dp = new int[M]; // 각 구간을 마지막으로 썼을 때의 결과
		Arrays.fill(dp, -1); // 아직 계산 안 된 상태를 -1로 표시
		
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			list.add(new int[] {a, b}); // [시작, 끝] 형태로 저장
		}
		
		// 시작일 기준 오름차순 정렬
		list.sort((a, b)-> a[0]-b[0]);
		
		int res = Integer.MAX_VALUE; // 최대 공백 일수를 최소화한 값
		// i번째 구간을 첫번째로 선택하는 구간이라고 가정
		for(int i=0; i<M; i++) {
			int t = list.get(i)[0]-1; // 1일 ~ list.get(i)[0]까지의 공백 일수
			
			// 첫 구간 앞의 공백과, i번째 구간을 시작으로 이후에 최적으로 고른경우의 최대 공백 중 더 큰 값
			res = Math.min(res, Math.max(t, solve(i)));
		}
		
		System.out.println(res);
	}
	
	// n번째 구간을 마지막으로 선택한 상태에서
	// 이후에 선택할 구간들을 최적으로 골랐을 때의 최소 가능한 최대 공백 리턴
	public static int solve(int n) {
		if(dp[n] != -1) return dp[n]; // 이미 계산한 값이면 재사용
		
		int res = Integer.MAX_VALUE;
		
		// n이후의 구간들 중에서, 겹치지 않는 구간만 선택 후보
		for(int i=n+1; i<M; i++) {
			// 시작일이 현재 구간의 종료일 이하라면 겹치는 구간 
			int currE = list.get(n)[1];
			int nextS = list.get(i)[0];
			if(nextS <= currE) continue;
			
			
			//안냐세여 저는 서울 14반 귀염둥이 김서형입니당!!!!
			//이제 이 키보드는 지원이꺼에용!!
			
			
			
			
			
			
			
			// 두 구간 사이의 공백 
			// currE = 5, nextS = 8이면 6, 7 두 날이 공백
			int t = nextS - currE;
			
			// 이 사이 공백(t-1)과, i번째 구간을 선택한 뒤 이후 최적 선택에서의 최대 공백(solve(i)) 중 큰 값
			res = Math.min(res, Math.max(t-1, solve(i)));
		}
		
		res = Math.min(res, N-list.get(n)[1]);
		return dp[n] = res;
	}
}
