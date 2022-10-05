import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int K, W, H, time = 0;
	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };
	static int[] mon_dx = { -1, -2, -2, -1, 1, 2, 2, 1 };
	static int[] mon_dy = { -2, -1, 1, 2, 2, 1, -1, -2 };
	static int[][] arr;
	static boolean[][][] visit;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		K = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		W = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		arr = new int[H][W];
		visit = new boolean[H][W][K+1];
		for (int i = 0; i < H; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < W; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		int res = bfs();
		System.out.println(res);
	}

	private static int bfs() {
		Queue<Point> q = new LinkedList<>();
		q.add(new Point(0, 0, 0));
		visit[0][0][0] = true;

		while (!q.isEmpty()) {
			int size = q.size();
			for (int s = 0; s < size; s++) {
				Point n = q.poll();
				if (n.x == H - 1 && n.y == W - 1) {

					return time;
				}
				for (int i = 0; i < 4; i++) {
					int nx = n.x + dx[i];
					int ny = n.y + dy[i];
					if (nx >= 0 && ny >= 0 && nx < H && ny < W && arr[nx][ny] == 0) {
						if (!visit[nx][ny][n.s]) {
							visit[nx][ny][n.s] = true;
							q.add(new Point(nx, ny, n.s));
						}
					}
				}
				if (n.s + 1 <= K) {
					for (int i = 0; i < 8; i++) {
						int nx = n.x + mon_dx[i];
						int ny = n.y + mon_dy[i];
						if (nx >= 0 && ny >= 0 && nx < H && ny < W && arr[nx][ny] == 0) {
							if (!visit[nx][ny][n.s + 1]) {
								q.add(new Point(nx, ny, n.s + 1));
								visit[nx][ny][n.s + 1] = true;
							}
						}
					}
				}
			}
			time++;
		}
		return -1;

	}

	private static class Point {
		int x;
		int y;
		int s;

		public Point(int x, int y, int s) {
			this.x = x;
			this.y = y;
			this.s = s;
		}

	}
}