import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int R, C, N;
	static char[][] arr;
	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };
	static Queue<Point> q = new LinkedList<>();
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		arr = new char[R][C];

		for (int i = 0; i < R; i++) {
			String line = br.readLine();
			for (int j = 0; j < C; j++) {
				arr[i][j] = line.charAt(j);
				if (arr[i][j] == 'O') {
					q.add(new Point(i, j));
				}
			}
		}

		if (N % 2 == 0) {
			for (int i = 0; i < R; i++) {
				for (int j = 0; j < C; j++) {
					sb.append('O');
				}
				sb.append('\n');
			}
			System.out.println(sb.toString());

		} else {
			if (N % 4 == 1) {
				if (N == 1) {
					sbadd();
				} else {
					change();
					bfs();

					for (int i = 0; i < R; i++) {
						for (int j = 0; j < C; j++) {
							if (arr[i][j] == 'O') {
								q.add(new Point(i, j));
							}
						}
					}

					change();
					bfs();
					sbadd();

				}
			}
			if (N % 4 == 3) {
				change();
				bfs();

				for (int i = 0; i < R; i++) {
					for (int j = 0; j < C; j++) {
						sb.append(arr[i][j]);
					}
					sb.append('\n');
				}
				System.out.println(sb.toString());
			}
		}

	}

	private static void bfs() {
		while (!q.isEmpty()) {
			Point n = q.poll();
			arr[n.x][n.y] = '.';
			for (int i = 0; i < 4; i++) {
				int nx = n.x + dx[i];
				int ny = n.y + dy[i];
				if (nx >= 0 && ny >= 0 && nx < R && ny < C) {
					arr[nx][ny] = '.';
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

	static void change() {
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if (arr[i][j] == '.') {
					arr[i][j] = 'O';
				}
			}
		}
	}

	static void sbadd() {
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				sb.append(arr[i][j]);
			}
			sb.append('\n');
		}
		System.out.println(sb.toString());
	}
}