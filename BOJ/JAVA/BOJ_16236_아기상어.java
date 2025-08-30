import java.io.*;
import java.util.*;

public class Main {
	static int N;                  // 공간의 크기 (N×N)
	static int[][] arr;            // 공간 상태를 저장하는 배열
	static PriorityQueue<Shark> pq = new PriorityQueue<>(); // 먹을 수 있는 물고기 후보 우선순위 큐
	static int[] dx = {-1, 1, 0, 0}; // 상, 하, 좌, 우 이동
	static int[] dy = {0, 0, -1, 1};

	// 상어/물고기 위치 및 이동거리 저장 클래스
	public static class Shark implements Comparable<Shark> {
		int x, y, d; // 좌표(x,y), 시작 위치로부터 거리 d

		public Shark(int x, int y, int d) {
			this.x = x;
			this.y = y;
			this.d = d;
		}

		// 우선순위 규칙: 거리 → 위쪽(x 작은) → 왼쪽(y 작은)
		@Override
		public int compareTo(Shark o) {
			if (this.d != o.d) return Integer.compare(this.d, o.d); // 가까운 거리 우선
			else {
				if (this.x == o.x) return Integer.compare(this.y, o.y); // 같은 행 → 왼쪽
				else return Integer.compare(this.x, o.x); // 위쪽 우선
			}
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine()); // 공간 크기 입력
		arr = new int[N][N];
		StringTokenizer st;

		Queue<Shark> queue = new LinkedList<>();
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
				if (arr[i][j] == 9) { // 아기상어의 시작 위치
					arr[i][j] = 0; // 상어 있는 칸은 빈칸으로 처리
					queue.add(new Shark(i, j, 0)); // BFS 시작 좌표 등록
				}
			}
		}

		// 처음 상어 크기는 2로 시작
		bfs(queue, 2);

		int move = 0;       // 총 이동 거리(시간)
		int cnt = 0;        // 현재 크기에서 먹은 물고기 수
		int size = 2;  // 상어 크기

		// 먹을 수 있는 물고기가 남아있는 동안 반복
		while (!pq.isEmpty()) {
			Shark now = pq.poll();     // 가장 우선순위 높은 물고기 선택 (가까움, 위, 왼쪽)
			arr[now.x][now.y] = 0;     // 먹은 칸은 빈칸 처리
			if (++cnt == size) {  // 먹은 횟수가 현재 크기와 같아지면
				cnt = 0;               // 먹은 횟수 초기화
				size++;           // 상어 크기 증가
			}
			move += now.d;             // 이동 시간 누적

			// 새로운 BFS 시작 (먹은 위치에서 다시 탐색)
			queue = new LinkedList<>();
			queue.add(new Shark(now.x, now.y, 0));
			bfs(queue, size);
		}

		// 최종 이동 시간 출력
		System.out.println(move);
	}

	private static void bfs(Queue<Shark> queue, int size) {
		pq = new PriorityQueue<>(); // 새 후보 우선순위 큐 초기화
		boolean[][] v = new boolean[N][N]; // 방문 체크 배열

		while (!queue.isEmpty()) {
			Shark now = queue.poll();
			for (int i = 0; i < 4; i++) {
				int nx = now.x + dx[i];
				int ny = now.y + dy[i];

				// 범위 밖이거나, 상어 크기보다 큰 물고기 칸이거나, 이미 방문한 칸이면 스킵
				if (nx < 0 || ny < 0 || nx >= N || ny >= N || arr[nx][ny] > size || v[nx][ny]) continue;

				v[nx][ny] = true; // 방문 처리
				queue.add(new Shark(nx, ny, now.d + 1)); // 큐에 추가(탐색 계속)

				// 먹을 수 있는 물고기라면 후보에 추가
				if (arr[nx][ny] != 0 && arr[nx][ny] < size) {
					pq.add(new Shark(nx, ny, now.d + 1));
				}
			}
		}
	}
}
