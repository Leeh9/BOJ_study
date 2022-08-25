import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static int N, cnt;
	static int[][] arr, dis;
	static int[] dx = { 0, 0, -1, 1 };
	static int[] dy = { -1, 1, 0, 0 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		int cnt = 1;
		while (true) {
			N = Integer.parseInt(br.readLine());
			if (N == 0) {
				break;
			}
			arr = new int[N][N];
			dis = new int[N][N];

			for (int i = 0; i < N; i++) {
				Arrays.fill(dis[i], Integer.MAX_VALUE);
			}
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					arr[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			int a= bfs();
			sb.append("Problem " + cnt + ": " + a);
			sb.append("\n");
			cnt++;
		}
		System.out.println(sb.toString());
	}

	private static int bfs() {
		PriorityQueue<Point> q = new PriorityQueue<Point>();
		dis[0][0] = arr[0][0];
		q.add(new Point(0, 0, arr[0][0]));

		while (!q.isEmpty()) {
			Point n = q.poll();
			for (int i = 0; i < 4; i++) {
				int nx = n.x + dx[i];
				int ny = n.y + dy[i];
				if (nx >= 0 && ny >= 0 && nx < N && ny < N) {
					if (dis[nx][ny] > dis[n.x][n.y] + arr[nx][ny]) {
						dis[nx][ny] = dis[n.x][n.y] + arr[nx][ny];
						q.add(new Point(nx, ny, dis[nx][ny]));
					}
				}
			}
		}
		return dis[N - 1][N - 1];

	}

	static class Point implements Comparable<Point> {
		int x;
		int y;
		int score;

		public Point(int x, int y, int score) {
			this.x = x;
			this.y = y;
			this.score = score;
		}

		@Override
		public int compareTo(Point o) {
			int d = this.score - o.score;
			return d;
		}

	}
}