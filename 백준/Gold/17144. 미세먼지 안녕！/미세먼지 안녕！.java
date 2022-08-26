import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int R, C, T, AirCleanTopX, AirCleanTopY, AirCleanBotX, AirCleanBotY;
	static int[] dx = { -1, 0, 1, 0 };
	static int[] dy = { 0, 1, 0, -1 };
	static int[][] arr, copyarr;
	static Queue<Point> q = new LinkedList<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());
		arr = new int[R][C];
		boolean airCheck = false;
		for (int i = 0; i < R; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < C; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
				if (arr[i][j] == -1 && !airCheck) {
					AirCleanTopX = i;
					AirCleanTopY = j;
					AirCleanBotX = i + 1;
					AirCleanBotY = j;
					airCheck = true;
				}
				if (arr[i][j] > 0) {
					q.add(new Point(i, j, arr[i][j]));
				}
			}
		}
		for (int tc = 0; tc < T; tc++) {
			// 배열 복사
			copyarr = new int[R][C];
			// 확산
			AddDust();
			// 순환
			AirCleanTop();
			AirCleanBot();

			// 순환 후 미세먼지 다시 큐에 추가
			for (int i = 0; i < R; i++) {
				for (int j = 0; j < C; j++) {
					if (arr[i][j] > 0) {
						q.add(new Point(i, j, arr[i][j]));
					}
				}
			}
		}
		int cnt = 0;
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if (arr[i][j] > 0)
					cnt += arr[i][j];
			}
		}
		System.out.println(cnt);
	}

	private static void AddDust() {
		while (!q.isEmpty()) {
			Point n = q.poll();
			for (int i = 0; i < 4; i++) {
				int nx = n.x + dx[i];
				int ny = n.y + dy[i];
				if (nx >= 0 && ny >= 0 && nx < R && ny < C && arr[nx][ny] != -1) {
					copyarr[nx][ny] += arr[n.x][n.y] / 5;
					copyarr[n.x][n.y] -= arr[n.x][n.y] / 5;
				}
			}
		}
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				arr[i][j] += copyarr[i][j];
			}
		}

	}

	private static void AirCleanTop() {
		int i = AirCleanTopX - 1;
		int j = AirCleanTopY;
		while (true) {
			if (j == 0) {
				if (i == 0)
					arr[i][j] = arr[i][j + 1];
				else
					arr[i][j] = arr[i - 1][j];
				i--;
			}
			if (i == 0) {
				if (j == C - 1) {
					arr[i][j] = arr[i + 1][j];
				} else
					arr[i][j] = arr[i][j + 1];
				j++;
			}
			if (j == C - 1) {
				if (i == AirCleanTopX) {
					arr[i][j] = arr[i][j - 1];
				} else
					arr[i][j] = arr[i + 1][j];
				i++;
			}
			if (i == AirCleanTopX) {
				if (arr[i][j - 1] == -1) {
					arr[i][j] = 0;
					break;
				} else
					arr[i][j] = arr[i][j - 1];
				j--;

			}
		}
	}

	private static void AirCleanBot() {
		int i = AirCleanBotX + 1;
		int j = AirCleanBotY;
		while (true) {
			if (j == 0) {
				if (i == R - 1)
					arr[i][j] = arr[i][j + 1];
				else
					arr[i][j] = arr[i + 1][j];
				i++;
			}
			if (i == R - 1) {
				if (j == C - 1) {
					arr[i][j] = arr[i - 1][j];
				} else
					arr[i][j] = arr[i][j + 1];
				j++;
			}
			if (j == C - 1) {
				if (i == AirCleanBotX) {
					arr[i][j] = arr[i][j - 1];
				} else
					arr[i][j] = arr[i - 1][j];
				i--;
			}
			if (i == AirCleanBotX) {
				if (arr[i][j - 1] == -1) {
					arr[i][j] = 0;
					break;
				} else
					arr[i][j] = arr[i][j - 1];
				j--;
			}
		}
	}

	static class Point {
		int x;
		int y;
		int dust;

		public Point(int x, int y, int dust) {
			this.x = x;
			this.y = y;
			this.dust = dust;
		}

	}
}