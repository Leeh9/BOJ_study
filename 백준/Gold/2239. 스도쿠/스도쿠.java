import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main {
	static int[][] map = new int[9][9];
	static ArrayList<Point> list;
	static ArrayList<Integer>[] arr;
	static StringBuilder sb = new StringBuilder();
	static boolean res = false;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		list = new ArrayList<>();
		for (int i = 0; i < 9; i++) {
			String line = br.readLine();
			for (int j = 0; j < 9; j++) {
				map[i][j] = line.charAt(j) - '0';
				if (map[i][j] == 0) {
					list.add(new Point(i, j));
				}
			}
		}
		dfs(0);

		System.out.println(sb.toString());
	}

	private static void dfs(int idx) {
		if (idx == list.size()) {
			res = true;
			for (int i = 0; i < 9; i++) {
				for (int j = 0; j < 9; j++) {
					sb.append(map[i][j]);
				}
				sb.append('\n');
			}
			return;
		}
		Point n = list.get(idx);
		for (int i = 1; i <= 9; i++) {
			if (check(n.x, n.y, i)) {
				map[n.x][n.y] = i;
				dfs(idx + 1);
				if (res)
					return;
			}
		}
		map[n.x][n.y] = 0;
		return;
	}

	// 스도쿠 가능한지 확인작업
	private static boolean check(int x, int y, int num) {
		// 가로 세로 조사
		for (int i = 0; i < 9; i++) {
			if (map[i][y] == num) {
				return false;
			}
			if (map[x][i] == num) {
				return false;
			}
		}
		// 3x3칸 조사
		int temp_x = x / 3 * 3;
		int temp_y = y / 3 * 3;
		for (int i = temp_x; i < temp_x + 3; i++) {
			for (int j = temp_y; j < temp_y + 3; j++) {
				if (map[i][j] == num) {
					return false;
				}
			}
		}
		return true;
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