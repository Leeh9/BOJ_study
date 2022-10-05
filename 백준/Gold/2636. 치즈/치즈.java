import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int N, M, cnt=0;
	static int[] dx = { 0, 0, -1, 1 };
	static int[] dy = { -1, 1, 0, 0 };
	static int[][] arr;
	static boolean[][] visit;
	static ArrayList<Point> list;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		arr = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		int time = 0;
		while (true) {

			visit = new boolean[N][M];
			boolean check = false;
			// 가장자리 찾기
			bfs(0, 0);
			cnt = list.size();

			// 1초 후
			for (int i = 0; i < cnt; i++) {
				Point n = list.get(i);
				arr[n.x][n.y] = 0;
			}
			time++;
			
			int n = 0;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					if (arr[i][j] == 1) {
						n++;
					}
				}
			}
			if (n == 0) {
				break;
			}
		}

		System.out.println(time);
		System.out.println(cnt);

	}

	private static void bfs(int x, int y) {
		Queue<Point> q = new LinkedList<>();
		list = new ArrayList<>();
		q.add(new Point(x, y));
		visit[x][y] = true;

		while (!q.isEmpty()) {
			Point n = q.poll();
			for (int i = 0; i < 4; i++) {
				int nx = n.x + dx[i];
				int ny = n.y + dy[i];
				if (nx >= 0 && ny >= 0 && nx < N && ny < M) {
					if (!visit[nx][ny] && arr[nx][ny] == 0) {
						q.add(new Point(nx, ny));
						visit[nx][ny] = true;
					}
					if (!visit[nx][ny] && arr[nx][ny] == 1) {
						list.add(new Point(nx, ny));
						visit[nx][ny] = true;
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