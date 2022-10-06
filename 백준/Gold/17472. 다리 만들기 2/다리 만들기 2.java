import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int N, M, bridgecnt, islandNum, cnt;
	static int min = Integer.MAX_VALUE;
	static int[][] map;
	static int[][] con;
	static boolean check;
	static boolean[][] visit;
	static int[] num;
	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };
	static ArrayList<Bridge> Bridgelist = new ArrayList<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		cnt = 1;
		visit = new boolean[N][M];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (map[i][j] == 1 && !visit[i][j]) {
					bfs(i, j, cnt);
					cnt++;
				}
			}
		}
		// 섬의 개수
		islandNum = cnt - 1;

		// 섬끼리의 연결 확인 하는 행렬
		con = new int[cnt][cnt];

		// 다리 만들기
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (map[i][j] > 0) {
					getBridge(map[i][j], i, j);
				}
			}
		}

//		for (int i = 0; i < N; i++) {
//			for (int j = 0; j < M; j++) {
//				System.out.print(map[i][j]);
//			}
//			System.out.println();
//		}
		bridgecnt = 0;
		for (int i = 1; i < cnt; i++) {
			for (int j = i + 1; j < cnt; j++) {
				if (con[i][j] > 0) {
					bridgecnt++;
					Bridgelist.add(new Bridge(i, j, con[i][j]));
				}
			}
		}

//		for (int i = 1; i < cnt; i++) {
//			for (int j = 1; j < cnt; j++) {
//				System.out.print(con[i][j]);
//			}
//			System.out.println();
//		}
		
		// 다리 개수중에 섬-1개를 조합으로 뽑아서 조사함
		num = new int[bridgecnt];
		comb(0, 0);

		if (min == Integer.MAX_VALUE) {
			System.out.println("-1");
		} else {
			System.out.println(min);
		}
	}

	private static void comb(int c, int start) {
		if (c == islandNum - 1) {
			// 섬끼리 연결되어 있는지 확인하는 배열
			int[][] arr = new int[cnt][cnt];
			int sum = 0;
			check = false;
			for (int i = 0; i < c; i++) {
				Bridge b = Bridgelist.get(num[i]);
				arr[b.start][b.end] = b.len;
				arr[b.end][b.start] = b.len;
				sum += b.len;
			}
			check = isConnect(arr);

			if (check) {
				min = Math.min(min, sum);
			}
			return;
		}

		for (int i = start; i < bridgecnt; i++) {
			num[c] = i;
			comb(c + 1, i + 1);
		}
	}

	private static boolean isConnect(int[][] arr) {
		boolean[] visitIsland = new boolean[cnt];
		Queue<Integer> q = new LinkedList<>();
		q.add(1);

		while (!q.isEmpty()) {
			int n = q.poll();
			visitIsland[n] = true;
			for (int i = 1; i < cnt; i++) {
				if (arr[n][i] > 0 && !visitIsland[i]) {
					q.add(i);
				}
			}
		}

		for (int i = 1; i < cnt; i++) {
			if (!visitIsland[i]) {
				return false;
			}
		}

		return true;
	}

	private static void getBridge(int type, int x, int y) {
		// 4방향으로 다른 섬 탐색
		for (int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			while (true) {
				// 정상적으로 맵안에 있고 나와 다른 번호여야만 함
				if (nx >= 0 && ny >= 0 && nx < N && ny < M && map[nx][ny] != type) {
					if (map[nx][ny] == 0) {
						nx += dx[i];
						ny += dy[i];
					} else {
						// 다른 섬과 만났으면 거리 구하기
						int dis = (Math.abs(nx - x) + Math.abs(ny - y)) - 1;
						// 거리 2이상만 유효
						if (dis >= 2) {
							if (con[type][map[nx][ny]] == 0) {
								con[type][map[nx][ny]] = dis;
							} else {
								con[type][map[nx][ny]] = Math.min(con[type][map[nx][ny]], dis);
							}
						}
						break;
					}
				} else {
					break;
				}
			}
		}
	}

	private static void bfs(int x, int y, int cnt) {
		Queue<Point> q = new LinkedList<>();
		q.add(new Point(x, y));
		visit[x][y] = true;
		map[x][y] = cnt;

		while (!q.isEmpty()) {
			Point n = q.poll();
			for (int i = 0; i < 4; i++) {
				int nx = n.x + dx[i];
				int ny = n.y + dy[i];
				if (nx >= 0 && ny >= 0 && nx < N && ny < M) {
					if (!visit[nx][ny] && map[nx][ny] == 1) {
						visit[nx][ny] = true;
						map[nx][ny] = cnt;
						q.add(new Point(nx, ny));
					}
				}
			}
		}
	}

	private static class Bridge {
		int start;
		int end;
		int len;

		public Bridge(int start, int end, int len) {
			this.start = start;
			this.end = end;
			this.len = len;
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