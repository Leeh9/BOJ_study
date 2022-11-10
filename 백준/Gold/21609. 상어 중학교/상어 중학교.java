import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int N, M, point = 0;
	static int[][] map;
	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };
	static boolean[][] visit;
	static PriorityQueue<Block> pq;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new int[N][N];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		while (true) {
			pq = new PriorityQueue<>();
			visit = new boolean[N][N];

			// 블록 그룹 찾기
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (!visit[i][j] && (map[i][j] >= 1 && map[i][j] <= M)) {
						search(i, j, map[i][j]);
						//System.out.println("Gg" + i + " " + j + " " + map[i][j]);
					}
				}
			}
			// 블록 그룹 없으면 반복문 종료
			if (pq.size() == 0) {
				break;
			}

			// 오토 플레이 기능 시작
			// 1번 작업
			Block b = pq.poll();

			// 2번 작업
			for (int i = 0; i < b.list.size(); i++) {
				Point p = b.list.get(i);
				map[p.x][p.y] = -2;
			}
			point += b.size * b.size;

			// 3번 작업
			for (int j = 0; j < N; j++) {
				int temp = N - 1;
				for (int i = N - 1; i >= 0; i--) {
					if (map[i][j] == -1) {
						temp = i - 1;
					} else if (map[i][j] == -2) {
						temp = Math.max(temp, i);
					} else {
						if (temp > i) {
							map[temp][j] = map[i][j];
							map[i][j] = -2;
							temp--;
						} else {
							temp--;
						}
					}
				}
			}

			// 4번 작업
			int[][] new_map = new int[N][N];

			for(int i=0; i<N; i++){
	            for(int j=0; j<N; j++){
	            	new_map[i][j] = map[j][N-i-1];
	            }
	        }

			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					map[i][j] = new_map[i][j];
				}
			}
			
			// 5번 작업
			for (int j = 0; j < N; j++) {
				int temp = N - 1;
				for (int i = N - 1; i >= 0; i--) {
					if (map[i][j] == -1) {
						temp = i - 1;
					} else if (map[i][j] == -2) {
						temp = Math.max(temp, i);
					} else {
						if (temp > i) {
							map[temp][j] = map[i][j];
							map[i][j] = -2;
							temp--;
						} else {
							temp--;
						}
					}
				}
			}
			
		}

		System.out.println(point);
	}

	private static void search(int x, int y, int num) {
		Queue<Point> q = new LinkedList<>();
		// visit = new boolean[N][N];
		boolean[][] visit2 = new boolean[N][N];
		q.add(new Point(x, y));
		visit[x][y] = true;
		visit2[x][y] = true;
		ArrayList<Point> list = new ArrayList<>();
		list.add(new Point(x, y));

		int cnt = 1;
		int rainbow_cnt = 0;
		while (!q.isEmpty()) {
			Point n = q.poll();
			for (int i = 0; i < 4; i++) {
				int nx = n.x + dx[i];
				int ny = n.y + dy[i];

				if (nx >= 0 && ny >= 0 && nx < N && ny < N && !visit2[nx][ny]) {
					if (map[nx][ny] == num) {
						// System.out.println(x+" "+y+" "+num+" "+nx+" "+ny+" "+map[nx][ny]);
						visit[nx][ny] = true;
						visit2[nx][ny] = true;
						q.add(new Point(nx, ny));
						list.add(new Point(nx, ny));
						cnt++;
					} else if (map[nx][ny] == 0) {
						visit2[nx][ny] = true;
						q.add(new Point(nx, ny));
						list.add(new Point(nx, ny));
						cnt++;
						rainbow_cnt++;
					}
				}
			}
		}

		if (cnt >= 2) {
			pq.add(new Block(cnt, rainbow_cnt, x, y, list));
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

	static class Block implements Comparable<Block> {
		int size;
		int rainbow_cnt;
		int main_x;
		int main_y;
		ArrayList<Point> list;

		public Block(int size, int rainbow_cnt, int main_x, int main_y, ArrayList<Point> list) {
			this.size = size;
			this.rainbow_cnt = rainbow_cnt;
			this.main_x = main_x;
			this.main_y = main_y;
			this.list = list;
		}

		// priority 결정
		@Override
		public int compareTo(Block o) {
			if (this.size == o.size) {
				if (this.rainbow_cnt == o.rainbow_cnt) {
					if (this.main_x == o.main_x) {
						return o.main_y - this.main_y;
					} else {
						return o.main_x - this.main_x;
					}
				} else {
					return o.rainbow_cnt - this.rainbow_cnt;
				}
			} else {
				return o.size - this.size;
			}
		}

	}
}