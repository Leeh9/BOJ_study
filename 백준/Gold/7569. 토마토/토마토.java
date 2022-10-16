import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int M, N, H, cnt = 0;
	static int[] dx = { 0, 0, -1, 1 };
	static int[] dy = { -1, 1, 0, 0 };
	static int[] dz = { 1, -1 };
	static int[][][] arr;
	static boolean check = false;
	static ArrayList<Point> list = new ArrayList<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		arr = new int[M][N][H];

		int blank_cnt = 0;
		for (int z = 0; z < H; z++) {
			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					arr[i][j][z] = Integer.parseInt(st.nextToken());
					if (arr[i][j][z] == 1) {
						list.add(new Point(i, j, z));
					}
					if (arr[i][j][z] == -1) {
						blank_cnt++;
					}
				}
			}
		}

		if (M * N * H - blank_cnt == list.size()) {
			System.out.println("0");
			return;
		} else {
			bfs();
		}

		if (check) {
			System.out.println("-1");
		} else {
			System.out.println(cnt-1);
		}

	}

	private static void bfs() {
		Queue<Point> q = new LinkedList<>();
		boolean[][][] visit = new boolean[M][N][H];
		for (int i = 0; i < list.size(); i++) {
			Point n = list.get(i);
			q.add(new Point(n.x, n.y, n.z));
			visit[n.x][n.y][n.z] = true;
		}

		while (!q.isEmpty()) {
			int size = q.size();

			for (int s = 0; s < size; s++) {
				Point n = q.poll();
				for (int i = 0; i < 4; i++) {
					int nx = n.x + dx[i];
					int ny = n.y + dy[i];
					if (nx >= 0 && ny >= 0 && nx < M && ny < N) {
						if (!visit[nx][ny][n.z] && arr[nx][ny][n.z] == 0) {
							q.add(new Point(nx, ny, n.z));
							arr[nx][ny][n.z]= 1; 
							visit[nx][ny][n.z] = true;
						}
					}
				}
				for (int i = 0; i < 2; i++) {
					int nz = n.z + dz[i];
					if (nz >= 0 && nz < H) {
						if (!visit[n.x][n.y][nz] && arr[n.x][n.y][nz] == 0) {
							q.add(new Point(n.x, n.y, nz));
							arr[n.x][n.y][nz]= 1; 
							visit[n.x][n.y][nz] = true;
						}
					}
				}
			}
			cnt++;
		}
		for (int z = 0; z < H; z++) {
			for (int i = 0; i < M; i++) {
				for (int j = 0; j < N; j++) {
					if (arr[i][j][z] == 0) {
						check = true;
						return;
					}
				}
			}
		}
	}

	static class Point {
		int x;
		int y;
		int z;

		public Point(int x, int y, int z) {
			this.x = x;
			this.y = y;
			this.z = z;
		}

	}
}