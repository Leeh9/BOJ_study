import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int N, M, min = Integer.MAX_VALUE;
	static char[][] map;
	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };
	static int start_x, start_y;
	static boolean[][][][][][][][] visit;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new char[N][M];
		int[] letters = new int[6];
		for (int i = 0; i < N; i++) {
			String line = br.readLine();
			for (int j = 0; j < M; j++) {
				map[i][j] = line.charAt(j);
				if (map[i][j] == '0') {
					start_x = i;
					start_y = j;
				}
			}
		}

		bfs(start_x, start_y, 0, 0, 0, 0, 0, 0, 0);

		if (min == Integer.MAX_VALUE)
			System.out.println("-1");
		else
			System.out.println(min);
	}

	private static void bfs(int x, int y, int dis, int a, int b, int c, int d, int e, int f) {
		// System.out.println(x+" "+y);
		Queue<Point> q = new LinkedList<>();
		boolean[][][][][][][][] visit = new boolean[N][M][2][2][2][2][2][2];
		q.add(new Point(x, y, dis, a, b, c, d, e, f));
		visit[x][y][0][0][0][0][0][0] = true;

		while (!q.isEmpty()) {
			int size = q.size();
			for (int s = 0; s < size; s++) {

				Point n = q.poll();

				for (int i = 0; i < 4; i++) {
					int nx = n.x + dx[i];
					int ny = n.y + dy[i];

					if (nx >= 0 && ny >= 0 && nx < N && ny < M && map[nx][ny] != '#') {
						if (!visit[nx][ny][n.a][n.b][n.c][n.d][n.e][n.f]) {
							if (map[nx][ny] == '.' || map[nx][ny] == '0') {
								q.add(new Point(nx, ny, n.dis + 1, n.a, n.b, n.c, n.d, n.e, n.f));
								visit[nx][ny][n.a][n.b][n.c][n.d][n.e][n.f] = true;
							} else if (map[nx][ny] == '1') {
								min = Math.min(min, n.dis + 1);
								return;
							} else if (map[nx][ny] == 'A' || map[nx][ny] == 'B' || map[nx][ny] == 'C'
									|| map[nx][ny] == 'D' || map[nx][ny] == 'E' || map[nx][ny] == 'F') {
								if (map[nx][ny] == 'A' && n.a == 1) {
									q.add(new Point(nx, ny, n.dis + 1, n.a, n.b, n.c, n.d, n.e, n.f));
									visit[nx][ny][n.a][n.b][n.c][n.d][n.e][n.f] = true;
								}
								if (map[nx][ny] == 'B' && n.b == 1) {
									q.add(new Point(nx, ny, n.dis + 1, n.a, n.b, n.c, n.d, n.e, n.f));
									visit[nx][ny][n.a][n.b][n.c][n.d][n.e][n.f] = true;
								}
								if (map[nx][ny] == 'C' && n.c == 1) {
									q.add(new Point(nx, ny, n.dis + 1, n.a, n.b, n.c, n.d, n.e, n.f));
									visit[nx][ny][n.a][n.b][n.c][n.d][n.e][n.f] = true;
								}
								if (map[nx][ny] == 'D' && n.d == 1) {
									q.add(new Point(nx, ny, n.dis + 1, n.a, n.b, n.c, n.d, n.e, n.f));
									visit[nx][ny][n.a][n.b][n.c][n.d][n.e][n.f] = true;
								}
								if (map[nx][ny] == 'E' && n.e == 1) {
									q.add(new Point(nx, ny, n.dis + 1, n.a, n.b, n.c, n.d, n.e, n.f));
									visit[nx][ny][n.a][n.b][n.c][n.d][n.e][n.f] = true;
								}
								if (map[nx][ny] == 'F' && n.f == 1) {
									q.add(new Point(nx, ny, n.dis + 1, n.a, n.b, n.c, n.d, n.e, n.f));
									visit[nx][ny][n.a][n.b][n.c][n.d][n.e][n.f] = true;
								}
							} else if (map[nx][ny] == 'a' || map[nx][ny] == 'b' || map[nx][ny] == 'c'
									|| map[nx][ny] == 'd' || map[nx][ny] == 'e' || map[nx][ny] == 'f') {
								if (map[nx][ny] == 'a') {
									q.add(new Point(nx, ny, n.dis + 1, 1, n.b, n.c, n.d, n.e, n.f));
									visit[nx][ny][1][n.b][n.c][n.d][n.e][n.f] = true;
								}
								if (map[nx][ny] == 'b') {
									q.add(new Point(nx, ny, n.dis + 1, n.a, 1, n.c, n.d, n.e, n.f));
									visit[nx][ny][n.a][1][n.c][n.d][n.e][n.f] = true;
								}
								if (map[nx][ny] == 'c') {
									q.add(new Point(nx, ny, n.dis + 1, n.a, n.b, 1, n.d, n.e, n.f));
									visit[nx][ny][n.a][n.b][1][n.d][n.e][n.f] = true;
								}
								if (map[nx][ny] == 'd') {
									q.add(new Point(nx, ny, n.dis + 1, n.a, n.b, n.c, 1, n.e, n.f));
									visit[nx][ny][n.a][n.b][n.c][1][n.e][n.f] = true;
								}
								if (map[nx][ny] == 'e') {
									q.add(new Point(nx, ny, n.dis + 1, n.a, n.b, n.c, n.d, 1, n.f));
									visit[nx][ny][n.a][n.b][n.c][n.d][1][n.f] = true;
								}
								if (map[nx][ny] == 'f') {
									q.add(new Point(nx, ny, n.dis + 1, n.a, n.b, n.c, n.d, n.e, 1));
									visit[nx][ny][n.a][n.b][n.c][n.d][n.e][1] = true;
								}
							}
						}
					}
				}
			}
		}

	}

	static class Point {
		int x;
		int y;
		int dis;
		int a;
		int b;
		int c;
		int d;
		int e;
		int f;

		public Point(int x, int y, int dis, int a, int b, int c, int d, int e, int f) {
			this.x = x;
			this.y = y;
			this.dis = dis;
			this.a = a;
			this.b = b;
			this.c = c;
			this.d = d;
			this.e = e;
			this.f = f;
		}

	}
}