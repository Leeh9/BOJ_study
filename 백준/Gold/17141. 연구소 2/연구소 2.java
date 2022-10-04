import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int N, M, min = Integer.MAX_VALUE;
	static int[][] arr;
	static int[][] arr_copy;
	static boolean check;
	static boolean[][] visit;
	static int[] num;
	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };
	static ArrayList<Point> list = new ArrayList<>();
	static Queue<Point> q;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		arr = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
				if (arr[i][j] == 2) {
					list.add(new Point(i, j));
				}
			}
		}
		num = new int[list.size()];

		comb(0, 0);

		if (min == Integer.MAX_VALUE)
			System.out.println("-1");
		else
			System.out.println(min);

	}

	private static void comb(int cnt, int start) {
		if (cnt == M) {
			check = false;
			q = new LinkedList<>();
			visit = new boolean[N][N];
			deepcopy();
			// System.out.println("들어옴");
			for (int i = 0; i < cnt; i++) {
				Point n = list.get(num[i]);
				arr_copy[n.x][n.y] = 3;
				q.add(new Point(n.x, n.y));
				visit[n.x][n.y] = true;
			}

			int res = bfs();
			if (res != -1) {
				min = Math.min(min, res);
			}
			return;
		}

		for (int i = start; i < list.size(); i++) {
			num[cnt] = i;
			comb(cnt + 1, i + 1);
		}
	}

	private static int bfs() {
		int cnt = -1;
		while (!q.isEmpty()) {
			int size = q.size();
			for (int s = 0; s < size; s++) {
				Point n = q.poll();
				for (int i = 0; i < 4; i++) {
					int nx = n.x + dx[i];
					int ny = n.y + dy[i];
					if (nx >= 0 && ny >= 0 && nx < N && ny < N) {
						if (!visit[nx][ny] && arr_copy[nx][ny] != 1 && arr_copy[nx][ny] != 3) {
							visit[nx][ny] = true;
							arr_copy[nx][ny] = 3;
							q.add(new Point(nx, ny));
						}
					}
				}
			}
			cnt++;
		}

		int res = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (arr_copy[i][j] == 0) {
					check = true;
				}
			}
		}
		if (check)
			res = -1;
		else
			res = cnt;

		return res;
	}

	private static void deepcopy() {
		arr_copy = new int[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				arr_copy[i][j] = arr[i][j];
			}
		}
	}

	private static class Point {
		int x;
		int y;

		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
}