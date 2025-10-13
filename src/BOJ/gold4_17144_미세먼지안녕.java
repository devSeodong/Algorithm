package BOJ;

import java.io.*;
import java.util.*;

public class gold4_17144_미세먼지안녕 {
	public static void main(String[] args) throws IOException {
		/* R*C인 격자판의 집 
		 - 1번열 ( 인덱스 0 )에 공기정청기, 크기는 두 행
		 - 1초동안 
		 - 미세먼지 확산
			 모든 미세먼지 네 방향 ( 공기청정기가 있거나, 칸이 없으면 확산 X )
			 확산되는 양 A/5 
			 남은 미세먼지 양 -> A - A/5 * 확산된 방향의 개수
		 - 공기청정기 작동 
		 	위쪽 공기청정기 바람 반시계 방향
		 	아래쪽 공기청정기 바람 시계방향 
		 	바람 불면 미세먼지 바람방향대로 한칸씩 이동
		 	공기청정기로 들어간 미세먼지는 모두 정화
		*/ 
		
		// 입력 : R, C, T, 미세먼지 정보 ( -1 공기청정기 )
		// 출력 : T초가 지난 후 방에 남아있는 미세먼지의 양 
		
		
	}
}
