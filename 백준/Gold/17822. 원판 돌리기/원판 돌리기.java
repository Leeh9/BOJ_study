import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int N, M, T, num, dir, cnt, CircleSum = 0;
	static int[][] arr;
	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };
	static boolean chk;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());
		arr = new int[N][M];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());

			for (int j = 0; j < M; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		for (int tc = 0; tc < T; tc++) {
			st = new StringTokenizer(br.readLine());
			num = Integer.parseInt(st.nextToken());
			dir = Integer.parseInt(st.nextToken());
			cnt = Integer.parseInt(st.nextToken());

			for (int j = num - 1; j < N; j = j + num) {
				if (dir == 0) {
					for (int t = 0; t < cnt; t++) {
						turnClock(j);
					}
				} else {
					for (int t = 0; t < cnt; t++) {
						turnAntiClock(j);
					}
				}
			}

			chk = false;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					if (arr[i][j] != 0) {
						check(i, j);
					}
				}
			}

			if (!chk) {
				getAvg();
			}

//			for (int i = 0; i < N; i++) {
//				for (int j = 0; j < M; j++) {
//					System.out.print(arr[i][j]);
//				}
//				System.out.println();
//			}
//			System.out.println();
		}

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				CircleSum += arr[i][j];
			}
		}
		System.out.println(CircleSum);
	}

	private static void getAvg() {
		int sum = 0;
		int c = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				sum += arr[i][j];
				if (arr[i][j] != 0) {
					c++;
				}
			}
		}
		double avg = sum / (c * 1.0);

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (arr[i][j] > avg) {
					arr[i][j]--;
				} else if (arr[i][j] < avg) {
					if (arr[i][j] != 0) {
						arr[i][j]++;
					}
				}
			}
		}
	}

	private static void check(int x, int y) {
		Queue<Point> q = new LinkedList<>();
		boolean[][] visit = new boolean[N][M];
		q.add(new Point(x, y));
		visit[x][y] = true;
		boolean v = false;
		while (!q.isEmpty()) {
			Point n = q.poll();

			for (int i = 0; i < 4; i++) {
				int nx = n.x + dx[i];
				int ny = n.y + dy[i];

				if (ny == -1)
					ny = M - 1;
				if (ny == M)
					ny = 0;
				if (nx >= 0 && ny >= 0 && nx < N && ny < M && !visit[nx][ny]) {
					if (arr[nx][ny] == arr[n.x][n.y]) {
						chk = true;
						v = true;
						q.add(new Point(nx, ny));
						visit[nx][ny] = true;
					}
				}
			}

			if (v) {
				arr[n.x][n.y] = 0;
			}
		}

	}

	static class Point {
		int x;
		int y;

		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	private static void turnClock(int j) {
		int[] ex = new int[M];
		ex[0] = arr[j][M - 1];
		for (int i = 1; i < M; i++) {
			ex[i] = arr[j][i - 1];
		}
		for (int i = 0; i < M; i++) {
			arr[j][i] = ex[i];
		}

	}

	private static void turnAntiClock(int j) {
		int[] ex = new int[M];
		ex[M - 1] = arr[j][0];
		for (int i = M - 2; i >= 0; i--) {
			ex[i] = arr[j][i + 1];
		}
		for (int i = 0; i < M; i++) {
			arr[j][i] = ex[i];
		}
	}
}
