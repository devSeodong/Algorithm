package BOJ;

import java.io.*;
import java.util.*;

public class silver1_2531_회전초밥 {
	public static void main(String[] args) throws IOException {
		// 벨트의 임의의 한 위치부터 k개의 접시를 연속해서 먹을 경우 할인된 정액 가격으로 제공
		// 위 행사에 참여할 경우 쿠폰에 적혀진 종류의 초밥 하나를 추가로 무료 제공. 
		
		// 입력 : N, d, k, c, 회전  초밥 정보
		// 출력 : 먹을 수 있는 초밥의 가짓수 최댓값 
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int d = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		int c = Integer.parseInt(st .nextToken());
		
		int[] arr = new int[N];
		for(int i=0; i<N; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}
		
		Map<Integer, Integer> map = new HashMap<>();
		
		// 맨 첫번째로 선택한 초밥들 기본 셋팅
		int cnt = 0;
		for(int i=0; i<k; i++) {
			map.put(arr[i], map.getOrDefault(arr[i], 0)+1);
			if(map.get(arr[i]) == 1) cnt++; // 있는 초밥이 아니면 cnt++
		}
		
		int mapKey = map.containsKey(c)?0 : 1; // 쿠폰이 없으면 +1 
		int max = cnt + mapKey; // 최댓값 갱신
		
		for(int i=1; i<N; i++) { 
			int r = arr[i-1]; // map에서 빠져야할 접시
			 map.put(r, map.get(r)-1); // 빠지는 값 개수 --
			 if(map.get(r) == 0) { // 만약 갯수가 0이면
				 map.remove(r); // 맵에서 삭제 
				 cnt--; // 초밥 종류 갯수 --
			 }
			 
			 int l = arr[(i+k-1)%N]; // 새로 들어와야할 접시
			 map.put(l, map.getOrDefault(l, 0)+1); 
			 if(map.get(l) == 1) cnt++;
			 
			 int cKey = map.containsKey(c)?0:1;
			 int C = cnt + cKey;
			 max = Math.max(max, C);
		}

		System.out.println(max);
	}
}
