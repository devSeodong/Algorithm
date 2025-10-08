package BOJ;

import java.io.*;
import java.util.*;

public class gold5_16987_계란으로계란치기 {
	static int N, res;
	static int[][] eggs;

	public static void main(String[] args) throws NumberFormatException, IOException {
		// 가장 왼쪽의 계란을 든다
		// 깨지지 않은 계란 중에서 하나를 친다
		// ( 손에 든 계란이 깨졌거나 칠 계란이 없으면 넘어감 )
		// 가장 최근에 든 계란이 가자 ㅇ오른쪽이라면 종료

		// 입력 : 계란의 수 N, 계란 정보
		// 출력 : 깰 수 있는 계란의 최대 개수

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		eggs = new int[N][2];

		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			eggs[i][0] = Integer.parseInt(st.nextToken());
			eggs[i][1] = Integer.parseInt(st.nextToken());
		}

		dfs(0, 0);
		System.out.println(res);
	}

	public static void dfs(int d, int c) {
		if(d==N) {
			res = Math.max(res, c);
			return;
		}

		if(eggs[d][0]<=0 || c==N-1) {
			dfs(d+1, c);
			return;
		}

		int s = eggs[d][1];
		for(int i=0; i<N; i++) {
			if(d==i || eggs[i][0] <=0) continue;

			int s2 = eggs[i][1];
			eggs[d][0] -= s2;
			eggs[i][0] -= s;

			if(eggs[d][0] <= 0 && eggs[i][0]<=0) {
				dfs(d+1, c+2);
			} else if(eggs[d][0] > 0 && eggs[i][0] > 0){
				dfs(d+1, c);
			} else {
				dfs(d+1, c+1);
			}

			eggs[d][0] += s2;
			eggs[i][0] += s;
		}
	}
}
