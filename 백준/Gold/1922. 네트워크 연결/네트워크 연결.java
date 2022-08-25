import java.util.Arrays;
import java.util.Scanner;

public class Main {
	static int N, M;
	static int[][] g;
	static int[] parent;
	static int cost = 0;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		g = new int[M][3];
		parent = new int[N];
		for (int i = 0; i < M; i++) {
			g[i][0] = sc.nextInt();
			g[i][1] = sc.nextInt();
			g[i][2] = sc.nextInt();
		}

		Arrays.sort(g, (o1, o2) -> {
			return o1[2] - o2[2];
		});

		make();

		for (int i = 0; i < M; i++) {
			if (find(g[i][0]-1) != find(g[i][1]-1)) {
				union(g[i][0]-1, g[i][1]-1);
				cost += g[i][2];
			}
		}
		System.out.println(cost);
	}

	static void make() {
		for (int i = 0; i < N; i++) {
			parent[i] = i;
		}
	}

	static int find(int x) {
		if (parent[x] == x) {
			return x;
		}

		return parent[x] = find(parent[x]);
	}

	static void union(int x, int y) {
		x = find(x);
		y = find(y);
		if (x > y) {
			parent[x] = y;
		} else {
			parent[y] = x;
		}
	}
}