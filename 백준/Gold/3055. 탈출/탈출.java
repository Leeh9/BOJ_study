import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int R, C, B_x, B_y, G_x, G_y, cnt;
	static char[][] arr;
	static Queue<Point> q = new LinkedList<>();
	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };
	static boolean[][] visit_Go, visit_water;
	static boolean check = false;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		arr = new char[R][C];
		for (int i = 0; i < R; i++) {
			String line = br.readLine();
			for (int j = 0; j < C; j++) {
				arr[i][j] = line.charAt(j);
				if (arr[i][j] == 'D') {
					B_x = i;
					B_y = j;
				}
				if (arr[i][j] == '*') {
					q.add(new Point(i, j));
				}
				if (arr[i][j] == 'S') {
					G_x = i;
					G_y = j;
				}
			}
		}
		cnt = 0;

		visit_Go = new boolean[R][C];
		visit_water = new boolean[R][C];
		bfs_g();

		if (!check)
			System.out.println("KAKTUS");
		else
			System.out.println(cnt);

	}

	private static void bfs_g() {
		Queue<Point> p = new LinkedList<>();

		p.add(new Point(G_x, G_y));

		while (!p.isEmpty()) {
			check = false;
			int size = p.size();

			// 물 확장
			bfs_w();

			for (int s = 0; s < size; s++) {
				Point n = p.poll();

				for (int i = 0; i < 4; i++) {
					int nx = n.x + dx[i];
					int ny = n.y + dy[i];

					if (nx >= 0 && ny >= 0 && nx < R && ny < C && !visit_Go[nx][ny]) {
						if (arr[nx][ny] != '*' && arr[nx][ny] != 'X') {
							check = true;
							p.add(new Point(nx, ny));
							visit_Go[nx][ny] = true;

						}
						// 굴이면 탈출
						if (arr[nx][ny] == 'D') {
							cnt++;
							return;
						}

					}
				}
			}
			cnt++;
		}

	}

	private static void bfs_w() {
		int size = q.size();
		for (int s = 0; s < size; s++) {
			Point n = q.poll();
			for (int i = 0; i < 4; i++) {
				int nx = n.x + dx[i];
				int ny = n.y + dy[i];
				if (nx >= 0 && ny >= 0 && nx < R && ny < C && !visit_water[nx][ny]) {
					if (arr[nx][ny] != 'D' && arr[nx][ny] != 'X' && arr[nx][ny] != 'S') {
						q.add(new Point(nx, ny));
						visit_water[nx][ny] = true;
						arr[nx][ny] = '*';
					}
				}
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
}