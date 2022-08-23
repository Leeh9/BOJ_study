import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int N, M, zeroCnt, Max;
	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };
	static int[][] arr;
	static int[] visit;
	static boolean[][] virus;
	static ArrayList<Point> p = new ArrayList<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		arr = new int[N][M];
		virus = new boolean[N][M];
		zeroCnt = 0;
		Max = 0;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
				// 빈 칸 좌표 등록
				if (arr[i][j] == 0) {
					p.add(new Point(i, j));
					zeroCnt++;
				}
			}
		}
		visit = new int[zeroCnt];

		comb(0, 0);
		System.out.println(Max);
	}

	private static void comb(int cnt, int start) {
		if (cnt == 3) {
			int[][] Narr = new int[N][M];
			// 원본 배열 복사
			Narr = copy(Narr);
			// 3개 벽으로 바꿔줌
			for (int i = 0; i < zeroCnt; i++) {
				if (visit[i] != 0) {

					Point n = p.get(visit[i] - 1);
					Narr[n.x][n.y] = 1;
				}
			}
			// bfs탐색
			int safe = bfs(Narr);

			// 안전구역이 최대크기인지 검사
			Max = Math.max(safe, Max);
			return;
		}
		for (int i = start; i < zeroCnt; i++) {
			visit[cnt] = i + 1;
			comb(cnt + 1, i + 1);
			// visit[cnt] = false;
		}
	}

	private static int bfs(int[][] narr) {
		Queue<Point> q = new LinkedList<>();

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				// 바이러스의 좌표 등록
				if (narr[i][j] == 2) {
					q.add(new Point(i, j));
					virus[i][j] = true;
				}
			}
		}

		while (!q.isEmpty()) {
			int size = q.size();

			for (int s = 0; s < size; s++) {
				Point n = q.poll();
				for (int i = 0; i < 4; i++) {
					int nx = n.x + dx[i];
					int ny = n.y + dy[i];
					if (nx >= 0 && ny >= 0 && nx < N && ny < M && narr[nx][ny] == 0) {
						narr[nx][ny] = 2;
						q.add(new Point(nx, ny));
					}
				}
			}
		}
		int safe = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (narr[i][j] == 0) {
					safe++;
				}
			}
		}
		return safe;
	}

	static class Point {
		int x;
		int y;

		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}

	}

	static int[][] copy(int[][] narr) {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				narr[i][j] = arr[i][j];
			}
		}

		return narr;
	}
}