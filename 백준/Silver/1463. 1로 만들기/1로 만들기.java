import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
	static int min = Integer.MAX_VALUE;
	static int[] dp;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int N = sc.nextInt();
		int cnt = 0;

		if (N == 1) {
			System.out.println("0");
			return;
		}

		// dfs(N, cnt);
		// bfs(N,cnt);
		dp = new int[N + 1];
		dp[0] = 0;
		dp[1] = 0;
		dpdp(N);
		// System.out.println(min);
		System.out.println(dp[N]);
	}

	private static void dfs(int n, int cnt) {
		if (n == 1) {
			min = Math.min(min, cnt);
			return;
		}
		if (n % 3 == 0) {
			dfs(n / 3, cnt + 1);
		}
		if (n % 2 == 0) {
			dfs(n / 2, cnt + 1);
		}
		if (n > 1) {
			dfs(--n, cnt + 1);
		}
	}

	private static void bfs(int n, int cnt) {
		Queue<Point> q = new LinkedList<>();
		q.add(new Point(n, cnt));

		while (!q.isEmpty()) {
			Point p = q.poll();
			// System.out.println("n:"+p.n + ", cnt:"+cnt);
			if (p.n == 1) {
				min = Math.min(min, p.cnt);
			}
			if (p.n % 3 == 0) {
				q.add(new Point(p.n / 3, p.cnt + 1));
			}
			if (p.n % 2 == 0) {
				q.add(new Point(p.n / 2, p.cnt + 1));
			}
			if (p.n > 1) {
				q.add(new Point(--p.n, p.cnt + 1));
			}
		}
	}

	private static class Point {
		int n;
		int cnt;

		public Point(int n, int cnt) {
			this.n = n;
			this.cnt = cnt;
		}
	}

	private static void dpdp(int n) {
		for (int i = 2; i <= n; i++) {
			dp[i] = dp[i - 1] + 1;
			if (i % 3 == 0) {
				dp[i] = Math.min(dp[i], dp[i / 3] + 1);
			}
			if (i % 2 == 0) {
				dp[i] = Math.min(dp[i], dp[i / 2] + 1);
			}
		}
	}

}