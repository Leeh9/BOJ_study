import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
	static ArrayList<Integer>[] arr = new ArrayList[51];
	static int n, cnt = 0;
	static int[][] map;
	static boolean[] check;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		n = Integer.parseInt(br.readLine());
		map = new int[n + 1][n + 1];
		StringTokenizer st;
		for (int i = 1; i <= n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= n; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == 0) {
					cnt++;
				}
			}
		}

		check = new boolean[n + 1];
		if (cnt == n * n) {
			System.out.println("1");
			for (int i = 1; i <= n; i++) {
				System.out.print(i + " ");
			}
			return;
		}
		for (int i = 1; i <= 50; i++) {
			arr[i] = new ArrayList<>();

		}
		int idx = 1;
		for (int i = 1; i <= n; i++) {
			if (!check[i]) {
				arr[idx].add(i);
			}
			for (int j = 1; j <= n; j++) {
				if (i != j && map[i][j] == 0) {
					if (!check[i] && !check[j]) {
						arr[idx].add(j);
						check[j] = true;
					}

					if (check[i] && !check[j]) {
						System.out.println("0");
						return;
					}
				}

			}
			if (!check[i]) {
				check[i] = true;
				idx++;
			}
		}
		for (int i = 1; i < idx; i++) {
			if (arr[i].size() == 1) {
				System.out.println("0");
				return;
			}
		}

		System.out.println(idx - 1);
		for (int i = 1; i < idx; i++) {
			for (int j = 0; j < arr[i].size(); j++) {
				System.out.print(arr[i].get(j) + " ");
			}
			System.out.println();
		}

	}
}