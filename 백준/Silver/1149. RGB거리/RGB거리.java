import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int[][] arr, dp;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());
		arr = new int[N][3];
		dp = new int[N][3];
		StringTokenizer st;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 3; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		dp[0][0] = arr[0][0];
		dp[0][1] = arr[0][1];
		dp[0][2] = arr[0][2];
		int min = Math.min(calc(N-1, 0), calc(N-1, 1));
		min = Math.min(min, calc(N-1, 2));
		
		System.out.println(min);

	}

	private static int calc(int n, int temp) {
		if (dp[n][temp] == 0) {

			if (temp == 0) {
				dp[n][0] = Math.min(calc(n - 1, 1), calc(n - 1, 2)) + arr[n][0];
			}
			if (temp == 1) {
				dp[n][1] = Math.min(calc(n - 1, 0), calc(n - 1, 2)) + arr[n][1];
			}
			if (temp == 2) {
				dp[n][2] = Math.min(calc(n - 1, 0), calc(n - 1, 1)) + arr[n][2];
			}
		}

		return dp[n][temp];

	}
}